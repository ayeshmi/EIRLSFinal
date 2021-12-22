package com.example.demo.model;

import java.util.EnumMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HandleOverduePayment implements Payment<OverduePayment>{

	@Override
	public PaymentType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void payment(OverduePayment object) {
		// TODO Auto-generated method stub
		
	}
	
}
