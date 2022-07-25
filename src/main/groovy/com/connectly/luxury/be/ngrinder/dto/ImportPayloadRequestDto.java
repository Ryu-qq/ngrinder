package com.connectly.luxury.be.ngrinder.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ImportPayloadRequestDto {
    String pg;

    String payMethod;

    String merchantUid;

    String name;

    long amount;

    String buyerEmail;

    String buyerName;

    String buyerTel;

    String buyerAddr;

    String buyerPostcode;


}
