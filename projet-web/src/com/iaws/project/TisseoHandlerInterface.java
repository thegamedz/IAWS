package com.iaws.project;

import java.util.HashMap;
import java.util.List;

public interface TisseoHandlerInterface {
	/*
	 	URL : http://pt.data.tisseo.fr/linesList?...paramètres... 
		 
		 
		Nom Description du paramètre Requis ? Valeur défaut 
		network Opérateur de transport Non Tisséo 
		format Désigne le format de sortie Non Xml 
		lineId Filtre sur une seule ligne Non 
		displayTerminus Retourne en plus les arrêts logiques terminus de 
		chaque ligne (0/1) 
		Non 0
		
	*/
	/**
	 * Recupère toutes les lignes repertoriées
	 * @return
	 */
	public HashMap<String, Ligne> getLignes();
	
	/**
	 * Récupére la ligne répértoriée
	 * @param idLigne
	 * @return
	 */
	public Ligne getLigne(String idLigne);
	
	public String getLinesToString();
	
	public String getTimeOfStopPoint(String idStopPoint);
	
}
