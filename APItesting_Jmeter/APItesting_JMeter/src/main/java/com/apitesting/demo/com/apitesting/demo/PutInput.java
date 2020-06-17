package com.apitesting.demo;

import java.util.ArrayList;

public class PutInput {
	private int putcount;
	private ArrayList<String> putpaths = new ArrayList<String>();
    private ArrayList<String> putbody  = new ArrayList<String>();
    
	public int getPutcount() {
		return putcount;
	}
	
	public void setPutcount(int putcount) {
		this.putcount = putcount;
	}
	
	public ArrayList<String> getPutpaths() {
		return putpaths;
	}
	
	public void setPutpaths(ArrayList<String> putpaths) {
		this.putpaths = putpaths;
	}
	
	public ArrayList<String> getPutbody() {
		return putbody;
	}
	
	public void setPutbody(ArrayList<String> putbody) {
		this.putbody = putbody;
	}
}
