package com.iaws.project.parametres;
/**
 * Cette API permet d’obtenir la liste de toutes les lignes perturbées du réseau. <br>
 * Cela permet par exemple d’afficher un pictogramme à chaque fois que vous affichez cette ligne à l’utilisateur. <br> 
 *
 * @author koumad salim
 *
 */
public class ParamListeLignesPerturbees extends Params {
	/**
	 * URL configuré avec la clé correspodante permettant d'acceder à l'api Tisseo et de récuperer les lignes	
	 */
	public static final String url = "http://pt.data.tisseo.fr/linesDisruptedList?"+
										key+"="+tisseokeyvalue+"&format=xml";
	
}
