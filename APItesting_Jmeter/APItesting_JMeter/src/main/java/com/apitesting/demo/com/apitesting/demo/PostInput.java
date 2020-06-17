package com.apitesting.demo;

import java.util.ArrayList;

public class PostInput {
	private int postcount;
	private ArrayList<String> postpaths= new ArrayList<String>();
    private ArrayList<String> postbody  = new ArrayList<String>();
    
	public int getPostcount() {
		return postcount;
	}
	
	public void setPostcount(int postcount) {
		this.postcount = postcount;
	}
	
	public ArrayList<String> getPostpaths() {
		return postpaths;
	}
	
	public void setPostpaths(ArrayList<String> postpaths) {
		this.postpaths = postpaths;
	}
	
	public ArrayList<String> getPostbody() {
		return postbody;
	}
	
	public void setPostbody(ArrayList<String> postbody) {
		this.postbody = postbody;
	}
}
