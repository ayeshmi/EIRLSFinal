package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.example.demo.model.Comment;



@Repository
public interface CommentRepository extends JpaRepository<Comment,Long>, JpaSpecificationExecutor<Comment>{
	List<Comment> findByType(String id);
	
	@Query(value="select * from comment b where b.userid like :keyword2 and b.commentid like :keyword1", nativeQuery = true)
	Comment deleteComment(@Param("keyword1")String b,@Param("keyword2")Long a);
	
	
}
