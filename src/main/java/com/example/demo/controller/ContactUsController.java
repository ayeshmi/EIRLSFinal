package com.example.demo.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.model.ContactUs;
import com.example.demo.dto.MessageResponse;
import com.example.demo.repository.ContactUsRepository;
import com.example.demo.service.ContactUsService;



@RestController

@RequestMapping("/api/auth")
public class ContactUsController {
	
	@Autowired
	ContactUsRepository contactusRepository;
	
	@Autowired
	ContactUsService contactusService;

	
	@GetMapping("/allConatctUs")
	public ResponseEntity<Object> getAllContactUsDetails(){
		List<ContactUs> contacts= contactusService.getAllContactUsDetails();
		
		if(contacts.size() != 0) {
			return ResponseEntity.ok(contacts);
		}
		else {
			MessageResponse message =new MessageResponse("No Records found!");
			return ResponseEntity.badRequest().body(message);
		}
	}
	

	@PostMapping("/contactus")
	public ResponseEntity<Object> addNewContactusDetails(@RequestBody ContactUs contactus) {
MessageResponse message = contactusService.addNewContactusDetails(contactus);
		
		if(message.getMessage().equals("ContactUs is successfully added!")) {
			return ResponseEntity.ok(message);
		}
		else {
			return ResponseEntity.badRequest().body(message);
		}
	
		
		 
		
	}
	
	@GetMapping("/contactus/{id}")
	public ResponseEntity<ContactUs> getContactUsDetailsById(@PathVariable String id){
		
		ContactUs contactUs=contactusService.getContactUsDetailsById(id);
		return ResponseEntity.ok(contactUs);
	} 
	
	
	//update employee rest api
	
	@PostMapping("/contactus/{id}")
	public ResponseEntity<ContactUs> upadateEmployee(@PathVariable String id,@RequestBody ContactUs contactDetails){
		System.out.println("hello contact"+contactDetails.getAnswer());
	ContactUs updateConatctUS=contactusService.upadateContactUSDetails(id, contactDetails.getAnswer(),contactDetails.getEmail());
		return ResponseEntity.ok(updateConatctUS);
	}
	
	@DeleteMapping("/deleteContactUs/{id}")
	public ResponseEntity<?> deleteContactUs(@PathVariable Long id){
		contactusService.deleteContactUs(id);
		
		return ResponseEntity.ok(new MessageResponse("Successfully Deleted!"));
		
	}
	  
	
}
