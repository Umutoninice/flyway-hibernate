package com.example.FlywayDemo2.Model;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "product_id")
        private int productId;

        @Column(nullable = false, length = 150)
        private String name;

        @Column(nullable = false, precision = 10, scale = 2)
        private BigDecimal price;

        @Column(name = "stock_quantity", nullable = false)
        private Integer stockQuantity = 0;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private ProductCategory category = ProductCategory.ELECTRONICS;

        public Product() {
        }

        public Product(String name, BigDecimal price, Integer stockQuantity, ProductCategory category) {
            this.name = name;
            this.price = price;
            this.stockQuantity = stockQuantity;
            this.category = category;
        }

        public int getProductId() { return productId; }
        public void setProductId(int  productId) { this.productId = productId; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public BigDecimal getPrice() { return price; }
        public void setPrice(BigDecimal price) { this.price = price; }

        public Integer getStockQuantity() { return stockQuantity; }
        public void setStockQuantity(Integer stockQuantity) { this.stockQuantity = stockQuantity; }

        public ProductCategory getCategory() { return category; }
        public void setCategory(ProductCategory category) { this.category = category; }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", category=" + category +
                '}';
    }
};


