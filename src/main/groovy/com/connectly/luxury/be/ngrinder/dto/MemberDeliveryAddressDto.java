package com.connectly.luxury.be.ngrinder.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MemberDeliveryAddressDto {
    long deliveryAddressId;

    String deliveryAddressName;

    String memberId;

    String receiverName;

    String receiverMobilePhoneNo;

    String receiverZipCode;

    String receiverRoadAddress;

    String receiverAddressDetail;

    String receiverDeliveryRequest;

    String defaultDeliveryAddressYn;

}
