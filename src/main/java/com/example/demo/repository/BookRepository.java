package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Book;

public interface BookRepository extends JpaRepository<Book,Long>  {
	Book findByTitle(String title);
	
	//ArrayList<Video> video = new ArrayList<>(2);
	
	List<Book> findByCategory(String category);
}
