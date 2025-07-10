package com.example;

public class ShippingCostService {
 
    public double calcularCosto(double peso, boolean fragil) {
        double tarifaBase;
        if (peso <= 1.0) {
            tarifaBase = 5.0;
        } else if (peso <=5.0) {
            tarifaBase = 10.0;
        } else {
            tarifaBase = 20.0;
        }
    return fragil ? tarifaBase + 5.0 : tarifaBase;
    }
}
