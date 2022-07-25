package com.connectly.luxury.be.ngrinder.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PaymentMethodDto {
    long paymentMethodId;

    String paymentMethodName;

    String paymentMethodDisplayName;

    String paymentMethodDisplayYn;

    public PaymentMethodDto generateData(){
        this.paymentMethodId=3L;
        this.paymentMethodName="card";
        this.paymentMethodDisplayName = "신용카드";
        this.paymentMethodDisplayYn="Y";
        return this;

    }
}
