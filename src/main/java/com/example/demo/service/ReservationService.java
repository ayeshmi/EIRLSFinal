package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MessageResponse;
import com.example.demo.model.Book;
import com.example.demo.model.BookReservation;
import com.example.demo.model.ReservationDetails;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BookReservationRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.UserRepository;

@Service
public class ReservationService {
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
	
	public MessageResponse addToCartBookReservation(BookReservation bookReservation) {
		LocalDate localDate = LocalDate.now();
		ReservationDetails bookReservationS = reservationRepository.findByUsername(bookReservation.getEmail());

		int days = bookReservationS.getBookDurationDays();
		int numberOfBooks = bookReservationS.getNumberOfBooks();

		LocalDate newDate = localDate.plusDays(days);

		bookReservation.setDate(localDate.toString());
		bookReservation.setReturnDate(newDate.toString());

		int count = reservationRepository.countOfBooks(bookReservation.getEmail());

		bookReservation.setLendingStatus("cart");

		if (count == numberOfBooks || count >= numberOfBooks) {

			return new MessageResponse("Your Book Lending Limitation is Exceeded!");
		}

		else {
			long bookId = Long.parseLong(bookReservation.getBookId());
			Book book = bookRepository.findById(bookId).orElseThrow();
		
			int numberOfCopies = book.getNumberOfCopies();
			int ongoingBookReservation = bookReservationRepository.countOfBooksById(bookReservation.getBookId());
			if (numberOfCopies == ongoingBookReservation) {
				return new MessageResponse("Book is already lended, no any copies available.");
			} else {
				bookReservationRepository.save(bookReservation);
				return new MessageResponse("Your Book Lending is Successfully Added!");
			}

		}
		
		
	}
	
	public List<BookReservation> getCartBookReseravtionDetails(String email) {
		List<BookReservation> bookReservation=null;
		try {
			
		 bookReservation = bookReservationRepository.getAllCartBookReservation(email);
	}catch(Exception e) {
		e.printStackTrace();
	}
		return bookReservation;
	}
}
