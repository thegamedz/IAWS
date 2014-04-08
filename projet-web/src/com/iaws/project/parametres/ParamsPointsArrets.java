package com.iaws.project.parametres;
/**
 * Cette API permet d’obtenir des listes d’arrêts.<br> 
 * L’ensemble des arrêts d’un réseau, d’une zone géographique, ou d’une zone d’arrêt. 
 * @author koumad salim & abdoul gassama
 *
 */
public abstract class ParamsPointsArrets extends Params {

		/**
		 * URL configuré avec la clé correspodante permettant d'acceder à l'api Tisseo et de récuperer les Points d'arret	
		 */
		
		public static final String url="http://pt.data.tisseo.fr/stopPointsList?key="+Params.tisseokeyvalue+"&";
		
		/**
		 * Description: 		Filtre pour les arrêts dont les données GPS sont comprises dans ce bounding box<br>
		 * Requis: 				NON<br>
		 * Valeur par défault: 	<br>
		 * Valeurs possibles :	<br> 
		 * Utilisé dans: 		Liste des arrets,<br> 
		 */
		public static final String bbox = "&bbox";
		
		/**	Tri résultats selon la distance au centre de la bounding box (0,1)
		 * 
		 */
		public static final String sortByDistance  = "&sortByDistance ";
		
		/**
		 * Filtre le nombre maxi de resultats à retourner
		 */
		
		public static final String number = "&number";
		/**
		 * Description: 		Retourne en plus les destinations de chaque poteau (0/1) <br>
		 * Requis:				NON<br>
		 * Valeur par default:	0 (pas de destination)<br>
		 * Valeurs possibles: 	0/1 <br>
		 * Utilisé dans:		Liste des zones d'arrets, liste des arrets
		 */
		
		public static final String displayDestinations = "&displayDestinations";
		
		/**
		 * Description: 		Retourne en plus les lignes de chaque arrêt<br>
		 * Requis: 				NON<br>
		 * Valeur par défault: 	0 (pas de lignes)<br>
		 * Valeurs possibles :	0/1 <br> 
		 * Utilisé dans: 		Liste des zones d'arrets, liste des arrets,<br> 
		 */
		
		public static final String displayLines = "&displayLines";
		
		/**
		 * 	Description: Retourne en plus les coordonnées de chaque arrêt (poteau d’arrêt et arrêt logique) (0/1)<br>
		 * Requis: 				NON<br>
		 * Valeur par défault: 	0 (pas de coordonnées)<br>
		 * Valeurs possibles :	0/1 <br> 
		 * Utilisé dans: 		liste des arrets,<br> 
		*/
		public static final String displayCoordXY = "&displayCoordXY";
		
		/**
		 * Description: 		Filtre sur les arrêts de la ligne désignée uniquement <br>
		 * Requis: 				NON<br>
		 * Valeur par défault: 	<br>
		 * Valeurs possibles :	un Long qui référencie une ligne <br> 
		 * Utilisé dans: 		Liste des arrets, Liste des lignes, <br>
		 */
		public static final String lineId = "&lineId";
		
		/**
		 * Description: 		Filtre sur la zone d’arrêt uniquement definie <br>
		 * Requis: 				NON<br>
		 * Valeur par défault: 	<br>
		 * Valeurs possibles :	un Long qui référencie une ligne <br> 
		 * Utilisé dans: 		Liste des arrets, Liste des lignes, <br>
		 */
		public static final String stopAreaId = "&stopAreaId";

		/**
		 * 
		 * @param _network 
		 * @param _format
		 * @param _lineId {@link #lineId}
		 * @param _displayTerminus
		 * @return
		 */
		public static String getUrl(String _network, String _format, String _bbox, String _sortByDistance, 
									String _number, String _displayDestinations, String _displayLines, 
									String _displayCoordXY,String _lineId,String _stopAreaId)
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
				returnedUrl.append(bbox+"="+_bbox);
			}
			if(!_sortByDistance.isEmpty())
			{
				returnedUrl.append(sortByDistance+"="+_sortByDistance);
			}
			if(!_number.isEmpty())
			{
				returnedUrl.append(number+"="+_number);
			}
			if(!_displayDestinations.isEmpty())
			{
				returnedUrl.append(displayDestinations+"="+_displayDestinations);
			}
			if(!_displayLines.isEmpty())
			{
				returnedUrl.append(displayLines+"="+_displayLines);
			}
			if(!_displayCoordXY.isEmpty())
			{
				returnedUrl.append(displayCoordXY+"="+_displayCoordXY);
			}
			if(!_lineId.isEmpty())
			{
				returnedUrl.append(lineId+"="+_lineId);
			}
			if(!_stopAreaId.isEmpty())
			{
				returnedUrl.append(stopAreaId+"="+_stopAreaId);
			}
			
			return(returnedUrl.toString());
		}
}
