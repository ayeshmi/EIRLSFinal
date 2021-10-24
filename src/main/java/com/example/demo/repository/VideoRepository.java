package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Video;

public interface VideoRepository extends JpaRepository<Video,Long>{
	Video findByTitle(String title);
	//ArrayList<Video> video = new ArrayList<>(2);
	
	List<Video> findByCategory(String category);
}
