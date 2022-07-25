package com.connectly.luxury.be.ngrinder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class ImportPayloadResponseDto {
    String impUid;
    String merchantUid;
    long paidAmount;

}
