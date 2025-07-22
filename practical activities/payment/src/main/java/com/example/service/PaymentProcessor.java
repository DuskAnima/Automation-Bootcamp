package com.example.service;

import java.time.LocalDateTime;

import com.example.model.Payment;
import com.example.model.PaymentHistory;
import com.example.model.User;

public class PaymentProcessor {
    private final PaymentMethod creditCardPayment;
    private final PaymentMethod bankTransferPayment;
    private final PaymentHistory paymentHistory;

    public PaymentProcessor(PaymentMethod creditCardPayment, PaymentMethod bankTransferPayment, PaymentHistory paymentHistory) {
        this.creditCardPayment = creditCardPayment;
        this.bankTransferPayment = bankTransferPayment;
        this.paymentHistory = paymentHistory;
    }

    public boolean processPayment(double amount, User user, String method) {
        if (amount <= 0 || user == null) {
            throw new IllegalArgumentException("Invalid amount or user");
        }
        boolean result;
        if("CreditCard".equalsIgnoreCase(method)) {
            result = creditCardPayment.process(amount, user); // En esta línea se invoca el mock, se salta el modelo y se inyecta directamente en el servicio.
        } else if ("BankTransfer".equalsIgnoreCase(method)) {
            result = bankTransferPayment.process(amount, user);
        } else {
            throw new IllegalArgumentException("Unknown payment method");
        }
        if (result) {
            paymentHistory.add(new Payment(amount, user, method, LocalDateTime.now()));
        }
        return result;
    }

    public PaymentHistory getPaymentHistory() {
        return paymentHistory;
    }
}
