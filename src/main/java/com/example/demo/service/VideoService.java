package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dto.MessageResponse;
import com.example.demo.model.Book;
import com.example.demo.model.User;
import com.example.demo.model.Video;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VideoRepository;
import java.time.temporal.ChronoUnit;

@Service
public class VideoService {

	@Autowired
	VideoRepository videoRepository;
	
	@Autowired
	EmailSender emailSender;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Video> getAllVideos() {
		List<Video> videos = null;
		try {
			videos = videoRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return videos;
	}

	
	public List<Video> getVideosByCategory(String category) {

	
		List<Video> list = videoRepository.findByCategory(category);
	
		return list;
	}

	public MessageResponse addVideo(Video video1) {

		if (videoRepository.existsByTitle(video1.getTitle())) {
			return new MessageResponse("Error: This video is already registered in this system.");
		} else {
			videoRepository.save(video1);
			List<User> users=userRepository.getUsers();
			for(int i=0;i<users.size();i++) {
				users.get(i).getEmail();
				emailSender.sendEmailNewVideo();
				
			}
			return new MessageResponse("Video is successfully registered!");
		}

	}

	public MessageResponse deleteVideo(Long id) {

		MessageResponse messageResponse = null;

		try {
			Video video = videoRepository.findById(id).orElseThrow();
			videoRepository.delete(video);
			messageResponse = new MessageResponse("Video is successfully deleted!");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return messageResponse;

	}

	public List<Video> advanceSearch(String specs) {
		List<Video> video = null;
		try {
			video = videoRepository.search(specs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return video;

	}

	public List<Video> getVideosByRomance(String category,Long id) {
		List<Video> list = new ArrayList<>(2);
		User user=userRepository.findById(id).orElseThrow();
		String dateOfBirth=user.getDateOfBirth();
		LocalDate localDate = LocalDate.parse(dateOfBirth);
		
		int days=(int) ChronoUnit.DAYS.between(localDate, LocalDate.now());
		List<Video> array=null;
		if(days >= 6593) {
			 array = videoRepository.getVideoAgeLimitation(category,"No");
		}
		else {
			System.out.println("funcann dd"+days);
			array = videoRepository.getVideoAgeLimitation(category,"Yes");
		}
		
		
		
		list.add(array.get(0));
		list.add(array.get(1));
		list.add(array.get(2));
		list.add(array.get(3));
		System.out.println("Array Size2" + list.size());
		return list;
	}
	
	public List<Video> getVideosByRomanceUser(String category,Long id) {
		
		User user=userRepository.findById(id).orElseThrow();
		String dateOfBirth=user.getDateOfBirth();
		LocalDate localDate = LocalDate.parse(dateOfBirth);
		
		int days=(int) ChronoUnit.DAYS.between(localDate, LocalDate.now());
		List<Video> array=null;
		if(days >= 6593) {
			 array = videoRepository.findAll();
		}
		else {
			System.out.println("funcann dd"+days);
			array = videoRepository.getVideoAgeLimitation(category,"No");
		}
		
		return array;
	}

	public Video getVideoById(Long id) {
		
		Video video = null;
		try {
			video = videoRepository.findById(id).orElseThrow();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return video;
	}


	public MessageResponse addVideoCopy(Video book) {
		Video book1=videoRepository.findByTitle(book.getTitle());
		int total=book1.getNumberOfCopies() +book.getNumberOfCopies();
		book1.setNumberOfCopies(total);
		videoRepository.save(book1);
		MessageResponse messageResponse=new MessageResponse("Video copies are increased!");
		return messageResponse;
	}

}
