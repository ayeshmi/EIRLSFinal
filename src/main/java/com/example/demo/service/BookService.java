package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Book;
import com.example.demo.model.User;
import com.example.demo.dto.MessageResponse;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.UserRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailSender emailSender;

	public List<Book> getAllBooks() {
		List<Book> books=null;
		try {
			books=bookRepository.findAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return books;
	}

	@Transactional
	public MessageResponse addbook(Book book) {
		try {
			if (bookRepository.existsByTitle(book.getTitle())) {
				return new MessageResponse("Error: This book is already registered in this system.");
			}
			if (bookRepository.existsByInumber(book.getInumber())) {
				return new MessageResponse("Error: This book is already registered in this system.");
			}
			bookRepository.save(book);
			List<User> users=userRepository.getUsers();
			for(int i=0;i<users.size();i++) {
				users.get(i).getEmail();
				emailSender.sendEmailNewBook();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new MessageResponse("Book is successfully registered!");

	}

	public Book getBookById(Long id) {
		Book book = null;
		try {
			book = bookRepository.findById(id).orElseThrow();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return book;
	}

	public List<Book> getBooksByCategory(String category) {

		List<Book> list = new ArrayList<>(2);
		List<Book> array = bookRepository.findByCategory(category);
		// array.get(1);
		list.add(array.get(0));
		list.add(array.get(1));
		list.add(array.get(2));
		System.out.println("Array Size1" + list.size());
		return list;
	}

	public List<Book> getBooksByRomance(String category) {

		List<Book> list = new ArrayList<>(2);
		List<Book> array = bookRepository.findByCategory(category);
		// array.get(1);
		list.add(array.get(0));
		list.add(array.get(1));
		list.add(array.get(2));
		list.add(array.get(3));
		System.out.println("Array Size2" + list.size());
		return list;
	}

	public List<Book> getBooksByComic(String category) {

		List<Book> list = new ArrayList<>(2);
		List<Book> array = bookRepository.findByCategory(category);
		// array.get(1);
		list.add(array.get(0));
		list.add(array.get(1));
		list.add(array.get(2));
		list.add(array.get(3));
		System.out.println("Array Size3" + list.size());
		return list;
	}

	public List<Book> getBooksByAction(String category) {

		List<Book> list = new ArrayList<>(2);
		List<Book> array = bookRepository.findByCategory(category);
		// array.get(1);
		list.add(array.get(0));
		list.add(array.get(1));
		list.add(array.get(2));
		list.add(array.get(3));
		System.out.println("Array Size4" + list.size());
		return list;
	}

	public List<Book> getBooksByFantasy(String category) {

		List<Book> list = new ArrayList<>(2);
		List<Book> array = bookRepository.findByCategory(category);
		// array.get(1);
		list.add(array.get(0));
		list.add(array.get(1));
		list.add(array.get(2));
		list.add(array.get(3));
		System.out.println("Array Size5" + list.size());
		return list;
	}

	public List<Book> getBooksByHorror(String category) {

		List<Book> list = new ArrayList<>(2);
		List<Book> array = bookRepository.findByCategory(category);
		// array.get(1);
		list.add(array.get(0));
		list.add(array.get(1));
		list.add(array.get(2));
		list.add(array.get(3));
		System.out.println("Array Size123" + list.size());
		return list;
	}

	public List<Book> getBooksByDrama(String category) {

		List<Book> list = new ArrayList<>(2);
		List<Book> array = bookRepository.findByCategory(category);
		// array.get(1);
		list.add(array.get(0));
		list.add(array.get(1));
		list.add(array.get(2));
		list.add(array.get(3));
		return list;
	}

	public MessageResponse deleteBook(Long id) {
		MessageResponse messageResponse=null;
		
		try {
			Book book = bookRepository.findById(id).orElseThrow();
			bookRepository.delete(book);
			messageResponse=new MessageResponse("Book is successfully deleted!");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return messageResponse;
	}

	public List<Book> advanceSearch(String specs) {
		List<Book> book = bookRepository.search(specs);
		return book;
	}



	

}
