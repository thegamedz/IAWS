package com.iaws.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.iaws.project.parametres.Params;
import com.iaws.project.parametres.ParamsPointsArrets;
import com.iaws.project.parametres.ParamsProchainPassage;

/**
 * Cette classe fournis l'abstraction des interactions avec l'api Tisseo, elle se charge de récuperer les données,
 * les parser et éventuellement les mettre dans la base de données.
 * @author koumad salim
 *
 */
public class TisseoHandler implements TisseoHandlerInterface{
	private static HashMap<String, String> getIdByShortName = new HashMap<String, String>();
	private static HashMap<String, Ligne> listeLignes = new HashMap<String, Ligne>();
	/* A inclure dans un champ des arrets de paul sabatier comme étant une destination */
	private void log(String str)
	{
		System.out.println(str);
	}
	public TisseoHandler()
	{
		try {
			process();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public HashMap<String, Ligne> getLignes() {
		
		return(listeLignes);
	}
	
	public void process() throws Exception
	{
		log("Starting process");
		if (!listeLignes.isEmpty())
		{	
			log("Ending process, nothig to download, everything up to date");
			return;
		}
		log("Creating/Loading DB");
		DatabaseHandler.createDB();
		JSONHandler parser = new JSONHandler();
		//bbox=1.4432,43.5435,1.4847,43.5769
		JSONObject obj = (JSONObject) parser.getJObject(ParamsPointsArrets.getUrl("", "json", "1.4432,43.5435,1.4847,43.5769", "", "", "1", "1", "", "", 
				//Params.stopAreaIdPaulSabatier));
				""));
		obj = (JSONObject) obj.get("physicalStops");
		JSONArray physicalStop = (JSONArray) obj.get("physicalStop");
		for (int i = 0 ; i < physicalStop.size(); i++)
		{
			/* On récupére l'objet de la liste */
			JSONObject tempJSON = (JSONObject) physicalStop.get(i);
			
			/* On récupére les information de l'arrêt */
			String idDeLarretDePaulSabatier = (String) tempJSON.get("id");
			String nomDeLarretDePaulSabatier = (String) tempJSON.get("name");
			
			/* Afficher */
			//chaineResultat.append("<br>--------<br>");
			Arret nouvelArret = new Arret(idDeLarretDePaulSabatier, nomDeLarretDePaulSabatier);
			/* Parcourir les destinations possibles (vers des zones d'arret) */
			JSONArray destinationsDeLarretDePaulSabatier = (JSONArray) tempJSON.get("destinations");
			for (int j = 0; j < destinationsDeLarretDePaulSabatier.size(); j++)
			{
				/*On récupére l'objet de la liste des destinations qui contien des StopArea */
				JSONObject stopAreaJSON = (JSONObject) destinationsDeLarretDePaulSabatier.get(j);
				
				/* On récupére les informations possibles (ligne ... ) */
				String idDeLarretDeDestination = (String) stopAreaJSON.get("id");
				String nomDeLarretDeDestination = (String) stopAreaJSON.get("name");
				
				/* Afficher */
				Destination nouvelleDestination = new Destination(idDeLarretDeDestination, nomDeLarretDeDestination);
				
				
				/* Parcourir les différents lignes passant par cet arret pour cette destination */
				JSONArray lignesMenantADestination = (JSONArray) stopAreaJSON.get("line");
				for (int k = 0; k < lignesMenantADestination.size(); k++)
				{
					/*On récupére l'objet de la liste des lignes */
					JSONObject lineJSON = (JSONObject) lignesMenantADestination.get(k);
					
					/*On récupére les informations de la ligne */
					String idDeLaLignePassantParLarret = (String) lineJSON.get("id");
					String nomDeLaLignePassantParLarret = (String) lineJSON.get("name");
					String courtNomDeLaLignePassantParLarret = (String) lineJSON.get("shortName");
					String reseauDeLaLignePassantParLarret = (String) lineJSON.get("network");
					
					/* Vérifier si la destination n'est pas pour paulSabatier alors l'ajouter */
					//if (!idDeLarretDeDestination.equals("1970324837185012")) {
						nouvelArret.addDestination(new Destination(nouvelleDestination), idDeLaLignePassantParLarret);
						//System.out.println(nouvelleDestination.getId()+" => "+nouvelleDestination.getNom());
					//}					//log(nouvelArretPaulSabatier.getNom()+"==> "+nouvelArretPaulSabatier.getNomDestination(idDeLaLignePassantParLarret));
					/* Créer la ligne */
					Ligne lignePassantParLarret;
					if (!listeLignes.containsKey(idDeLaLignePassantParLarret)) {
					lignePassantParLarret = new Ligne(idDeLaLignePassantParLarret,
						nomDeLaLignePassantParLarret,
						courtNomDeLaLignePassantParLarret,
						reseauDeLaLignePassantParLarret);
						getIdByShortName.put(courtNomDeLaLignePassantParLarret, idDeLaLignePassantParLarret);
						lignePassantParLarret.addArret(nouvelArret);
						/* Gerer exception */
						try {
						DatabaseHandler.insertLine(idDeLaLignePassantParLarret, courtNomDeLaLignePassantParLarret, nomDeLaLignePassantParLarret);
						long likesDeLaLigne = DatabaseHandler.getNbLikes(idDeLaLignePassantParLarret);
						long unlikesDeLaLigne = DatabaseHandler.getNbDislikes(idDeLaLignePassantParLarret);
						lignePassantParLarret.setLikes(likesDeLaLigne);
						lignePassantParLarret.setUnlikes(unlikesDeLaLigne);
						listeLignes.put(idDeLaLignePassantParLarret, lignePassantParLarret);
						log("Ligne: "+lignePassantParLarret.getNomCourtDeLaLigne()+", Likes: "+lignePassantParLarret.getLikes());
						} catch (Exception e){
							/* Ignorer */
							log("ERREUR");
						}
						
					}
					else {
						lignePassantParLarret = listeLignes.get(idDeLaLignePassantParLarret);
						if (lignePassantParLarret.getArret(idDeLarretDePaulSabatier) == null) {
							lignePassantParLarret.addArret(nouvelArret);
						}
					}					
					
				}
			}
			
			
		}
	}

	
	@Override
	public Ligne getLigne(String idLigne) {
		if(listeLignes.containsKey(idLigne))
			return(listeLignes.get(idLigne));
		return null;
	}

	public String getArretsToStringV1(String idLigne)
	{
		if (!listeLignes.containsKey(idLigne))
		{
			return("ligne innexistante!");
		}
		Ligne ligne = listeLignes.get(idLigne);
		
		StringBuffer chaineResultat = new StringBuffer();
		chaineResultat.append("<div class=\"top\">");
		chaineResultat.append("<h1> Arrets de la ligne: "+ligne.getNomDeLaLigne()+"</h1>");
		chaineResultat.append("</div>");
		chaineResultat.append("<div class=\"stops\">");
		/* récupérer la liste des arrets de la ligne */
		ArrayList<Arret> arrets = ligne.getArrets();
		for (int i = 0 ; i < arrets.size(); i++)
		{
			Arret arret = arrets.get(i);
			chaineResultat.append("<li onclick=\"showStuff('time"+i+"'); return false;\">"
								+ "<h3>"+arret.getNom()+"</h3>"
								+ "<p>Vers: "+arret.getNomDestination(idLigne)+"</p><br> "
								+ "<p id=\"time"+i+"\" style=\"display: none;\" > Prochain Depart: "+getTimeOfStopPoint(arret.getId())+"</input></li>");
		}
		chaineResultat.append("</div>");
		return chaineResultat.toString();
	}
	
	public String getArretsToStringByShortName(String shortname)
	{
		if (!getIdByShortName.containsKey(shortname))
		{
			return("'"+shortname+"'");
			//return("ligne innexistante!");
		}
		String idLigne = getIdByShortName.get(shortname);
		Ligne ligne = listeLignes.get(idLigne);
				
		StringBuffer chaineResultat = new StringBuffer();
		chaineResultat.append("<div class=\"top\">");
		chaineResultat.append("<h1> Arrets de la ligne: "+ligne.getNomDeLaLigne()+"</h1>");
		chaineResultat.append("</div>");
		chaineResultat.append("<div class=\"stops\">");
		/* récupérer la liste des arrets de la ligne */
		ArrayList<Arret> arrets = ligne.getArrets();
		for (int i = 0 ; i < arrets.size(); i++)
		{
			Arret arret = arrets.get(i);
			chaineResultat.append("<li>"+arret.getNom()+", vers: "+arret.getNomDestination(idLigne)+"; le: "+getTimeOfStopPoint(arret.getId())+"</li>");
		}
		chaineResultat.append("</div>");
		return chaineResultat.toString();
	}
	
	@Override
	public String getLinesToString() {
		StringBuffer chaineResultat = new StringBuffer();
		Iterator<Entry<String, Ligne>> it = listeLignes.entrySet().iterator();
		log("Starting getting lines");
		/*
		 * onclick="post_to_url('arrets.jsp', { id:'lm'} , { submit: 'post' } );"
		 */
		
			chaineResultat.append("<div align=\"left\" class=\"lines\"> ");
		while(it.hasNext())
		{	
			Map.Entry<String, Ligne> pairs = (Map.Entry<String, Ligne>) it.next();
			// Récupérer les likes et unlikes d'une ligne et dresser une note
			long likes = DatabaseHandler.getNbLikes(pairs.getKey());
			long unlikes = DatabaseHandler.getNbDislikes(pairs.getKey());
			chaineResultat.append("<div>");
			chaineResultat.append("<span>Note: "+(likes-unlikes)+"</span>");
			chaineResultat.append("<span><li onclick=\"post_to_url('arrets.jsp', { idLigne:'" 
									+ pairs.getValue().getId()+"'} , { submit: 'post' } );\">");
			chaineResultat.append("<h3>");
			chaineResultat.append(pairs.getValue().getNomCourtDeLaLigne());
			chaineResultat.append("</h3>");
			chaineResultat.append("<p> Ligne: "+pairs.getValue().getNomDeLaLigne()+"</p>");
			chaineResultat.append("</li></span>");
			chaineResultat.append("<span style=\"float: right\"><input type='button' value=\"Like!\" onclick=\"post_to_url('lines.jsp', { idLigne:'" 
									+ pairs.getValue().getId()+"', type:'like'} , { submit: 'post' } );\"/>");
			chaineResultat.append("<span style=\"float: right\"><input type='button' value=\"Dislike!\" onclick=\"post_to_url('lines.jsp', { idLigne:'" 
					+ pairs.getValue().getId()+"', type:'dislike'} , { submit: 'post' } );\"/>");
			chaineResultat.append("</div>");
		}
		chaineResultat.append("</div>");
		log(chaineResultat.toString());
		log("Starting web page!");
		return chaineResultat.toString();
	}
	
	@Override
	public String getTimeOfStopPoint(String idStopPoint) {
		// TODO Auto-generated method stub
		JSONHandler jsonHandler = new JSONHandler();
		
		Object json = jsonHandler.getJObject(ParamsProchainPassage.getUrl("", "json", "", idStopPoint, "", ""));
		if(jsonHandler.getKindOfObject(json) == 2)
			return(ParamsProchainPassage.getUrl("", "json", "", idStopPoint, "", "")+"<br>"+(String) json);
		JSONObject obj1 = (JSONObject) json;
		obj1 = (JSONObject) obj1.get("departures");
		JSONArray departure = (JSONArray) obj1.get("departure");
		
		/* On ne prend que le prochain */
		JSONObject obj = (JSONObject) departure.get(0);
		String isRealTime = (String) obj.get("realTime");
		String dateTime = (String) obj.get("dateTime");
		if (dateTime == null)
			return("Aucun Passage Prévu");
		String heure = dateTime.split(" ")[1];
		return heure;
	}
	
	
}
