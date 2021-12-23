package com.example.movieapi.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	Movie movie;
	
	@Autowired
	MovieRepository movieRepository;
	
	@Override
	public void create(MovieRequest movieRequest) {
		// TODO Auto-generated method stub
		
			String name = movieRequest.getName();
			String genre = movieRequest.getGenre();
			String language = movieRequest.getLanguage();
			if(name != null && genre!=null && language!=null) {
				movie.setName(name);
				movie.setGenre(genre);
				movie.setLanguage(language);
				movieRepository.save(movie);
			}
		
	}

	@Override
	public List<Movie> get(HashMap<String,String> searchParams) {
		// TODO Auto-generated method stub
		if(searchParams.containsKey("name"))
			return movieRepository.findByName((String)searchParams.get("name"));
		if(searchParams.containsKey("language"))
			return movieRepository.findByLanguage((String)searchParams.get("language"));
		if(searchParams.containsKey("genre"))
			return movieRepository.findByGenre((String)searchParams.get("genre"));
		return null;
		
	}

	@Override
	public void update(MovieRequest movieRequest) {
		// TODO Auto-generated method stub
		movie.setName(movieRequest.getName());
		movie.setGenre(movieRequest.getGenre());
		movie.setLanguage(movieRequest.getLanguage());
		movieRepository.save(movie);
	}

	@Override
	public void delete(String name) {
		// TODO Auto-generated method stub
		movieRepository.delete(name);
	}
	
	
	
}
