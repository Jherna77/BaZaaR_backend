package com.jhernandez.backend.bazaar.application.port;

import com.jhernandez.backend.bazaar.domain.model.Payment;

public interface PaymentProviderPort {

    Payment createPayment(Payment payment);

}
