package com.iaws.project;

public class VeloArret {

	


	private String name;
	private String available_bikes;
	private String adress;
	
	public VeloArret(){
		
	}
	
	
	public VeloArret(String number,String name, String available_bikes,String adress) {
		super();
		this.name = name;
		this.available_bikes = available_bikes;
		this.adress = adress;
	}
	
	public String toString() {
		return name+" "+adress+" "+available_bikes;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAvailable_bikes() {
		return available_bikes;
	}
	public void setAvailable_bikes(String available_bikes) {
		this.available_bikes = available_bikes;
	}



	public String getAdress() {
		return adress;
	}



	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	private String number;
	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}

	
	
	
}
