package com.hashedin.bittu.imdb;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream.GetField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Scanner;

import javax.sql.DataSource;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class DataImporter {

	
	static JdbcTemplate jdbcTemplate = null;
	static XmlBeanFactory factory=null; 

	public static void main(String args[]) throws IOException {

		factory= new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		DataSource dataSource = factory.getBean(DataSource.class);
		DataImporter importer = new DataImporter(dataSource);
		getTopMovieByGenre();
		
	}

	public DataImporter(DataSource dataSource) 
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public static void getTopMovieByGenre()
	{
		List<String> listgenre=getGenre();
		
		for(int i=0;i<listgenre.size();i+=2)
		{
            String genre=listgenre.get(i).toString();
			
            if(genre.contains("'"))
            {
            	genre.replace("'","''");
            }
            String str="select P.movieTitle from (select A.movieId, A.movieTitle, A.genreName, B.avgRating from (select MMG.movieId,MMG.movieTitle,Genre.genreId,Genre.genreName from Genre inner join (select Movie.movieId,movieTitle,genreId from Movie inner join MovieMapGenre on Movie.movieId=MovieMapGenre.movieId) as MMG on MMG.genreId=Genre.genreId) as A inner join (select itemid,AVG(rating) as avgRating from Rating group by itemid) as B on A.movieId=B.itemid) as P where P.genreName='"+genre+"' order by avgRating desc limit 0, 25";
			
			List<String> mv=jdbcTemplate.query(str, new RowMapper<String>(){
				
				public String mapRow(ResultSet rs,int rowNum) throws SQLException
				{
					return(rs.getString(1));
				}	
			});
			
			System.out.println(listgenre.get(i));
			for(int j=0;j<mv.size();j++)
			{
				System.out.println(mv.get(j));
			}
			System.out.println();
		}
	}
	
	public static List<String> getGenre()
	{
		String query="select genreName from Genre";
		List<String> genreList=jdbcTemplate.query(query, new RowMapper<String>(){
			
			public String mapRow(ResultSet rs,int rowNum) throws SQLException
			{
				return(rs.getString(1));
			}	
		});
		
		return genreList;
	}
	
	
	public static void InsertData()
	{
		callUser();
		callGenre();
		callMovie();
		callRating();
	}
	
	public static void callUser()
	{
		Parsing ps = (Parsing) factory.getBean("Parsing");	
		ps.setfilename("/home/bittu/ml-100k/u.user");
		ps.parseFile();
		List<String> listUser = ps.getList();
		User usr=(User) factory.getBean("User");
		usr.setJdbcTemplate(jdbcTemplate);
		usr.setList(listUser);
		usr.importDataToTable();
	}
	
	public static void callMovie()
	{
		Parsing ps = (Parsing) factory.getBean("Parsing");	
		ps.setfilename("/home/bittu/ml-100k/u.item");
		ps.parseFile();
		List<String> listMovie = ps.getList();
		Movie mv=(Movie) factory.getBean("Movie");
		mv.setJdbcTemplate(jdbcTemplate);
		mv.setList(listMovie);
		mv.importDataToTable();
	}
	
	public static void callGenre()
	{ 
		Parsing ps = (Parsing) factory.getBean("Parsing");	
		ps.setfilename("/home/bittu/ml-100k/u.genre");
		ps.parseFile();
		List<String> listgenre = ps.getList();
		Genre gn=(Genre) factory.getBean("Genre");
		gn.setJdbcTemplate(jdbcTemplate);
		gn.setList(listgenre);
		gn.importDataToTable();
	}
	
	public static void callRating()
	{
		Parsing ps = (Parsing) factory.getBean("Parsing");	
		Parsing2 ps2 =(Parsing2) factory.getBean("Parsing2");
		ps2.setfilename("/home/bittu/ml-100k/u.data");
		ps2.parseFile();
		List<String> listRating = ps2.getList();
		Rating rat = (Rating) factory.getBean("Rating");
		rat.setJdbcTemplate(jdbcTemplate);
		rat.setList(listRating);
		rat.importDataToTable();
	}
	


}
