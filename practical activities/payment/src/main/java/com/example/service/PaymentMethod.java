package com.example.service;

import com.example.model.User;

public interface PaymentMethod {
    
    boolean process(double amount, User user);

    String getMethodName();
}
