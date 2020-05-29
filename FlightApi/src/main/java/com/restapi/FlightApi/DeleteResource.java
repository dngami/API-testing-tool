package com.restapi.FlightApi;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("delflight")
public class DeleteResource {

	FlightRepo fr = new FlightRepo();
	@DELETE
	@Path("byid/{id}")
	public Flight removeFlight(@PathParam("id") int id)
	{
		Flight a = fr.getflight(id);
		if(a.getId()!=0)
		{
			fr.delete(id);
		}
		
		return a;
	}
}