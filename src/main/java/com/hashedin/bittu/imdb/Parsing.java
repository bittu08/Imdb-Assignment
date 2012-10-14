package com.hashedin.bittu.imdb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Parsing implements IParsingToClass {

    private String filename;
	public List<String> list=null;

	public void parseFile() 
	{
		list=new ArrayList<String>();
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split("\\|");
				for(int i=0;i<tokens.length;i++)
				{
					list.add(tokens[i]);
				}
			
			}
			
		} catch (Exception e) {
			System.err.println("Parse Error: " + e.getMessage());
		}
		
	}

	public List<String> getList() {
		return list;
	}

	public void setfilename(String filename)
	{
		this.filename=filename;
	}
	
	
	
	

}
