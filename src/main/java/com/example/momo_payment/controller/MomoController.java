package com.example.momo_payment.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.momo_payment.constants.MomoParameter;
import com.example.momo_payment.models.MomoResponse;
import com.example.momo_payment.services.MomoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/momo")
public class MomoController {
    private final MomoService momoService;

    @PostMapping("create")
    public MomoResponse createPayment() {
        return momoService.createPayment();
    }

    @GetMapping("ipn-handler")
    public String ipnHandler(@RequestParam Map<String, String> request) {

        Integer resultCode = Integer.valueOf(request.get(MomoParameter.RESULT_CODE));
        return resultCode == 0 ? "Giao dich thanh cong" : "Giao dich that bai";
    }
}
