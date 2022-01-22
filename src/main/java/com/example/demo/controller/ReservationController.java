package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.model.BookReservation;
import com.example.demo.dto.MessageResponse;
import com.example.demo.model.Paymentdto;
import com.example.demo.model.ReservationDetails;
import com.example.demo.model.User;
import com.example.demo.model.Video;
import com.example.demo.model.Book;
import com.example.demo.model.VideoReservation;
import com.example.demo.repository.BookReservationRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.VideoReservationRepository;
import com.example.demo.service.EmailSender;
import com.example.demo.service.ReservationService;

@RestController

@RequestMapping("/api/auth")
public class ReservationController {

	@Autowired
	BookReservationRepository bookReservationRepository;
	
	@Autowired
	VideoReservationRepository videoReservationRepository;

	@Autowired
	ReservationService reservationService;
	
	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private EmailSender emailSender;


	@PostMapping("/bookReservation")
	public ResponseEntity<Object> addBookReservation(@RequestBody BookReservation bookReservation) {
		MessageResponse message = reservationService.addToCartBookReservation(bookReservation);
		if (message.getMessage().equals("Your Book Lending is Successfully Added!")) {
			return ResponseEntity.ok(message);
		} else {
			return ResponseEntity.badRequest().body(message);
		}
	}
	
	@PostMapping("/bookOrder")
	public ResponseEntity<Object> addBookOrder(@RequestBody BookReservation bookReservation) {
		System.out.println("order called");
		MessageResponse message = reservationService.addBookOrder(bookReservation);
		if (message.getMessage().equals("Order is added!")) {
			return ResponseEntity.ok(message);
		} else {
			return ResponseEntity.badRequest().body(message);
		}
	}
	
	@PostMapping("/onlineBookReservation")
	public ResponseEntity<Object> addBookReservationOnline(@RequestBody BookReservation bookReservation) {
		MessageResponse message = reservationService.addBookReservationOnline(bookReservation);
		if (message.getMessage().equals("Your Online Book Lending is Successfully Added to Cart!")) {
			return ResponseEntity.ok(message);
		} else {
			return ResponseEntity.badRequest().body(message);
		}
	}
	
	@PostMapping("/onlineVideoReservation")
	public ResponseEntity<Object> addVideoReservationOnline(@RequestBody VideoReservation videoReservation) {
		MessageResponse message = reservationService.addvideoReservationOnline(videoReservation);
		if (message.getMessage().equals("Your video Lending is Successfully Added!")) {
			return ResponseEntity.ok(message);
		} else {
			return ResponseEntity.badRequest().body(message);
		}
	}
	
	@PostMapping("/videoReservation")
	public ResponseEntity<Object> addVideoReservation(@RequestBody VideoReservation videoReservation) {
		MessageResponse message = reservationService.addToCartVideoReservation(videoReservation);
		if (message.getMessage().equals("Your Video Lending is Successfully Added!")) {
			return ResponseEntity.ok(message);
		} else {
			return ResponseEntity.badRequest().body(message);
		}

	}

	@PostMapping("/advanceBookReservation12")
	public ResponseEntity<Object> advanceBookReservation(@RequestBody BookReservation bookReservation) {
		
		MessageResponse message=reservationService.advanceBookReservation(bookReservation);
		if (message.getMessage().equals("Your Book Lending is Successfully Added!")) {
			return ResponseEntity.ok(message);
		} else {
			return ResponseEntity.badRequest().body(message);
		}

	}
	
	@PostMapping("/advanceVideoReservation")
	public ResponseEntity<Object> advanceVideoReservation(@RequestBody VideoReservation videoReservation) {
		MessageResponse message=reservationService.advanceVideoReservation( videoReservation);
		if (message.getMessage().equals("Your Video Lending is Successfully Added!")) {
			return ResponseEntity.ok(message);
		} else {
			return ResponseEntity.badRequest().body(message);
		}
	}

	@GetMapping("/getAllCartBookReservation/{email}")
	public ResponseEntity<Object> getCartBookReseravtionDetails(@PathVariable("email") String email) {
		List<BookReservation> bookReservation = reservationService.getCartBookReseravtionDetails(email);
		if (bookReservation.size() != 0) {
			return ResponseEntity.ok(bookReservation);
		} else {
			MessageResponse message = new MessageResponse("No Records found!");
			return ResponseEntity.badRequest().body(message);
		}
		
	}
	
