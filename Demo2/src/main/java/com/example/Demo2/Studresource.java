package com.example.Demo2;

import java.util.List;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("students")
public class Studresource
{
	public Repository kid =new Repository();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStud()
	{
		return kid.getstud();
	}
	
	
	@GET
	@Path("stud/{rollNo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStud(@PathParam("rollNo") int rollNo)
	{
		return kid.getStud(rollNo);
	}
	
	@POST
	@Path("new")
	@Consumes(MediaType.APPLICATION_JSON)
	public Student create(Student s)
	{
		System.out.println(s);
		kid.create(s);
		return s;
	}
	
	@PUT
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Student update(Student s)
	{
		System.out.println(s);
		if(kid.getStud(s.getRollNo()).getRollNo()==0)
		{
			kid.create(s);
		}else
		{
			kid.update(s);
		}
		return s;
	}
	
	@DELETE
	@Path("remove/{rollNo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student remove(@PathParam("rollNo") int rollNo)
	{
		Student s= kid.getStud(rollNo);
		if(s.getRollNo()!=0)
		{
			kid.delStud(rollNo);
		}
		return s;
	}

}
