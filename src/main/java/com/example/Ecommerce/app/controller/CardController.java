package com.example.Ecommerce.app.controller;

import com.example.Ecommerce.app.model.Card;
import com.example.Ecommerce.app.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    // 1000 lari aq iyos
    private List<Card> cards = new ArrayList<>();

    @GetMapping("/{userType}")
    public Card getCard(@PathVariable String userType) {
        for (Card card : cards) {
            if (card.getUserType().equals(userType)) {
                return card;
            }
        }
        throw new RuntimeException("Card not found for user type: " + userType);
    }

    @PostMapping("/{userType}/addProduct")
    public void addProductToCard(@PathVariable String userType, @RequestBody Card card) {
        if (!userType.equals("admin")) {
            throw new RuntimeException("Unauthorized access: Only admins can add products to the card.");
        }

        else {
            cards.add(card);
        }
    }

    @PatchMapping("/{userType}")
    public void updateCardItem(@PathVariable String userType, @RequestBody Product updatedProduct) {
        for (Card card : cards) {
            if (card.getUserType().equals(userType)) {
                for (Product product : card.getCardItems()) {
                    if (product.getName().equals(updatedProduct.getName())) {
                        product.setQuantity(updatedProduct.getQuantity());
                        product.setPrice(updatedProduct.getPrice());
                        return;
                    }
                }
                throw new RuntimeException("Product not found in the card for user type: " + userType);
            }
        }
        throw new RuntimeException("Card not found for user type: " + userType);
    }

    @DeleteMapping("/{userType}")
    public void removeFromCard(@PathVariable String userType, @RequestBody Product productToRemove) {
        for (Card card : cards) {
            if (card.getUserType().equals(userType)) {
                if (!card.getCardItems().removeIf(product -> product.getName().equals(productToRemove.getName()))) {
                    throw new RuntimeException("Product not found in the card for user type: " + userType);
                }
                return;
            }
        }
        throw new RuntimeException("Card not found for user type: " + userType);
    }
}
