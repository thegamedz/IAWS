package com.iaws.project;

public class Destination {
	private String id;
	private String nom;
	/* Peut etre ajouter la liste des arrêts de cette zone */
	
	public Destination(String _id, String _nom) {
		id = _id;
		nom = _nom;
	}

	public Destination(Destination _dest) {
		id = _dest.id;
		nom = _dest.nom;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
}
