package com.example.demo.service;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.MessageResponse;
import com.example.demo.model.Book;
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
		JwtResponse jwtResponse = null;
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());

			jwtResponse = new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(),
					roles, userDetails.getProfileImage(),userDetails.getAge());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jwtResponse;
	}

	public List<User> getAllUsers() {

		List<User> users = null;
		try {
			users = userRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public User uploadProfileDetails(User user1) {
		// TODO Auto-generated method stub
		return null;
	}

	public MessageResponse deleteUser(Long id) {
		MessageResponse messageResponse = null;

		try {
			User user = userRepository.findById(id).orElseThrow();
			userRepository.delete(user);
			messageResponse = new MessageResponse("User is successfully deleted!");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return messageResponse;
	}

}