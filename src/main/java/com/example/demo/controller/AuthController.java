package com.example.demo.controller;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.service.EmailSender;
import com.example.demo.model.User;
import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.MessageResponse;
import com.example.demo.model.Paymentdto;
import com.example.demo.model.ReservationDetails;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.SignupRequest;
import com.example.demo.model.ERole;
import com.example.demo.model.Role;
import com.example.demo.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private EmailSender emailSender;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserService userService;

	@Autowired
	private PaymentRepository paymentRepository;

	@PostMapping("/signin")
	public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		JwtResponse jwtResponse = userService.loginService(loginRequest);
		if (jwtResponse != null) {
			return ResponseEntity.ok(jwtResponse);
		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("Something went wrong, try again!"));
		}

	}

	@GetMapping("/employees")
	public List<User> getAllUser() {
		return userService.getAllUsers();
	}

	@GetMapping("/employees/{username}")
	public String getUserProfilePicture(@PathVariable String username) {
		User user = userRepository.findUserByUsername(username);
		return user.getImageOfProfile();
	}

	@GetMapping("/employees/{email}")
	public String changeProfilePicture(@PathVariable String email) {
		User user = userRepository.findUserByUsername(email);
		return user.getImageOfProfile();
	}

	@PutMapping("/updateProfileDetails")
	public ResponseEntity<User> uploadProfileDetails(@RequestParam("user") User user) {

		User user1 = userService.uploadProfileDetails(user);
		return new ResponseEntity<User>(user1, HttpStatus.OK);

	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

		System.out.println("hello");
		LocalDate localDate = LocalDate.now();
		System.out.println("Successfully Deleted" + signUpRequest.getDateOfBirth());
		System.out.println("Successfully Deleted" + localDate);

		String enteredDate = signUpRequest.getDateOfBirth();
		LocalDate date = LocalDate.parse(enteredDate);

		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}
		if (localDate.equals(date)) {
			System.out.println("Hello" + localDate);
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Entered date is invalid"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getDateOfBirth(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getUserType());

		ReservationDetails reservation = null;
		System.out.println("USerType" + signUpRequest.getUserType());
		String userType = signUpRequest.getUserType();

		if (userType.equals("Bronze")) {
			reservation = new ReservationDetails(signUpRequest.getEmail(), 3, 5, 21, 5, 50, 100, 1000, 20);
		} else if (userType.equals("Silver")) {
			// reservation = new ReservationDetails(signUpRequest.getEmail(), "5", "7",
			// "28", "7", "40", "80", "2000",
			// "15");
		} else if (userType.equals("Gold")) {
			// reservation = new ReservationDetails(signUpRequest.getEmail(), "7", "9",
			// "28", "10", "30", "60", "3000",
			// "10");
		} else {
			// reservation = new ReservationDetails(signUpRequest.getEmail(), "10", "10",
			// "35", "7", "20", "40", "5000",
			// "5");
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
		addPayment(signUpRequest.getEmail());
		emailSender.sendEmail();
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

	}

	public void addNewReservationDetails(String userType, SignupRequest signUpRequest) {
		ReservationDetails reservation = null;

		if (userType.equals("Bronze")) {
			reservation = new ReservationDetails(signUpRequest.getEmail(), 3, 5, 21, 5, 50, 100, 1000, 20);
		} else if (userType.equals("Silver")) {
			// reservation = new ReservationDetails(signUpRequest.getEmail(), "5", "7",
			// "28", "7", "40", "80", "2000",
			// "15");
		} else if (userType.equals("Gold")) {
			// reservation = new ReservationDetails(signUpRequest.getEmail(), "7", "9",
			// "28", "10", "30", "60", "3000",
			// "10");
		} else {
			// reservation = new ReservationDetails(signUpRequest.getEmail(), "10", "10",
			// "35", "7", "20", "40", "5000",
			// "5");
		}
		reservationRepository.save(reservation);
	}

	public void addPayment(String email) {

		Paymentdto payment = new Paymentdto();
		payment.setEmail(email);
		payment.setPaymentStatus("unpaid");
		payment.setReason("registrationFee");
		payment.setPrice(2000);
		paymentRepository.save(payment);
		System.out.println("payment is added");

	}

}
