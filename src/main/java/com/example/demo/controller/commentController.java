package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MessageResponse;
import com.example.demo.model.Book;
import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;
import com.example.demo.service.CommentService;

@RestController
@RequestMapping("/api/auth")
public class commentController {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private CommentRepository commentRepository;
	
	//adding new book
	@PostMapping("/addCommentBook")
	public void addComment(@RequestBody Comment comment) {
		System.out.println("id is"+comment.getUserID());
		commentService.addComment(comment);
		//return ResponseEntity.ok(new MessageResponse("Book added successfully!"));
	}
	
	@GetMapping("/allCommentByID/{id}")
	public ResponseEntity<Object> getCommentByID(@PathVariable String id){
		
	List<Comment> comments=commentService.getBookById(id);
	
	if( comments.size() != 0) {
		return ResponseEntity.ok(comments);
	}
	else {
		MessageResponse message =new MessageResponse("No Records found!");
		return ResponseEntity.badRequest().body(message);
	}
		
	}
	
	@DeleteMapping("/deleteCommentByID/{id}/{userID}")
	public void deleteCommentByID(@PathVariable("id") String id,@PathVariable("userID") Long user){
		
	Comment comment=commentRepository.deleteComment(id, user);
	
	commentRepository.delete(comment);
	System.out.println("Successfull");
		
	}
	
	@DeleteMapping("/deleteInappropiateCommentByID/{id}")
	public void deleteInappropiateCommentByID(@PathVariable("id") Long id){
	System.out.println("Hello u did");	
	Comment comment=commentRepository.findById(id).orElseThrow();
	
	commentRepository.delete(comment);
	System.out.println("Successfull");
		
	}
	
	
}
