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
		System.out.println("image is"+bookReservation.getImage());

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
			System.out.println("balck lsit"+userIds.size());
			List<Long> userIdsV = videoReservationRepository.getBlackListCutomers();
			System.out.println("users " + userIds.get(0));

			
			for (int i = 0; i < userIds.size(); i++) {

				long userID = Long.parseLong(userIds.get(i));
				User user = userRepository.findById(userID).orElseThrow();
				user.setStatus("blackList");
				userRepository.save(user);
				users.add(user);

			}
			for (int i = 0; i < userIdsV.size(); i++) {
				User user = userRepository.findById(userIdsV.get(i)).orElseThrow();
				user.setStatus("blackList");
				userRepository.save(user);
				
				for(int j=0;j<users.size();j++) {
					if(user.getId()!= users.get(i).getId()) {
						users.add(user);
					}else {
						System.out.println("user is already in the system");
					}
				}
				
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
	
	public List<ReservationDetails> getReseravtionDetails() {
		List<ReservationDetails> videoReservation=null;
		try {
			
			videoReservation = reservationRepository.findAll();
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

	public MessageResponse deleteBookFromCart(Long id) {
		MessageResponse message=null;
		BookReservation bookReservation = bookReservationRepository.findById(id).orElseThrow();
		bookReservationRepository.delete(bookReservation);
		message=new MessageResponse("Book is deleted from the cart.");
		return message;
	}

	public MessageResponse addBookReservationOnline(BookReservation bookReservation) {
		LocalDate localDate = LocalDate.now();
		ReservationDetails bookReservationS = reservationRepository.findByUsername(bookReservation.getEmail());

		int days = bookReservationS.getBookDurationDays();
		int numberOfBooks = bookReservationS.getNumberOfBooks();

		LocalDate newDate = localDate.plusDays(days);

		bookReservation.setDate(localDate.toString());
		bookReservation.setReturnDate(newDate.toString());

		int count = reservationRepository.countOfBooks(bookReservation.getEmail());

		bookReservation.setLendingStatus("cart");
		bookReservation.setUsername("online");
		System.out.println("image is"+bookReservation.getImage());

		if (count == numberOfBooks || count >= numberOfBooks) {

			return new MessageResponse("Your Book Lending Limitation is Exceeded!");
		}

		else {
			
				bookReservationRepository.save(bookReservation);
				return new MessageResponse("Your Online Book Lending is Successfully Added to Cart!");
			
	}
	}

	public List<Book> getAllBookReservationOnline(Long id) {
		List<Book> books=new ArrayList<>();
		List<BookReservation> bookReservation = bookReservationRepository.getAllBookOnline(id);
		for(int i=0;i<bookReservation.size();i++) {
			Long id1=Long.parseLong(bookReservation.get(i).getBookId());
			
			Book book = bookRepository.findById(id1).orElseThrow();	
			System.out.println("ahjfaf"+book);
			books.add(book);
		}
		
		return books;
	}
	
	public List<Video> getAllVideoReservationOnline(Long id) {
		List<Video> videos=new ArrayList<>();
		List<VideoReservation> videoReservation = videoReservationRepository.getAllVideoOnline(id);
		for(int i=0;i<videoReservation.size();i++) {
			
			Video video = videoRepository.findById(videoReservation.get(i).getVideoId()).orElseThrow();	
			System.out.println("ahjfaf"+video);
			videos.add(video);
		}
		
		return videos;
	}

	public MessageResponse addvideoReservationOnline(VideoReservation videoReservation) {
		LocalDate localDate = LocalDate.now();
		ReservationDetails bookReservationS = reservationRepository.findByUsername(videoReservation.getEmail());

		int days = bookReservationS.getBookDurationDays();
		int numberOfBooks = bookReservationS.getNumberOfBooks();

		LocalDate newDate = localDate.plusDays(days);

		videoReservation.setDate(localDate.toString());
		videoReservation.setReturnDate(newDate.toString());

		int count = reservationRepository.countOfVideos(videoReservation.getEmail());

		videoReservation.setLendingStatus("cart");
		videoReservation.setUsername("online");
		System.out.println("image is"+videoReservation.getImage());

		if (count == numberOfBooks || count >= numberOfBooks) {

			return new MessageResponse("Your Book Lending Limitation is Exceeded!");
		}

		else {
			
				videoReservationRepository.save(videoReservation);
				return new MessageResponse("Your video Lending is Successfully Added!");
			
	}
	}

	public MessageResponse addBookOrder(BookReservation bookReservation) {
		bookReservation.setUsername("order");
		bookReservationRepository.save(bookReservation);
		return new MessageResponse("Order is added!"); 
	}

	public List<Book> getOrderBooks(Long id) {
		List<Book> books=new ArrayList<>();
		List<BookReservation> bookReservation = bookReservationRepository.getAllOrderBooks(id);
		for(int i=0;i<bookReservation.size();i++) {
			Long id1=Long.parseLong(bookReservation.get(i).getBookId());
			
			Book book = bookRepository.findById(id1).orElseThrow();	
			System.out.println("ahjfaf"+book);
			books.add(book);
		}
		
		return books;
	
	}

	
	
	
}
