package com.example.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



public class PaymentHistoryTest {
    @Test 
    void testAddAndGetPayments() {
        PaymentHistory history = new PaymentHistory();

        User user = new User("Test User");
        Payment payment = new Payment(100, user, "CreditCard", java.time.LocalDateTime.now());
        history.add(payment);
        assertEquals(1, history.getPayments().size());
        assertEquals(payment, history.getPayments().get(0));
    }
}
