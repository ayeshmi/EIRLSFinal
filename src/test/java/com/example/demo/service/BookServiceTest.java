package com.example.demo.service;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.Book;
import com.example.demo.model.User;
import com.example.demo.repository.BookRepository;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class BookServiceTest {

	@Autowired
	BookService bookservice;
	
	@MockBean
	BookRepository bookRepository;
	
	
	 @Test
	    @DisplayName("Test findAll")
	    void testFindAll() {
	        // Setup our mock repository
		 Book book1 = new Book(5l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	Book book2 = new Book(6l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	
	       
	        doReturn(Arrays.asList(book1, book2)).when(bookRepository).findAll();

	        // Execute the service call
	        List<Book> widgets = bookservice.getAllBooks(); 

	        // Assert the response
	        Assertions.assertEquals(2, widgets.size(), "findAll should return 2 widgets");
	    }
	 
	    @Test
	    @DisplayName("Test save")
	    void testSave() {
	    	// Setup our mock repository
	    	Book book = new Book(5l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	
	        doReturn(book).when(bookRepository).save(any());

	        // Execute the service call
	      bookservice.addbook(book);
	   
	        
	    }
	    
	    @Test
	    @DisplayName("Test findById Success")
	    void testFindById() {
	        // Setup our mock repository
	    	Book book = new Book(5l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	
	    	doReturn(Optional.of(book)).when(bookRepository).findById(1l);

	        // Execute the service call
	      //  Book returnedWidget = bookservice.getBookById(1L);

	        // Assert the response
	      // Assertions.assertTrue(returnedWidget.isPresent(), "Widget was not found");
	      //  Assertions.assertSame(returnedWidget.getId(), book.getId(), "The widget returned was not the same as the mock");
	       // Assertions.assertAll(book.getId()).isEqualTo(1L);

	    }
	    
	
	    
	    @Test
	    @DisplayName("Test findById Success")
	    void testFindByCategory() {
	        // Setup our mock repository
	    	
	    	Book book1 = new Book(5l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	Book book2 = new Book(6l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	Book book3 = new Book(7l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	
	    	doReturn(Arrays.asList(book1, book2,book3)).when(bookRepository).findByCategory("Romance");
	    	
	    	List<Book> list12 = new ArrayList<>(2);
	    	
	        // Execute the service call
	    	List<Book> array  = bookservice.getBooksByCategory("Romance");
	    	//list12[2]=list.get(2);
	    	list12.add(array.get(0));
	    	list12.add(array.get(1));
	    	list12.add(array.get(2));
	        Assertions.assertEquals(3, list12.size(), "findAll should return 2 widgets");

	    }
	
	    @Test
	    @DisplayName("Test findById Success")
	    void testFindByCategoryRomance() {
	        // Setup our mock repository
	    	
	    	Book book1 = new Book(5l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	Book book2 = new Book(6l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	Book book3 = new Book(7l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	Book book4 = new Book(8l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	doReturn(Arrays.asList(book1, book2,book3,book4)).when(bookRepository).findByCategory("Romance");
	    	
	    	List<Book> list12 = new ArrayList<>(3);
	    	
	        // Execute the service call
	    	List<Book> array  = bookservice.getBooksByRomance("Romance");
	    	//list12[2]=list.get(2);
	    	list12.add(array.get(0));
	    	list12.add(array.get(1));
	    	list12.add(array.get(2));
	    	list12.add(array.get(3));
	        Assertions.assertEquals(4, list12.size(), "findAll should return 2 widgets");

	    }
	    @Test
	    @DisplayName("Test findById Success")
	    void testFindByCategoryComic() {
	        // Setup our mock repository
	    	
	    	Book book1 = new Book(5l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	Book book2 = new Book(6l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	Book book3 = new Book(7l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	Book book4 = new Book(8l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	doReturn(Arrays.asList(book1, book2,book3,book4)).when(bookRepository).findByCategory("Comic");
	    	
	    	List<Book> list12 = new ArrayList<>(3);
	    	
	        // Execute the service call
	    	List<Book> array  = bookservice.getBooksByComic("Comic");
	    	//list12[2]=list.get(2);
	    	list12.add(array.get(0));
	    	list12.add(array.get(1));
	    	list12.add(array.get(2));
	    	list12.add(array.get(3));
	        Assertions.assertEquals(4, list12.size(), "findAll should return 4 widgets");

	    }
	    
	    @Test
	    @DisplayName("Test findById Success")
	    void testFindByCategoryAction() {
	        // Setup our mock repository
	    	
	    	Book book1 = new Book(5l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	Book book2 = new Book(6l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	Book book3 = new Book(7l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	Book book4 = new Book(8l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	doReturn(Arrays.asList(book1, book2,book3,book4)).when(bookRepository).findByCategory("Action");
	    	
	    	List<Book> list12 = new ArrayList<>(3);
	    	
	        // Execute the service call
	    	List<Book> array  = bookservice.getBooksByAction("Action");
	    	//list12[2]=list.get(2);
	    	list12.add(array.get(0));
	    	list12.add(array.get(1));
	    	list12.add(array.get(2));
	    	list12.add(array.get(3));
	        Assertions.assertEquals(4, list12.size(), "findAll should return 4 widgets");

	    }
	    
	    @Test
	    @DisplayName("Test findById Success")
	    void testFindByCategoryFantasy() {
	        // Setup our mock repository
	    	
	    	Book book1 = new Book(5l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	Book book2 = new Book(6l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	Book book3 = new Book(7l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	Book book4 = new Book(8l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	doReturn(Arrays.asList(book1, book2,book3,book4)).when(bookRepository).findByCategory("Fantasy");
	    	
	    	List<Book> list12 = new ArrayList<>(3);
	    	
	        // Execute the service call
	    	List<Book> array  = bookservice.getBooksByFantasy("Fantasy");
	    	//list12[2]=list.get(2);
	    	list12.add(array.get(0));
	    	list12.add(array.get(1));
	    	list12.add(array.get(2));
	    	list12.add(array.get(3));
	        Assertions.assertEquals(4, list12.size(), "findAll should return 4 widgets");

	    }
	    
	    @Test
	    @DisplayName("Test findById Success")
	    void testFindByCategoryHorror() {
	        // Setup our mock repository
	    	
	    	Book book1 = new Book(5l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	Book book2 = new Book(6l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	Book book3 = new Book(7l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	Book book4 = new Book(8l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	doReturn(Arrays.asList(book1, book2,book3,book4)).when(bookRepository).findByCategory("Horror");
	    	
	    	List<Book> list12 = new ArrayList<>(3);
	    	
	        // Execute the service call
	    	List<Book> array  = bookservice.getBooksByHorror("Horror");
	    	//list12[2]=list.get(2);
	    	list12.add(array.get(0));
	    	list12.add(array.get(1));
	    	list12.add(array.get(2));
	    	list12.add(array.get(3));
	        Assertions.assertEquals(4, list12.size(), "findAll should return 4 widgets");

	    }
	    

	    @Test
	    @DisplayName("Test findById Success")
	    void testFindByCategoryDrama() {
	        // Setup our mock repository
	    
	    	Book book1 = new Book(5l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	Book book2 = new Book(6l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	Book book3 = new Book(7l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	Book book4 = new Book(8l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
	    	doReturn(Arrays.asList(book1, book2,book3,book4)).when(bookRepository).findByCategory("Drama");
	    	
	    	List<Book> list12 = new ArrayList<>(3);
	    	
	        // Execute the service call
	    	List<Book> array  = bookservice.getBooksByDrama("Drama");
	    	//list12[2]=list.get(2);
	    	list12.add(array.get(0));
	    	list12.add(array.get(1));
	    	list12.add(array.get(2));
	    	list12.add(array.get(3));
	        Assertions.assertEquals(4, list12.size(), "findAll should return 4 widgets");

	    }
	    
		
		
		  @Test
		    @DisplayName("Test findById Success")
		    void testFindByAllCategory() {
			  Book book1 = new Book(5l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
		    	Book book2 = new Book(6l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");

				doReturn(Arrays.asList(book1, book2)).when(bookRepository).findByCategory("Drama");

				// Execute the service call
				List<Book> widgets = bookservice.getAllBooksByCategory("Drama");

				// Assert the response
				Assertions.assertEquals(2, widgets.size(), "findAll should return 2 widgets");

		    }
		  
	
		
		  
		  @Test
		    @DisplayName("Test findById Success")
		    void testAdvanceSearch() {
			  Book book1 = new Book(5l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");
		    	Book book2 = new Book(6l, "Drama", "Description", "d","d",1L,"d",1234,"d","d");

				doReturn(Arrays.asList(book1, book2)).when(bookRepository).search("Drama");

				// Execute the service call
				List<Book> widgets = bookservice.advanceSearch("Drama");

				// Assert the response
				Assertions.assertEquals(2, widgets.size(), "findAll should return 2 widgets");

		    }
		
}
