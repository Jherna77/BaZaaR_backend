package com.jhernandez.backend.bazaar.infrastructure.adapter.api.controller;

import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.PaymentRequestDto;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.PAYMENTS;

@RestController
@RequestMapping(PAYMENTS)
public class PaymentController {

    // @PostMapping("/create-payment-intent")
    // public Map<String, String> createPaymentIntent(@RequestBody Map<String, Object> requestData) throws Exception {
    //     Long amount = Long.parseLong(requestData.get("amount").toString());

    //     PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
    //             .setAmount(amount)
    //             .setCurrency("euros")
    //             .build();

    //     PaymentIntent intent = PaymentIntent.create(params);

    //     Map<String, String> response = new HashMap<>();
    //     response.put("clientSecret", intent.getClientSecret());
    //     return response;
    // }

    @PostMapping("/create-payment-intent")
    // public ResponseEntity<Map<String, String>> createPaymentIntent(@RequestBody PaymentRequest paymentRequest) {
    public ResponseEntity<?> createPaymentIntent(@RequestBody PaymentRequestDto paymentRequest) throws StripeException {
        // Validate the payment request
        if (paymentRequest.getAmount() == null || paymentRequest.getCurrency() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid payment request");
        }

        // Create a PaymentIntent with the specified amount and currency
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(paymentRequest.getAmount())
                .setCurrency(paymentRequest.getCurrency())
                .build();

            PaymentIntent intent = PaymentIntent.create(params);
            Map<String, String> responseData = new HashMap<>();
            responseData.put("clientSecret", intent.getClientSecret());
            return ResponseEntity.ok(responseData);
       
    }

}
