package com.example.momo_payment.apis;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.momo_payment.models.MomoRequest;
import com.example.momo_payment.models.MomoResponse;


@FeignClient(name = "momo", url = "${momo.end-point}")
public interface  MomoApi {
    @PostMapping("/create")
    MomoResponse createPayment(@RequestBody MomoRequest momoRequest);
}
