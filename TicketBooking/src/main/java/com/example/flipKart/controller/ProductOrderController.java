package com.example.flipKart.controller;


import com.example.flipKart.entity.Order;

import com.example.flipKart.exception.ProductOrderException;
import com.example.flipKart.model.OrderModel;

import com.example.flipKart.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class ProductOrderController {
    @Autowired
   private OrderService orderService;

    @PostMapping("/{itemId}")
    public ResponseEntity<Order> save(@RequestBody OrderModel model, @PathVariable long itemId) {
        Order order = orderService.saveOrder(model,itemId);
        return new ResponseEntity<>(order,order != null? HttpStatus.OK:HttpStatus.NO_CONTENT);
    }

    @GetMapping("/order/{tnr}")
    public ResponseEntity<Order> getOrder(@PathVariable String trn) throws ProductOrderException {
        Order order = orderService.getOrder(trn);
        return new ResponseEntity<>(order,order != null? HttpStatus.OK:HttpStatus.NO_CONTENT);
    }

    @GetMapping("/history/{email}")
    public ResponseEntity<List<Order>> getHistoryByEmailId(@PathVariable String email){
        List<Order>orders = orderService.getHistory(email);
        return !CollectionUtils.isEmpty(orders) ? new ResponseEntity<>(orders,HttpStatus.OK) :  new ResponseEntity<>(orders,HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/cancel/{tnr}")
    public String cancelOrder(@PathVariable String tnr) throws ProductOrderException{
        return orderService.cancelOrder(tnr);
    }
}
