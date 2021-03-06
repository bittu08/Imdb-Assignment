package com.hashedin.bittu.imdb;

import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class Rating implements ICommon {
	
	private int userId;
	private int itemId;
	private int rating;
	private long timestamp;
	private JdbcTemplate jdbcTemplate;
	private List<String> list;

	public void importDataToTable()
	{
	
		String INSERT_USER_QUERY="insert into Rating(userId,itemId,rating,timestamp) values(?,?,?,?)";
		int[] types = new int[] {Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER};
		
        for(int i=0;i<list.size()-1;)
		{
        	Rating us=new Rating();
			us.setUserId(Integer.parseInt(list.get(i++)));
			us.setItemId(Integer.parseInt(list.get(i++)));
			us.setRating(Integer.parseInt(list.get(i++)));
			us.setTimestamp(Integer.parseInt(list.get(i++)));
	
			
			Object[] params = new Object[] {us.getUserId(),us.getItemId(),us.getRating(),us.getTimestamp()};
			jdbcTemplate.update(INSERT_USER_QUERY,params, types);
	        
	        }
			System.out.println("Data Inserted To Rating Successfully");
	}
	

	@Override
	public String toString() {
		return "Rating [userId=" + userId + ", itemId=" + itemId + ", rating="
				+ rating + ", timestamp=" + timestamp + "]";
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}


	public void setList(List<String> list) {
		this.list=list;
	}


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate =DataImporter.jdbcTemplate;
	}
	
	

}
