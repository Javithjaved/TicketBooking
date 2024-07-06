package com.theatre.ticketsBooking.Config;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theatre.ticketsBooking.DTO.ErrorResponse;
import com.theatre.ticketsBooking.Service.JwtServiceImpl;
import com.theatre.ticketsBooking.Service.UserServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

import org.springframework.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	 @Autowired
	    JwtServiceImpl jwtService;
	    @Autowired
	    UserServiceImpl userService;

	    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

	    @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	            throws ServletException, IOException {
	        String authHeader = request.getHeader("Authorization");
	        logger.info("Authorization Header: {}", authHeader);

	        if (!StringUtils.hasText(authHeader) || !authHeader.startsWith("Bearer ")) {
	            logger.warn("No Bearer token found in request header");
	            filterChain.doFilter(request, response);
	            return;
	        }

	        try {
	            String jwtToken = authHeader.substring(7);
	            String username = jwtService.extractUserName(jwtToken);
	            logger.info("Extracted Username: {}", username);

	            if (StringUtils.hasLength(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
	                UserDetails userDetails = userService.loadUserByUsername(username);
	                if (jwtService.isTokenValid(jwtToken, userDetails)) {
	                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
	                            userDetails, null, userDetails.getAuthorities());
	                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	                    logger.info("Authentication successful for user: {}", username);
	                } else {
	                    logger.warn("Invalid JWT token");
	                }
	            }
	        } catch (ExpiredJwtException ex) {
	            logger.error("JWT token has expired", ex);
	            response.setStatus(HttpStatus.UNAUTHORIZED.value());
	            response.setContentType("application/json");
	            ErrorResponse errorResponse = new ErrorResponse("JWT token has expired", HttpStatus.UNAUTHORIZED.value());
	            ObjectMapper objectMapper = new ObjectMapper();
	            String jsonResponse = objectMapper.writeValueAsString(errorResponse);
	            response.getWriter().write(jsonResponse);
	            return;
	        } catch (Exception ex) {
	            logger.error("Error during JWT authentication", ex);
	            response.setStatus(HttpStatus.UNAUTHORIZED.value());
	            response.setContentType("application/json");
	            ErrorResponse errorResponse = new ErrorResponse("Invalid JWT token", HttpStatus.UNAUTHORIZED.value());
	            ObjectMapper objectMapper = new ObjectMapper();
	            String jsonResponse = objectMapper.writeValueAsString(errorResponse);
	            response.getWriter().write(jsonResponse);
	            return;
	        }
	        filterChain.doFilter(request, response);
	    }
}
