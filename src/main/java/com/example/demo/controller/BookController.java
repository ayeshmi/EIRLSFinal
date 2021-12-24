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

import com.example.demo.model.Book;
import com.example.demo.dto.MessageResponse;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;

@RestController

@RequestMapping("/api/auth")
public class BookController {
	@Autowired
	BookRepository bookRepository;

	@Autowired
	BookService bookService;

	@GetMapping("/books")
	public ResponseEntity<Object> getAllBooks() {
		List<Book> books=bookService.getAllBooks();
		
		if(books.size() != 0) {
			return ResponseEntity.ok(books);
		}
		else {
			MessageResponse message =new MessageResponse("No Records found!");
			return ResponseEntity.badRequest().body(message);
		}
		
	}

	// get book by id rest api
	@GetMapping("/selectedBook/{category}")
	public List<Book> getBooksByCategory(@PathVariable String category) {

		List<Book> list = bookService.getBooksByCategory(category);

		return list;
	}

	@GetMapping("/RselectedBookR/{category}")
	public List<Book> getBooksByRomance(@PathVariable String category) {

		List<Book> list = bookService.getBooksByRomance(category);
		return list;
	}

	@GetMapping("/RselectedBookC/{category}")
	public List<Book> getBooksByComic(@PathVariable String category) {

		List<Book> list = bookService.getBooksByComic(category);
		return list;
	}

	@GetMapping("/RselectedBookA/{category}")
	public List<Book> getBooksByAction(@PathVariable String category) {

		List<Book> list = bookService.getBooksByAction(category);
		return list;
	}

	@GetMapping("/RselectedBookF/{category}")
	public List<Book> getBooksByFantasy(@PathVariable String category) {

		List<Book> list = bookService.getBooksByFantasy(category);
		return list;
	}

	@GetMapping("/RselectedBookH/{category}")
	public List<Book> getBooksByHorror(@PathVariable String category) {

		List<Book> list = bookService.getBooksByHorror(category);
		return list;

	}

	@GetMapping("/RselectedBookD/{category}")
	public List<Book> getBooksByDrama(@PathVariable String category) {

		List<Book> list = bookService.getBooksByDrama(category);
		return list;
	}

	// adding new book
	@PostMapping("/addbook")
	public ResponseEntity<Object> addbook(@RequestBody Book book) {
		
		MessageResponse message = bookService.addbook(book);
		
		if(message.getMessage().equals("Book is successfully registered!")) {
			return ResponseEntity.ok(message);
		}
		else {
			return ResponseEntity.badRequest().body(message);
		}
		
	}

	// get book by id rest api
	@GetMapping("/book/{id}")
	public ResponseEntity<Object> getBookById(@PathVariable Long id) {
		Book book = bookService.getBookById(id);
		if(book != null) {
			return ResponseEntity.ok(book);
		}
		else {
			return ResponseEntity.badRequest().body(new MessageResponse("Selected Book is not exit!"));	
		}
		
	}

	@GetMapping("/searchBooks/{name}")
	public ResponseEntity<Object> searchVideo(@PathVariable("name") String specs) {

		
		List<Book> books=bookService.advanceSearch(specs);
		
		if( books.size() != 0) {
			return ResponseEntity.ok( books);
		}
		else {
			MessageResponse message =new MessageResponse("No Records found!");
			return ResponseEntity.badRequest().body(message);
		}
	}

	@DeleteMapping("/deleteBook/{id}")
	public ResponseEntity<Object> deleteBook(@PathVariable Long id) {
		MessageResponse message =bookService.deleteBook(id);
		if(message.getMessage().equals("Book is successfully deleted!")){
			return ResponseEntity.ok(message);
		}else {
			return ResponseEntity.badRequest().body(new MessageResponse("Something went wrong, try again!"));
		}

	}

}
