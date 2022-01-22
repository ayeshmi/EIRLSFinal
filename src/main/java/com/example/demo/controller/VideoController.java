package com.example.demo.controller;


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

import com.example.demo.dto.MessageResponse;
import com.example.demo.model.Book;
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
	public ResponseEntity<Object> getAllVideos(){
		List<Video> list = videoService.getAllVideos();
		
		if(list.size() != 0) {
			return ResponseEntity.ok(list);
		}
		else {
			MessageResponse message =new MessageResponse("No Records found!");
			return ResponseEntity.badRequest().body(message);
		}
		
	}
	//get book by id rest api
			@GetMapping("/selectedVideo/{category}")
			public List<Video> getVideosByCategory(@PathVariable String category){
				
				List<Video> list = videoService.getVideosByCategory(category);
				return list;
			}
			
			@PostMapping("/addVideo")
			public ResponseEntity<Object> addVideo(@RequestBody Video video1){
			System.out.println("dddd"+video1.getAuthor());
				MessageResponse message =videoService.addVideo(video1);
				if(message.getMessage().equals("Video is successfully registered!")) {
					return ResponseEntity.ok(message);
				}
				else {
					return ResponseEntity.badRequest().body(message);
				}
			}
			
			@DeleteMapping("/deleteVideo/{id}")
			public ResponseEntity<Object> deleteVideo(
					@PathVariable Long id){

				MessageResponse message =videoService.deleteVideo(id);
				if(message.getMessage().equals("Video is successfully deleted!")){
					return ResponseEntity.ok(message);
				}else {
					return ResponseEntity.badRequest().body(new MessageResponse("Something went wrong, try again!"));
				}
				
			}
			
			@GetMapping("/searchVideos/{name}")
		    public ResponseEntity<Object> searchVideo(@PathVariable("name") String specs) {
				List<Video> videos=videoService.advanceSearch(specs);
				
				if(videos.size() != 0) {
					return ResponseEntity.ok(videos);
				}
				else {
					MessageResponse message =new MessageResponse("No Records found!");
					return ResponseEntity.badRequest().body(message);
				}
		    }
			
			@GetMapping("/RselectedVideoR/{category}/{id}")
			public List<Video> getVideoByRomance(@PathVariable("category") String category,@PathVariable("id")Long id ) {
                System.out.println(" calledRomance"+id);
				List<Video> list = videoService.getVideosByRomance(category,id);
				System.out.println(" calledRomance11"+list.size());
				return list;
			}
			
			@GetMapping("/RselectedVideoUser/{category}/{id}")
			public List<Video> getVideoByRomanceUser(@PathVariable("category") String category,@PathVariable("id")Long id ) {
                System.out.println(" calledRomance"+id);
				List<Video> list = videoService.getVideosByRomanceUser(category, id);
				System.out.println(" calledRomance11"+list.size());
				return list;
			}
			
			@GetMapping("/Video/{id}")
			public ResponseEntity<Object> getVideoById(@PathVariable Long id) {
				Video video = videoService.getVideoById(id);
				if(video != null) {
					return ResponseEntity.ok(video);
				}
				else {
					return ResponseEntity.badRequest().body(new MessageResponse("Selected Book is not exit!"));	
				}
				
			}
			
			@PostMapping("/addVideoCopy")
			public ResponseEntity<Object> addBookCopy(@RequestBody Video book){
				System.out.println("calledrerer");
				MessageResponse message = videoService.addVideoCopy(book);
				if(message.getMessage().equals("Video copies are increased!")) {
					System.out.println("calledrerer");
					return ResponseEntity.ok(message);
				}
				else {
					System.out.println("calledrerer");
					return ResponseEntity.badRequest().body(message);
				}
				
			}
			
			

	
}
