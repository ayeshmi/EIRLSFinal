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
	
	
	private String username;
	
	
	private int numberOfBooks;
	
	
	private int numberOfVideos;
	
	
	private int bookDurationDays;
	
	
	private int videoDurationDays;
	
	
	private int bookCharges;
	
	
	private int videoCharges;
	

	private int annualFee;
	
	
	private int overdur;
	
	
	private int ongoingBookAmount;
	
	
	private int ongoingVideoAmount;
	

	public ReservationDetails() {
		
	}
	
	

	public ReservationDetails(@NotBlank String username, @NotBlank @Size(max = 10) int numberOfBooks,
			@NotBlank @Size(max = 10) int numberOfVideos, @NotBlank @Size(max = 10) int bookDurationDays,
			@NotBlank @Size(max = 10) int videoDurationDays, @Size(max = 10) int bookCharges,
			@NotBlank @Size(max = 10) int videoCharges, @NotBlank @Size(max = 10) int annualFee,
			@NotBlank @Size(max = 10) int overdur) {
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


	
	public int getNumberOfBooks() {
		return numberOfBooks;
	}

	public void setNumberOfBooks(int numberOfBooks) {
		this.numberOfBooks = numberOfBooks;
	}

	public int getNumberOfVideos() {
		return numberOfVideos;
	}

	public void setNumberOfVideos(int numberOfVideos) {
		this.numberOfVideos = numberOfVideos;
	}

	public int getBookDurationDays() {
		return bookDurationDays;
	}

	public void setBookDurationDays(int bookDurationDays) {
		this.bookDurationDays = bookDurationDays;
	}

	public int getVideoDurationDays() {
		return videoDurationDays;
	}

	public void setVideoDurationDays(int videoDurationDays) {
		this.videoDurationDays = videoDurationDays;
	}

	public int getBookCharges() {
		return bookCharges;
	}

	public void setBookCharges(int bookCharges) {
		this.bookCharges = bookCharges;
	}

	public int getVideoCharges() {
		return videoCharges;
	}

	public void setVideoCharges(int videoCharges) {
		this.videoCharges = videoCharges;
	}

	public int getAnnualFee() {
		return annualFee;
	}

	public void setAnnualFee(int annualFee) {
		this.annualFee = annualFee;
	}

	public int getOverdur() {
		return overdur;
	}

	public void setOverdur(int overdur) {
		this.overdur = overdur;
	}

	public int getOngoingBookAmount() {
		return ongoingBookAmount;
	}

	public void setOngoingBookAmount(int ongoingBookAmount) {
		this.ongoingBookAmount = ongoingBookAmount;
	}

	public int getOngoingVideoAmount() {
		return ongoingVideoAmount;
	}

	public void setOngoingVideoAmount(int ongoingVideoAmount) {
		this.ongoingVideoAmount = ongoingVideoAmount;
	}
	
}