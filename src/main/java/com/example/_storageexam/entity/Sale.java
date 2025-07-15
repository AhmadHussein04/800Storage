package com.example._storageexam.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sale")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "creation_date")
    private LocalDateTime creation_date;

    @Column(name = "seller")
    private String seller;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    @JsonManagedReference  // This tells Jackson to include this in JSON
    private List<SaleTransaction> transactions = new ArrayList<>();
}
