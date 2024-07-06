package com.theatre.ticketsBooking.DTO;

public class ErrorResponse {
	 private int statusCode;
	    private String message;

	    public ErrorResponse(String message, int StatusCode) {
	        this.statusCode = StatusCode;
	        this.message = message;
	    }
	    public int getStatusCode() {
	        return statusCode;
	    }

	    public void setStatusCode(int statusCode) {
	        this.statusCode = statusCode;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }
}
