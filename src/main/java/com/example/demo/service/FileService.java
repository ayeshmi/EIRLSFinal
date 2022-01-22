package com.example.demo.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.example.demo.model.Book;
import com.example.demo.model.FileResponse;
import com.example.demo.model.User;
import com.example.demo.model.Video;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.FileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VideoRepository;

@Service
public class FileService {

	@Autowired
	private FileStorageService fileStorageService;
	
	@Autowired
	private FileRepository fileRepository;
	
	@Autowired
	private VideoRepository videoRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public FileResponse uploadProfileImage(MultipartFile file,String user){
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/auth/image/")
				.path(fileName)
				.toUriString();
		
		FileResponse fileResponse = new FileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize(),user);
		fileRepository.save(fileResponse);
		return fileResponse;
	}
	
	
	public Video uploadVideo( MultipartFile file,String title){
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/auth/video/")
				.path(fileName)
				.toUriString();
		Video video=videoRepository.findByTitle(title);
		//Video video = new Video(title, fileDownloadUri);
		video.setFileDownloadUri(fileDownloadUri);
		videoRepository.save(video);
		return video;
	}
	
	
	public Video uploadVideoImage(MultipartFile file,String title){
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/auth/video/")
				.path(fileName)
				.toUriString();
		Video video=videoRepository.findByTitle(title);
		//Video video = new Video(title, fileDownloadUri);
		video.setImageOfVideo(fileDownloadUri);
		videoRepository.save(video);
		return video;
	}
	

	public Book uploadBookImage(MultipartFile file,String title){
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/auth/video/") 
				.path(fileName)
				.toUriString();
		Book book=bookRepository.findByTitle(title);
		//Video video = new Video(title, fileDownloadUri);
		book.setImageOfVideo(fileDownloadUri);
		bookRepository.save(book);
		return book;
	}
	
	public User uploadProfileImageUser(MultipartFile file,String title){
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/auth/video/") 
				.path(fileName)
				.toUriString();
		User user=userRepository.findUserByUsername(title);
		//Video video = new Video(title, fileDownloadUri);
		user.setImageOfProfile(fileDownloadUri);
		userRepository.save(user);
		System.out.println("profile pic is updated");
		return user;
	}
	

	public ResponseEntity<Resource> downloadFile( String fileName,HttpServletRequest request){
		
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


	public User addProfilePicture(MultipartFile file, String title) {
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/auth/video/") 
				.path(fileName)
				.toUriString();
		User user=userRepository.findByEmail(title);
		//Video video = new Video(title, fileDownloadUri);
		user.setImageOfProfile(fileDownloadUri);
		userRepository.save(user);
		System.out.println("profile pic is updated");
		return user;
	}


	public Book uploadOnlineBook(MultipartFile file, String title) {
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/auth/video/") 
				.path(fileName)
				.toUriString();
		Book book=bookRepository.findByTitle(title);
		//Video video = new Video(title, fileDownloadUri);
		book.setOnlineBook(fileDownloadUri);
		bookRepository.save(book);
		return book;
	}
	
}
