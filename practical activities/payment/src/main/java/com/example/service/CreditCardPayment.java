package com.example.service;

import com.example.model.User;

public class CreditCardPayment implements PaymentMethod {
    @Override
    public boolean process(double amount, User user){
        return true;
    }

    @Override
    public String getMethodName() {
        return "CreditCard";
    }
}
