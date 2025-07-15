package com.example._storageexam.service;

import com.example._storageexam.dto.SaleCreationRequest;
import com.example._storageexam.dto.SaleTransactionDTO;
import com.example._storageexam.dto.SaleTransactionUpdateDTO;
import com.example._storageexam.entity.*;
import com.example._storageexam.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    private ClientRepository clientRepo;
    @Autowired
    private SaleRepository saleRepo;
    @Autowired
    private SaleTransactionRepository trxRepo;
    @Autowired
    private SaleTransactionLogRepository logRepo;
    @Autowired
    private  ProductRepository productRepo;


    public Sale getSaleById(int id) {
        Optional<Sale> saleOpt = saleRepo.findById(id);

        if (saleOpt.isEmpty()) {
            throw new RuntimeException("Sale with ID " + id + " not found");
        }

        return saleOpt.get();
    }


    public Sale createSale(SaleCreationRequest request) {
        Optional<Client> clientOpt = clientRepo.findById(request.getClientId());
        if (!clientOpt.isPresent()) {
            throw new RuntimeException("Client not found");
        }
        Client client = clientOpt.get();

        Sale sale = new Sale();
        sale.setClient(client);
        sale.setSeller(request.getSeller());

        List<SaleTransaction> transactions = new ArrayList<>();
        for (SaleTransactionDTO trxDto : request.getTransactions()) {
            Optional<Product> productOpt = productRepo.findById(trxDto.getProductId());
            if (!productOpt.isPresent()) {
                throw new RuntimeException("Product not found");
            }
            Product product = productOpt.get();

            SaleTransaction trx = new SaleTransaction();
            trx.setProduct(product);
            trx.setQuantity(trxDto.getQuantity());
            trx.setUnit_price(trxDto.getUnitPrice());
            trx.setSale(sale);
            transactions.add(trx);
        }
        sale.setTransactions(transactions);

        return saleRepo.save(sale);
    }


    @Transactional
    public void updateSaleTransactions(int saleId, List<SaleTransactionUpdateDTO> updates, String username) {
        for (SaleTransactionUpdateDTO dto : updates) {
            Optional<SaleTransaction> trxOpt = trxRepo.findById(dto.transactionId);
            if (!trxOpt.isPresent()) {
                throw new RuntimeException("Transaction not found");
            }

            SaleTransaction trx = trxOpt.get();

            if (trx.getSale().getId() != saleId) {
                throw new IllegalArgumentException("Transaction does not belong to the specified sale");
            }

            boolean quantityChanged = trx.getQuantity() != dto.quantity;
            boolean priceChanged = trx.getUnit_price() != dto.unitPrice;

            if (quantityChanged || priceChanged) {
                SaleTransactionLog log = new SaleTransactionLog();
                log.setTransaction_id(trx.getId());
                log.setOld_quantity(trx.getQuantity());
                log.setNew_quantity(dto.quantity);
                log.setOld_price(trx.getUnit_price());
                log.setNew_price(dto.unitPrice);
                log.setUpdated_by(username);
                log.setUpdate_time(LocalDateTime.now());

                trx.setQuantity(dto.quantity);
                trx.setUnit_price(dto.unitPrice);

                trxRepo.save(trx);
                logRepo.save(log);
            }
        }
    }
 }

