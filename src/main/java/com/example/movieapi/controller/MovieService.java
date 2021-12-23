package com.example.movieapi.controller;

import java.util.HashMap;
import java.util.List;

public interface MovieService {
	
	void create(MovieRequest movieRequest);
	List<Movie> get(HashMap<String,String> searchParams);
	void update(MovieRequest movieRequest);
	void delete(String name); 
	
}
