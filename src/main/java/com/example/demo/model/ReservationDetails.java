package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="reservation_Details")
public class ReservationDetails {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 40)
	private String username;
	
	@NotBlank
	@Size(max = 10)
	private String numberOfBooks;
	
	@NotBlank
	@Size(max = 10)
	private String numberOfVideos;
	
	@NotBlank
	@Size(max = 10)
	private String bookDurationDays;
	
	@NotBlank
	@Size(max = 10)
	private String videoDurationDays;
	
	
	@Size(max = 10)
	private String bookCharges;
	
	@NotBlank
	@Size(max = 10)
	private String videoCharges;
	
	@NotBlank
	@Size(max = 10)
	private String annualFee;
	
	@NotBlank
	@Size(max = 10)
	private String overdur;
	

	public ReservationDetails() {
		
	}


	
	
	public ReservationDetails(String username, String numberOfBooks,
			 String numberOfVideos,  String bookDurationDays,
			String videoDurationDays,  String bookCharges,
			 String videoCharges,  String annualFee,
			 String overdur) {
		super();
		this.username = username;
		this.numberOfBooks = numberOfBooks;
		this.numberOfVideos = numberOfVideos;
		this.bookDurationDays = bookDurationDays;
		this.videoDurationDays = videoDurationDays;
		this.bookCharges = bookCharges;
		this.videoCharges = videoCharges;
		this.annualFee = annualFee;
		this.overdur = overdur;
	}




	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getNumberOfBooks() {
		return numberOfBooks;
	}


	public void setNumberOfBooks(String numberOfBooks) {
		this.numberOfBooks = numberOfBooks;
	}


	public String getNumberOfVideos() {
		return numberOfVideos;
	}


	public void setNumberOfVideos(String numberOfVideos) {
		this.numberOfVideos = numberOfVideos;
	}


	public String getBookDurationDays() {
		return bookDurationDays;
	}


	public void setBookDurationDays(String bookDurationDays) {
		this.bookDurationDays = bookDurationDays;
	}


	public String getVideoDurationDays() {
		return videoDurationDays;
	}


	public void setVideoDurationDays(String videoDurationDays) {
		this.videoDurationDays = videoDurationDays;
	}


	public String getBookCharges() {
		return bookCharges;
	}


	public void setBookCharges(String bookCharges) {
		this.bookCharges = bookCharges;
	}


	public String getVideoCharges() {
		return videoCharges;
	}


	public void setVideoCharges(String videoCharges) {
		this.videoCharges = videoCharges;
	}


	public String getAnnualFee() {
		return annualFee;
	}


	public void setAnnualFee(String annualFee) {
		this.annualFee = annualFee;
	}


	public String getOverdur() {
		return overdur;
	}


	public void setOverdur(String overdur) {
		this.overdur = overdur;
	}

	


	
	
}
