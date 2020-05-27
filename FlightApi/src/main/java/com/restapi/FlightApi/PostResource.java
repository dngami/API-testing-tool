package com.restapi.FlightApi;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

@Path("addflight")
public class PostResource {
	
	FlightRepo fr = new FlightRepo();
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Flight newFlight(Flight a1)
	{
		fr.create(a1);
		return a1;
	}
	
}