package com.example.movieapi.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mysql.cj.xdevapi.PreparableStatement;

@Repository
public class MovieRepositoryImpl implements MovieRepository{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public void save(Movie movie) {
		// TODO Auto-generated method stub
		List<Movie> movie1 = findByName(movie.getName());
		if(movie1==null){
			String query="Insert into movie\n"+
				"(`name`,`genre`,`language`)\n"+
				"Values\n"+
				"(?,?,?)";
		
			jdbcTemplate.update(query,movie.getName(),movie.getGenre(),movie.getLanguage());
		}
		if(movie.getLanguage()!=null) {
			String sql1 = "Update movie set `language` = ? where `name` = ? ";
		
			jdbcTemplate.update(sql1,movie.getLanguage(),movie.getName());
		}
		if(movie.getGenre()!=null) {
		
			String sql2 = "Update movie set `genre`=? where `name`=? ";
		
			jdbcTemplate.update(sql2,movie.getGenre(),movie.getName());
		}
		
		
	}

	@Override
	public List<Movie> findByName(String name) {
		// TODO Auto-generated method stub
		
		String sql = "Select * from movie where `name` = ?";
		
		List<Movie> movie = jdbcTemplate.query(sql,new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement)throws SQLException{
				preparedStatement.setString(1, name);
			} 
		},new RowMapper<Movie>() {
			@Override
			public Movie mapRow(ResultSet resultset,int i) throws SQLException {
				Movie movie = new Movie();
				movie.setName(resultset.getString("name"));
				movie.setGenre(resultset.getString("genre"));
				movie.setLanguage(resultset.getString("language"));
				return movie;
			}

			
		});
		
		if(movie==null || movie.size()==0)
			return null;
		
		return movie;
	}

	@Override
	public List<Movie> findByLanguage(String language) {
		// TODO Auto-generated method stub
		String sql = "Select * from movie where `language` = ?";
		
		List<Movie> movie = jdbcTemplate.query(sql,new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement)throws SQLException{
				preparedStatement.setString(1, language);
			} 
		},new RowMapper<Movie>() {
			@Override
			public Movie mapRow(ResultSet resultset,int i) throws SQLException {
				Movie movie = new Movie();
				movie.setName(resultset.getString("name"));
				movie.setGenre(resultset.getString("genre"));
				movie.setLanguage(resultset.getString("language"));
				return movie;
			}

			
		});
		
		if(movie==null || movie.size()==0)
			return null;
		
		return movie;

	}

	@Override
	public List<Movie> findByGenre(String genre) {
		// TODO Auto-generated method stub
		
		String sql = "Select * from movie where genre = ?";
		List<Movie> movie = jdbcTemplate.query(sql,new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setString(1, genre);
			}
			
		}, new RowMapper<Movie>() {

			@Override
			public Movie mapRow(ResultSet resultset, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				
				Movie movie = new Movie();
				movie.setName(resultset.getString("name"));
				movie.setGenre(resultset.getString("genre"));
				movie.setLanguage(resultset.getString("language"));
				return movie;
			}
		
		});
		
		return movie;
	}

	@Override
	public void update(Movie movie) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String name) {
		// TODO Auto-generated method stub
		String sql = "Delete from movie where name = ?";
		jdbcTemplate.update(sql,name);
		
	}

}
