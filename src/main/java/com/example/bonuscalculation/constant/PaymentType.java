package com.example.bonuscalculation.constant;

public enum PaymentType {
    SHOP("Shop"),
    ONLINE("Online");

    final String value;

    PaymentType(String value) {
        this.value = value;
    }
}
