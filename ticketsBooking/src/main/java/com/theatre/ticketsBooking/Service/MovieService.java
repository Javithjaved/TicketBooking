package com.theatre.ticketsBooking.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theatre.ticketsBooking.Entity.Movie;
import com.theatre.ticketsBooking.Repository.MovieRespository;
@Service
public class MovieService {
	
	@Autowired
	private MovieRespository movieRespository ;
	public Movie CreateMovie(Movie theatre) {
		return this.movieRespository.save(theatre);
	}
	public List<Movie> RetriveMovie() {
		return this.movieRespository.findAll();
	}
	public Optional<Movie> Singlemovie(Long id) {
		return this.movieRespository.findById(id);
	}

}
