package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Book;
import com.example.demo.model.Comment;
import com.example.demo.model.ContactUs;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long>{
	List<Comment> findByType(String id);
}
