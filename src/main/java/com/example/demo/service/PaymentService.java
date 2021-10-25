package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.OverduePayment;
import com.example.demo.model.PaymentFactory;
import com.example.demo.model.PaymentType;

@Service
public class PaymentService {
	@Autowired
	PaymentFactory paymentFactory;

    @Autowired
    public PaymentService(PaymentFactory paymentFactory) {
        this.paymentFactory = paymentFactory;
    }

    public void handleView() {
       // PaymentFactory.getPaymentType(PaymentType.OverduePayment).payment(new OverduePayment());
    }
}
