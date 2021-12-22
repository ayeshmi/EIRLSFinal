package com.example.demo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BookReservation;
import com.example.demo.model.MessageResponse;
import com.example.demo.model.Paymentdto;
import com.example.demo.model.ReservationDetails;
import com.example.demo.repository.BookReservationRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.ReservationRepository;


@RestController

@RequestMapping("/api/auth")
public class ReservationController {

	@Autowired
	BookReservationRepository bookReservationRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@PostMapping("/bookReservation")
	public ResponseEntity<?> addBookReservation(@RequestBody BookReservation bookReservation ) {
		System.out.println("CurrentDate12"+bookReservation.getEmail());
	    LocalDate localDate = LocalDate.now();
	    ReservationDetails bookReservationS  =  reservationRepository.findByUsername(bookReservation.getEmail());
	    
	   int days=bookReservationS.getBookDurationDays();
	   int numberOfBooks=bookReservationS.getNumberOfBooks();
	    
	    LocalDate newDate = localDate.plusDays(days);
	    
	   bookReservation.setDate(localDate.toString());
	    bookReservation.setReturnDate(newDate.toString());
	    
	    int count=reservationRepository.countOfBooks(bookReservation.getEmail());
	   
	    bookReservation.setLendingStatus("cart");
        
	    if(count == numberOfBooks || count >= numberOfBooks) {
	    	
	    	return ResponseEntity.ok(new MessageResponse("Your Book Lending Limitation is Exceeded!"));
	    }
	    
	    else {
	    	
	    	bookReservationRepository.save(bookReservation);
	    	return ResponseEntity.ok(new MessageResponse("Your Book Lending is Successfully Added!"));
	    }
	  //  System.out.println("Count is"+count);
	  //  return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
		//return ResponseEntity.ok(new MessageResponse("Reservation is completed"));
	}
	
	@GetMapping("/getAllCartBookReservation/{email}")
	public ResponseEntity<Object> getCartBookReseravtionDetails(@PathVariable("email") String email){
		List<BookReservation> bookReservation= bookReservationRepository.getAllCartBookReservation(email);
		return ResponseEntity.ok(bookReservation);
	}
	
	@GetMapping("/getAllOngoingBookReservation/{email}")
	public ResponseEntity<Object> getOngoingBookReseravtionDetails(@PathVariable("email") String email){
		System.out.println("Count is countuu"+email);
		List<BookReservation> bookReservation= bookReservationRepository.getAllOngoingBookReservation(email);
		return ResponseEntity.ok(bookReservation);
	}
	
	@GetMapping("/getCheckOutTotalPrice/{email}")
	public ResponseEntity<Object> checkOutDetailsForBookReservation(@PathVariable("email") String email){
		List<BookReservation> bookReservation= bookReservationRepository.getAllCartBookReservation(email);
		int size=bookReservation.size();
		ReservationDetails bookReservationS  =  reservationRepository.findByUsername(email);
		int lendingPrice=bookReservationS.getBookCharges();
		int totalCheckoutPrice=size*lendingPrice;
		System.out.println("Count is count");
		return ResponseEntity.ok(totalCheckoutPrice);
	}
	
	@PostMapping("/confirmBookCart/{email}")
	public ResponseEntity<Object> confirmBookCart(@PathVariable("email") String email,@RequestBody Paymentdto payment){
		List<BookReservation> bookReservation= bookReservationRepository.getAllCartBookReservation(email);
		System.out.println("Count is count4555"+bookReservation.size());
		for(int i=0;i<bookReservation.size();i++) {
			bookReservation.get(i).setStatus("ongoing");
			
			bookReservationRepository.save(bookReservation.get(i));
		}
		payment.setEmail(email);
		payment.setPrice(payment.getPrice());
		payment.setReason("LendingFee");
		paymentRepository.save(payment);
		
		System.out.println("Count is count4555"+payment.getPrice());
		//System.out.println("Count is count45"+price);
		return ResponseEntity.ok("Payment is added succesfully");
	}
	
	@GetMapping("/getAllBookReservation")
	public ResponseEntity<Object> getAllReservationToHandleReturns(){
		List<BookReservation> bookReservation= bookReservationRepository.viewAllBookReservation();
		ReservationDetails bookReservationS= null;
		for(int i=0;i<bookReservation.size();i++) {
			LocalDate date = LocalDate.parse(bookReservation.get(i).getReturnDate());
			 bookReservationS  =  reservationRepository.findByUsername(bookReservation.get(i).getEmail());
			Long noOfDaysBetween = ChronoUnit.DAYS.between(date,LocalDate.now());
			int days=Math.toIntExact(noOfDaysBetween);
			if(noOfDaysBetween <= 0) {
				bookReservation.get(i).setOverduePayment(0);
			}
			else {
				bookReservation.get(i).setOverduePayment(bookReservationS.getOverdur()*days);
			}
			bookReservationRepository.save(bookReservation.get(i));
			System.out.println("dddddd"+noOfDaysBetween);
		}
		
		return ResponseEntity.ok(bookReservation);
	}
	
	
	
}
