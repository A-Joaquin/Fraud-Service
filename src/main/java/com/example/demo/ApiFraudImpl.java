package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApiFraudImpl implements ApiFraud {

    private final List<String> blacklist;

    // Inyectamos la lista negra desde FraudProperties
    public ApiFraudImpl(FraudProperties fraudProperties) {
        this.blacklist = fraudProperties.getBlacklist();
    }

    @Override
    public boolean isCardBlacklisted(String cardNumber) {
        // Verificamos si la tarjeta está en la lista negra
        return blacklist.contains(cardNumber);
    }
}
