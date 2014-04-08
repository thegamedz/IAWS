package com.iaws.project.parametres;

/**
 * Cette API permet d’obtenir la liste des prochains passages à un poteau d’arrêt.<br>
 * Ne concerne que les Bus et Tramway. Aucune information ne sera retournée pour le métro ou les <br>
 * TAD. 
 * @author koumad salim
 *
 */
public abstract class ParamsProchainPassage extends Params{
	
	/**
	 * URL configuré avec la clé correspodante permettant d'acceder à l'api Tisseo et de récuperer les infos	
	 */
	public static final String url = 	"http://pt.data.tisseo.fr/departureBoard?"+
										key+"="+tisseokeyvalue;
	
	
	public static final String operatorCode = "&operatorCode";
	
	/**
	 * Description: Désigne le numéro de l’arrêt physique ou poteau<br>
	 * Non Requis <br>
	 */
	public static final String stopPointId = "&stopPointId";
	
	/**
	 * Description: Filtre sur le nb maxi de résultats à retourner<br>
	 * Par défault il retourne tout les résultats (si il est omis)
	 */
	public static final String number = "&number";
	
	/**
	 * Description: Filtre les arrêts de la ligne uniquement <br>
	 * par défault retourne tous les résultats
	 */
	public static final String lineId = "&lineId";
	
	/**
	 * Description: Permet de spécifier si on souhaite des horaires « théoriques » (0) ou « temps réels » (1) <br>
	 * Valeur par défaut: 1 (Temps Réel).
	 */
	public static final String displayRealTime ="&displayRealTime";
	
	public static String getUrl(String _network, String _format, String _operatorCode, String _stopPointID, String _number, String _lineId)
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
		if(!_operatorCode.isEmpty())
		{
			returnedUrl.append(operatorCode+"="+_operatorCode);
		}
		if(!_stopPointID.isEmpty())
		{
			returnedUrl.append(stopPointId+"="+_stopPointID);
		}
		if(!_number.isEmpty())
		{
			returnedUrl.append(number+"="+_number);
		}
		if(!_lineId.isEmpty())
		{
			returnedUrl.append(lineId+"="+_lineId);
		}
		return(returnedUrl.toString());
	}
	
}
