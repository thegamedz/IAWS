package com.iaws.project;

import java.io.IOException;

import org.json.simple.JSONObject;

public class DatabaseHandler {
	private static String DB_ADRESS = "http://localhost:5984/";
	private static final String DB_NAME = "likesunlikesdb";
	private static final JSONHandler parseur = new JSONHandler();
	/*private static final String FIELD_REV = "_rev";
	private static final String FIELD_NOM_LIGNE = "lineName";
	private static final String FIELD_NOM_COURT_LIGNE = "shortName";
	private static final String FIELD_NB_LIKES = "nbLikes";
	private static final String FIELD_NB_DISLIKES = "nbDislikes";*/
	private static final RestWSConsumer rest = new RestWSConsumer();
	
	public static void main(String amine[]) throws Exception {
		DatabaseHandler db = new DatabaseHandler();
		//db.deleteDB();
		//db.createDB();
		//insertLine("13", "5", "5");
		//likeunlike("13", false);
		//likeunlike("13", true);
		log(getNbLikes("11"));
		//
		//System.out.println(rest.httpGet("http://localhost:5984/test/4200d399f0c385805cfd6c2a57000e02"));	
	}
	public static void setDBAdress(String adr) {
		DB_ADRESS = adr;
	}
	public static void createDB(){
		log(DB_ADRESS+DB_NAME);
		try {
			log("Creating BDD at : "+rest.httpPut(DB_ADRESS+DB_NAME, ""));
		} catch (Exception e) {
			log("BDD Info: "+e.toString());
		}
		
	}
	
	public static void deleteDB(){
		log(DB_ADRESS+DB_NAME);
		try {
			log("Delete BDD : "+rest.httpDelete(DB_ADRESS+DB_NAME));
		} catch (Exception e) {
			log("BDD Info: "+e.toString());
		}
		
	}
	
	public static String insertLine(String lineId, String shortName, String name) throws Exception {
		log ("Creation de l'objet: "+lineId+", "+shortName+", "+name);
		String resp = "";
		/* Verifier si la ligne existe */
		try {
			resp = rest.httpGet(DB_ADRESS+DB_NAME+"/"+lineId);
		} catch (IOException e) {
			/* si il n'existe pas alors on le cree */
			resp = "reponse de l'insertion: "+rest.httpPut(DB_ADRESS+DB_NAME+"/"+lineId, 
							"{"
						+ 		"\"name\" : \"" + name + "\","
						+ 		"\"shortname\" : \"" + shortName + "\","
						+ 		"\"likes\" : 0, "
						+ 		"\"unlikes\" : 0"
						+ 	"}");
		}
		return(log(resp));
	}
	
	public static String likeunlike(String lineId, boolean b) throws Exception {
		String resp = "";
		/* Verifier si la ligne existe */
		try {
			resp = rest.httpGet(DB_ADRESS+DB_NAME+"/"+lineId);
			Object obj = parseur.getJObjectFromString(resp);
			if (parseur.getKindOfObject(obj) == 2) { 
				return ("Erreur ! lors de l'ajour d'un like/unlike");
			}
			// {"_id":"12","_rev":"1-ca2e3fb328791ab6f2eb152933493b16","name":"0","shortname":"0","likes":0,"unlikes":0}
			JSONObject json = (JSONObject) obj;
			long likes;
			long dislikes;
			
			likes 	= (Long) json.get("likes");
			dislikes = (Long) json.get("unlikes");
			if (b) {
				likes++;
			} else {
				dislikes++;
			}
			StringBuffer c = new StringBuffer();
			c.append("{");
			c.append(			"\"_rev\" : \""+((String) json.get("_rev"))+"\","
						+ 		"\"name\" : \"" + ((String) json.get("name")) + "\","
						+ 		"\"shortname\" : \"" + ((String) json.get("shortName")) + "\","
						+ 		"\"likes\" : " + likes + ","
						+ 		"\"unlikes\" : "+ dislikes
						+ 	"}");
			resp = "reponse de la modification: "+rest.httpPut(DB_ADRESS+DB_NAME+"/"+lineId, c.toString());
			
			
		} catch (IOException e) {
			/* si il n'existe pas alors on le cree */
			resp = "Ligne introuvalble pour like unliker!";
		}
		return(log(resp));
	}
	
	public static long getNbLikes(String lineId) {
		long nbLikes = 0;
		Object obj = parseur.getJObject(DB_ADRESS+DB_NAME+"/"+lineId);
		if (parseur.getKindOfObject(obj) != 0) {
			return(-1);
		}
		JSONObject json = (JSONObject) obj;
		nbLikes = (Long) json.get("likes");
		return(nbLikes);
	}
	
	public static long getNbDislikes(String lineId) {
		long nbDislikes = 0;
		Object obj = parseur.getJObject(DB_ADRESS+DB_NAME+"/"+lineId);
		if (parseur.getKindOfObject(obj) != 0) {
			return(-1);
		}
		JSONObject json = (JSONObject) obj;
		nbDislikes = (Long) json.get("unlikes");
		return(nbDislikes);
	}
	
	public static String log(Object str)
	{
		System.out.println(str.toString());
		return(str.toString());
	}
	
	
}
