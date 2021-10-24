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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.model.Book;
import com.example.demo.model.FileResponse;
import com.example.demo.model.Video;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.FileRepository;
import com.example.demo.repository.VideoRepository;
import com.example.demo.service.FileService;
import com.example.demo.service.FileStorageService;


@RestController

@RequestMapping("/api/auth")
public class FileController {
	@Autowired
	private FileStorageService fileStorageService;
	
	@Autowired
	private FileRepository fileRepository;
	
	@Autowired
	private VideoRepository videoRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
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
	
	@PostMapping("/addVideo")
	public ResponseEntity<Video> upadateEmployee(@RequestBody Video video1){
	
	Video newVideoDetails=videoRepository.save(video1);
		return ResponseEntity.ok(newVideoDetails);
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
}