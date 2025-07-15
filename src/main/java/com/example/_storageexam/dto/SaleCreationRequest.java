package com.example._storageexam.dto;

import jakarta.validation.Valid;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SaleCreationRequest {
    private int clientId;
    private String seller;
    @Valid
    private List<SaleTransactionDTO> transactions;
}
