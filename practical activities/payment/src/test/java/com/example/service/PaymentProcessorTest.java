package com.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.model.Payment;
import com.example.model.PaymentHistory;
import com.example.model.User;

@ExtendWith(MockitoExtension.class) // Habilita extensión para trabajar con mockito y junit.
public class PaymentProcessorTest {
    
    @Mock
    private PaymentMethod creditCardPayment;

    @Mock 
    private PaymentMethod bankTransferPayment;

    @Mock
    private PaymentHistory paymentHistory;

    @Captor // Permite capturar argumentos pasados a métodos mockeados
    private ArgumentCaptor<Payment> paymentCaptor;

    @InjectMocks
    private PaymentProcessor paymentProcessor;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("Richard Stallman");
        paymentProcessor = new PaymentProcessor(creditCardPayment, bankTransferPayment, paymentHistory); // Injecta los mocks
    }

    @Test
    void testProcessPayment_CreditCardSuccess() {
        given(creditCardPayment.process(100, user)). // Simulando la tarjeta de credito
        willReturn(true); // No estamos llamando a la clase, estamos simulando la llamada del proceso de pago
        boolean result = paymentProcessor.processPayment(100, user, "CreditCard");
        assertTrue(result); // Verifica que el resultado sea exitoso

        // Esto verifica que el flujo completo de tarjetas de crédito funciona correctamente.
        verify(creditCardPayment).process(100, user); //Verifica que el método fue llamado exitosamente
        verify(paymentHistory).add(any(Payment.class)); // Estamos verificando SOLAMENTE que se haya llamado al método .add()

    }

    @Test
    void testProcessPayment_BankTransferSuccess() {
        given(bankTransferPayment.process(200, user)). // Simulando la tarjeta de credito
        willReturn(true); // No estamos llamando a la clase, estamos simulando la llamada del proceso de pago
        boolean result = paymentProcessor.processPayment(200, user, "BankTransfer");
        assertTrue(result); // Verifica que el resultado sea exitoso

        // Esto verifica que el flujo completo de tarjetas de crédito funciona correctamente.
        verify(bankTransferPayment).process(200, user); //Verifica que el método fue llamado exitosamente
        verify(paymentHistory).add(any(Payment.class)); // Estamos verificando SOLAMENTE que se haya llamado al método .add()

    }

    @Test 
    void testProcessPayment_InvalidAmount(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> 
        paymentProcessor.processPayment(0, user, "CreditCard"));
        
        assertEquals("Invalid amount or user", exception.getMessage());
    }

    @Test 
    void testProcessPayment_NullUser(){
        Exception exceptionCreditCard = assertThrows(IllegalArgumentException.class, () -> 
        paymentProcessor.processPayment(100, null, "CreditCard"));
        Exception exceptionBankTransfer = assertThrows(IllegalArgumentException.class, () -> 
        paymentProcessor.processPayment(100, null, "CreditCard"));
        
        assertEquals("Invalid amount or user", exceptionCreditCard.getMessage());
        assertEquals("Invalid amount or user", exceptionBankTransfer.getMessage());
    }

    @Test
    void testProcessPayment_FailureInMethod() {
        given(creditCardPayment.process(100, user)).willReturn(false);
        boolean result = paymentProcessor.processPayment(100, user, "CreditCard");
        assertFalse(result);
        verify(creditCardPayment).process(100, user);
        verify(paymentHistory, never()).add(any(Payment.class));
    }

    @Test 
    void testPaymentHistory_Captor() {
        given(creditCardPayment.process(150, user)).willReturn(true);
        paymentProcessor.processPayment(150, user, "CreditCard");
        verify(paymentHistory).add(paymentCaptor.capture());
        Payment payment = paymentCaptor.getValue();

        assertEquals(150, payment.getAmount());
        assertEquals(user, payment.getUser());
        assertEquals("CreditCard", payment.getMethod());

    }
}
