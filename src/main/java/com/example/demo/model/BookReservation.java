package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="bookReservation")
public class BookReservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max = 200)
	private String bookName;
	
	@Size(max = 50)
	private String date;
	
	@Size(max = 50)
	private String username;
	
	@Size(max = 50)
	private  String userId;
	
	public BookReservation() {
		
	}
	
	public BookReservation(String bookName, String date, String username, String userId) {
		super();
		this.bookName = bookName;
		this.date = date;
		this.username = username;
		this.userId = userId;
	}
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
}
