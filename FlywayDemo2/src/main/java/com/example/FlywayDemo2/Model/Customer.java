package com.example.FlywayDemo2.Model;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

    @Entity
    @Table(name = "customers")
    public class Customer {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(nullable = false, length = 100)
        private String name;

        @Column(nullable = false, length = 150, unique = true)
        private String email;

        @Column(name = "password_hash", nullable = false, length = 255)
        private String passwordHash;

        @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Order> orders = new ArrayList<>();

        public Customer() {
        }

        public Customer(String name, String email, String passwordHash) {
            this.name = name;
            this.email = email;
            this.passwordHash = passwordHash;
        }

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPasswordHash() { return passwordHash; }
        public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

        public List<Order> getOrders() { return orders; }
        public void setOrders(List<Order> orders) { this.orders = orders; }

        @Override
        public String toString() {
            return "Customer{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", passwordHash='" + passwordHash + '\'' +
                    ", orders=" + orders +
                    '}';
        }
    }


