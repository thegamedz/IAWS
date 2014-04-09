package com.iaws.project;

import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JSONHandler {
	
	public JSONHandler(){}
	
	/**
	 * Gets a JSON text from a URL over HTTP protocol and parse the text.	 * 
	 * @param url the link to the JSON ressource
	 * @return Return an Object containing all JSON elements
	 */
	public Object getJObject(String url)
	{
		
		RestWSConsumer rest = new RestWSConsumer();
		String restOutput;
		JSONObject result = new JSONObject();
		JSONArray result1=null;
		try {
			restOutput = rest.httpGet(url);
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(restOutput);
			if(obj.getClass().equals(result.getClass())){
				result = (JSONObject)obj;
				return result;
			}
			else{
				result1 = (JSONArray)obj;
				return result1;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return(		"IOException, probably error in url: "+url
					+	"\nprintstack:"+e.toString());
		} catch (ParseException e)
		{
			return(		"ParseException, error in JSon Stream from url: "+url
					+	"\nprintstack:"+e.toString());
		}
	
	}
	
	public Object getJObjectFromString(String str)
	{
		JSONObject result = new JSONObject();
		JSONArray result1=null;
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(str);
			if(obj.getClass().equals(result.getClass())){
				result = (JSONObject)obj;
				return result;
			}
			else{
				result1 = (JSONArray)obj;
				return result1;
			}
		} catch (ParseException e)
		{
			return(		"ParseException, error in JSon Stream: "+str
					+	"\nprintstack:"+e.toString());
		}
	
	}
	/**
	 * Get the class of an object
	 * @param o The object to test
	 * @return 		0 if it's a JSONObject, 
	 * 				1 if it's a JSONArray,
	 * 				2 if it's a String,
	 * 				-1 else.
	 */
	public int getKindOfObject(Object o)
	{
		try
		{
			JSONObject obj = (JSONObject) o;
			return(0);
		}catch (java.lang.ClassCastException e)
		{
			try
			{
				JSONArray  arr = (JSONArray) o;
				return(1);
			}catch(java.lang.ClassCastException e1)
			{
				try
				{
					String     str = (String) o;
					return(2);
				}catch(java.lang.ClassCastException e2)
				{
					return(-1);
				}
			}
			
		}
	}
	
	
}
