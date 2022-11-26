package com.example.flipKart.services;

import com.example.flipKart.entity.Order;
import com.example.flipKart.exception.ProductOrderException;
import com.example.flipKart.model.CustomerModel;
import com.example.flipKart.model.OrderModel;
import com.example.flipKart.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
    @Service
    public  class OrderService {
        private static int trnIndex = 0;
        @Autowired
        OrderRepository orderRepository;

        @Autowired
        private RestTemplate rest;

        String url = "lb://PRODUCT/product/item-price/{quantity}/{itemNumber}";
        Map<String,Object> urlVariables = new HashMap<>();
        public Order saveOrder(OrderModel model, long itemId) {
            Order order = new Order();
            String trnNumber = "trn000"+ ++trnIndex;
            order.setTrnNumber(trnNumber);
            order.setItemId(itemId);
            order.setEmail(model.getEmail());
            //  order.setMealType(model.getMealType());
            order.setName(model.getName());
            order.setQuantity(model.getCustomers().size());
            StringBuilder customer = new StringBuilder();
            for(CustomerModel p:model.getCustomers()) {
                customer.append(p.getCustomerName())
                        .append(",")
                        .append(p.getCustomerAge())
                        .append(",")
                        .append(p.getCustomerGender())
                        .append(";");
            }
            order.setCustomers(customer.substring(0, customer.length()-1));
            urlVariables.put("quantity", model.getQuantity());
            urlVariables.put("itemNumber", itemId);
            ResponseEntity<Double> price = rest.getForEntity(url, double.class,urlVariables);
            order.setPrice(price.getBody() * model.getCustomers().size());
            Order ordered = orderRepository.save(order);
            return order;
        }

        public String cancelOrder(String tnr) throws ProductOrderException {
            try{
                orderRepository.deleteById(tnr);
                return tnr+" Successfully cancelled";
            }
            catch (Exception e) {
                throw new ProductOrderException("No Order found with pnr "+tnr);
            }
        }

        public Order getOrder(String tnr) throws ProductOrderException {
            return orderRepository.findById(tnr).
                    orElseThrow(()-> new ProductOrderException("No Order found with pnr "+tnr));
        }

        public List<Order> getHistory(String email) {
            return orderRepository.findByEmail(email.toLowerCase());
        }




    }

