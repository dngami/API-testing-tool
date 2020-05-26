package com.blackpearl.APItesting;


import java.util.List;
import java.util.Arrays;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("brandnew")
public class BrandnewResource {
	
	BrandnewRepos repoTemp = new BrandnewRepos();
	
	// this is to deal get request
//	@GET
//	@Produces(MediaType.APPLICATION_XML)
//	public List<Brandnew>  getNews(){
//		return repoTemp.getNews();
//	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Brandnew>  getNews(){
		return repoTemp.getNews();
	}
	
	
	
	@GET
	@Path("new/{id}")             // anything within {} is placeholder
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Brandnew  getNew(@PathParam("id") int id){
		// create a object of BrandnewRepos class first
		return repoTemp.getNew(id);
			
	}
	
	@POST
	@Path("addNew")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Brandnew createNew(Brandnew aa) {
		System.out.println(aa);
		repoTemp.create(aa);
		return aa;
	}
	 
}

