package com.hashedin.bittu.imdb;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Scanner;

import javax.sql.DataSource;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class DataImporter {
	
	

	private static final String INSERT_MODEL_QUERY = "insert into model (id,make, modelname, year, startPrice, endPrice) "
													+ "values (?,?, ?, ?, ?, ?)";
	static JdbcTemplate jdbcTemplate=null;
	public static void main(String args[]) throws IOException 
	{
		
		XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		DataSource dataSource = factory.getBean(DataSource.class);				
		DataImporter importer = new DataImporter(dataSource);
		
		Parsing ps=new Parsing();
		
		/***********User**********************/
		List<String> listUser=ps.parseFile("/home/bittu/ml-100k/u.user");
		User usr=new User();
		usr.importToUserTable(jdbcTemplate,listUser);
		
		/**************Movie****************/
		
		List<String> listMovie=ps.parseFile("/home/bittu/ml-100k/u.item");
		Movie mv=new Movie();
		//mv.importToMovieTable(jdbcTemplate,listMovie);
		
		/************Genre******************/
		
		List<String> listgenre=ps.parseFile("/home/bittu/ml-100k/u.genre");
		Genre gn=new Genre();
		gn.importToGenreTable(jdbcTemplate,listgenre);
		
		/************Rating******************/
		
		Parsing2 ps2=new Parsing2();
		List<String> listRating=ps2.parseFile("/home/bittu/ml-100k/u.data");
		Rating rat=new Rating();
		gn.importToGenreTable(jdbcTemplate,listRating);
		
		
		
	}
	
	public DataImporter(DataSource dataSource) 
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	/*

	private void getCarModels() 
	{
		
		double endPrice,startPrice;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Start Price::");
		startPrice=sc.nextDouble();
		System.out.println("Enter End Proice");
		endPrice=sc.nextDouble();
		
		List<Model> allModels = jdbcTemplate.query("select make, modelname, year, startPrice, " +
				"endPrice from model where startPrice>="+startPrice+"and endPrice<="+endPrice, new RowMapper<Model>() {

			public Model mapRow(ResultSet rs, int rowNum) throws SQLException {
				Model m = new Model();
				m.setMake(rs.getString(1));
				m.setModelName(rs.getString(2));
				m.setYear(rs.getString(3));
				m.setMaxPrice(rs.getDouble(4));
				m.setMinPrice(rs.getDouble(5));
				return m;
			}
		});
		
		for(Model m : allModels) {
			System.out.println(m);
		}
	}
	
	
	*/
			

}
