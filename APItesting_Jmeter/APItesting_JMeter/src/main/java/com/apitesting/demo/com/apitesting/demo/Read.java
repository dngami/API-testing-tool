package com.apitesting.demo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Read {
	Scanner obj = new Scanner(System.in);
	
	/*
	 * Read all the Get HTTP Requests Data
	 * get count i.e. Total number of GET Requests
	 * get Path all requests path
	 */
	public GetInput getread()  throws FileNotFoundException
	{
		GetInput gets = new GetInput();
	//	Scanner obj1 = new Scanner(System.in);
		System.out.println("\n\nGET Requests Details");
		System.out.println("Number of GET Request: ");
		gets.setGetcount(obj.nextInt());
		//String n;
		//n=obj1.next();
		//System.out.println(n+" here lies the problem");
		ArrayList<String> paths = new ArrayList<String>();
		for(int i=0;i<gets.getGetcount();i++)
		{
			System.out.println("Path for "+ (i+1)+" request:");
			paths.add(obj.next());
		}
		gets.setGetpaths(paths);
		//obj1.close();
		return gets;
	}
	
	/*
	 * Read all the PUT HTTP Requests Data
	 * Put count i.e. Total number of Put Requests
	 * put Path all requests path
	 * Body(DATA) for each request
	 */
	public PutInput putread() throws FileNotFoundException
	{
		PutInput put = new PutInput();
		//Scanner obj = new Scanner(System.in);
		System.out.println("\n\nPUT Requests Details");
		System.out.println("Number of put Request: ");
		put.setPutcount(obj.nextInt());
		ArrayList<String> paths = new ArrayList<String>();
	//	ArrayList<String> body  = new ArrayList<String>();
		for(int i=0;i<put.getPutcount();i++)
		{
			System.out.println("Path for "+ (i+1)+" request:");
			paths.add(obj.next());
		//	System.out.println("Body for "+ (i+1)+" request:");
		//	body.add(obj.next());
		}
		put.setPutpaths(paths);
	//	put.setPutbody(body);
		//obj.close();
		return put;
	}
	
	/*
	 * Read all the POST HTTP Requests Data
	 * Post count i.e. Total number of Post Requests
	 * post Path all requests path
	 * Body(DATA) for each request
	 */
	public PostInput postread() throws FileNotFoundException
	{
		PostInput post = new PostInput();
		//Scanner obj = new Scanner(System.in);
		System.out.println("\n\nPOST Requests Details");
		System.out.println("Number of post Request: ");
		post.setPostcount(obj.nextInt());
		ArrayList<String> paths = new ArrayList<String>();
		ArrayList<String> body  = new ArrayList<String>();
		for(int i=0;i<post.getPostcount();i++)
		{
			System.out.println("Path for "+ (i+1)+" request:");
			paths.add(obj.next());
			System.out.println("Body for "+ (i+1)+" request:");
			body.add(obj.next());
		}
		post.setPostpaths(paths);
		post.setPostbody(body);
		//obj.close();
		return post;
	}
	
	/*
	 * Read all the Delete HTTP Requests Data
	 * delete count i.e. Total number of Delete Requests
	 * delete Path all requests path
	 */
	public DelInput delread() throws FileNotFoundException
	{
		DelInput del = new DelInput();
		//Scanner obj = new Scanner(System.in);
		System.out.println("\n\nDELETE Requests Details");
		System.out.println("Number of Delete Request: ");
		del.setDelcount(obj.nextInt());
		ArrayList<String> paths = new ArrayList<String>();
		for(int i=0;i<del.getDelcount();i++)
		{
			System.out.println("Path for "+ (i+1)+" request:");
			paths.add(obj.next());
		}
		del.setDelpaths(paths);
		obj.close();
		return del;
	}
	
	/*
	 * Reads common values in all HTTP requests
	 * Domain
	 * port
	 * thread
	 * loop
	 * ramp
	 */
	public Inputs genread() throws FileNotFoundException
	{
		Inputs var = new Inputs();
		//Scanner obj = new Scanner(System.in);
		System.out.println("Enter Domain: ");
		var.setDomain(obj.next());
		System.out.println("Enter Port Number (Enter 80 for Default): ");
		var.setPort(obj.nextInt());
		System.out.println("Number of Threads (Users): ");
		var.setThread(obj.nextInt());
		System.out.println("Number of Loops: ");
		var.setLoop(obj.nextInt());
		System.out.println("Ramp time in sec: ");
		var.setRamp(obj.nextInt());
		//obj.close();
		return var;
	}
}
