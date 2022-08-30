package com.example.bonuscalculation.state;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShopPayment implements PaymentState {
    @Override
    public void next(PaymentContext payment) {

        // снимаем средства
        payment.getAccount().withdrawFunds(payment.getPaymentSum());
        // следующее состояние всегда Банк
        payment.setState(new Bank());

        log.info("Client spent {}", payment.getPaymentSum());

        payment.nextState();
    }

}
