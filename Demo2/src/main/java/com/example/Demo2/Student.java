package com.example.Demo2;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student 
{
	private int rollNo;
	private String name;
	private String branch;
	private int marks;
	public int getRollNo() {
		return rollNo;
	}
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	@Override
	public String toString() {
		return "Student [rollNo=" + rollNo + ", name=" + name + ", branch=" + branch + ", marks=" + marks + "]";
	}
	
}
