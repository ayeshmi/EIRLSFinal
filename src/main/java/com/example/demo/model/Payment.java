package com.example.demo.model;

public interface Payment<T> {
	PaymentType getType();
    void payment(T object);
}
