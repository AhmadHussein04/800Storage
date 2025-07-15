package com.example._storageexam.dto;

import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SaleTransactionDTO {
     private int productId;
    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;
    private double unitPrice;
}
