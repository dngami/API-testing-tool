package com.telusko.demorest;


//import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("aliens")
public class AlienResource 
{
	AlienRepository repo = new AlienRepository();
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Alien> getAliens()
	{
		return repo.getAliens();
	}
	@GET
	@Path("alien/{points}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Alien getAlien(@PathParam("points") int points)
	{
		return repo.getAlien(points);
	}
	@POST
	@Path("alien")
	public Alien createAlien(Alien a1)
	{
		System.out.println(a1);
		repo.create(a1);
		
		return a1;
	}
	@PUT
	@Path("alien")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Alien updateAlien(Alien a1)
	{
		System.out.println(a1);
		if(repo.getAlien(a1.getPoints()).getPoints()==0)
		{
			repo.create(a1);
		}
		else
		{
			repo.update(a1);
		}
		
		return a1;
	}
	
	@DELETE
	@Path("alien/{points}")
	public Alien killAlien(@PathParam("points") int points)
	{
		Alien a = repo.getAlien(points);
		if(a.getPoints()!=0)
		{
			repo.delete(points);
		}
		
		return a;
	}


}
