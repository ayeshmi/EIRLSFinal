package com.example.demo.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.model.Book;
import com.example.demo.model.ContactUs;
import com.example.demo.model.Video;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.VideoRepository;

@SpringBootTest
public class VideoServiceTest {

	@Autowired
	VideoService videoservice;

	@MockBean
	VideoRepository videoRepository;

	@Test
	@DisplayName("Test findAll")
	void testFindAll() {
		// Setup our mock repository
		Video video1 = new Video();
		Video video2 = new Video();

		doReturn(Arrays.asList(video1, video2)).when(videoRepository).findAll();

		// Execute the service call
		List<Video> widgets = videoservice.getAllVideos();

		// Assert the response
		Assertions.assertEquals(2, widgets.size(), "findAll should return 2 widgets");
	}

	@Test
	@DisplayName("Test save")
	void testSave() {
		// Setup our mock repository
		Video video1 = new Video();
		doReturn(video1).when(videoRepository).save(any());

		// Execute the service call
		videoservice.addVideo(video1);

	}

	@Test
	@DisplayName("Test findAll")
	void testFindVideoByCategory() {
		// Setup our mock repository
		Video video1 = new Video();
		Video video2 = new Video();

		doReturn(Arrays.asList(video1, video2)).when(videoRepository).findByCategory("");

		// Execute the service call
		List<Video> widgets = videoservice.getVideosByCategory("");

		// Assert the response
		Assertions.assertEquals(2, widgets.size(), "findAll should return 2 widgets");
	}
	
	
	
	@Test
	@DisplayName("Test findAll")
	void testAdvanceBookSearch() {
		// Setup our mock repository
		Video video1 = new Video();
		Video video2 = new Video();

		doReturn(Arrays.asList(video1, video2)).when(videoRepository).search("a");

		// Execute the service call
		List<Video> widgets = videoservice.advanceSearch("a");

		// Assert the response
		Assertions.assertEquals(2, widgets.size(), "findAll should return 2 widgets");
	}



	@Test
	@DisplayName("Test findAll")
	void testViewBookByID() {
		// Setup our mock repository
		Video video1 = new Video();
		Video video2 = new Video();

		doReturn(video1).when(videoRepository).getOne(1L);

		// Execute the service call
		 videoservice.getVideoById(1L);

	}
	
}
