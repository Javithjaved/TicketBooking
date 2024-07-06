package com.theatre.ticketsBooking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theatre.ticketsBooking.Entity.Movie;

public interface MovieRespository extends JpaRepository<Movie, Long> {

}
