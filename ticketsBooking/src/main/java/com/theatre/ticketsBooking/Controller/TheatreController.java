package com.theatre.ticketsBooking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theatre.ticketsBooking.Entity.Theatre;
import com.theatre.ticketsBooking.Service.TheatreService;

@RestController
@RequestMapping("/api/v1/theatre")
public class TheatreController {
	
	@Autowired
	
	private TheatreService theatreService;
	
	@PostMapping
	public Theatre CreateTheatre(@RequestBody Theatre theatre) {
		return this.theatreService.createTheatre(theatre);
	}
}
