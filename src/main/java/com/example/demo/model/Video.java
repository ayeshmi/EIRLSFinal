package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="video")
public class Video {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max = 40)
    private String category;
	
	@Size(max = 40)
    private String ageLimitation;
	
	@NotBlank
	@Size(max = 100)
    private String title;	
	
	@Size(max = 50)
	private String date;

	@Size(max = 500)
	private String description;
	
	@Size(max = 750)
	private String fileDownloadUri;
	@Size(max = 750)
	private String imageOfVideo;

	public Video() {
		
	}
	
	public Video( String category, String title, String date,
			 String description,  String fileDownloadUri) {
		super();
		this.category = category;
		this.title = title;
		this.date = date;
		this.description = description;
		this.fileDownloadUri = fileDownloadUri;
	}
	
	

	public Video(String title,String fileDownloadUri) {
		super();
		this.title = title;
		this.fileDownloadUri = fileDownloadUri;
	}





	public String getAgeLimitation() {
		return ageLimitation;
	}

	public void setAgeLimitation(String ageLimitation) {
		this.ageLimitation = ageLimitation;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}
	
	
}
