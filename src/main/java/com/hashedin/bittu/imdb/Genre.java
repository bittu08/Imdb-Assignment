package com.hashedin.bittu.imdb;

import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class Genre {

	private String genre;
	
	
	
	public void importToGenreTable(JdbcTemplate jdbcTemplate,List<String> list)
	{
	
		String INSERT_USER_QUERY="insert into Genre(genreId,genreName) values(?,?)";
		int[] types = new int[] {Types.INTEGER, Types.VARCHAR};
		
        for(int i=0;i<list.size();)
		{
        	Genre gn=new Genre();
        	gn.setGenre(list.get(i++));
        	gn.setValue(Integer.parseInt(list.get(i++)));
			
			Object[] params = new Object[] {gn.getValue(),gn.getGenre()};
			
			jdbcTemplate.update(INSERT_USER_QUERY,params, types);
	        
	        }
			System.out.println("Data Inserted To Gener Successfully");
	}
	
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	private int value;
	
	@Override
	public String toString() {
		return "Genre [genre=" + genre + ", value=" + value + "]";
	}
	
	
	
	
}
