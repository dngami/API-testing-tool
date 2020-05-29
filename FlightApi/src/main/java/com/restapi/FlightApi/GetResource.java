package com.restapi.FlightApi;

import java.util.List;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("flightInfo")
public class GetResource {
	FlightRepo repo = new FlightRepo();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> getList()
	{
		return repo.getlist();
	}

	@GET
	@Path("{FlightId}")
	@Produces(MediaType.APPLICATION_JSON)
    public Flight getflight(@PathParam("FlightId") int FlightId)
	{
		return repo.getflight(FlightId);
	}
	
    @GET
    @Path("departure/{departure}/arrival/{arrival}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Flight> getIt(@PathParam("departure") String departure,@PathParam("arrival") String arrival) {
    	return repo.FlightsByDestination(departure,arrival);
    }
    
    @GET
	@Path("airport/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> getFlightByType(@PathParam("type") String type){
		return repo.getFlightByType(type);
	}
    
    @GET
    @Path("depart/{airport}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Flight> getIt(@PathParam("airport") String airport) {
    	return repo.FlightsDepartingFrom(airport);
    }
    
    @GET
    @Path("arrive/{airport}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Flight> getIt1(@PathParam("airport") String airport) {
    	return repo.FlightsArrivingAt(airport);
    }
}
