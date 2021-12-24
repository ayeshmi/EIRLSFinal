package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.MessageResponse;
import com.example.demo.model.Book;
import com.example.demo.model.ContactUs;
import com.example.demo.repository.ContactUsRepository;

@Component
public class ContactUsService {
	@Autowired
	ContactUsRepository contactusRepository;
	
	@Autowired
	EmailSender emailSender;
	
	
	public List<ContactUs> getAllContactUsDetails(){
		
		List<ContactUs> requests=null;
		try {
			requests=contactusRepository.findAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return requests;
	}
	
	public MessageResponse addNewContactusDetails(ContactUs contactus) {
		try {
			contactusRepository.save(contactus);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new MessageResponse("ContactUs is successfully added!");
	}
	
	public ContactUs getContactUsDetailsById(String id){
		Long Id=Long.parseLong(id);
		ContactUs contactUs=contactusRepository.findById(Id) 
				.orElseThrow();
		return contactUs;
	}
	
	public ContactUs upadateContactUSDetails( String id, String answer,String email){
		Long Id=Long.parseLong(id);
		System.out.println("hello Ayeshmi");
		ContactUs contactUs=contactusRepository.findById(Id)
				.orElseThrow();
				
		contactUs.setAnswer(answer);
	ContactUs updateContact=contactusRepository.save(contactUs);
	emailSender.sendEmailContactUs(email, answer);
		return updateContact;
	}
	
	public ResponseEntity<Map<String,Boolean>> deleteContactUs(Long id){
		ContactUs contactUs=contactusRepository.findById(id)
				.orElseThrow();
		contactusRepository.delete(contactUs);
		Map<String,Boolean> response=new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
