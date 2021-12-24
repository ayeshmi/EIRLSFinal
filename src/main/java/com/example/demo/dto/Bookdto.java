package com.example.demo.dto;

import java.util.function.BooleanSupplier;



public class Bookdto {
	
	private Long id;	

    private String category;
	
    private String title;

    private String author;

    private String inumber;

    private String numberOfCopies;

	private String bookDescription;

	private String date;

	private String message;

	private String imageOfVideo;
	
	public Bookdto(Long id,  String category, String title, String author, String edition,String iSBNnumber,  String price,String numberOfCopies,String date,String message) {
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
	
	public Bookdto(String category, String title, String author, String iSBNnumber,  String numberOfCopies,String date,String message) {
		
		this.category = category;
		this.title = title;
		this.author = author;
		this.inumber = iSBNnumber;
		this.numberOfCopies = numberOfCopies;
		this.date=date;
		this.message=message;
	}

	public Bookdto()
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

	public String getInumber() {
		return inumber;
	}

	public void setInumber(String inumber) {
		this.inumber = inumber;
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
	
	public String getBookDescription() {
		return bookDescription;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

	public BooleanSupplier isPresent() {
		// TODO Auto-generated method stub
		return null;
	}
}
