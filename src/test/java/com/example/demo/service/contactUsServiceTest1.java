package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
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
import com.example.demo.repository.ContactUsRepository;

@SpringBootTest
class contactUsServiceTest1 {

	@Autowired
	ContactUsService contactUsService;
	
	@MockBean
	ContactUsRepository contactusRepository;
	
	 @Test
	    @DisplayName("Test findAll")
	    void testFindAll() {
	        // Setup our mock repository
	        ContactUs widget1 = new ContactUs(1l, "Widget Name", "Description", "d","d");
	        ContactUs widget2 = new ContactUs(2l, "Widget Name", "Description", "d","d");
	       
	        doReturn(Arrays.asList(widget1, widget2)).when(contactusRepository).findAll();

	        // Execute the service call
	        List<ContactUs> widgets = contactUsService.getAllContactUsDetails(); 

	        // Assert the response
	        Assertions.assertEquals(2, widgets.size(), "findAll should return 2 widgets");
	    }
	 
	 @Test
	    @DisplayName("Test save")
	    void testSave() {
	    	// Setup our mock repository
		 ContactUs contactUS = new ContactUs(1l, "Widget Name", "Description", "d","d");
	        doReturn(contactUS).when(contactusRepository).save(any());

	        // Execute the service call
	        contactUsService.addNewContactusDetails(contactUS);
	   
	        
	    }


}
