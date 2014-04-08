package com.iaws.project.parametres;
/**
 * LISTE DES ZONES D’ARRETS <br>
 * 

 * @author thegame
 *
 */
public class ParamsZonesArrets extends Params{
	
	public static final String url = "http://pt.data.tisseo.fr/stopAreasList?"+Params.tisseokeyvalue+"&";
	/**
	 * Description: 		Filtre pour les arrêts dont les données GPS sont comprises dans ce bounding box<br>
	 * Requis: 				NON<br>
	 * Valeur par défault: 	<br>
	 * Valeurs possibles :	<br> 
	 * Utilisé dans: 		Liste des arrets,<br> 
	 */

	public static final String bbox = "&bbox";
	
	/**
	 * Description: 		Retourne en plus les lignes de chaque arrêt<br>
	 * Requis: 				NON<br>
	 * Valeur par défault: 	0 (pas de lignes)<br>
	 * Valeurs possibles :	0/1 <br> 
	 * Utilisé dans: 		Liste des arrets,<br> 
	 */
	
	public static final String displayLines = "&displayLines";
	
	/**
	 * Description: 		Retourne en plus les coordonnées de chaque arrêt. C’est le barycentre des arrêts de la zone.<br>
	 * Requis: 				NON<br>
	 * Valeur par défault: 	0 (pas de coordonnées)<br>
	 * Valeurs possibles :	0/1 <br> 
	 * Utilisé dans: 		Liste des arrets,<br>
	 * Pas très utile je pense dans notre cas. 
	 */
	public static final String displayCoordXY = "&displayCoordXY";
	
	/**
	 * Description: 		Filtre sur les zones d’arrêts arrivant et partant de ce terminus uniquement <br>
	 * Requis: 				NON<br>
	 * Valeur par défault: 	<br>
	 * Valeurs possibles :	un Long qui référencie une ligne <br> 
	 * Utilisé dans: 		Liste des arrets, Liste des lignes, <br>
	 */
	public static final String lineId = "&lineId";
	
	
	/**
	 * Description: 		Filtre sur les arrêts de la ligne uniquement.<br>
	 * Requis: 				NON<br>
	 * Valeur par défault: 	<br>
	 * Valeurs possibles :	un Long qui référencie un arret <br> 
	 * Utilisé dans: 		Liste des arrets, <br>
	 */
	public static final String terminusId = "&terminusId";
	
	/**
	 * 
	 * @param _network
	 * @param _format
	 * @param _bbox
	 * @param _displayLines
	 * @param _lineId
	 * @param _terminusId
	 * @return
	 */
	public static String getUrl(String _network, String _format, String _bbox, String _displayLines, 
								String _lineId,String _terminusId)
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
		if(!_bbox.isEmpty())
		{
			returnedUrl.append(bbox+"="+_format);
		}
		if(!_displayLines.isEmpty())
		{
			returnedUrl.append(displayLines+"="+_displayLines);
		}
		if(!_lineId.isEmpty())
		{
			returnedUrl.append(lineId+"="+_lineId);
		}
		if(!_terminusId.isEmpty())
		{
			returnedUrl.append(terminusId+"="+_terminusId);
		}
		
		return(returnedUrl.toString());
	}
	
}
