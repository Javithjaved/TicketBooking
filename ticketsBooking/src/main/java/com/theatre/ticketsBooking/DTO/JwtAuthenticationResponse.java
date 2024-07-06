package com.theatre.ticketsBooking.DTO;

public class JwtAuthenticationResponse {
	private String Token;
	private String refreshtoken;
	public JwtAuthenticationResponse() {
		
	}
	public JwtAuthenticationResponse(String token, String refreshToken) {
		 this.Token=token;
		 this.refreshtoken=refreshToken;
	}
	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		Token = token;
	}
	public String getRefreshtoken() {
		return refreshtoken;
	}
	public void setRefreshtoken(String refreshtoken) {
		this.refreshtoken = refreshtoken;
	}
	
}
