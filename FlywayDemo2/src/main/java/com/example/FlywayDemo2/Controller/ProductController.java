package com.example.FlywayDemo2.Controller;
import com.example.FlywayDemo2.Model.Product;
import com.example.FlywayDemo2.Model.ProductCategory;
import com.example.FlywayDemo2.Services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


    @RestController
    @RequestMapping("/api/products")
    public  class ProductController {

        private ProductService productService;

        @PostMapping
        public ResponseEntity<Product> createProduct(@RequestBody Product product) {
            Product savedProduct = productService.saveProduct(product);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        }

        @GetMapping
        public ResponseEntity<List<Product>> getAllProducts() {
            List<Product> products = productService.getAllProducts();
            return ResponseEntity.ok(products);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Product> getProductById(@PathVariable int id) {
            Product product = productService.getProductById(id);
            return ResponseEntity.ok(product);
        }

        @GetMapping("/category/{category}")
        public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable ProductCategory category) {
            List<Product> products = productService.getProductsByCategory(category);
            return ResponseEntity.ok(products);
        }
    }

