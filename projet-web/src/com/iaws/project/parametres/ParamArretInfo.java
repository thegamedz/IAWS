package com.iaws.project.parametres;

import org.apache.jasper.tagplugins.jstl.core.Param;

public class ParamArretInfo extends Param{
	   private static String id_arret;

				/**
				 * URL configuré avec la clé correspodante permettant d'acceder à l'api Tisseo et de récuperer les Points d'arret	
				 */
				
				public static final String url="https://api.jcdecaux.com/vls/v1/stations/"+id_arret+"?contract="+Params.contractname+"&apiKey="+Params.jcdkeyvalue;
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
