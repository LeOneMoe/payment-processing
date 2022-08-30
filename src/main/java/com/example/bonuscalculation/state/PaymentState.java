package com.example.bonuscalculation.state;

// интерфейс состояния платежа, 
// все конкретные реализации состояний будут имплементировать данный интерфейс
public interface PaymentState {
    // метод для запуска состояния и перехода на следующее
    void next(PaymentContext payment);
}
