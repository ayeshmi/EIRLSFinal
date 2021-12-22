package com.example.demo.model;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentFactory {
	

	
	private static final Map<PaymentType, Payment> viewerMap = new EnumMap<>(PaymentType.class);

    @Autowired
    private PaymentFactory(List<Payment> payments) {
      //  for (Payment payment : payments) {
        //    viewerMap.put(payment.getType(), payment);
      //  }
    }

    public static <T> Payment<T> getPaymentType(PaymentType paymentType) {
    	Payment<T> viewer = viewerMap.get(paymentType);
        if (viewer == null) {
            throw new IllegalArgumentException();
        }
        return viewer;
    }
}
