package com.example.momo_payment.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MomoRequest {
    private String partnerCode;
    private String requestType;
    private String ipnUrl;
    private String orderId;
    private long amount;
    private String orderInfo;
    private String requestId;
    private String redirectUrl;
    private String lang;
    private String extraData;
    private String signature;
}
