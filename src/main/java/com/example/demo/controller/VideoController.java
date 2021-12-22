package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.model.MessageResponse;
import com.example.demo.model.Video;
import com.example.demo.repository.VideoRepository;
import com.example.demo.service.VideoService;

@RestController

@RequestMapping("/api/auth")
public class VideoController {

	@Autowired
	VideoRepository videoRepository;
	
	@Autowired 
	VideoService videoService;
	
	@GetMapping("/AllVideos")
	public List<Video> getAllContactUsDetails(){
		return videoRepository.findAll(); 
	}
	//get book by id rest api
			@GetMapping("/selectedVideo/{category}")
			public List<Video> getVideosByCategory(@PathVariable String category){
				
				List<Video> list = videoService.getAllVideos();
				return list;
			}
			
			@PostMapping("/addVideo")
			public ResponseEntity<?> upadateEmployee(@RequestBody Video video1){
			//System.out.println("Videooo");
			ResponseEntity<?> response=videoService.addVideo(video1);
				return response;
			}
			
			@DeleteMapping("/deleteVideo/{id}")
			public ResponseEntity<?> deleteVideo(@PathVariable Long id){
				videoService.deleteVideo(id);
				
				return ResponseEntity.ok(new MessageResponse("Successfully Deleted!"));
				
			}
			
			@GetMapping("/searchVideos/{name}")
		    public ResponseEntity<List<Video>> searchVideo(@PathVariable("name") String specs) {
				System.out.println("Videooooo");
		        return new ResponseEntity<>(videoService.advanceSearch(specs), HttpStatus.OK);
		       // System.out.println("hello1296");
		    }
			
			

	
}
