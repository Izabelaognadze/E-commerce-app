package com.example.Ecommerce.app.controller;

import com.example.Ecommerce.app.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private List<Product> products = new ArrayList<>();

    public ProductsController() {
        products.add(new Product("Apple", 200, 800, "admin"));
        products.add(new Product("Samsung", 100, 700, "admin"));
        products.add(new Product("Honor 5", 100, 600, "admin"));
    }

    @GetMapping
    public List<Product> getItems() {
        return products;
    }

    @PostMapping
    public void addItem(@RequestBody Product product) {
        if ("admin".equals(product.getUserType())) {
            if (product.getPrice() <= 0 || product.getQuantity() <= 0) {
                throw new RuntimeException("Price and quantity should be more than zero");
            }
            products.add(product);
        } else {
            throw new RuntimeException("Only admin can add a product");
        }
    }

    @PatchMapping("/{productName}")
    public void updateItem(@PathVariable String productName, @RequestBody Product updatedProduct, @RequestHeader("userType") String userType) {
        if ("admin".equals(userType)) {
            boolean updated = false;
            for (Product product : products) {
                if (product.getName().equalsIgnoreCase(productName)) {
                    product.setName(updatedProduct.getName());
                    product.setQuantity(updatedProduct.getQuantity());
                    product.setPrice(updatedProduct.getPrice());
                    updated = true;
                    System.out.println("Product updated: " + productName);
                    break;
                }
            }
            if (!updated) {
                throw new RuntimeException("Product not found");
            }
        } else {
            throw new RuntimeException("Only admin can update a product");
        }
    }


    @DeleteMapping("/{productName}")
    public void deleteItem(@PathVariable String productName, @RequestHeader("userType") String userType) {
        if ("admin".equals(userType)) {
            products.removeIf(product -> product.getName().equals(productName));
        } else {
            throw new RuntimeException("only admin can delete a product");
        }
    }
}
