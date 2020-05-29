package com.restapi.FlightApi;

import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

@Path("add")
public class PostResource {
	
	FlightRepo fr = new FlightRepo();
	@POST
	@Path("flight")
	@Consumes(MediaType.APPLICATION_JSON)
	public Flight newFlight(Flight a1)
	{
		fr.create(a1);
		return a1;
	}
	
	@POST
	@Path("list")
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Flight> newList (List<Flight> a1)
	{
		fr.addlist(a1);
		return a1;
	}
	
}