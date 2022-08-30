package com.example.bonuscalculation.state;

import com.example.bonuscalculation.constant.PaymentType;
import com.example.bonuscalculation.repository.Account;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

// контекстный класс платежа
@Setter
@Getter
@Slf4j
public class PaymentContext {
    //здесь храним текущее состояние
    private PaymentState state;

    //здесь храним счет клиента
    private final Account account = Account.getInstance();

    // сумма платежа
    private final BigDecimal paymentSum;

    // тип платежа
    private PaymentType paymentType;

    // конструктор для создания платежа
    public PaymentContext(BigDecimal paymentSum, PaymentType paymentType) {
        this.paymentSum = paymentSum;
        this.paymentType = paymentType;
    }

    // метод для произведения платежа, который запускает цепочку состояний
    public void proceedPayment() {

        // проверка на наличие необходимой суммы у клиента
        if (account.getFunds().compareTo(paymentSum) < 0) {
            log.error("Client doesn`t have enough funds");
            // кидаем ошибку
            throw new RuntimeException("Client doesn`t have enough funds");
        }

        // проверка на то, что сумма больше 0
        if (paymentSum.compareTo(BigDecimal.ZERO) <= 0) {
            log.error("Cannot process zero or negative payment sum");
            // кидаем ошибку
            throw new RuntimeException("Cannot process zero or negative payment sum");
        }

        // в зависимости от типа платежа создаем необходимое начальное состояние
        state = switch (paymentType) {
            case SHOP -> new ShopPayment();
            case ONLINE -> new OnlinePayment();
        };

        // запускаем следующее состояние
        nextState();
    }

    public void nextState() {
        state.next(this);
    }
}
