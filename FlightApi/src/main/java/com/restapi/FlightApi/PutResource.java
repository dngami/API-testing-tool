package com.restapi.FlightApi;


import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("edit/{id}")
public class PutResource
{
	public FlightRepo flight = new FlightRepo();
	
	@PUT
	@Path("arr/{t}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
	public String updatearrtime(@PathParam("id") int id,@PathParam("t") String t)
	{
		Flight a= flight.getflight(id);
		if(a.getId()==0)
		{
			return ("No Flight exsist by this id");
				
		}
		else 
		{
			flight.arrTimeupdate(id,t);
		}
		
		return ("Successfully Updated");	
	}
	
	@PUT
	@Path("dept/{t}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
	public String updatedepttime(@PathParam("id") int id,@PathParam("t") String t)
	{
		Flight a= flight.getflight(id);
		if(a.getId()==0)
		{
			return ("No Flight exsist by this id");
				
		}
		else 
		{
			flight.deptTimeupdate(id,t);
		}
		
		return ("Successfully Updated");	
	}
	
	@PUT
	@Path("arrapt/{s}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
	public String updatearrapt(@PathParam("id") int id,@PathParam("s") String s)
	{
		Flight a= flight.getflight(id);
		if(a.getId()==0)
		{
			return ("No Flight exsist by this id");
				
		}
		else 
		{
			flight.arrAptupdate(id,s);
		}
		
		return ("Successfully Updated");	
	}
	
	@PUT
	@Path("deptapt/{s}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
	public String updatedeptapt(@PathParam("id") int id,@PathParam("s") String s)
	{
		Flight a= flight.getflight(id);
		if(a.getId()==0)
		{
			return ("No Flight exsist by this id");
				
		}
		else 
		{
			flight.deptAptupdate(id,s);
		}
		
		return ("Successfully Updated");	
	}
	
	@PUT
	@Path("price/{p}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
	public String updateprice(@PathParam("id") int id,@PathParam("p") int p)
	{
		Flight a= flight.getflight(id);
		if(a.getId()==0)
		{
			return ("No Flight exsist by this id");
				
		}
		else 
		{
			flight.priceupdate(id,p);
		}
		
		return ("Successfully Updated");	
	}
	
}