	@GetMapping("/getAllCartVideoReservation/{email}")
	public ResponseEntity<Object> getCartVideoReseravtionDetails(@PathVariable("email") String email) {
		List<VideoReservation> videoReservation = reservationService.getCartVideoReseravtionDetails(email);
		if (videoReservation.size() != 0) {
			return ResponseEntity.ok(videoReservation);
		} else {
			MessageResponse message = new MessageResponse("No Records found!");
			return ResponseEntity.badRequest().body(message);
		}
		
	}

	@GetMapping("/getAllOngoingBookReservation/{email}")
	public ResponseEntity<Object> getOngoingBookReseravtionDetails(@PathVariable("email") String email) {
		System.out.println("Count is countuu" + email);
		List<BookReservation> bookReservation = bookReservationRepository.getAllOngoingBookReservation(email);
		System.out.println("Count is countuu" + bookReservation);
		return ResponseEntity.ok(bookReservation);
	}
	
	@GetMapping("/getAllOngoingVideoReservation/{email}")
	public ResponseEntity<Object> getOngoingVideoReseravtionDetails(@PathVariable("email") String email) {
		System.out.println("Count is countuu" + email);
		List<VideoReservation> videoReservation = videoReservationRepository.getAllOngoingVideoReservation(email);
		return ResponseEntity.ok(videoReservation);
	}

	@GetMapping("/getCheckOutTotalPrice/{email}")
	public ResponseEntity<Object> checkOutDetailsForBookReservation(@PathVariable("email") String email) {
		int totalPrice=reservationService.checkOutDetailsForBookReservation(email);
		return ResponseEntity.ok(totalPrice);
	}
	
	@GetMapping("/getCheckOutTotalPriceVideo/{email}")
	public ResponseEntity<Object> checkOutDetailsForVideoReservation(@PathVariable("email") String email) {
		int totalPrice=reservationService.checkOutDetailsForVideoReservation(email);
		return ResponseEntity.ok(totalPrice);
	}

	@PostMapping("/confirmBookCart/{email}")
	public ResponseEntity<Object> confirmBookCart(@PathVariable("email") String email,
			@RequestBody Paymentdto payment) {
		List<BookReservation> bookReservation = bookReservationRepository.getAllCartBookReservation(email);
		System.out.println("Count is count4555" + bookReservation.size());
		for (int i = 0; i < bookReservation.size(); i++) {
			bookReservation.get(i).setStatus("ongoing");

			bookReservationRepository.save(bookReservation.get(i));
		}
		payment.setEmail(email);
		payment.setPrice(payment.getPrice());
		payment.setReason("LendingFee");
		payment.setPaymentStatus("unpaid");
		paymentRepository.save(payment);
		emailSender.sendEmail();
		System.out.println("Count is count4555" + payment.getPrice());
		// System.out.println("Count is count45"+price);
		return ResponseEntity.ok("Payment is added succesfully");
	}
	
	@PostMapping("/confirmVideoCart/{email}")
	public ResponseEntity<Object> confirmVideoCart(@PathVariable("email") String email,
			@RequestBody Paymentdto payment) {
		
		System.out.println("Count is count4555ddddddddd" );
		List<VideoReservation> videoReservation = videoReservationRepository.getAllCartVideoReservation(email);
		
		for (int i = 0; i < videoReservation.size(); i++) {
			videoReservation.get(i).setStatus("ongoing");

			videoReservationRepository.save(videoReservation.get(i));
		}
		payment.setEmail(email);
		payment.setPrice(payment.getPrice());
		payment.setReason("LendingFee");
		payment.setPaymentStatus("unpaid");
		paymentRepository.save(payment);
		emailSender.sendEmail();
		
		// System.out.println("Count is count45"+price);
		return ResponseEntity.ok("Payment is added succesfully");
	}

	@GetMapping("/getAllBookReservation")
	public ResponseEntity<Object> getAllReservationToHandleReturns() {
		List<BookReservation> bookReservation = bookReservationRepository.viewAllBookReservation();
		ReservationDetails bookReservationS = null;
		for (int i = 0; i < bookReservation.size(); i++) {
			LocalDate date = LocalDate.parse(bookReservation.get(i).getReturnDate());
			bookReservationS = reservationRepository.findByUsername(bookReservation.get(i).getEmail());
			Long noOfDaysBetween = ChronoUnit.DAYS.between(date, LocalDate.now());
			int days = Math.toIntExact(noOfDaysBetween);
			if (noOfDaysBetween <= 0) {
				bookReservation.get(i).setOverduePayment(0);
			} else {
				bookReservation.get(i).setOverduePayment(bookReservationS.getOverdur() * days);
			}
			bookReservationRepository.save(bookReservation.get(i));
			System.out.println("dddddd" + noOfDaysBetween);
		}

		return ResponseEntity.ok(bookReservation);
	}

