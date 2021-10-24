package com.example.demo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.repository.UserRepository;

import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.ContactUsRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.EmailSender;
import com.example.demo.service.JwtUtils;
import com.example.demo.model.UserDetailsImpl;
import com.example.demo.model.User;
import com.example.demo.model.JwtResponse;
import com.example.demo.model.Book;
import com.example.demo.model.ContactUs;
import com.example.demo.model.MessageResponse;
import com.example.demo.model.ReservationDetails;
import com.example.demo.model.LoginRequest;
import com.example.demo.model.SignupRequest;
import com.example.demo.model.ERole;
import com.example.demo.model.Role;
import com.example.demo.service.UserService;
import com.example.demo.service.BookService;
import com.example.demo.service.ContactUsService;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;
		
	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	EmailSender emailSender;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	UserService userService;


	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

     JwtResponse jwtResponse= userService.loginService(loginRequest);
		return ResponseEntity.ok(jwtResponse);
	}
	
	
	@GetMapping("/employees")
	public List<User> getAllUser(){
		return userService.getAllUsers();
	}

	
	
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		System.out.println("hello");
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
				 signUpRequest.getEmail(),
				 signUpRequest.getDateOfBirth(),
				 encoder.encode(signUpRequest.getPassword()),
				 signUpRequest.getUserType());
			
		String username=signUpRequest.getUsername();
		ReservationDetails reservation;
		
		if(signUpRequest.getUserType()=="Bronze") {
			 reservation=new ReservationDetails(signUpRequest.getUsername(),"3","5","21","5","50","100","1000","20");
		}
		else if(signUpRequest.getUserType()=="Silver") {
			 reservation=new ReservationDetails(signUpRequest.getUsername(),"5","7","28","7","40","80","2000","15");
		}
		else if(signUpRequest.getUserType()=="Gold") {
			 reservation=new ReservationDetails(signUpRequest.getUsername(),"7","9","28","10","30","60","3000","10");
		}
		else {
			 reservation=new ReservationDetails(signUpRequest.getUsername(),"10","10","35","7","20","40","5000","5");
		}
		reservationRepository.save(reservation);
        
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole); 
				}
			});
			
		}

		user.setRoles(roles);
		userRepository.save(user);
		//emailSender=new EmailSender();
		emailSender.sendEmail();
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
		
	}

	
	
	
		
	
		
	
	
}
