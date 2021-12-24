package com.example.demo.controller;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.example.demo.model.Book;
import com.example.demo.model.FileResponse;
import com.example.demo.dto.MessageResponse;
import com.example.demo.model.User;
import com.example.demo.model.Video;

import com.example.demo.service.FileService;
import com.example.demo.service.FileStorageService;


@RestController

@RequestMapping("/api/auth")
public class FileController {
	@Autowired
	private FileStorageService fileStorageService;
	
	
	
	@Autowired
	private FileService fileService;
	
	String name;
	
	@PutMapping("/image")
	public ResponseEntity<FileResponse> uploadProfileImage(@RequestParam("file") MultipartFile file,@RequestParam("user")String user){

		
		FileResponse fileResponse=fileService.uploadProfileImage(file, user);
		return new ResponseEntity<FileResponse>(fileResponse,HttpStatus.OK);
	}
	
	@PutMapping("/video")
	public ResponseEntity<Video> uploadVideo(@RequestParam("Video") MultipartFile file,@RequestParam("Title") String title){
	
		Video video=fileService.uploadVideo(file, title);
		
		return new ResponseEntity<Video>(video,HttpStatus.OK);
	}
	
	@PutMapping("/videoImage")
	public ResponseEntity<Video> uploadVideoImage(@RequestParam("Video") MultipartFile file,@RequestParam("Title") String title){
		
		Video video=fileService.uploadVideoImage(file, title);
		
		return new ResponseEntity<Video>(video,HttpStatus.OK);
	}
	
	@PutMapping("/bookImage")
	public ResponseEntity<Book> uploadBookImage(@RequestParam("Video") MultipartFile file,@RequestParam("Title") String title){
		
		Book book=fileService.uploadBookImage(file, title);
		return new ResponseEntity<Book>(book,HttpStatus.OK);
	}
	
	
	
	@GetMapping("image/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName,HttpServletRequest request){
		
		Resource resource = fileStorageService.loadFileAsResource(fileName);
		System.out.println("hello ayeshmi");
		String contentType = null;
		
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		}catch(IOException ex) {
			System.out.println("Could not determine fileType");
		}
		
		if(contentType==null) {
			contentType = "application/octet-stream";
		}
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.body(resource);
	}
	
	@GetMapping("video/{fileName:.+}")
	public ResponseEntity<Resource> downloadVideo(@PathVariable String fileName,HttpServletRequest request){
		System.out.println(" get video url");
		Resource resource = fileStorageService.loadFileAsResource(fileName);
		
		String contentType = null;
		
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		}catch(IOException ex) {
			System.out.println("Could not determine fileType");
		}
		
		if(contentType==null) {
			contentType = "application/octet-stream";
		}
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.body(resource);
	}
	
	@PutMapping("/profileImage12")
	public ResponseEntity<MessageResponse> uploadProfileImageUser(@RequestParam("file") MultipartFile file,@RequestParam("user") String title){
		
		User user=fileService.uploadProfileImageUser(file, title);
		MessageResponse message=new MessageResponse("Profile Image is successfully updated.");
		return new ResponseEntity<MessageResponse>(message,HttpStatus.OK);
	}
	
	@PutMapping("/addProfilePicture")
	public ResponseEntity<MessageResponse> addProfilePicture(@RequestParam("file") MultipartFile file,@RequestParam("user") String title){
		System.out.println(" get file name"+file);
		System.out.println(" get email"+title);
		User user=fileService.addProfilePicture(file, title);
		MessageResponse message=new MessageResponse("Profile Image is successfully updated.");
		return new ResponseEntity<MessageResponse>(message,HttpStatus.OK);
	}
}