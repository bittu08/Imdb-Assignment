package com.hashedin.bittu.Imdb;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.hashedin.bittu.imdb.Movie;

public class MovieTestCase {

	@Test
	public void test() 
	{
		Movie mv=new Movie();
		XmlBeanFactory factory= new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		DataSource dataSource = factory.getBean(DataSource.class);
		
		
		fail("Not yet implemented");
	}

}
