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
	
	
    private String edition;
	
	
	@Size(max = 30)
    private String ISBNnumber;
	
	@NotBlank
	@Size(max = 1000)
    private String price;
	
	@NotBlank
	@Size(max = 20)
    private String numberOfCopies;

	@NotBlank
	@Size(max = 50)
	private String date;
	

	@Size(max = 20)
	private String message;
	
	@Size(max = 750)
	private String imageOfVideo;
	
	public Book(Long id,  String category, String title, String author, String edition,String iSBNnumber,  String price,String numberOfCopies,String date,String message) {
		super();
		this.id = id;
		this.category = category;
		this.title = title;
		this.author = author;
		this.edition = edition;
		this.ISBNnumber = iSBNnumber;
		this.price = price;
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

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getISBNnumber() {
		return ISBNnumber;
	}

	public void setISBNnumber(String iSBNnumber) {
		ISBNnumber = iSBNnumber;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getNumberOfCopies() {
		return numberOfCopies;
	}

	public void setNumberOfCopies(String numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BooleanSupplier isPresent() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
