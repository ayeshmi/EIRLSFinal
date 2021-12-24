package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Book;
import com.example.demo.model.BookReservation;
import com.example.demo.model.MessageResponse;
import com.example.demo.model.Paymentdto;
import com.example.demo.model.ReservationDetails;
import com.example.demo.model.User;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BookReservationRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.UserRepository;


@RestController

@RequestMapping("/api/auth")
public class ReservationController {

	@Autowired
	BookReservationRepository bookReservationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
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
	    	long bookId=Long.parseLong(bookReservation.getBookId());
	    	Book book=bookRepository.findById(bookId).orElseThrow();
	    	String a=book.getNumberOfCopies();
	    	int numberOfCopies=Integer.parseInt(a);
	    	int ongoingBookReservation=bookReservationRepository.countOfBooksById(bookReservation.getBookId());
	    	if(numberOfCopies==ongoingBookReservation) {
	    		return ResponseEntity.ok(new MessageResponse("Book is already lended, no any copies available."));
	    	}
	    	else {
	    		bookReservationRepository.save(bookReservation);
		    	return ResponseEntity.ok(new MessageResponse("Your Book Lending is Successfully Added!"));
	    	}
	    	
	    }
	  //  System.out.println("Count is"+count);
	  //  return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
		//return ResponseEntity.ok(new MessageResponse("Reservation is completed"));
	}
	
	@PostMapping("/advanceBookReservation12")
	public ResponseEntity<?> advanceBookReservation(@RequestBody BookReservation bookReservation ) {
		System.out.println("CurrentDate1289"+bookReservation.getEmail());
	    LocalDate localDate = LocalDate.parse(bookReservation.getDate());
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
	    	long bookId=Long.parseLong(bookReservation.getBookId());
	    	Book book=bookRepository.findById(bookId).orElseThrow();
	    	String a=book.getNumberOfCopies();
	    	int numberOfCopies=Integer.parseInt(a);
	    	int ongoingBookReservation=bookReservationRepository.countOfBooksById(bookReservation.getBookId());
	    	if(numberOfCopies==ongoingBookReservation) {
	    		return ResponseEntity.ok(new MessageResponse("Book is already lended, no any copies available."));
	    	}
	    	else {
	    		bookReservationRepository.save(bookReservation);
		    	return ResponseEntity.ok(new MessageResponse("Your Book Lending is Successfully Added!"));
	    	}
	    	
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
		payment.setPaymentStatus("unpaid");
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
	
	@GetMapping("/getBookReservationById/{id}")
	public ResponseEntity<Object> getBookReservationById(@PathVariable("id")String id){
		List<BookReservation> bookReservation= bookReservationRepository.getBookReservationByBookId(id);
		
		return ResponseEntity.ok(bookReservation);
	}
	
	@GetMapping("/viewBlackListCustomers")
	public ResponseEntity<Object> viewBlackListCustomers(){
		List<String> userIds= bookReservationRepository.getBlackListCutomers();
		System.out.println("users "+userIds.get(1));
		
		List<User> users=new ArrayList<>();
		for(int i=1;i<userIds.size();i++) {
			
			long userID = Long.parseLong(userIds.get(i));
			User user=userRepository.findById(userID).orElseThrow();
			
			user.setStatus("blackList");
			userRepository.save(user);
			
			users.add(user);
			
		}
		
		return ResponseEntity.ok(users);
	}
	
	
	
	
	
}
 