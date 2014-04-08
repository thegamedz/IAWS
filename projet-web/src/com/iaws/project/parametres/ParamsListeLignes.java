package com.iaws.project.parametres;

/**
 * Classe donnant accès aux parametres d'une requête ListeLignes<br>
 * Exemples de requêtes : 
 
	<p>1. Liste de toutes lignes au format xml du réseau tisséo 
	http://pt.data.tisseo.fr/linesList<p/> 
	 
	<p>2. liste de toutes les lignes du réseau tisséo au format json 
	http://pt.data.tisseo.fr/linesList?format=json&network=Tisséo<p/> 
	 
	<p>3. liste de toutes les lignes du réseau Tisséo avec les arrêts logiques terminus. 
	http://pt.data.tisseo.fr/linesList&displayTerminus=1<p/>
 * @author Koumad Salim & Abdoul Gassama
 *
 */
public abstract class ParamsListeLignes extends Params{
	
	/**
	 * URL configuré avec la clé correspodante permettant d'acceder à l'api Tisseo et de récuperer les lignes	
	 */
	
	public static final String url="http://pt.data.tisseo.fr/linesList?key="+Params.tisseokeyvalue;
			
	/**
	 * Description: 		Designe une seule ligne<br>
	 * Requis: 				NON<br>
	 * Valeur par défault: 	<br>
	 * Valeurs possibles :	un Long valide<br> 
	 * Utilisé dans: 		liste des lignes,<br> 
	 */
	public static final String lineId = "&lineId";
	
	/**
	 * Description: 		Retourne en plus les arrêts logiques terminus de chaque ligne<br>
	 * Requis: 				NON<br>
	 * Valeur par défault: 	0<br>
	 * Valeurs possibles :	0/1<br> 
	 * Utilisé dans: 		liste des lignes,<br> 
	 */
	public static final String displayTerminus = "&displayTerminus";
	
	/**
	 * 
	 * @param _network 
	 * @param _format
	 * @param _lineId {@link #lineId}
	 * @param _displayTerminus
	 * @return
	 */
	public static String getUrl(String _network, String _format, String _lineId, String _displayTerminus)
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
		if(!_lineId.isEmpty())
		{
			returnedUrl.append(lineId+"="+_lineId);
		}
		if(!_displayTerminus.isEmpty())
		{
			returnedUrl.append(displayTerminus+"="+_displayTerminus);
		}
		
		return(returnedUrl.toString());
	}
	
}
