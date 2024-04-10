package com.example.Ecommerce.app.controller;
import com.example.Ecommerce.app.model.Orders;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    private List<Orders> ordersList = new ArrayList<>();

    @GetMapping("/{productName}")
    public Orders getOrder(@PathVariable String userType) {
        for (Orders order : ordersList) {
            if (order.getProduct().getName().equals(userType)) {
                return order;
            }
        }//todo userTypes mixedvit iyos da იუზერის ორდერე
        throw new RuntimeException("Order not found for product: " + userType);
    }

    //todo პოსტ რექუესტი დაამატე იუზერტაიპის მიხედვით

    @PatchMapping("/{productName}")
    public void updateOrderBalance(@PathVariable String productName) {
        for (Orders order : ordersList) {
            if (order.getProduct().getName().equals(productName)) {
                order.setBalance(1000);
                return;
            }
        }
        throw new RuntimeException("Order not found for product: " + productName);
    }
//admins da tavis uzers sheudzlia usertype, order name, product name.
    @DeleteMapping("/{productName}")
    public void deleteOrder(@PathVariable String productName) {
        ordersList.removeIf(order -> order.getProduct().getName().equals(productName));
    }
}
