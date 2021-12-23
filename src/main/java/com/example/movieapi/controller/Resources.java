package com.example.movieapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Resources {
	
	@Autowired
	MovieService movieService;
	
	@PostMapping("/v1/movie")
	void createMovie(@RequestBody MovieRequest movieRequest) throws Exception { 
		movieService.create(movieRequest);
	}
	
	@GetMapping("/v1/movie")
	public List<Movie> getMovie(@RequestParam HashMap<String,String> searchParams)
	{
		return movieService.get(searchParams);
		
	}
	
	@PutMapping("/v1/movie/{name}")
	public void updateMovie(@PathVariable("name") String name,
			@RequestBody MovieRequest movieRequest) {
			movieRequest.setName(name);
			movieService.update(movieRequest);
	}
	@DeleteMapping("/v1/movie/{name}")
	public void deleteMovie(@PathVariable("name") String name) {
		movieService.delete(name);
	}
}
