package com.example._storageexam.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SaleTransactionUpdateDTO {
    public int transactionId;
    public int quantity;
    public double unitPrice;
}
