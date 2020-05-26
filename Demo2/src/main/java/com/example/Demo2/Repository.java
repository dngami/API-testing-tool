package com.example.Demo2;

import java.util.*;
import java.sql.*;

public class Repository
{
	Connection con = null;
	public Repository()
	{
		String url="jdbc:mysql://localhost:3306/stud";
		String username="root";
		String password="root";
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con= DriverManager.getConnection(url,username,password);		
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public List<Student> getstud()
	{
		List<Student> studs = new ArrayList<>();
		String sql="select * from student";
		try 
		{
			Statement st= con.createStatement();
			ResultSet rs= st.executeQuery(sql);
			while(rs.next()) {
				Student a =new Student();
				a.setRollNo(rs.getInt(1));
				a.setBranch(rs.getString(2));
				a.setName(rs.getString(3));
				a.setMarks(rs.getInt(4));
				studs.add(a);
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return studs;
	}
	
	public Student getStud(int rollNo)
	{
		Student a = new Student();
		String sql="select * from student where rollNo="+ rollNo;
		try 
		{
			Statement st= con.createStatement();
			ResultSet rs= st.executeQuery(sql);
			if(rs.next()) {
				a.setRollNo(rs.getInt(1));
				a.setBranch(rs.getString(2));
				a.setName(rs.getString(3));
				a.setMarks(rs.getInt(4));
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return a;
	}
	
	public void create(Student s)
	{
		String sql="insert into student values (?,?,?,?)";
		try 
		{
			PreparedStatement st= con.prepareStatement(sql);
			
			st.setInt(1, s.getRollNo());
			st.setString(2, s.getBranch());
			st.setString(3, s.getName());
			st.setInt(4, s.getMarks());
			st.executeUpdate();
			
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
	}

	public void delStud(int rollNo) 
	{
		String sql="delete from student where rollNo=?";
		try 
		{
			PreparedStatement st= con.prepareStatement(sql);
		
			st.setInt(1, rollNo);
			st.executeUpdate();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public void update(Student s) 
	{
		String sql="update student set branch=?, name=?, marks=? where rollNo=?";
		try 
		{
			PreparedStatement st= con.prepareStatement(sql);
			st.setString(1, s.getBranch());
			st.setString(2, s.getName());
			st.setInt(3, s.getMarks());
			st.setInt(4, s.getRollNo());
			st.executeUpdate();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

}
