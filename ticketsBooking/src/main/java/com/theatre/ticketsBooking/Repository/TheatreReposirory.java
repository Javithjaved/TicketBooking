package com.theatre.ticketsBooking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theatre.ticketsBooking.Entity.Theatre;
@Repository
public interface TheatreReposirory extends JpaRepository<Theatre,Long> {

}
