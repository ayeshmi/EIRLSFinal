package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="comment")
public class Comment {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentID;
	@NotBlank
	@Size(max = 60)
	private String username;
	@NotBlank
	@Size(max = 60)
	private String commentDetails;
	@NotBlank
	@Size(max = 60)
	private String type;
	@NotBlank
	@Size(max = 200)
	private String typeID;
	
	
	
	public Comment(Long commentID, String username, String commentDetails, String type, String typeID) {
		super();
		this.commentID = commentID;
		this.username = username;
		this.commentDetails = commentDetails;
		this.type = type;
		this.typeID = typeID;
	}
	
	
	public Comment() {
		
	}


	public Long getCommentID() {
		return commentID;
	}
	
	public void setCommentID(Long commentID) {
		this.commentID = commentID;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCommentDetails() {
		return commentDetails;
	}
	
	public void setCommentDetails(String commentDetails) {
		this.commentDetails = commentDetails;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getTypeID() {
		return typeID;
	}
	
	public void setTypeID(String typeID) {
		this.typeID = typeID;
	}


	
	
	
}
