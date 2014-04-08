package com.iaws.project.parametres;

public abstract class Params {
	public static final String stopAreaIdPaulSabatier = "1970324837185012";
	public static final String stopAreaIdFaculteDePharmacie = "1970324837185357";
	public static final String[] stopAreaIdsList = { 
													stopAreaIdPaulSabatier, 
													stopAreaIdFaculteDePharmacie};
	public static final String url = "http://pt.data.tisseo.fr/";
	/**
	 * Nom du parametre qui permet d'inclure une clé de l'api Tisséo
	 */
	public static final String key = "key";
	
	/**
	 * Nom du parametre qui permet d'inclure une clé de l'api JCDecaux
	 */
	public static final String apikey = "apikey";
	
	/**
	 * Clé fournis pour l'utilisation de l'api de tisseo, à inclure dans l'url avec le parametre key
	 */
	public static final String tisseokeyvalue="a03561f2fd10641d96fb8188d209414d8";
	
	/**
	 * Clé fournis pour l'utilisation de l'api de JCDecaux, à inclur dans l'url avec le parametre apikey
	 */
	public static final String jcdkeyvalue="e9190f737f9e19e5714037d19f558ffab9019fcb";
	
	/**
	 * nom du parametre network, peut etre Tisséo ou ALL ou tout autre réseau valide
	 */
	public static final String network = "&network";
	
	/**
	 * nom du parametre format, peut etre du JSON ou XML
	 */
	public static final String format = "&format";
	
	/**
	 * nom du contract qui est toulouse
	 */
	
	public static final String contractname="toulouse";
	
	/**
	 * retourne une url en donnant en paramêtre les paramêtres correspondants dans l'ordre
	 */
	public static String getUrl(String args[])
	{
		return("");
	}
	
}
