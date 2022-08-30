package com.example.bonuscalculation.constant;


// enum типов платежа
public enum PaymentType {
    SHOP("Shop"),
    ONLINE("Online");

    // значение платежа в формате String
    final String value;

    PaymentType(String value) {
        this.value = value;
    }
}
