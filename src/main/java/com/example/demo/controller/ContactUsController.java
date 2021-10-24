package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ContactUs;
import com.example.demo.repository.ContactUsRepository;
import com.example.demo.service.BookService;
import com.example.demo.service.ContactUsService;

@RestController

@RequestMapping("/api/auth")
public class ContactUsController {
	
	@Autowired
	ContactUsRepository contactusRepository;
	
	@Autowired
	ContactUsService contactusService;
	
	@GetMapping("/allConatctUs")
	public List<ContactUs> getAllContactUsDetails(){
		return contactusService.getAllContactUsDetails();
	}
	

	//create contactus details rest api
	@PostMapping("/contactus")
	public ContactUs addNewContactusDetails(@RequestBody ContactUs contactus) {
		return contactusService.addNewContactusDetails(contactus);
	}
	
	@GetMapping("/contactus/{id}")
	public ResponseEntity<ContactUs> getContactUsDetailsById(@PathVariable String id){
		Long Id=Long.parseLong(id);
		ContactUs contactUs=contactusService.getContactUsDetailsById(id);
		return ResponseEntity.ok(contactUs);
	} 
	
	
	//update employee rest api
	
	@PostMapping("/contactus/{id}")
	public ResponseEntity<ContactUs> upadateEmployee(@PathVariable String id,@RequestBody ContactUs contactDetails){
		
	ContactUs updateConatctUS=contactusService.upadateContactUSDetails(id, contactDetails);
		return ResponseEntity.ok(updateConatctUS);
	}
	
}
