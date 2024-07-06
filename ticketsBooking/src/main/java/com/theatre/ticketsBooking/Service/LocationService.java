package com.theatre.ticketsBooking.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.theatre.ticketsBooking.Entity.Location;
import com.theatre.ticketsBooking.Repository.LocationRepository;

@Service
public class LocationService {

	@Autowired
	private LocationRepository locationRepository;
	
	public Location Createlocation(Location location) {
	
			return this.locationRepository.save(location);
	}

	public List<Location> retrivelocation() {
		return this.locationRepository.findAll();
	}
}
