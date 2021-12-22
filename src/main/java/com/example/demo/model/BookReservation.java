package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;

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
	private String returnDate;
	
	@Size(max = 100)
	private String username;
	
	@Size(max = 100)
	@Email
	private String email;
	
	@Size(max = 50)
	private  String userId;
	
	@Size(max = 50)
	private  String bookId;
	
	@Size(max = 50)
	private String status;
	
	@Size(max = 50)
	private String lendingStatus;
	
	private int overduePayment;
	
	public BookReservation() {
		
	}
	
	public BookReservation(String bookName, String date, String username, String userId) {
		super();
		this.bookName = bookName;
		this.date = date;
		this.username = username;
		this.userId = userId;
	}
	
	
	
	public BookReservation( String bookName, String date,
			 String username,  String userId,  String bookId,String returnDate) {
		super();
		this.bookName = bookName;
		this.date = date;
		this.username = username;
		this.userId = userId;
		this.bookId = bookId;
		this.returnDate=returnDate;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLendingStatus() {
		return lendingStatus;
	}

	public void setLendingStatus(String lendingStatus) {
		this.lendingStatus = lendingStatus;
	}

	public int getOverduePayment() {
		return overduePayment;
	}

	public void setOverduePayment(int overduePayment) {
		this.overduePayment = overduePayment;
	}
	
	
	
}
