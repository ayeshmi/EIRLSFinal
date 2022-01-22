package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name="videoReservation")
public class VideoReservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max = 200)
	private String videoName;
	
	@Size(max = 50)
	private String date;
	
	@Size(max = 50)
	private String returnDate;
	
	@Size(max = 100)
	private String username;
	
	@Size(max = 100)
	@Email
	private String email;
	
	
	private  Long userId;
	

	private  Long videoId;
	
	@Size(max = 50)
	private String status;
	
	@Size(max = 50)
	private String lendingStatus;
	
	private int overduePayment;
	@Size(max = 750)
	private String image;
	
	public VideoReservation() {
		
	}
	
	public VideoReservation(String videoName, String date, String username, Long userId) {
		super();
		this.videoName = videoName;
		this.date = date;
		this.username = username;
		this.userId = userId;
	}
	
	
	
	public VideoReservation( String videoName, String date,
			 String username,  Long userId,  Long videoId,String returnDate) {
		super();
		this.videoName = videoName;
		this.date = date;
		this.username = username;
		this.userId = userId;
		this.videoId = videoId;
		this.returnDate=returnDate;
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
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

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	

}
