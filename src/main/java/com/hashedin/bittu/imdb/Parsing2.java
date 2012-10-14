package com.hashedin.bittu.imdb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Parsing2 implements IParsingToClass {

	private List<String> list=null;
	private String filename;
	
	public void parseFile() 
	{
		list=new ArrayList<String>();
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = "";
			StringTokenizer token = null;

			
			while ((line = br.readLine()) != null) {
		

				token=new StringTokenizer(line,"\t");

				while (token.hasMoreTokens()) 
				{
				
					list.add(token.nextToken());
					
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
