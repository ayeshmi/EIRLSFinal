package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.example.demo.dto.MessageResponse;
import com.example.demo.model.Book;
import com.example.demo.model.BookReservation;
import com.example.demo.model.ReservationDetails;
import com.example.demo.model.User;
import com.example.demo.model.Video;
import com.example.demo.model.VideoReservation;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BookReservationRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VideoRepository;
import com.example.demo.repository.VideoReservationRepository;

@Service
public class ReservationService {
	@Autowired
	BookReservationRepository bookReservationRepository;

	@Autowired
	VideoReservationRepository videoReservationRepository;

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private VideoRepository videoRepository;
	
	@Autowired
	private UserRepository userRepository;
	
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
	
	public MessageResponse addToCartVideoReservation(VideoReservation videoReservation) {
		LocalDate localDate = LocalDate.now();
		ReservationDetails bookReservationS = reservationRepository.findByUsername(videoReservation.getEmail());

		int days = bookReservationS.getVideoDurationDays();
		int numberOfBooks = bookReservationS.getNumberOfVideos();

		LocalDate newDate = localDate.plusDays(days);

		videoReservation.setDate(localDate.toString());
		videoReservation.setReturnDate(newDate.toString());

		int count = reservationRepository.countOfVideos(videoReservation.getEmail());

		videoReservation.setLendingStatus("cart");

		if (count == numberOfBooks || count >= numberOfBooks) {

			return new MessageResponse("Your Video Lending Limitation is Exceeded!");
		}

		else {
			
			Video video = videoRepository.findById((long) videoReservation.getVideoId()).orElseThrow();
		
			int numberOfCopies = video.getNumberOfCopies();
		
			int ongoingBookReservation = videoReservationRepository.countOfVideosById(videoReservation.getVideoId());
			if (numberOfCopies == ongoingBookReservation) {
				return new MessageResponse("Book is already lended, no any copies available.");
			} else {
				videoReservationRepository.save(videoReservation);
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
	
	public List<BookReservation> getAllReservationforBook(String bookId){
		List<BookReservation> bookReservation=null;
		try {
			
			 bookReservation = bookReservationRepository.getBookReservationByBookId(bookId);
		}catch(Exception e) {
			e.printStackTrace();
		}
			return bookReservation;
	}

	public List<VideoReservation> getVideoReservationByVideoId(Long id) {
		List<VideoReservation> videoReservation = videoReservationRepository.getVideoReservationByVideoId(id);
		return videoReservation;
	}
	
	
	public MessageResponse advanceBookReservation(BookReservation bookReservation) {
		MessageResponse message=null;
		try {
			LocalDate localDate = LocalDate.parse(bookReservation.getDate());
			ReservationDetails bookReservationS = reservationRepository.findByUsername(bookReservation.getEmail());

			int days = bookReservationS.getBookDurationDays();
			int numberOfBooks = bookReservationS.getNumberOfBooks();

			LocalDate newDate = localDate.plusDays(days);

			bookReservation.setDate(localDate.toString());
			bookReservation.setReturnDate(newDate.toString());

			int count = reservationRepository.countOfBooks(bookReservation.getEmail());

			bookReservation.setLendingStatus("cart");

			if (count == numberOfBooks || count >= numberOfBooks) {
                message=new MessageResponse("Your Book Lending Limitation is Exceeded!");
				return message;
			}

			else {
				long bookId = Long.parseLong(bookReservation.getBookId());
				Book book = bookRepository.findById(bookId).orElseThrow();
				// String a=book.getNumberOfCopies();
				int numberOfCopies = book.getNumberOfCopies();
				int ongoingBookReservation = bookReservationRepository.countOfBooksById(bookReservation.getBookId());
				if (numberOfCopies == ongoingBookReservation) {
					message=new MessageResponse("Book is already lended, no any copies available.");
					return message;
				} else {
					bookReservationRepository.save(bookReservation);
					message=new MessageResponse("Your Book Lending is Successfully Added!");
					return message;
				}

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return message;
		
	}
	
	public MessageResponse advanceVideoReservation(VideoReservation videoReservation) {
		MessageResponse message=null;
		try {
			System.out.println("CurrentDate1289" + videoReservation.getEmail());
			LocalDate localDate = LocalDate.parse(videoReservation.getDate());
			ReservationDetails videoReservationS = reservationRepository.findByUsername(videoReservation.getEmail());

			int days = videoReservationS.getBookDurationDays();
			int numberOfBooks = videoReservationS.getNumberOfBooks();

			LocalDate newDate = localDate.plusDays(days);

			videoReservation.setDate(localDate.toString());
			videoReservation.setReturnDate(newDate.toString());

			int count = reservationRepository.countOfBooks(videoReservation.getEmail());

			videoReservation.setLendingStatus("cart");

			if (count == numberOfBooks || count >= numberOfBooks) {
				message=new MessageResponse("Your Book Lending Limitation is Exceeded!");
				return message;
			}

			else {
				
				Video book = videoRepository.findById(videoReservation.getVideoId()).orElseThrow();
				// String a=book.getNumberOfCopies();
				int numberOfCopies = book.getNumberOfCopies();
				int ongoingBookReservation = videoReservationRepository.countOfVideosById(videoReservation.getVideoId());
				if (numberOfCopies == ongoingBookReservation) {
					message=new MessageResponse("Book is already lended, no any copies available.");
					return message;
				} else {
					
					videoReservationRepository.save(videoReservation);
					message=new MessageResponse("Your Book Lending is Successfully Added!");
					return message;
				}

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return message;

	}
	
	public List<User> viewBlackListCustomers()
	{
		List<User> users = new ArrayList<>();
		try {
			List<String> userIds = bookReservationRepository.getBlackListCutomers();
			List<Long> userIdsV = (List<Long>) videoReservationRepository.getBlackListCutomers();
			System.out.println("users " + userIds.get(1));

			
			for (int i = 1; i < userIds.size(); i++) {

				long userID = Long.parseLong(userIds.get(i));
				User user = userRepository.findById(userID).orElseThrow();
				user.setStatus("blackList");
				userRepository.save(user);
				users.add(user);

			}
			for (int i = 1; i < userIdsV.size(); i++) {
				User user = userRepository.findById(userIdsV.get(i)).orElseThrow();
				user.setStatus("blackList");
				userRepository.save(user);
				users.add(user);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		

		return users;
	}

	public List<VideoReservation> getCartVideoReseravtionDetails(String email) {
		List<VideoReservation> videoReservation=null;
		try {
			
			videoReservation = videoReservationRepository.getAllCartVideoReservation(email);
	}catch(Exception e) {
		e.printStackTrace();
	}
		return videoReservation;
	}
	
	
	
	public int checkOutDetailsForVideoReservation(String email) {
		int totalCheckoutPrice=0;
		try {
			List<VideoReservation> videoReservation = videoReservationRepository.getAllCartVideoReservation(email);
			int size = videoReservation.size();
			ReservationDetails bookReservationS = reservationRepository.findByUsername(email);
			int lendingPrice = bookReservationS.getBookCharges();
			totalCheckoutPrice = size * lendingPrice;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return totalCheckoutPrice;
	}
	
	public int checkOutDetailsForBookReservation(String email) {
		int totalCheckoutPrice=0;
		try {
			List<BookReservation> bookReservation = bookReservationRepository.getAllCartBookReservation(email);
			int size = bookReservation.size();
			ReservationDetails bookReservationS = reservationRepository.findByUsername(email);
			int lendingPrice = bookReservationS.getBookCharges();
			totalCheckoutPrice = size * lendingPrice;
			System.out.println("Count is count");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return totalCheckoutPrice;
	}
	
}
