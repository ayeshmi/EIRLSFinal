package com.example.demo.model;

import java.util.function.BooleanSupplier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="book")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 40)
    private String category;
	
	@NotBlank
	@Size(max = 100)
    private String title;
	
	@NotBlank
	@Size(max = 60)
    private String author;
	
	
    private Long inumber;
	
	private int numberOfPages;
	
	private int year;
	
    private int numberOfCopies;
    
    private int price;

	@Size(max = 1000)
	private String bookDescription;
	
	@Size(max = 50)
	private String date;
	

	@Size(max = 20)
	private String message;
	
	@Size(max = 750)
	private String imageOfVideo;
	
	@Size(max = 1000)
	private String bookExcerpt ;
	
	public Book(Long id,  String category, String title, String author, String edition,Long iSBNnumber,  String price,int numberOfCopies,String date,String message) {
		super();
		this.id = id;
		this.category = category;
		this.title = title;
		this.author = author;
		this.inumber = iSBNnumber;
		this.numberOfCopies = numberOfCopies;
		this.date=date;
		this.message=message;
	}
	
	public Book(String category, String title, String author, Long iSBNnumber,  int numberOfCopies,String date,String message) {
		
		this.category = category;
		this.title = title;
		this.author = author;
		this.inumber = iSBNnumber;
		this.numberOfCopies = numberOfCopies;
		this.date=date;
		this.message=message;
	}

	public Book()
	{
		
	}
	
	public String getImageOfVideo() {
		return imageOfVideo;
	}

	public void setImageOfVideo(String imageOfVideo) {
		this.imageOfVideo = imageOfVideo;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public BooleanSupplier isPresent() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
