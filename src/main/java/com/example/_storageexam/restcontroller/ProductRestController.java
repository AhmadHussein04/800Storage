package com.example._storageexam.restcontroller;

import com.example._storageexam.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example._storageexam.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("800Storage/products")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product newproduct) {
        Optional<Product> updatedProduct = productService.updateProduct(id, newproduct);

        if (updatedProduct.isPresent()) {
            return ResponseEntity.ok(updatedProduct.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        try {
            Optional<Product> product = productService.getProductById(id);
            if (product.isPresent()) {
                return ResponseEntity.ok(product.get());
            } else {
                return ResponseEntity.status(404).body("Product not found with id: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error fetching product: " + e.getMessage());
        }
    }

}
