package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Book;
import com.example.demo.model.ContactUs;
import com.example.demo.model.Video;
import com.example.demo.repository.VideoRepository;

@RestController

@RequestMapping("/api/auth")
public class VideoController {

	@Autowired
	VideoRepository videoRepository;
	
	@GetMapping("/AllVideos")
	public List<Video> getAllContactUsDetails(){
		return videoRepository.findAll(); 
	}
	//get book by id rest api
			@GetMapping("/selectedVideo/{category}")
			public List<Video> getVideosByCategory(@PathVariable String category){
				
				List<Video> list = new ArrayList<>(2);
			List<Video> array=videoRepository.findByCategory(category);
		        //array.get(1);
			list.add(array.get(0));
				list.add(array.get(1));
				list.add(array.get(2));
				System.out.println("Array Size123"+list.size());
				
				
				 SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
				 String dateBeforeString = "31-01-2014";
				 String dateAfterString = "02-02-2014";

				 try {
				       Date dateBefore = myFormat.parse(dateBeforeString);
				       Date dateAfter = myFormat.parse(dateAfterString);
				       long difference = dateAfter.getTime() - dateBefore.getTime();
				       float daysBetween = (difference / (1000*60*60*24));
			               /* You can also convert the milliseconds to days using this method
			                * float daysBetween = 
			                *         TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS)
			                */
				       System.out.println("Number of Days between dates: "+daysBetween);
				 } catch (Exception e) {
				       e.printStackTrace();
				 }
				 return list;
			}
			

	
}
