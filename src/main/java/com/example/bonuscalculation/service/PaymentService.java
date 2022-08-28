package com.example.bonuscalculation.service;

import com.example.bonuscalculation.constant.PaymentType;
import com.example.bonuscalculation.state.PaymentContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {
    public void pay(String type, BigDecimal amount) {
        PaymentType paymentType = PaymentType.valueOf(type.toUpperCase());

        PaymentContext paymentContext = new PaymentContext(amount, paymentType);
        paymentContext.proceedPayment();
    }
}
