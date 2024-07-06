package com.theatre.ticketsBooking.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.theatre.ticketsBooking.Entity.User;
import com.theatre.ticketsBooking.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService  {
	@Autowired
	UserRepository repository;
	public UserDetails loadUserByUsername(String useremail) throws UsernameNotFoundException {
		   Optional<User> user = repository.findByEmail(useremail);
		   if(user.isEmpty()) {
			   throw new UsernameNotFoundException("User not found");
		   }
		return user.get();
	}

}
