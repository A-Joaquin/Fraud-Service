package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FraudServiceTest {

    private ApiFraud apiFraud;
    private FraudService fraudService;

    @BeforeEach
    void setUp() {
        apiFraud = Mockito.mock(ApiFraud.class);
        fraudService = new FraudService(apiFraud);
    }

    @Test
    void checkFraud_CardBlacklisted() {
        FraudRequestDto requestDto = new FraudRequestDto("1234567812345678");
        when(apiFraud.isCardBlacklisted(requestDto.cardNumber())).thenReturn(true);

        FraudResponseDto response = fraudService.checkFraud(requestDto);
        assertTrue(response.isFraudulent());
        assertEquals("The card is blacklisted.", response.message());
        verify(apiFraud).isCardBlacklisted(requestDto.cardNumber());
    }

    @Test
    void checkFraud_CardNotBlacklisted() {
        FraudRequestDto requestDto = new FraudRequestDto("1111222233334444");
        when(apiFraud.isCardBlacklisted(requestDto.cardNumber())).thenReturn(false);

        FraudResponseDto response = fraudService.checkFraud(requestDto);
        assertFalse(response.isFraudulent());
        assertEquals("The card is not blacklisted.", response.message());
        verify(apiFraud).isCardBlacklisted(requestDto.cardNumber());
    }
}
