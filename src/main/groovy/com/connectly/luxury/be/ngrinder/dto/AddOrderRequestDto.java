package com.connectly.luxury.be.ngrinder.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class AddOrderRequestDto {

    List<Long> cartIds;
    MemberDeliveryAddressDto memberDeliveryAddress;
    PaymentMethodDto paymentMethod;
    ImportPayloadRequestDto importPayloadRequest;
    ImportPayloadResponseDto importPayloadResponse;

    public AddOrderRequestDto addMerChantUid(String merchantUid){
        this.importPayloadResponse.merchantUid = merchantUid;
        return this;
    }
//    public AddOrderRequestDto generateData(){
//        return AddOrderRequestDto.builder()
//                .paymentMethod(paymentMethod.generateData())
//                .MemberDeliveryAddress(MemberDeliveryAddress.generateData())
//                .importPayloadRequest(importPayloadRequest.generateData())
//                .importPayloadResponse(importPayloadResponse.generateData())
//                .build();
//    }


}
