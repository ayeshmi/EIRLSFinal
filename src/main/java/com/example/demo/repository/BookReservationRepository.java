package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BookReservation;


@Repository
public interface BookReservationRepository extends JpaRepository<BookReservation,Long> , JpaSpecificationExecutor<BookReservation>{
	
	BookReservation findByEmail(String email);
	
	@Query(value="select * from book_reservation b where b.email like :keyword and lending_status='cart' and lending_status= NULL", nativeQuery = true)
	List<BookReservation> getAllCartBookReservation(@Param("keyword")String a);
	
	@Query(value="select * from book_reservation b where b.email like :keyword and status= 'ongoing'", nativeQuery = true)
	List<BookReservation> getAllOngoingBookReservation(@Param("keyword")String a);
	
	@Query(value="select * from book_reservation b where status != 'completed'", nativeQuery = true)
	List<BookReservation> viewAllBookReservation();
}
