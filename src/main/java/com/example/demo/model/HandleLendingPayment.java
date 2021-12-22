package com.example.demo.model;

import org.springframework.stereotype.Component;

@Component
public class HandleLendingPayment implements Payment<LendingPayment>{

	@Override
	public PaymentType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void payment(LendingPayment object) {
		// TODO Auto-generated method stub
		
	}

}
