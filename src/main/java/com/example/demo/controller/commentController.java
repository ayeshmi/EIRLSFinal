package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Comment;
import com.example.demo.service.CommentService;

@RestController
@RequestMapping("/api/auth")
public class commentController {

	@Autowired
	private CommentService commentService;
	
	//adding new book
	@PostMapping("/addCommentBook")
	public void addComment(@RequestBody Comment comment) {
		System.out.println("Hello Book"+comment.getCommentDetails());
		commentService.addComment(comment);
		//return ResponseEntity.ok(new MessageResponse("Book added successfully!"));
	}
	
	@GetMapping("/allCommentByID/{id}")
	public List<Comment> getCommentByID(@PathVariable String id){
		
	List<Comment> updateConatctUS=commentService.getBookById(id);
	System.out.println("Hello Comment"+updateConatctUS);
		return updateConatctUS;
	}
	
	
}
