package com.iaws.project;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.iaws.project.parametres.ParramsVeloArret;

public class JcdecoHandler implements JcdecoHandlerInterface{
	//private static HashMap<String, VeloArret> listeVelosArret = new HashMap<String, VeloArret>();
	private static ArrayList<VeloArret> List_velo = new ArrayList<>();
	public JcdecoHandler()
	{
		process();
	}
	public void process(){
		
		JSONHandler parser = new JSONHandler();
		
		Object obj = parser.getJObject(ParramsVeloArret.getUrl());
	JSONArray jsonarr = (JSONArray)obj;
		System.out.println(obj.toString());
	for(int i=0;i<jsonarr.size();i++)
		{
		JSONObject jsobj = new JSONObject();
		jsobj = (JSONObject)jsonarr.get(i);
		
		String number = jsobj.get("number").toString();
		String name = jsobj.get("name").toString();
		String nbvelodispo = jsobj.get("available_bikes").toString();
		String address = jsobj.get("address").toString();
		VeloArret va = new VeloArret(number,name,nbvelodispo,address);
		List_velo.add(va);
		}
	
	}
	
	@Override
	public ArrayList<VeloArret> getVelosArret() {
		return List_velo;
	}

	@Override
	public VeloArret getVeloArret(String idLigne) {
		// TODO Auto-generated method stub
		return null;
	}
}
