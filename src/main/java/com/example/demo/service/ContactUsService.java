package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.Book;
import com.example.demo.model.ContactUs;
import com.example.demo.repository.ContactUsRepository;

@Component
public class ContactUsService {
	@Autowired
	ContactUsRepository contactusRepository;
	
	
	public List<ContactUs> getAllContactUsDetails(){
		return contactusRepository.findAll();
	}
	
	public ContactUs addNewContactusDetails(ContactUs contactus) {
		return contactusRepository.save(contactus);
	}
	
	public ContactUs getContactUsDetailsById(String id){
		Long Id=Long.parseLong(id);
		ContactUs contactUs=contactusRepository.findById(Id) 
				.orElseThrow();
		return contactUs;
	}
	
	public ContactUs upadateContactUSDetails( String id, ContactUs contactDetails){
		Long Id=Long.parseLong(id);
		System.out.println("hello Ayeshmi");
		ContactUs contactUs=contactusRepository.findById(Id)
				.orElseThrow();
				
		contactUs.setAnswer(contactDetails.getAnswer());
	ContactUs updateContact=contactusRepository.save(contactUs);
		return updateContact;
	}
	
}
