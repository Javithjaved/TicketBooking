package com.theatre.ticketsBooking.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theatre.ticketsBooking.Entity.Movie;
import com.theatre.ticketsBooking.Service.MovieService;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {
	@Autowired
	MovieService movieService;
	
	@PostMapping
	public Movie createMovie(@RequestBody Movie theatre) {
		return this.movieService.CreateMovie(theatre);
	}
	@GetMapping
	public List<Movie> retriveMovie(){
		return this.movieService.RetriveMovie();
	}
	@GetMapping("/{id}")
	public Optional<Movie> SingleMovie(@PathVariable Long id) {
		return this.movieService.Singlemovie(id);
	}
}
