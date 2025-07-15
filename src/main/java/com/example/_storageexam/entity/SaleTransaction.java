package com.example._storageexam.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "saletransaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SaleTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "unit_price")
    private double unit_price;

    @ManyToOne
    private Product product;

    @ManyToOne
    @JsonBackReference  // This prevents infinite loop
    private Sale sale;
}
