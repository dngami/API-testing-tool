package com.apitesting.demo;

public class Inputs {
	private String domain;
    private int port;
    private int loop;
    private int thread;
    private int ramp;
    
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
}
