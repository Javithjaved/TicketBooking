package com.theatre.ticketsBooking.Entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequenceGenerator")
    @Column(name="theatre_id")
    private Long Id;

    @Column(name="theatre_name")
    private String theatrename;

    @Column(name="show_time")
    private Date showtime;

    @Column(name="show_date")
    private Date showdate;
   
    @JoinColumn(name="movie_id")
    @ManyToOne
    private Movie movie;
    @JoinColumn(name="location_id")
    @ManyToOne
    private Location location;
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTheatrename() {
        return theatrename;
    }

    public void setTheatrename(String theatrename) {
        this.theatrename = theatrename;
    }

    public Date getShowtime() {
        return showtime;
    }

    public void setShowtime(Date showtime) {
        this.showtime = showtime;
    }

    public Date getShowdate() {
        return showdate;
    }

    public void setShowdate(Date showdate) {
        this.showdate = showdate;
    }

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
    
}
