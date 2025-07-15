package com.example._storageexam.restcontroller;


import com.example._storageexam.dto.SaleCreationRequest;
import com.example._storageexam.dto.SaleTransactionUpdateDTO;
import com.example._storageexam.entity.Sale;
import com.example._storageexam.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("800Storage/sales")
public class SaleRestController {
    @Autowired
    private SaleService saleService;

    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable int id) {
        try {
            Sale sale = saleService.getSaleById(id);
            return ResponseEntity.ok(sale);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @PostMapping
    public ResponseEntity<Sale> createSale(@RequestBody SaleCreationRequest request) {
        try {
            Sale createdSale = saleService.createSale(request);
            return ResponseEntity.ok(createdSale);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{saleId}/transactions")
    public ResponseEntity<Void> updateSaleTransactions(
            @PathVariable int saleId,
            @RequestBody List<SaleTransactionUpdateDTO> updates,
            @RequestParam String username) {
        saleService.updateSaleTransactions(saleId, updates, username);
        return ResponseEntity.ok().build();


    }

}