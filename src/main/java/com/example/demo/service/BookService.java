package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.Book;
import com.example.demo.model.MessageResponse;
import com.example.demo.repository.BookRepository;
@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;
	

	public List<Book> getAllBooks(){
		return bookRepository.findAll();
	}
	
	
	public void addbook( Book book) {
		  
		bookRepository.save(book);
		
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
	
}
