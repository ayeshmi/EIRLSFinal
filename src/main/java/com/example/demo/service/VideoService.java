package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dto.MessageResponse;
import com.example.demo.model.Video;
import com.example.demo.repository.VideoRepository;

@Service
public class VideoService {

	@Autowired
	VideoRepository videoRepository;

	public List<Video> getAllVideos() {
		List<Video> videos = null;
		try {
			videos = videoRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return videos;
	}

	// get book by id rest api
	@GetMapping("/selectedVideo/{category}")
	public List<Video> getVideosByCategory(String category) {

		List<Video> list = new ArrayList<>(2);
		List<Video> array = videoRepository.findByCategory(category);
		// array.get(1);
		list.add(array.get(0));
		list.add(array.get(1));
		list.add(array.get(2));
		System.out.println("Array Size123" + list.size());

		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
		String dateBeforeString = "31-01-2014";
		String dateAfterString = "02-02-2014";

		try {
			Date dateBefore = myFormat.parse(dateBeforeString);
			Date dateAfter = myFormat.parse(dateAfterString);
			long difference = dateAfter.getTime() - dateBefore.getTime();
			float daysBetween = (difference / (1000 * 60 * 60 * 24));
			/*
			 * You can also convert the milliseconds to days using this method float
			 * daysBetween = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS)
			 */
			System.out.println("Number of Days between dates: " + daysBetween);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public MessageResponse addVideo(Video video1) {

		if (videoRepository.existsByTitle(video1.getTitle())) {
			return new MessageResponse("Error: This video is already registered in this system.");
		} else {
			videoRepository.save(video1);
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

}
