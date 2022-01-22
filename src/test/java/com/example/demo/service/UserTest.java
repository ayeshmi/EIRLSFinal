package com.example.demo.service;

import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.dto.MessageResponse;
import com.example.demo.model.ReservationDetails;
import com.example.demo.model.User;
import com.example.demo.model.Video;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.UserRepository;

@SpringBootTest
public class UserTest {
	
	@Autowired
	UserService userservice;
	
	@MockBean
	UserRepository userRepository;
	
	@Test
	@DisplayName("Test findAll")
	void testFindAll() {
		// Setup our mock repository
		User video1 = new User();
		User video2 = new User();
		

		doReturn(Arrays.asList(video1, video2)).when(userRepository).findAll();

		// Execute the service call
		List<User> widgets = userservice.getAllUsers();

		// Assert the response
		Assertions.assertEquals(2, widgets.size(), "findAll should return 2 widgets");
	}
	

	
	@Test
	@DisplayName("Test findAll")
	void testAdvanceUserSearch() {
		// Setup our mock repository
		User video1 = new User();
		User video2 = new User();
		

		doReturn(Arrays.asList(video1, video2)).when(userRepository).advanceUsersSearch("a");

		// Execute the service call
		List<User> widgets = userservice.advanceUser("a");

		// Assert the response
		Assertions.assertEquals(2, widgets.size(), "findAll should return 2 widgets");
	}
	

	

}


