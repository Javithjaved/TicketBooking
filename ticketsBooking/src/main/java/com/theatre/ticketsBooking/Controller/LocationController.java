package com.theatre.ticketsBooking.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theatre.ticketsBooking.Entity.Location;
import com.theatre.ticketsBooking.Service.LocationService;

@RestController
@RequestMapping("/api/v1/location")
public class LocationController {

    @Autowired
    private LocationService locationservice;
    
    @PostMapping
    public Location CreateLocation(@RequestBody Location location) {
    	return this.locationservice.Createlocation(location);
    }
    @GetMapping
    public List<Location> RetriveLocation() {
    	return this.locationservice.retrivelocation();
    }
}

