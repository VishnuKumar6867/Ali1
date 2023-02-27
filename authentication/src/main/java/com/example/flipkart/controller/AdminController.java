package com.example.flipkart.controller;

import com.example.flipkart.entity.LoginDetails;
import com.example.flipkart.exception.AuthenticationException;
import com.example.flipkart.models.*;
import com.example.flipkart.services.AdminService;
import com.example.flipkart.services.LoginDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class AdminController {
    @Autowired
    LoginDetailsService service;
    @Autowired
    private AdminService adminService;

    @GetMapping("/home")
    public ResponseEntity<LoginDetails> home() {
        System.out.println("in home");
        return new ResponseEntity(new LoginDetails(),HttpStatus.OK);
    }

    @GetMapping("/user")
    public String userPage() {
        System.out.println("in user method");
        return "User Page";
    }

    @GetMapping("/admin")
    public String adminPage() {
        System.out.println("in admin method");
        return "Admin Page"+service.getByUsername("admin");
    }
    @PostMapping("/home/createuser")
    public String createUser(@RequestBody LoginDetails details) throws AuthenticationException {
        details.setRole("USER");
        return service.createUser(details).toString();
    }
    @GetMapping("/home/geteuser/{username}")
    public LoginDetails getUser(@PathVariable String username) {
       return service.getByUsername(username);
    }

    @GetMapping("/home/createadmin")
    public ResponseEntity<LoginDetails> createUser() throws AuthenticationException {
        LoginDetails details = new LoginDetails("admin","admin@123", "ADMIN");
        details = service.createUser(details);
        return new ResponseEntity<>(details, details!=null ? HttpStatus.OK:HttpStatus.NO_CONTENT);
    }
    @GetMapping("/admin/product/getAllProducts")
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        List<ProductResponse> response = adminService.getAllProduct();
        return new ResponseEntity<>(response, response != null ? HttpStatus.OK :HttpStatus.NO_CONTENT);
    }

    @PostMapping("/admin/product/register")
    public ResponseEntity<ProductResponse> registerProducts(@RequestBody ProductModel productModel){
       ProductResponse acknowledgement = adminService.registerProduct(productModel);
        return new ResponseEntity<>(acknowledgement, Objects.nonNull(acknowledgement) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @PutMapping("/admin/product/block/{productName}")
    public ResponseEntity<ProductResponse> blockProduct(@PathVariable String productName)
    {
       ProductResponse acknowledgement = adminService.blockProduct(productName);
        return new ResponseEntity<>(acknowledgement,Objects.nonNull(acknowledgement) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @PutMapping("/admin/product/unblock/{productName}")
    public ResponseEntity<ProductResponse> unBlockProduct(@PathVariable String productName)
    {
       ProductResponse acknowledgement = adminService.unBlockProduct(productName);
        return new ResponseEntity<>(acknowledgement,Objects.nonNull(acknowledgement) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @PostMapping("/admin/product/addItem")
    public ResponseEntity<ItemResponse> addItem(@RequestBody ItemModel itemModel) {
        ItemResponse item = adminService.addItem(itemModel);
        return new ResponseEntity<>(item, item != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }


    @PostMapping("/user/order/{itemId}")
    public ResponseEntity<OrderResponse> addOrder(@RequestBody OrderModel model, @PathVariable long itemId){
       OrderResponse response = adminService.saveOrder(model, itemId);
        return new ResponseEntity<>(response, response != null ? HttpStatus.OK :HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/order/{tnr}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable String tnr){
        OrderResponse response = adminService.getOrder(tnr);
        return new ResponseEntity<>(response, response != null ? HttpStatus.OK :HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/order/history/{email}")
    public ResponseEntity<List<OrderResponse>> historybyMail(@PathVariable String email){
        List<OrderResponse> response = adminService.historyByMail(email);
        return new ResponseEntity<>(response, response != null ? HttpStatus.OK :HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/user/order/cancel/{tnr}")
    public ResponseEntity<Result> cancelTicket(@PathVariable String tnr){
        String response = adminService.cancelOrder(tnr);
        Result result = new Result(response);
        return new ResponseEntity<>(result, response != null ? HttpStatus.OK :HttpStatus.NO_CONTENT);
    }
}
