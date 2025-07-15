package com.example._storageexam.repository;

import com.example._storageexam.entity.SaleTransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleTransactionLogRepository extends JpaRepository<SaleTransactionLog,Integer>{
}
