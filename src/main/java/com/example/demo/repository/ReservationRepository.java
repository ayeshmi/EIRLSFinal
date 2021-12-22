package com.example.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Paymentdto;
import com.example.demo.model.ReservationDetails;


@Repository
public interface ReservationRepository extends JpaRepository<ReservationDetails,Long>, JpaSpecificationExecutor<ReservationDetails>{

	ReservationDetails findByUsername(String username);
	
	
	@Query(value="select COUNT(*) from book_reservation b where b.email like :keyword and lending_status='cart'", nativeQuery = true)
	int countOfBooks(@Param("keyword")String a);
	
}
