package com.example.service;

import com.example.model.User;

public class BankTransferPayment implements PaymentMethod {
    @Override
    public boolean process(double amount, User user){
        return true;
    }

    @Override
    public String getMethodName() {
        return "BankTransfer";
    }
}
