package com.theatre.ticketsBooking.exception;

public class EmailNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public EmailNotFoundException(String message) {
		super(message);
	}
}
