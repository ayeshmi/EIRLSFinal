package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Checking;
import com.example.demo.dto.MessageResponse;
import com.example.demo.model.PaymentRequest;
import com.example.demo.model.Paymentdto;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.checkingRepository;

@RestController

@RequestMapping("/api/auth")
public class PaymentController {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private checkingRepository checkRepository;
	
	@GetMapping("/paymentDetails/{email}")
	public Paymentdto  getPaymentDetailsByEmail(@PathVariable("email") String email) {
		System.out.println("Payment is called");
		Paymentdto payment=paymentRepository.findPaymentDetailsByEmail(email);
		
		return payment;
	}
	
	@GetMapping("/lendingPaymentDetails/{email}")
	public Paymentdto  getLendingPaymentDetailsByEmail(@PathVariable("email") String email) {
		System.out.println("Payment is called");
		Paymentdto payment=paymentRepository.findLendingPaymentDetailsByEmail(email);
		
		return payment;
	}

	
	@PostMapping("/addPayment")
	public ResponseEntity<Object> addPayment(@RequestBody PaymentRequest paymentdto) {
		System.out.println("Payment is called45"+paymentdto.getCvv());
		//int cvv=Integer.parseInt(paymentdto.getCvv());
		//Long cardNumber=Long.parseLong(paymentdto.getCardNumber());
		Paymentdto payment=paymentRepository.findPaymentDetailsByEmail(paymentdto.getEmail());
		payment.setCardType(paymentdto.getCardType());
		payment.setCardHolderName(paymentdto.getCardHolderName());
		//payment.setCardNumber(cardNumber);
		payment.setExpiryDate(paymentdto.getExpiryDate());
		payment.setPaymentStatus("paid");
		//payment.setCvv(cvv);
		paymentRepository.save(payment);
		System.out.println("Payment is added");
		MessageResponse message = new MessageResponse("Check inputs and try again.");
		return new ResponseEntity<>(message,HttpStatus.OK);
	
	}
	
	@PostMapping("/checking")
	public void checking(@RequestBody Checking check) {
		checkRepository.save(check);
		System.out.println(""+check.getNum1());
		
	
	}
	
	@GetMapping("/allUnpaidPayments/{email}")
	public List<Paymentdto>  viewAllPaymentsForUser(@PathVariable("email") String email) {
		System.out.println("Payment is called");
		List<Paymentdto> payment=paymentRepository.findAllPaymentDetailsByEmail(email);
		
		return payment;
	}
	
	@PostMapping("/addBookOrder")
	public List<Paymentdto>  addBookOrder(@RequestBody Paymentdto paymentdto) {
		paymentdto.setReason("OrderFee");
		paymentRepository.save(paymentdto);
		return null;
	}
	
	
}
