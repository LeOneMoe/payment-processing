package com.example.bonuscalculation.state;

import com.example.bonuscalculation.constant.PaymentType;
import com.example.bonuscalculation.repository.Account;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

// контекстный клвсс платежа
@Setter
@Getter
@Slf4j
public class PaymentContext {
    private PaymentState state;

    private final Account account = Account.getInstance();

    private final BigDecimal paymentSum;
    private PaymentType paymentType;

    public PaymentContext(BigDecimal paymentSum, PaymentType paymentType) {
        this.paymentSum = paymentSum;
        this.paymentType = paymentType;
    }


    public void proceedPayment() {
        if (account.getFunds().compareTo(paymentSum) < 0) {
            log.error("Client doesn`t have enough funds");
            throw new RuntimeException("Client doesn`t have enough funds");
        }

        if (paymentSum.compareTo(BigDecimal.ZERO) <= 0) {
            log.error("Cannot process zero or negative payment sum");
            throw new RuntimeException("Cannot process zero or negative payment sum");
        }

        state = switch (paymentType) {
            case SHOP -> new ShopPayment();
            case ONLINE -> new OnlinePayment();
        };

        nextState();
    }

    public void nextState() {
        state.next(this);
    }
}
