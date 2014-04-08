package com.iaws.project.parametres;



public class ParramsVeloArret extends Params {

			/**
			 * URL configuré avec la clé correspodante permettant d'acceder à l'api Tisseo et de récuperer les Points d'arret	
			 */
			
			public static final String url="https://api.jcdecaux.com/vls/v1/stations?contract="+Params.contractname+"&apiKey="+Params.jcdkeyvalue;
			
			/**
			 * 
			 * @param _network 
			 * @param _format
			 * @param _lineId {@link #lineId}
			 * @param _displayTerminus
			 * @return
			 */
			
			public static String getUrl()
			{
				return(url.toString());
			}

}
