package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.Book;
import com.example.demo.model.MessageResponse;
import com.example.demo.model.Video;
import com.example.demo.repository.VideoRepository;

@Service
public class VideoService {
	
	@Autowired
	VideoRepository videoRepository;
	
	
	public List<Video> getAllVideos(){
		return videoRepository.findAll(); 
	}
	//get book by id rest api
			@GetMapping("/selectedVideo/{category}")
			public List<Video> getVideosByCategory( String category){
				
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
			
			
			public ResponseEntity<?> addVideo(Video video1){
			
				if (videoRepository.existsByTitle(video1.getTitle())) {
					return ResponseEntity
							.badRequest()
							.body(new MessageResponse("Error: This video is already registered in this system."));
				} 
				
			videoRepository.save(video1);
				return ResponseEntity.ok(new MessageResponse("Video is successfully registered!"));
			}
			
			public ResponseEntity<Map<String,Boolean>> deleteVideo(Long id) {
				Video video=videoRepository.findById(id)
						.orElseThrow();
				videoRepository.delete(video);
				Map<String,Boolean> response=new HashMap<>();
				response.put("deleted", Boolean.TRUE);
				return ResponseEntity.ok(response);
				
			}
			public List<Video> advanceSearch(String specs) {
				
					List<Video> video=videoRepository.search(specs);
					return video;
				
			}	

}
