package com.example.movieapi.controller;



import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class AppConfiguration {

	@Bean
	DataSource dataSource()  {
		Properties props = new Properties();
		DriverManagerDataSource driverManagerDataSource = null;
		String MYSQL_DB_DRIVER_CLASS="com.mysql.jdbc.Driver";
				String MYSQL_DB_URL="jdbc:mysql://dns1.nishchay.com:3306/movies";
				String MYSQL_DB_USERNAME="demouser";
				String MYSQL_DB_PASSWORD="123Nbr@";
		
		try {
//		FileInputStream fis = new FileInputStream("db.properties");
//		props.load(fis);
		driverManagerDataSource = 
				new DriverManagerDataSource();
		
		driverManagerDataSource.setDriverClassName(MYSQL_DB_DRIVER_CLASS);
		driverManagerDataSource.setUrl(MYSQL_DB_URL);
		driverManagerDataSource.setUsername(MYSQL_DB_USERNAME);
		driverManagerDataSource.setPassword(MYSQL_DB_PASSWORD);}
		catch(Exception ex) {
			
		}
		return driverManagerDataSource;
	}
	
	@Bean
	JdbcTemplate jdbcTemplate()  {
		return new JdbcTemplate(dataSource());
	}
	@Bean
	Movie movie() {
		return new Movie();
	}
}
