package com.example.bonuscalculation.state;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class OnlinePayment implements PaymentState {
    @Override
    public void next(PaymentContext payment) {
        // сниммаем средства со счета клиента
        payment.getAccount().withdrawFunds(payment.getPaymentSum());

        log.info("Client spent {} online", payment.getPaymentSum());

        // проверяем сумму для следующего состояния
        if (payment.getPaymentSum().compareTo(new BigDecimal(20)) < 0) {
            payment.setState(new Refund());
            payment.nextState();
        } else {
            payment.setState(new Bank());
            payment.nextState();
        }
    }

}
