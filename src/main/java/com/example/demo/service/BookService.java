package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.Book;
import com.example.demo.model.ContactUs;
import com.example.demo.model.MessageResponse;
import com.example.demo.repository.BookRepository;
@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;
	

	public List<Book> getAllBooks(){
		return bookRepository.findAll();
	}
	
	@Transactional
	public ResponseEntity<?> addbook( Book book) {
		if (bookRepository.existsByTitle(book.getTitle())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: This book is already registered in this system."));
		}  
		if (bookRepository.existsByInumber(book.getInumber())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: This book is already registered in this system."));
		} 
		bookRepository.save(book);
	 return ResponseEntity.ok(new MessageResponse("Book is successfully registered!"));
		
	}
	
	
	public Book getBookById(String id){
		Long Id=Long.parseLong(id);
		System.out.println("hello id"+id);
		Book  book=bookRepository.findById(Id)
				.orElseThrow();
		return book;
	}
	
	
	public List<Book> getBooksByCategory(String category){
		
		List<Book> list = new ArrayList<>(2);
	List<Book> array=bookRepository.findByCategory(category);
        //array.get(1);
	list.add(array.get(0));
		list.add(array.get(1));
		list.add(array.get(2));
		System.out.println("Array Size1"+list.size());
		return list;
	}
	
	
	public List<Book> getBooksByRomance(String category){
		
		List<Book> list = new ArrayList<>(2);
	List<Book> array=bookRepository.findByCategory(category);
        //array.get(1);
	list.add(array.get(0));
		list.add(array.get(1));
		list.add(array.get(2));
		list.add(array.get(3));
		System.out.println("Array Size2"+list.size());
		return list;
	}


	public List<Book> getBooksByComic(String category){
		
		List<Book> list = new ArrayList<>(2);
	List<Book> array=bookRepository.findByCategory(category);
        //array.get(1);
	list.add(array.get(0));
		list.add(array.get(1));
		list.add(array.get(2));
		list.add(array.get(3));
		System.out.println("Array Size3"+list.size());
		return list;
	}
	
	
	public List<Book> getBooksByAction(String category){
		
		List<Book> list = new ArrayList<>(2);
	List<Book> array=bookRepository.findByCategory(category);
        //array.get(1);
	list.add(array.get(0));
		list.add(array.get(1));
		list.add(array.get(2));
		list.add(array.get(3));
		System.out.println("Array Size4"+list.size());
		return list;
	}
	
	
	public List<Book> getBooksByFantasy( String category){
		
		List<Book> list = new ArrayList<>(2);
	List<Book> array=bookRepository.findByCategory(category);
        //array.get(1);
	list.add(array.get(0));
		list.add(array.get(1));
		list.add(array.get(2));
		list.add(array.get(3));
		System.out.println("Array Size5"+list.size());
		return list;
	}
	
	
	public List<Book> getBooksByHorror( String category){
		
		List<Book> list = new ArrayList<>(2);
	List<Book> array=bookRepository.findByCategory(category);
        //array.get(1);
	list.add(array.get(0));
		list.add(array.get(1));
		list.add(array.get(2));
		list.add(array.get(3));
		System.out.println("Array Size123"+list.size());
		return list;
	}
	

	public List<Book> getBooksByDrama(String category){
		
		List<Book> list = new ArrayList<>(2);
	List<Book> array=bookRepository.findByCategory(category);
        //array.get(1);
	list.add(array.get(0));
		list.add(array.get(1));
		list.add(array.get(2));
		list.add(array.get(3));
		System.out.println("Array Size"+list.size());
		return list;
	}
	
	public ResponseEntity<Map<String,Boolean>> deleteBook(Long id){
		Book book=bookRepository.findById(id)
				.orElseThrow();
		bookRepository.delete(book);
		Map<String,Boolean> response=new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	public List<Book> advanceSearch(String specs) {
		List<Book> book=bookRepository.search(specs);
		return book;
	}
	
}
