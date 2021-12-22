package com.example.demo.model;

import org.springframework.stereotype.Component;

@Component
public class HandleAnualMembershipPayment implements Payment<AnualMembershipPayment>{
	
	public void methods123() {
		System.out.println("Hello Book");
	}

	@Override
	public PaymentType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void payment(AnualMembershipPayment object) {
		// TODO Auto-generated method stub
		
	}
}
