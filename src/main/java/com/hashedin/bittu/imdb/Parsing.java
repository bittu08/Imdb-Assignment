package com.hashedin.bittu.imdb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Parsing {

	public List<String> list=null;
	
	public List<String> parseFile(String filename) 
	{
		list=new ArrayList<String>();
		try{
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = "";
			StringTokenizer token = null;
			

			while ((line = br.readLine()) != null) {
		

				// break comma separated file line by line
				token = new StringTokenizer(line, "|");

				while (token.hasMoreTokens()) 
				{
				
					list.add(token.nextToken());
					//System.out.println("Line #" + lineNum + ", Token #"
						//	+ tokenNum + ", Token: " + token.nextToken());
				}

			}

			return list;
		} catch (Exception e) {
			System.err.println("Parse Error: " + e.getMessage());
			return null;
		}
	}
	
	

}
