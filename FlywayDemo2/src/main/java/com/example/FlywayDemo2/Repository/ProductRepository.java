package com.example.FlywayDemo2.Repository;

import com.example.FlywayDemo2.Model.Product;
import com.example.FlywayDemo2.Model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product , Integer> {
    List<Product>findByCategory(ProductCategory category);
}
