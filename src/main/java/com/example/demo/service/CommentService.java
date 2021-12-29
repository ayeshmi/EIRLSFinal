package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;

@Service
public class CommentService {


	@Autowired
	private CommentRepository commentRepository;
	
	public void addComment(Comment comment) {
		  
		commentRepository.save(comment);
		
	}

	public Comment getCommentByID(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Comment> getBookById(String id){
		Long Id=Long.parseLong(id);
		
		List<Comment>  comment=commentRepository.findByType(id);
				
		System.out.println("Hello Comment123"+comment);
		return comment;
	}
	
}
