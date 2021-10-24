package com.example.demo.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Book;
import com.example.demo.model.MessageResponse;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import com.example.demo.model.Video;



@RestController

@RequestMapping("/api/auth")
public class BookController {
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	BookService bookService;
	
	@GetMapping("/books")
	public List<Book> getAllBooks(){
		return bookService.getAllBooks();
	}
	
	//get book by id rest api
			@GetMapping("/selectedBook/{category}")
			public List<Book> getBooksByCategory(@PathVariable String category){
				
				List<Book> list = bookService.getBooksByCategory(category);
			
				return list;
			}
			
			@GetMapping("/RselectedBookR/{category}")
			public List<Book> getBooksByRomance(@PathVariable String category){
				
				List<Book> list = bookService.getBooksByRomance(category);
				return list;
			}
			
			@GetMapping("/RselectedBookC/{category}")
			public List<Book> getBooksByComic(@PathVariable String category){
				
				List<Book> list = bookService.getBooksByComic(category);
				return list;
			}
			@GetMapping("/RselectedBookA/{category}")
			public List<Book> getBooksByAction(@PathVariable String category){
				
				List<Book> list = bookService.getBooksByAction(category);
				return list;
			}
			
			@GetMapping("/RselectedBookF/{category}")
			public List<Book> getBooksByFantasy(@PathVariable String category){
				
				List<Book> list =bookService.getBooksByFantasy(category);
				return list;
			}
			
			@GetMapping("/RselectedBookH/{category}")
			public List<Book> getBooksByHorror(@PathVariable String category){
				
				List<Book> list = bookService.getBooksByHorror(category);
				return list;
			}
			
			@GetMapping("/RselectedBookD/{category}")
			public List<Book> getBooksByDrama(@PathVariable String category){
				
				List<Book> list = bookService.getBooksByDrama(category);
				return list;
			}
			
			//adding new book
			@PostMapping("/addbook")
			public ResponseEntity<?> addbook(@RequestBody Book book) {
				System.out.println("Hello Book");
				bookService.addbook(book);
				return ResponseEntity.ok(new MessageResponse("Book added successfully!"));
			}
			
		
			//get book by id rest api
			@GetMapping("/book/{id}")
			public ResponseEntity<Book> getBookById(@PathVariable String id){
			Book book=	bookService.getBookById(id);
				return ResponseEntity.ok(book);
			}
			
}
