package com.iaws.project;

import java.util.ArrayList;


/*FORMAT JSON:
		{
			"color":"(255,0,0)",							=> Inutile
			"bgXmlColor":"#ff0000",							=> Inutile
			"fgXmlColor":"#FFFFFF",							=> Inutile
			"id":"11821949021891694",						=> Utile
			"name":"Basso Cambo / Balma - Gramont",			=> Utile
			"shortName":"A",								=> Utile
			"network":"Tisséo",								=> Indecis
			"transportMode":{								=> Utile
						"id":"13792273858822586",	=> Indecis car il n'y a que deux (bus et metro)
						"article":"le",				=> Indecis
						"name":"métro"				=> Utile
						}
		}
*/
public class Ligne {
	private ArrayList<Arret> listeArrets;
	/**
	 * Matching BDD's id
	 */
	private String id;
	
	/**
	 * Le nom complet de la ligne représenté par le point de départ et d'arrivé
	 */
	private String nomDeLaLigne;
	
	/**
	 * Le nom court de la ligne, par exemple Ligne A et B pour le metro, ligne 1 2 12 ... pour les bus
	 */
	private String nomCourtDeLaLigne;
	
	/**
	 * Le mode de transport:
	 * Valeurs possibles: [metro , bus]
	 */
	private String modeDeTransport;

	public Ligne(String id, String nomDeLaLigne, String nomCourtDeLaLigne,
			String modeDeTransport) {
		super();
		this.id = id;
		this.nomDeLaLigne = nomDeLaLigne;
		this.nomCourtDeLaLigne = nomCourtDeLaLigne;
		this.modeDeTransport = modeDeTransport;
		this.listeArrets = new ArrayList<Arret>();
	}

	public String getNomDeLaLigne() {
		return nomDeLaLigne;
	}

	public void setNomDeLaLigne(String nomDeLaLigne) {
		this.nomDeLaLigne = nomDeLaLigne;
	}

	public String getNomCourtDeLaLigne() {
		return nomCourtDeLaLigne;
	}

	public void setNomCourtDeLaLigne(String nomCourtDeLaLigne) {
		this.nomCourtDeLaLigne = nomCourtDeLaLigne;
	}

	public String getModeDeTransport() {
		return modeDeTransport;
	}

	public void setModeDeTransport(String modeDeTransport) {
		this.modeDeTransport = modeDeTransport;
	}

	public String getId() {
		return id;
	}
	
	public void addArret(Arret _arret)
	{
		listeArrets.add(new Arret(_arret));
	}
	
	public Arret getArret(String idArret)
	{
		for (int i = 0; i < listeArrets.size(); i++)
		{
			if (idArret == listeArrets.get(i).getId()) {
				return new Arret(listeArrets.get(i));
			}
		}
		return(null);
		
	}
	public ArrayList<Arret> getArrets()
	{
		return listeArrets;
	}
	
	
}
