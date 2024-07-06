package com.theatre.ticketsBooking.Service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.theatre.ticketsBooking.DTO.JwtAuthenticationResponse;
import com.theatre.ticketsBooking.DTO.RefreshtokenRequest;
import com.theatre.ticketsBooking.DTO.SignInRequest;
import com.theatre.ticketsBooking.DTO.SignUpRequest;
import com.theatre.ticketsBooking.Entity.User;
import com.theatre.ticketsBooking.Enum.Role;
import com.theatre.ticketsBooking.Repository.UserRepository;
import com.theatre.ticketsBooking.exception.EmailNotFoundException;
import com.theatre.ticketsBooking.exception.InvalidCredentialsException;
import org.springframework.security.core.AuthenticationException;
@Service
public class AuthenticationServiceImpl {
	@Autowired
	private UserRepository repository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtServiceImpl jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	  public User SignUp(SignUpRequest request, Role role) {
	        if (request.getPassword() == null || request.getEmail().isEmpty() || request.getEmail() == null || request.getPassword().isEmpty()) {
	            throw new InvalidCredentialsException("Please enter username and password");
	        }

	        Optional<User> existingUser = repository.findByEmail(request.getEmail());
	        if (existingUser.isPresent()) {
	            if (existingUser.get().getRole() == role) {
	                throw new InvalidCredentialsException("User with this email already exists");
	            } else {
	                throw new InvalidCredentialsException("Email already registered with a different role");
	            }
	        }
	        User user = new User();
	        user.setEmail(request.getEmail());
	        user.setPassword(encoder.encode(request.getPassword())); 
	        user.setRole(role);

	        return repository.save(user); 
	    }

	public User SignUpSuperAdmin(SignUpRequest request, Role superadmin) {
		return SignUp(request, Role.SUPERADMIN);
	}
	public User SignupAdmin(SignUpRequest request,Role Admin) {
		return SignUp(request, Role.ADMIN);
	}

	public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {
		if(signInRequest.getEmail().isEmpty() || signInRequest ==null || signInRequest.getPassword().isEmpty() || signInRequest.getPassword()==null) {
			throw new InvalidCredentialsException("Please enter username and password");
		}
		User user= this.repository.findByEmail(signInRequest.getEmail()).orElseThrow(() -> new EmailNotFoundException("Invalid email"));
		try {
			authenticationManager
			.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
		}catch (AuthenticationException e) {
			throw new InvalidCredentialsException("Invalid password");
		}
		String jwtToken = jwtService.generateAccessToken(user);
		String refreshToken = jwtService.generateRefreshToken(user);
		return new JwtAuthenticationResponse(jwtToken, refreshToken);
	}

	public JwtAuthenticationResponse refreshToken(RefreshtokenRequest refreshTokenRequest) {
		String userEmail = jwtService.extractUserName(refreshTokenRequest.getRefreshtoken());
		User user = this.repository.findByEmail(userEmail)
				.orElseThrow(() -> new EmailNotFoundException("User not found"));
		if (jwtService.isTokenValid(refreshTokenRequest.getRefreshtoken(), user)) {
			String jwt = jwtService.generateAccessToken(user);

			JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
			jwtAuthenticationResponse.setToken(jwt);
			jwtAuthenticationResponse.setRefreshtoken(refreshTokenRequest.getRefreshtoken());

			return jwtAuthenticationResponse;
		}
		return null;
	}
}
