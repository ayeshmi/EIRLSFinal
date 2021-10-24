package com.example.demo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.BookRepository;
import com.example.demo.model.BookReservation;
import com.example.demo.repository.BookReservationRepository;
import com.example.demo.model.ContactUs;
import com.example.demo.model.MessageResponse;

@RestController

@RequestMapping("/api/auth")
public class ReservationController {

	@Autowired
	BookReservationRepository bookReservationRepository;
	
	@PostMapping("/bookReservation")
	public BookReservation createEmployee(@RequestBody BookReservation bookReservation) {
		System.out.println("Ayeshmi Book reservation"+bookReservation.getUsername());
		Date date = new Date();
	    String strDateFormat = "hh:mm:ss a";
	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat); 
	    String formattedDate= dateFormat.format(date);
	    System.out.println("Current time of the day using Date - 12 hour format: " + formattedDate);
		return bookReservationRepository.save(bookReservation);
		//return ResponseEntity.ok(new MessageResponse("Reservation is completed"));
	}
	
	
	
}
