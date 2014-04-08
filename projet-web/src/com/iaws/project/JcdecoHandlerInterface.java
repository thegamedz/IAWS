package com.iaws.project;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;

public interface JcdecoHandlerInterface {


	public  ArrayList<VeloArret> getVelosArret();
	
	public VeloArret getVeloArret(String idLigne);
	
	
	
}
