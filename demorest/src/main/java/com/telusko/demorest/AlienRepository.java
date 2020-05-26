package com.telusko.demorest;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class AlienRepository 
{
	
	//Connection con=null;
	public AlienRepository()
	{
		try{
			String driver = "com.mysql.cj.jdbc.Driver";
			Class.forName(driver);
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public List<Alien> getAliens()
	{
		List<Alien> aliens = new ArrayList<>();
		String sql = "select * from alien";
		try
		{
			String url = "jdbc:mysql://localhost:3306/test";
			String username = "root";
			String password = "NYzv3Hbq_";
			Connection con = DriverManager.getConnection(url,username,password);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				Alien a = new Alien();
				a.setName(rs.getString(1));
				a.setPoints(rs.getInt(2));
				aliens.add(a);
			}
			st.close();
			con.close();
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return aliens;
	}
	
	public Alien getAlien(int points)
	{
		
		Alien a = new Alien();
		String sql = "select * from alien where points="+points;
		try
		{
			String url = "jdbc:mysql://localhost:3306/test";
			String username = "root";
			String password = "NYzv3Hbq_";
			Connection con = DriverManager.getConnection(url,username,password);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next())
			{
				a.setName(rs.getString(1));
				a.setPoints(rs.getInt(2));
			}
			st.close();
			con.close();
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return a;
	}
	public void create(Alien a1) {
		String sql = "insert into alien values(?,?)";
		try
		{
			String url = "jdbc:mysql://localhost:3306/test";
			String username = "root";
			String password = "NYzv3Hbq_";
			Connection con = DriverManager.getConnection(url,username,password);
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, a1.getName());
			st.setInt(2, a1.getPoints());
			st.executeUpdate();
			st.close();
			con.close();
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	public void update(Alien a1) {
		String sql = "update alien set aname=? where points=?";
		try
		{
			String url = "jdbc:mysql://localhost:3306/test";
			String username = "root";
			String password = "NYzv3Hbq_";
			Connection con = DriverManager.getConnection(url,username,password);
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, a1.getName());
			st.setInt(2, a1.getPoints());
			st.executeUpdate();
			st.close();
			con.close();
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	public void delete(int points) {
		String sql = "delete from alien where points=?";
		try
		{
			String url = "jdbc:mysql://localhost:3306/test";
			String username = "root";
			String password = "NYzv3Hbq_";
			Connection con = DriverManager.getConnection(url,username,password);
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, points);
			st.executeUpdate();
			st.close();
			con.close();
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
}
