package com.telcomdms.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.telcomdms.config.JwtUtils;
import com.telcomdms.model.JwtRequest;
import com.telcomdms.model.JwtResponse;
import com.telcomdms.model.User;
import com.telcomdms.service.impl.UserDetailsServiceImpl;



@CrossOrigin("*")
@RestController
public class AuthenticateController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	// Generate Token
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		
		try {
			
			authenticate(jwtRequest.getEmailId(), jwtRequest.getPassword());
			
		} catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User not found");
		}
		
		// if Authenticated
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getEmailId());
		String token = this.jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));		
	}
	
	
	private void authenticate(String emailId, String password) throws Exception {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(emailId, password));
		} catch (DisabledException e) {
			throw new Exception("USER DISABLED "+e.getMessage());
		} catch (BadCredentialsException e) {
			throw new Exception("Invalid Credentials "+e.getMessage());
		}
		
	}
	
	// Return the details of current user who generated the token
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		return (User) this.userDetailsService.loadUserByUsername(principal.getName());
	}
}
