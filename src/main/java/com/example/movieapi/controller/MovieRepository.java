package com.example.movieapi.controller;

import java.util.List;

public interface MovieRepository {
	
	void save(Movie movie);
	List<Movie> findByName(String name);
	List<Movie> findByLanguage(String language);
	List<Movie> findByGenre(String genre);
	void update(Movie movie);
	void delete(String name);
}
