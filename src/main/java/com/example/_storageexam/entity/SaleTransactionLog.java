package com.example._storageexam.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "saletransactionlog")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SaleTransactionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "transaction_id")
    private int transaction_id;
    @Column(name = "old_quantity")
    private int old_quantity;
    @Column(name = "new_quantity")
    private int new_quantity;
    @Column(name = "old_price")
    private double old_price;
    @Column(name = "new_price")
    private double new_price;
    @Column(name = "updated_by")
    private String updated_by;
    @Column(name = "update_time")
    private LocalDateTime update_time;
}
