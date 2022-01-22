package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.example.demo.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>  , JpaSpecificationExecutor<Book>{
	Book findByTitle(String title);
	
	//ArrayList<Video> video = new ArrayList<>(2);
	
	List<Book> findByCategory(String category);
	
	Book findByInumber(Long inumber);
	
	@Query(value="select * from book b where b.title like %:keyword% or b.book_description like %:keyword% or b.author like %:keyword% or b.category like %:keyword%", nativeQuery = true)
	List<Book> search(@Param("keyword")String a);
	
	Boolean existsByInumber(Long inumber);

	Boolean existsByTitle(String title);
}
