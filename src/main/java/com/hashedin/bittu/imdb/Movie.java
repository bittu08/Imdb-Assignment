package com.hashedin.bittu.imdb;

import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class Movie implements ICommon {
	
	private int movieId;
	private String movieTitle;
	private String releaseDate;
	private String videoReleaseDate;
	private String imdbUrl;
	private int genre;
	private JdbcTemplate jdbcTemplate;
	private List<String> list;
	
	
	
	public void importDataToTable()
	{
	
		String INSERT_USER_QUERY="insert into Movie(movieId,movieTitle,releaseDate,videoReleaseDate,imdbUrl) values(?,?,?,?,?)";
		int[] types = new int[] {Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR};
		
        for(int i=0;i<list.size();)
		{
        	Movie mv=new Movie();
			mv.setMovieId(Integer.parseInt(list.get(i++)));
			mv.setMovieTitle(list.get(i++));
			mv.setReleaseDate(list.get(i++));
			
			mv.setVideoReleaseDate(list.get(i++));
		
			mv.setImdbUrl(list.get(i++));
			
			Object[] params = new Object[] {mv.getMovieId(),mv.getMovieTitle(),mv.getReleaseDate(),mv.getVideoReleaseDate(),mv.getImdbUrl()};
			
			jdbcTemplate.update(INSERT_USER_QUERY,params, types);
			
			int cnt=0;
			
			while(cnt!=19)
			{
				if(Integer.parseInt(list.get(i))==1)
				{
					int[] type1=new int[]{Types.INTEGER,Types.INTEGER};
					Object[] params1=new Object[]{mv.getMovieId(),(i%24)-5};
					jdbcTemplate.update("insert into MovieMapGenre (movieId,genreId) values(?,?)",params1,type1);
				}
				i++;
				cnt++;
			}
	        
	     }
			System.out.println("Data Inserted To Movie Successfully");

	}
	

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", movieTitle=" + movieTitle
				+ ", releaseDate=" + releaseDate + ", videoReleaseDate="
				+ videoReleaseDate + ", imdbUrl=" + imdbUrl + ", genre="
				+ genre + "]";
	}


	public int getMovieId() {
		return movieId;
	}


	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}


	public String getMovieTitle() {
		return movieTitle;
	}


	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}


	public String getReleaseDate() {
		return releaseDate;
	}


	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}


	public String getVideoReleaseDate() {
		return videoReleaseDate;
	}


	public void setVideoReleaseDate(String videoReleaseDate) {
		this.videoReleaseDate = videoReleaseDate;
	}


	public String getImdbUrl() {
		return imdbUrl;
	}


	public void setImdbUrl(String imdbUrl) {
		this.imdbUrl = imdbUrl;
	}


	public int getGenre() {
		return genre;
	}


	public void setGenre(int genre) {
		this.genre = genre;
	}


	public void setList(List<String> list) {
	     this.list=list;
	}


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	

}
