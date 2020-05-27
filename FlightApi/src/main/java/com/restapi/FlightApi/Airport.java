package com.restapi.FlightApi;

import java.util.List;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class Airport {

	private String Name;
	private List<Flight> Arrivals;
	private List<Flight> Departures;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public List<Flight> getArrivals() {
		return Arrivals;
	}

	public void setArrivals(List<Flight> arrivals) {
		Arrivals = arrivals;
	}

	public List<Flight> getDepartures() {
		return Departures;
	}

	public void setDepartures(List<Flight> departures) {
		Departures = departures;
	}
	
}
