package com.iaws.project.parametres;

public abstract class ParamsRechercheLieu extends Params{
	
	/**
	 * URL configuré avec la clé correspodante permettant d'acceder à l'api Tisseo et de récuperer les lignes	
	 */
	
	public static final String url="http://pt.data.tisseo.fr/placesList?"+Params.tisseokeyvalue+"&";
	
	/**
	 * Retrouver un nom d'un lieu (Rue, arret, ...)<br>
	 * Utilisation: peut etre utile pour guider le choix d'un utilisateur grâce notemment à l'autocomplétion<br>
	 * Description: Texte saisi (3 caractères minimum)<br>
	 * Requis: non<br>
	 * Utilisé dans: RechercheLieu,<br>
	 */
	public static final String term = "&term";
	
	public static String getUrl(String _network, String _format, String _term)
	{
		StringBuffer returnedUrl = new StringBuffer(url);
		if (!_network.isEmpty())
		{
			returnedUrl.append(network+"="+_network);
		}
		if(!_format.isEmpty())
		{
			returnedUrl.append(format+"="+_format);
		}
		if(!_term.isEmpty())
		{
			returnedUrl.append(term+"="+_term);
		}
		return(returnedUrl.toString());
	}
	
	
}
