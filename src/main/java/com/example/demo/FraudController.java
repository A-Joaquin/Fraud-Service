package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fraud")
public class FraudController {

    private final FraudService fraudService;

    public FraudController(FraudService fraudService) {
        this.fraudService = fraudService;
    }

    @PostMapping("/check")
    public FraudResponseDto checkFraud(@RequestBody FraudRequestDto fraudRequestDto) {
        return fraudService.checkFraud(fraudRequestDto);
    }
}
