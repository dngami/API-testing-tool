package com.apitesting.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileIn {
	private static Logger log= Logger.getLogger(FileIn.class.getName());
	public Allsource takein() throws FileNotFoundException
	{
		String fileName;
		Scanner obj1=new Scanner(System.in);
		System.out.println("Input file path:");
        fileName=obj1.next();
        obj1.close();
		if (fileName == null || fileName.length() < 1) {
			throw new IllegalArgumentException("Filename may not be null or empty");
		}
		File file = new File(fileName);
		if (!file.exists()) {
			throw new FileNotFoundException(file.getAbsolutePath());
		}
		List<String> data = new ArrayList<String>();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(file));
			while (in.ready()) {
				String line = in.readLine();
				if (!line.startsWith("#") && line.length() > 0) {
					data.add(line);
				}
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					log.log(Level.SEVERE, e.getMessage(), e);
				}
			}
		}
		log.log(Level.INFO, data.size() + " elements loaded.");
		
		Allsource obj = new Allsource();
		
		ArrayList<String> getpath = new ArrayList<String>();
		ArrayList<String> postpath = new ArrayList<String>();
		ArrayList<String> delpath = new ArrayList<String>();
		ArrayList<String> putpath = new ArrayList<String>();
		ArrayList<String> postbody = new ArrayList<String>();
		int i=0;
	    obj.setDomain(data.get(i++));
	    obj.setPort(Integer.parseInt(data.get(i++)));
	    obj.setThread(Integer.parseInt(data.get(i++)));
	    obj.setLoop(Integer.parseInt(data.get(i++)));
	    obj.setRamp(Integer.parseInt(data.get(i++)));
	    obj.setGetcount(Integer.parseInt(data.get(i++)));
	    for(int j=0;j<obj.getGetcount();j++)
	    {
	    	getpath.add(data.get(i+j));
	    }
	    obj.setGetpaths(getpath);
	    i=i+obj.getGetcount();
	    System.out.println("");
	    obj.setPostcount(Integer.parseInt(data.get(i++)));
	    for(int j=0;j<(2*obj.getPostcount());j=j+2)
	    {
	    	postpath.add(data.get(i+j));
	    	postbody.add(data.get(i+j+1));
	    }
	    obj.setPostpaths(postpath);
	    obj.setPostbody(postbody);
	    i+= (2*obj.getPostcount());
	    
	    obj.setPutcount(Integer.parseInt(data.get(i++)));
	    for(int j=0;j<obj.getPutcount();j++)
	    {
	    	putpath.add(data.get(i+j));
	    }
	    i=i+ obj.getPutcount();
	    obj.setPutpaths(putpath);
	    obj.setDelcount(Integer.parseInt(data.get(i++)));
	    for(int j=0;j<obj.getDelcount();j++)
	    {
	    	delpath.add(data.get(i+j));
	    }
	    obj.setDelpaths(delpath);
	    return obj;
	}
}