package com.apitesting.demo;

import java.util.ArrayList;

public class Allsource 
{
	private String domain;
    private int port;
    private int loop;
    private int thread;
    private int ramp;
    private int getcount;
	private ArrayList<String> getpaths = new ArrayList<String>();
	private int postcount;
	private ArrayList<String> postpaths= new ArrayList<String>();
    private ArrayList<String> postbody  = new ArrayList<String>();
    private int delcount;
	private ArrayList<String> delpaths = new ArrayList<String>();
	private int putcount;
	private ArrayList<String> putpaths = new ArrayList<String>();
    private ArrayList<String> putbody  = new ArrayList<String>();
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getLoop() {
		return loop;
	}
	public void setLoop(int loop) {
		this.loop = loop;
	}
	public int getThread() {
		return thread;
	}
	public void setThread(int thread) {
		this.thread = thread;
	}
	public int getRamp() {
		return ramp;
	}
	public void setRamp(int ramp) {
		this.ramp = ramp;
	}
	public int getGetcount() {
		return getcount;
	}
	public void setGetcount(int getcount) {
		this.getcount = getcount;
	}
	public ArrayList<String> getGetpaths() {
		return getpaths;
	}
	public void setGetpaths(ArrayList<String> getpaths) {
		this.getpaths = getpaths;
	}
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
	public int getDelcount() {
		return delcount;
	}
	public void setDelcount(int delcount) {
		this.delcount = delcount;
	}
	public ArrayList<String> getDelpaths() {
		return delpaths;
	}
	public void setDelpaths(ArrayList<String> delpaths) {
		this.delpaths = delpaths;
	}
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
