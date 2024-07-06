package com.theatre.ticketsBooking.Entity;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="movie")
public class Movie {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequenceGenerator")
	@Column(name="movie_id")
	private Long Id;
	@Column(name="movies")
	private String movies;
	@Column(name="imdb")
	private String imdb;
	@Column(name="language")
	private List<String> language;
	@Column(name="facility")
	private List<String> facility;
	@Column(name="releasedate")
	private String releasedate;
	@Column(name="cast")
	private List<String> cast;
	@Column(name="about")
	private String about;
	@ManyToOne
	private Location location;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getMovies() {
		return movies;
	}
	public void setMovies(String movies) {
		this.movies = movies;
	}
	public String getImdb() {
		return imdb;
	}
	public void setImdb(String imdb) {
		this.imdb = imdb;
	}
	public List<String> getLanguage() {
		return language;
	}
	public void setLanguage(List<String> language) {
		this.language = language;
	}
	public List<String> getFacility() {
		return facility;
	}
	public void setFacility(List<String> facility) {
		this.facility = facility;
	}
	public String getReleasedate() {
		return releasedate;
	}
	public void setReleasedate(String releasedate) {
		this.releasedate = releasedate;
	}
	
	public List<String> getCast() {
		return cast;
	}
	public void setCast(List<String> cast) {
		this.cast = cast;
	}

	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	
	
}
