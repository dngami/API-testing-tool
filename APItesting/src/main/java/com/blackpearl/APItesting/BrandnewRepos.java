package com.blackpearl.APItesting;

import java.util.ArrayList;

import java.sql.*;
import java.util.List;

public class BrandnewRepos {


	Connection con = null;

	public BrandnewRepos() {
		//String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/restdb?autoReconnect=true&useSSL=false";
		String username = "root";
		String password = "seeU@4on6";
	//	System.setProperty(driver,"");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}


	public List<Brandnew>  getNews(){
		List<Brandnew>  news = new ArrayList<>();
		String sql = "select * from tableofnew";
		try {
			Statement st= con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				Brandnew a = new Brandnew();
				a.setName(rs.getString(1));
				a.setPoints(rs.getInt(2));

				news.add(a);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}

		return news;
	}

	public Brandnew getNew(int pt) {
		String sql = "select * from tableofnew where points=" + pt;
		Brandnew a = new Brandnew();
		try {
			Statement st= con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {

				a.setName(rs.getString(1));
				a.setPoints(rs.getInt(2));
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}

		return a;
	}

	public void create(Brandnew a) {
		String sql = "insert into tableofnew values(?,?)";
		try {
			PreparedStatement st= con.prepareStatement(sql);
			st.setString(1, a.getName());
			st.setInt(2, a.getPoints());
			//ResultSet rs = st.executeQuery(sql);
			st.executeUpdate();


		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}

