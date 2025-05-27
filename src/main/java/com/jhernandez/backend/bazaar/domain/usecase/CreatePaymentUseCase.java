package com.jhernandez.backend.bazaar.domain.usecase;

import com.jhernandez.backend.bazaar.domain.exception.PaymentException;
import com.jhernandez.backend.bazaar.domain.model.Payment;

public interface CreatePaymentUseCase {

    Payment createPayment(Payment payment) throws PaymentException;

}