	@GetMapping("/getBookReservationById/{id}")
	public ResponseEntity<Object> getBookReservationById(@PathVariable("id") String id) {
		System.out.println("dsdsds"+id);
		List<BookReservation> bookReservation = bookReservationRepository.getBookReservationByBookId(id);

		return ResponseEntity.ok(bookReservation);
	}
	
	@GetMapping("/getVideoReservationById/{id}")
	public ResponseEntity<Object> getVideoReservationById(@PathVariable("id") Long id) {
		System.out.println("dsdsdsee"+id);
		List<VideoReservation> videoReservation = reservationService.getVideoReservationByVideoId(id);

		return ResponseEntity.ok(videoReservation);
	}

	@GetMapping("/viewBlackListCustomers")
	public ResponseEntity<Object> viewBlackListCustomers() {
		
		System.out.println("called12356");
		List<User> users=reservationService.viewBlackListCustomers();
		
		if(users.size() != 0) {
			return ResponseEntity.ok(users);	
		}else {
			MessageResponse message =new MessageResponse("No Records found!");
			return ResponseEntity.badRequest().body(message);
		}
	}
	
	@GetMapping("/addBookOrder")
	public ResponseEntity<Object> addBookOrder(@RequestBody Paymentdto paymentdto) {
		List<User> users=reservationService.viewBlackListCustomers();
		if(users.size() != 0) {
			return ResponseEntity.ok(users);	
		}else {
			MessageResponse message =new MessageResponse("No Records found!");
			return ResponseEntity.badRequest().body(message);
		}
	}
	
	@GetMapping("/viewAllReservationDetails")
	public ResponseEntity<Object> viewAllReservationDetails() {
		List<ReservationDetails> users=reservationService.getReseravtionDetails();
		if(users.size() != 0) {
			return ResponseEntity.ok(users);	
		}else {
			MessageResponse message =new MessageResponse("No Records found!");
			return ResponseEntity.badRequest().body(message);
		}
	}
	
	@GetMapping("/getAllBookReservationOnline/{id}")
	public ResponseEntity<Object> getAllBookReservationOnline(@PathVariable("id") Long id) {
		List<Book> users=reservationService.getAllBookReservationOnline(id);
		if(users.size() != 0) {
			return ResponseEntity.ok(users);	
		}else {
			MessageResponse message =new MessageResponse("No Records found!");
			return ResponseEntity.badRequest().body(message);
		}
	}
	
	@GetMapping("/getAllVideoReservationOnline/{id}")
	public ResponseEntity<Object> getAllVideoReservationOnline(@PathVariable("id") Long id) {
		List<Video> videos=reservationService.getAllVideoReservationOnline(id);
		if(videos.size() != 0) {
			return ResponseEntity.ok(videos);	
		}else {
			MessageResponse message =new MessageResponse("No Records found!");
			return ResponseEntity.badRequest().body(message);
		}
	}
	
	@DeleteMapping("/deleteBookFromCart/{id}")
	public ResponseEntity<Object> deleteBookFromCart(@PathVariable("id") Long id) {
		System.out.println("dsdsdsd"+id);
		MessageResponse users=reservationService.deleteBookFromCart(id);
		if(users != null) {
			return ResponseEntity.ok(users);	
		}else {
			MessageResponse message =new MessageResponse("No Records found!");
			return ResponseEntity.badRequest().body(message);
		}
	}
	
	@GetMapping("/getOrderBooks/{id}")
	public ResponseEntity<Object> getOrderBooks(@PathVariable("id") Long id) {
		System.out.println("order books called");
		List<Book> videos=reservationService.getOrderBooks(id);
		if(videos.size() != 0) {
			return ResponseEntity.ok(videos);	
		}else {
			MessageResponse message =new MessageResponse("No Records found!");
			return ResponseEntity.badRequest().body(message);
		}
	}
	
	
	
	

}
