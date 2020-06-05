package com.restapi.FlightApi;

import java.util.*;

import com.restapi.FlightApi.Exception.DataNotFoundException;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;



public class FlightRepo {
	String url;
	String username;
	String password;
	
	public static Properties loadFile() throws Exception {
		Properties prop = new Properties();
		InputStream input = new FileInputStream("jdbc.properties");
		
		prop.load(input);
		input.close();
		return prop;
	}
	
	public FlightRepo()
	{
		try{
			
			Properties prop = loadFile();
		
			url = prop.getProperty("MYSQLJDBC.url");
		    username = prop.getProperty("MYSQLJDBC.username");
			password = prop.getProperty("MYSQLJDBC.password");
			String driver = prop.getProperty("MYSQLJDBC.driver");
			Class.forName(driver);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}	
	public void create(Flight a1) {
		if (a1.getId()==0||a1.getDeptApt()==null||a1.getArrApt()==null||a1.getDeptTime()==null||a1.getArrTime()==null||a1.getDistance()==0||a1.getPrice()==0||a1.getType()==null)
			throw new DataNotFoundException("Please provide all the parameters for the flight");
		String sql = "insert into flights values(?,?,?,?,?,?,?,?)";
		try
		{
			
			Connection con = DriverManager.getConnection(url,username,password);
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, a1.getId());
			st.setString(2, a1.getDeptApt());
			st.setString(3, a1.getArrApt());
			st.setString(4, a1.getDeptTime());
			st.setString(5, a1.getArrTime());
			st.setInt(6, a1.getDistance());
			st.setInt(7, a1.getPrice());
			st.setString(8, a1.getType());
			st.executeUpdate();
			st.close();
			con.close();
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	public void addlist(List<Flight> a1) 
	{
		for (Flight a:a1)
		{
			if (a.getId()==0||a.getDeptApt()==null||a.getArrApt()==null||a.getDeptTime()==null||a.getArrTime()==null||a.getDistance()==0||a.getPrice()==0||a.getType()==null)
				throw new DataNotFoundException("Please provide all the parameters for every flight");
		}
		String sql = "insert into flights values(?,?,?,?,?,?,?,?)";
		try
		{
			
			Connection con = DriverManager.getConnection(url,username,password);
			PreparedStatement st = con.prepareStatement(sql);
			for(Flight a: a1 )
			{
				st.setInt(1, a.getId());
				st.setString(2, a.getDeptApt());
				st.setString(3, a.getArrApt());
				st.setString(4, a.getDeptTime());
				st.setString(5, a.getArrTime());
				st.setInt(6, a.getDistance());
				st.setInt(7, a.getPrice());
				st.setString(8, a.getType());
				st.executeUpdate();
			}
			st.close();
			con.close();
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public List<Flight> getlist() 
	{
		List<Flight> fl = new ArrayList<>();
		String sql = "select * from flights";
		int count=0;
		try 
		{
			Connection con = DriverManager.getConnection(url, username, password);
			Statement st= con.createStatement();
			ResultSet rs= st.executeQuery(sql);
			while(rs.next()) 
			{
				Flight a =new Flight();
				a.setId(rs.getInt(1));
				a.setDeptApt(rs.getString(2));
				a.setArrApt(rs.getString(3));
				a.setDeptTime(rs.getString(4));
				a.setArrTime(rs.getString(5));
				a.setDistance(rs.getInt(6));
				a.setPrice(rs.getInt(7));
				a.setType(rs.getString(8));
				if (a.getDistance()!=0) count++; 
				fl.add(a);
			}
			st.close();
			con.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		if (count==0)
		{
			throw new DataNotFoundException("No flights are available");
		}
		else return fl;
	}
	
	
	public Flight getflight(int id) 
	{
		Flight a = new Flight();
		String sql="select * from flights where id='"+ id+"'";
		try 
		{
			
			Connection con = DriverManager.getConnection(url,username,password);
			Statement st = con.createStatement();
			ResultSet rs= st.executeQuery(sql);
			if(rs.next()) {
				a.setId(rs.getInt(1));
				a.setDeptApt(rs.getString(2));
				a.setArrApt(rs.getString(3));
				a.setDeptTime(rs.getString(4));
				a.setArrTime(rs.getString(5));
				a.setDistance(rs.getInt(6));
				a.setPrice(rs.getInt(7));
				a.setType(rs.getString(8));
			}
			st.close();
			con.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		if (a.getPrice()==0)
		{
			throw new DataNotFoundException("No flight exists by this id");
		}
		 return a;
	}

	public List<Flight> FlightsByDestination(String departure, String arrival) {
		String sql= "select * from flights where DeptApt='" + departure + "' and ArrApt='" + arrival +"'";
		List<Flight> flights=new ArrayList<Flight>();
		int count =0;
		try{
			Connection con = DriverManager.getConnection(url,username,password);
			Statement st=con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				Flight f = new Flight();
				f.setId(rs.getInt(1));
				f.setDeptApt(rs.getString(2));
				f.setArrApt(rs.getString(3));
				f.setDeptTime(rs.getString(4));
				f.setArrTime(rs.getString(5));
				f.setDistance(rs.getInt(6));
				f.setPrice(rs.getInt(7));
				f.setType(rs.getString(8));
				if (f.getDistance()!=0) count++;
				flights.add(f);
				
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		if (count==0)
		{
			throw new DataNotFoundException("No flights are available");
		}
		else return flights;
		
	}

	public List<Flight> FlightsDepartingFrom(String airport) {
		String sql= "select * from flights where DeptApt='"+airport+"'";
		List<Flight> flights=new ArrayList<Flight>();
		int count=0;
		try{
			Connection con = DriverManager.getConnection(url,username,password);
			Statement st=con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				Flight f = new Flight();
				f.setId(rs.getInt(1));
				f.setDeptApt(rs.getString(2));
				f.setArrApt(rs.getString(3));
				f.setDeptTime(rs.getString(4));
				f.setArrTime(rs.getString(5));
				f.setDistance(rs.getInt(6));
				f.setPrice(rs.getInt(7));
				f.setType(rs.getString(8));
				if (f.getDistance()!=0) count++;
				flights.add(f);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		if (count==0)
		{
			throw new DataNotFoundException("No departures from the given airport");
		}
		else return flights;
		
	}
	
	public List<Flight> FlightsArrivingAt(String airport) {
		String sql= "select * from flights where ArrApt='"+airport+"'";
		List<Flight> flights=new ArrayList<Flight>();
		int count=0;
		try{
			Connection con = DriverManager.getConnection(url,username,password);
			Statement st=con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				Flight f = new Flight();
				f.setId(rs.getInt(1));
				f.setDeptApt(rs.getString(2));
				f.setArrApt(rs.getString(3));
				f.setDeptTime(rs.getString(4));
				f.setArrTime(rs.getString(5));
				f.setDistance(rs.getInt(6));
				f.setPrice(rs.getInt(7));
				f.setType(rs.getString(8));
				if (f.getDistance()!=0) count++; 
				flights.add(f);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		if (count==0)
			throw new DataNotFoundException("No arrivals at the given airport");
		else return flights;
		
	}
	
	
	public List<Flight> getFlightByType(String type){

		List<Flight> flights= new ArrayList<>();
		String sql = "select * from flights where type='"+type+"'";
		int count=0;
		try{		
			Connection con = DriverManager.getConnection(url,username,password);
			Statement st=con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				Flight a=new Flight();
				a.setId(rs.getInt(1));
				a.setDeptApt(rs.getString(2));
				a.setArrApt(rs.getString(3));
				a.setDeptTime(rs.getString(4));
				a.setArrTime(rs.getString(5));
				a.setDistance(rs.getInt(6));
				a.setPrice(rs.getInt(7));
				a.setType(rs.getString(8));
				if (a.getDistance()==0) count++;
				flights.add(a);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		if (count==0) 
			throw new DataNotFoundException("No flights belong to the given type");
		else return flights;
		
	}
	
	
	


	public void arrTimeupdate(int id, String t)
	{
		String sql="update flights set ArrTime = ? where id=?";
		try 
		{
			
			Connection con = DriverManager.getConnection(url,username,password);
			PreparedStatement st= con.prepareStatement(sql);
			st.setString(1, t);
			st.setInt(2, id);
			st.executeUpdate();
			st.close();
			con.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
	}

	public void deptTimeupdate(int id, String t) 
	{
		String sql="update flights set DeptTime = ? where id=?";
		try 
		{
			
			Connection con = DriverManager.getConnection(url,username,password);
			PreparedStatement st= con.prepareStatement(sql);
			st.setString(1, t);
			st.setInt(2, id);
			st.executeUpdate();
			st.close();
			con.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public void arrAptupdate(int id, String s) 
	{
		String sql="update flights set ArrApt = ? where id=?";
		try 
		{
			
			Connection con = DriverManager.getConnection(url,username,password);
			PreparedStatement st= con.prepareStatement(sql);
			st.setString(1, s);
			st.setInt(2, id);
			st.executeUpdate();
			st.close();
			con.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public void deptAptupdate(int id, String s) 
	{
		String sql="update flights set DeptApt = ? where id=?";
		try 
		{
			
			Connection con = DriverManager.getConnection(url,username,password);
			PreparedStatement st= con.prepareStatement(sql);
			st.setString(1, s);
			st.setInt(2, id);
			st.executeUpdate();
			st.close();
			con.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public void priceupdate(int id, int p) 
	{
		String sql="update flights set Price = ? where id=?";
		try 
		{
			
			Connection con = DriverManager.getConnection(url,username,password);
			PreparedStatement st= con.prepareStatement(sql);
			st.setInt(1, p);
			st.setInt(2, id);
			st.executeUpdate();
			st.close();
			con.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void delete(int id) {
		String sql = "delete from flights where id=?";
		try
		{
			
			Connection con = DriverManager.getConnection(url,username,password);
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			st.executeUpdate();
			st.close();
			con.close();
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

}
