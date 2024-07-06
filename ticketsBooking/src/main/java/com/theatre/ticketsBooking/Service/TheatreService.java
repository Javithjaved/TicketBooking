//package com.theatre.ticketsBooking.Service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.theatre.ticketsBooking.Entity.Theatre;
//import com.theatre.ticketsBooking.Repository.TheatreReposirory;
//@Service
//public class TheatreService {
//	
//	@Autowired
//	private TheatreReposirory theatreReposirory;
//	
//	public Theatre Createtheatre(Theatre theatre) {
//		return this.theatreReposirory.save(theatre);
//	}
//
//}
package com.theatre.ticketsBooking.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theatre.ticketsBooking.Entity.Theatre;
import com.theatre.ticketsBooking.Repository.TheatreReposirory;

@Service
public class TheatreService {

	@Autowired
	private TheatreReposirory theatreRepository;

	private List<Date> predefinedShowtimes = new ArrayList<>();

	public TheatreService() {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
			predefinedShowtimes.add(dateFormat.parse("11:30 AM"));
			predefinedShowtimes.add(dateFormat.parse("2:30 PM"));
			predefinedShowtimes.add(dateFormat.parse("6:30 PM"));
			predefinedShowtimes.add(dateFormat.parse("9:30 PM"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Theatre createTheatre(Theatre theatre) {
		Date nextShowtime = getNextAvailableShowtime();
		theatre.setShowtime(nextShowtime);
		theatre.setShowdate(new Date());
		return theatreRepository.save(theatre);
	}

	private Date getNextAvailableShowtime() {
		Date now = new Date();
		List<Date> availableShowtimes = predefinedShowtimes.stream().filter(showtime -> showtime.after(now))
				.collect(Collectors.toList());

		return availableShowtimes.isEmpty() ? null : availableShowtimes.get(0);
	}

}
