package com.example.demo.service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import com.example.demo.model.ERole;
import com.example.demo.model.JwtResponse;
import com.example.demo.model.LoginRequest;
import com.example.demo.model.MessageResponse;
import com.example.demo.model.ReservationDetails;
import com.example.demo.model.Role;
import com.example.demo.model.SignupRequest;
import com.example.demo.model.User;
import com.example.demo.model.UserDetailsImpl;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.ReservationRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.demo.repository.RoleRepository;

@Service
public class UserService {
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	EmailSender emailSender;
	
	public JwtResponse loginService(LoginRequest loginRequest) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
System.out.println("Hello");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		JwtResponse jwtResponse=new JwtResponse(jwt, 
				 userDetails.getId(), 
				 userDetails.getUsername(), 
				 userDetails.getEmail(), 
				 
				 roles);
		
		return jwtResponse;
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	

	
	
}