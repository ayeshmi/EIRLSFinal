package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Bookdto;
import com.example.demo.model.Book;
import com.example.demo.model.MessageResponse;
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
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
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
	public ResponseEntity<?> addbook(@RequestBody Bookdto book) {
		System.out.println("Hello Book" + book.getInumber());
		// bookService.addbook(book);
		
		//ResponseEntity<?> response = bookService.addbook(book);
		return null;
	}

	// get book by id rest api
	@GetMapping("/book/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable String id) {
		Book book = bookService.getBookById(id);
		return ResponseEntity.ok(book);
	}

	@GetMapping("/searchBooks/{name}")
	public ResponseEntity<List<Book>> searchVideo(@PathVariable("name") String specs) {

		return new ResponseEntity<>(bookService.advanceSearch(specs), HttpStatus.OK);
		// System.out.println("hello1296");
	}

	@DeleteMapping("/deleteBook/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);

		return ResponseEntity.ok(new MessageResponse("Successfully Deleted!"));

	}

}
