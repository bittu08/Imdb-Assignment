package com.hashedin.bittu.imdb;

import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;


public class User implements ICommon {
	
	private int userId;
	private int age;
	private char gender;
	private String occupation;
	private String zipCode;
	private JdbcTemplate jdbcTemplate;
	private List<String> list;

	public void importDataToTable()
	{
	
		String INSERT_USER_QUERY="insert into User(userId,age,gender,occupation,zipCode) values(?,?,?,?,?)";
		int[] types = new int[] {Types.INTEGER, Types.INTEGER, Types.CHAR, Types.VARCHAR,Types.VARCHAR};
		
        for(int i=0;i<list.size();)
		{
        	User us=new User();
			us.setUserId(Integer.parseInt(list.get(i++)));
			us.setAge(Integer.parseInt(list.get(i++)));
			us.setGender(list.get(i++).charAt(0));
			us.setOccupation(list.get(i++));
			us.setZipCode(list.get(i++));
			
			Object[] params = new Object[] {us.getUserId(),us.getAge(),us.getGender(),us.getOccupation(),us.getZipCode() };
			
			jdbcTemplate.update(INSERT_USER_QUERY,params, types);
	        
	        }
			System.out.println("Data Inserted To User Successfully");
	}
	
	public void setList(List<String> list) {
		this.list=list;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate =DataImporter.jdbcTemplate;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", age=" + age + ", gender=" + gender
				+ ", occupation=" + occupation + ", zipCode=" + zipCode + "]";
	}

	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	

	
	

}
