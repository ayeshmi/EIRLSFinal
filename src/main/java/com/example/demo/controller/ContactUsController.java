package com.example.demo.controller;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


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
import com.example.demo.model.MessageResponse;
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
	public List<ContactUs> getAllContactUsDetails(){
		return contactusService.getAllContactUsDetails();
	}
	

	//create contactus details rest api
	@PostMapping("/contactus")
	public ResponseEntity<?> addNewContactusDetails(@RequestBody ContactUs contactus) {
		
		String dateBeforeString = "2017-05-24";
		String dateAfterString = "2017-07-29";
			
		//Parsing the date
		LocalDate dateBefore = LocalDate.parse(dateBeforeString);
		LocalDate dateAfter = LocalDate.parse(dateAfterString);
		//calculating number of days in between
		long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
			
		//displaying the number of days
		System.out.println(noOfDaysBetween);
		
		 contactusService.addNewContactusDetails(contactus);
		return ResponseEntity.ok(new MessageResponse("Contact Details Succesfully Sent!"));
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
