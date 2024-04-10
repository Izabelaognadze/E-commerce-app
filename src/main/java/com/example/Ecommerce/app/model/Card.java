package com.example.Ecommerce.app.model;

import java.util.ArrayList;
import java.util.List;

public class Card {
    private String userType;
    private List<Product> cardItems;

    public Card() {
        this.cardItems = new ArrayList<>();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<Product> getCardItems() {
        return cardItems;
    }

    public void addCardItem(Product product) {
        Product cardItem = new Product(product.getName(), product.getQuantity(), product.getPrice(), product.getUserType());
        cardItems.add(cardItem);
    }
}
