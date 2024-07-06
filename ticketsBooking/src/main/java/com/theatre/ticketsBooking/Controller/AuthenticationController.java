package com.theatre.ticketsBooking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.theatre.ticketsBooking.DTO.JwtAuthenticationResponse;
import com.theatre.ticketsBooking.DTO.RefreshtokenRequest;
import com.theatre.ticketsBooking.DTO.SignInRequest;
import com.theatre.ticketsBooking.DTO.SignUpRequest;
import com.theatre.ticketsBooking.Entity.User;
import com.theatre.ticketsBooking.Enum.Role;
import com.theatre.ticketsBooking.Service.AuthenticationServiceImpl;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
	@Autowired
	private AuthenticationServiceImpl authenticationService;

	@PostMapping("/signup/user")
	public User CreateUser(@RequestBody SignUpRequest request) {
		return this.authenticationService.SignUp(request, Role.USER);
	}

	@PostMapping("/signup/admin")
	public User CreateAdmin(@RequestBody SignUpRequest request) {
		return this.authenticationService.SignUp(request, Role.ADMIN);
	}

	@PostMapping("/signup/superadmin")
	public User CreateSuperadmin(@RequestBody SignUpRequest request) {
		return this.authenticationService.SignUpSuperAdmin(request, Role.SUPERADMIN);
	}

	@PostMapping("/login")
	public JwtAuthenticationResponse login(@RequestBody SignInRequest signInRequest) {
		return this.authenticationService.signIn(signInRequest);
	}

	@PostMapping("/refresh")
	public JwtAuthenticationResponse refresh(@RequestBody RefreshtokenRequest refreshTokenRequest) {
		return this.authenticationService.refreshToken(refreshTokenRequest);
	}
}
