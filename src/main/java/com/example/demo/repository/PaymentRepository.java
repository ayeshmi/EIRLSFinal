package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Paymentdto;


@Repository
public interface PaymentRepository extends JpaRepository<Paymentdto,Long> , JpaSpecificationExecutor<Paymentdto>{
	@Query(value="select * from payment b where b.email like :keyword and b.reason like 'registrationFee';", nativeQuery = true)
	Paymentdto findPaymentDetailsByEmail(@Param("keyword")String a);
	
	@Query(value="select * from payment b where b.email like :keyword and b.reason like 'LendingFee';", nativeQuery = true)
	Paymentdto findLendingPaymentDetailsByEmail(@Param("keyword")String a);
	
	@Query(value="select * from payment b where b.email like :keyword and b.payment_status like 'unpaid';", nativeQuery = true)
	List<Paymentdto> findAllPaymentDetailsByEmail(@Param("keyword")String a);
	
}
