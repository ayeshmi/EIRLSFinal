package com.example.demo.service;

import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.model.Book;
import com.example.demo.model.BookReservation;
import com.example.demo.model.ReservationDetails;
import com.example.demo.model.User;
import com.example.demo.model.Video;
import com.example.demo.model.VideoReservation;
import com.example.demo.repository.BookReservationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VideoRepository;
import com.example.demo.repository.VideoReservationRepository;

@SpringBootTest
public class ReservationServiceTest {

	@Autowired
	ReservationService reservationservice;
	
	@MockBean
	BookReservationRepository bookReservationRepository;
	
	@MockBean
	VideoReservationRepository videoReservationRepository;
	
	@MockBean
	VideoRepository videoRepository;
	
	@Test
	@DisplayName("Test findAll")
	void testBookCartReservation() {
		// Setup our mock repository
		BookReservation video1 = new BookReservation();
		BookReservation video2 = new BookReservation();
		

		doReturn(Arrays.asList(video1, video2)).when(bookReservationRepository).getAllCartBookReservation("ayeshmi17samaraa@gmail.com");

		// Execute the service call
		List<BookReservation> widgets = reservationservice.getCartBookReseravtionDetails("ayeshmi17samaraa@gmail.com");

		// Assert the response
		Assertions.assertEquals(2, widgets.size(), "findAll should return 2 widgets");
	}
	
	
	@Test
	@DisplayName("Test findAll")
	void testgetAllReservationforBook() {
		// Setup our mock repository
		BookReservation video1 = new BookReservation();
		BookReservation video2 = new BookReservation();
		

		doReturn(Arrays.asList(video1, video2)).when(bookReservationRepository).getBookReservationByBookId("a");
		// Execute the service call
		List<BookReservation> widgets = reservationservice.getAllReservationforBook("a");

		// Assert the response
		Assertions.assertEquals(2, widgets.size(), "findAll should return 2 widgets");
	}
	
	@Test
	@DisplayName("Test findAll")
	void testgetVideoReservationByVideoId() {
		// Setup our mock repository
		VideoReservation video1 = new VideoReservation();
		VideoReservation video2 = new VideoReservation();
		

		doReturn(Arrays.asList(video1, video2)).when(videoReservationRepository).getVideoReservationByVideoId(1L);
		// Execute the service call
		List<VideoReservation> widgets = reservationservice.getVideoReservationByVideoId(1L);

		// Assert the response
		Assertions.assertEquals(2, widgets.size(), "findAll should return 2 widgets");
	}
	


	
	
	@Test
	@DisplayName("Test findAll")
	void testgetReseravtionDetails() {
		// Setup our mock repository
		Video video1 = new Video();
		Video video2 = new Video();
		VideoReservation video3 = new VideoReservation();
		VideoReservation video4 = new VideoReservation();

		doReturn(Arrays.asList(video3, video4)).when(videoReservationRepository).getAllVideoOnline(1L);
		doReturn(Arrays.asList(video1, video2)).when(videoRepository).getOne(1L);
		// Execute the service call
		List<Video> widgets = reservationservice.getAllVideoReservationOnline(1L);

		// Assert the response
		Assertions.assertEquals(2, widgets.size(), "findAll should return 2 widgets");
	}
	
	
	
	@Test
	@DisplayName("Test findAll")
	void testgetCartVideoReseravtionDetails() {
		// Setup our mock repository
		VideoReservation video1 = new VideoReservation();
		VideoReservation video2 = new VideoReservation();
		

		doReturn(Arrays.asList(video1, video2)).when(videoReservationRepository).getAllCartVideoReservation("ayeshmi177samaraa@gmail.com");
		// Execute the service call
		List<VideoReservation> widgets = reservationservice.getCartVideoReseravtionDetails("ayeshmi177samaraa@gmail.com");

		// Assert the response
		Assertions.assertEquals(2, widgets.size(), "findAll should return 2 widgets");
	}
	
}
