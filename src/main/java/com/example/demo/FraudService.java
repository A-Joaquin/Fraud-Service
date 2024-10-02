package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class FraudService {

    private final ApiFraud apiFraud;

    public FraudService(ApiFraud apiFraud) {
        this.apiFraud = apiFraud;
    }

    public FraudResponseDto checkFraud(FraudRequestDto requestDto) {
        boolean isBlacklisted = apiFraud.isCardBlacklisted(requestDto.cardNumber());
        if (isBlacklisted) {
            return new FraudResponseDto(true, "The card is blacklisted.");
        } else {
            return new FraudResponseDto(false, "The card is not blacklisted.");
        }
    }
}
