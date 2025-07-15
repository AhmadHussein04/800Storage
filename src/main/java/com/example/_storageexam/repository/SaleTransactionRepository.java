package com.example._storageexam.repository;

import com.example._storageexam.entity.SaleTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleTransactionRepository extends JpaRepository<SaleTransaction, Integer> {
}
