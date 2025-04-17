package com.example.momo_payment.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.momo_payment.apis.MomoApi;
import com.example.momo_payment.models.MomoRequest;
import com.example.momo_payment.models.MomoResponse;
import com.example.momo_payment.utils.Encoder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MomoService {

    private final MomoApi momoApi;

    @Value("${momo.partner-code}")
    private String partnerCode;

    @Value("${momo.access-key}")
    private String accessKey;

    @Value("${momo.secret-key}")
    private String secretKey;

    @Value("${momo.return-url}")
    private String redirectUrl;

    @Value("${momo.ipn-url}")
    private String ipnUrl;

    @Value("${momo.request-type}")
    private String requestType;

    public MomoResponse createPayment() {
        String requestId = String.valueOf(System.currentTimeMillis());
        String orderId = String.valueOf(System.currentTimeMillis());
        String orderInfo = "Test Order: " + orderId;
        long amount = 50000;
        String extraData = "extraData";
        String rawSignature = String.format(
                "accessKey=%s&amount=%s&extraData=%s&ipnUrl=%s&orderId=%s&orderInfo=%s&partnerCode=%s&redirectUrl=%s&requestId=%s&requestType=%s",
                accessKey, amount, extraData, ipnUrl, orderId, orderInfo, partnerCode, redirectUrl, requestId, requestType
        );
        String signature = "";
        try {
            signature = Encoder.signHmacSHA256(rawSignature, secretKey);

        } catch (Exception e) {
            log.error("Error signing HMAC SHA256: {}", e.getMessage());
        }
        MomoRequest request = MomoRequest.builder()
                .partnerCode(partnerCode)
                .requestType(requestType)
                .ipnUrl(ipnUrl)
                .redirectUrl(redirectUrl)
                .orderId(orderId)
                .orderInfo(orderInfo)
                .requestId(requestId)
                .extraData(extraData)
                .amount(amount)
                .signature(signature)
                .lang("vi")
                .build();

        return momoApi.createPayment(request);
    }
}
