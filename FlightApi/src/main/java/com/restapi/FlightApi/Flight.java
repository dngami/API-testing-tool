package com.restapi.FlightApi;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement           //this converts class member to Json Or Xml binding type
public class Flight 
{
	private int id;                 //flight id
	private String DeptApt;         //Departure Airport
	private String ArrApt;          //Arrival Airport
	private String DeptTime;        //Departure Time
	private String ArrTime;         //Arrival Time
	private int Distance;           //Distance Between DeptApt and ArrApt
	private int Price;              //Price according to distance
	private String type;            //Type : Domestic(D) or  International (I)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDeptApt() {
		return DeptApt;
	}
	public void setDeptApt(String deptApt) {
		DeptApt = deptApt;
	}
	public String getArrApt() {
		return ArrApt;
	}
	public void setArrApt(String arrApt) {
		ArrApt = arrApt;
	}
	public String getDeptTime() {
		return DeptTime;
	}
	public void setDeptTime(String deptTime) {
		DeptTime = deptTime;
	}
	public String getArrTime() {
		return ArrTime;
	}
	public void setArrTime(String arrTime) {
		ArrTime = arrTime;
	}
	public int getDistance() {
		return Distance;
	}
	public void setDistance(int distance) {
		Distance = distance;
	}
	public int getPrice() {
		return Price;
	}
	public void setPrice(int price) {
		Price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
