package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.BookReservation;

public interface BookReservationRepository extends JpaRepository<BookReservation,Long>{
	
}
