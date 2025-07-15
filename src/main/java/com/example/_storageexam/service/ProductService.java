package com.example._storageexam.service;

import com.example._storageexam.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example._storageexam.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> updateProduct(int id, Product newProduct) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(newProduct.getName());
            product.setDescription(newProduct.getDescription());
            product.setCategory(newProduct.getCategory());
            productRepository.save(product);
            return Optional.of(product);
        } else {
            return Optional.empty();
        }

    }
    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

}