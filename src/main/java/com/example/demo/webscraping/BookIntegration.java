package com.example.demo.webscraping;

import java.util.function.BooleanSupplier;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class BookIntegration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

    private String category;

    private String title;

    private String author;
	
    private Long inumber;
	
	private int numberOfPages;
	
	private int year;
	
    private int numberOfCopies;
    
    private int price;

	private String bookDescription;

	private String date;
	
	private String bookExcerpt ;
	
   private String status;

	public BookIntegration()
	{
		
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getInumber() {
		return inumber;
	}

	public void setInumber(Long inumber) {
		this.inumber = inumber;
	}


	public int  getNumberOfCopies() {
		return numberOfCopies;
	}

	public void setNumberOfCopies(int  numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
	}


	public String getBookDescription() {
		return bookDescription;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

	public String getBookExcerpt() {
		return bookExcerpt;
	}

	public void setBookExcerpt(String bookExcerpt) {
		this.bookExcerpt = bookExcerpt;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BooleanSupplier isPresent() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
