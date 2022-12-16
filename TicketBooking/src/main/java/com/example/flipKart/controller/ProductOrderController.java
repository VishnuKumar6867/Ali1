package com.example.flipKart.controller;


import com.example.flipKart.entity.Order;

import com.example.flipKart.exception.ProductOrderException;
import com.example.flipKart.model.OrderModel;

import com.example.flipKart.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/shopping")
public class ProductOrderController {
    @Autowired
    OrderService orderService;


    private static final String GET_FLIPKART= "http://localhost:6502/flipkart/item-price/{quantity}/{itemNumber}";
    private static RestTemplate restTemplate = new RestTemplate();
    private void getDetailsById() {

        Map< String, String > params = new HashMap< String, String >();
        params.put("itemId", "2");

         RestTemplate restTemplate = new RestTemplate();
        Order result = restTemplate.getForObject(GET_FLIPKART,Order.class, params);

        System.out.println(result);
    }
    @PostMapping("/{itemId}")
    public ResponseEntity<Order> save(@RequestBody OrderModel model, @PathVariable long itemId) {
        Order order = orderService.saveOrder(model,itemId);
        return new ResponseEntity<>(order,order != null? HttpStatus.OK:HttpStatus.NO_CONTENT);
    }

    @GetMapping("/order/{trn}")
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
