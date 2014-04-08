package com.iaws.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil.ToStringAdapter;

/*
 * { 
	 "id": "3377699720884578", 
	 "name": "Armentières", 
	 "x": "531237.00859954499", 
	 "y": "1841469.9583295723", 
	 "operatorCodes": 	[ 
						 { 
							"operatorCode": { 
												"value": "201", 
												"network": "Tisséo" 
								 		    } 
						 } 
	 					], 
	 "destinations": [ 
						 { 
							 "cityName": "LABEGE", 
							 "id": "1970324837186881", 
							 "name": "Centre commercial", 
							 "line": [ 
												 { 
												 "color": "(86,63,0)", 
												 "id": "11821949022345002", 
												 "name": "Université Paul Sabatier / St-Orens Lycée",  
												OPEN DATA : API TISSEO
												Réf. : 
												Version : V0.6 
												Du : 10/12/2013 
												 
												Open Data : API Tisséo Page : 20/30 
												 "shortName": "108", 
												 "network": "Tisséo" 
												 }, 
												 {} 
							 		]
	 						}, 
	 { 
	 "cityName": "SAINT-ORENS-DE-GAMEVILLE", 
	 "id": "1970324837185279", 
	 "name": "St-Orens Lycée", 
	 "line": [ 
	 { 
	 "color": "(86,63,0)", 
	 "id": "11821949022345002", 
	 "name": "Université Paul Sabatier / St-Orens Lycée", 
	 "shortName": "108", 
	 "network": "Tisséo" 
	 }, 
	 {} 
	 ] 
	 }, 
	 { 
	 "cityName": "TOULOUSE", 
	 "id": "1970324837185012", 
	 "name": "Université Paul Sabatier", 
	 "line": [ 
	 { 
	 "color": "(86,63,0)", 
	 "id": "11821949022345002", 
	 "name": "Université Paul Sabatier / St-Orens Lycée", 
	 "shortName": "108", 
	 "network": "Tisséo" 
	 }, 
	 {} 
	 ] 
	 }, 
	 {} 
	 ], 
	 "stopArea": { 
			 "cityName": "TOULOUSE", 
			 "id": "1970324837186871", 
			 "name": "Armentières", 
			 "x": "531244.993561", 
			 "y": "1841463.974601" 
	 } 
 },
*/
public class Arret {
	private HashMap<String, Destination> listeDestination;
	private String id;
	private String nom;
	public Arret(String _id,
			String _nom) {
			id = _id;
			nom = _nom;
			listeDestination = new HashMap<String, Destination>();
	}
	
	public Arret(Arret newArret) {
		id = newArret.id;
		nom = newArret.nom;
		listeDestination = new HashMap<String, Destination>();
		Iterator<Entry<String, Destination>> it = newArret.listeDestination.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String, Destination> pairs = (Map.Entry<String, Destination>) it.next();
			addDestination(pairs.getValue(), pairs.getKey());
		}
	}

	public void addDestination(Destination _dest, String idLigne)
	{
		listeDestination.put(idLigne, new Destination(_dest));
	}

	public HashMap<String, Destination> getListeDestination() {
		return listeDestination;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getId() {
		return id;
	}
	
	public String getNomDestination(String idLigne) {
		if (listeDestination.containsKey(idLigne))
			return listeDestination.get(idLigne).getNom();
		else return "Ligne innexistante! ";
	}
	
	

}
