package com.theatre.ticketsBooking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theatre.ticketsBooking.Entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
