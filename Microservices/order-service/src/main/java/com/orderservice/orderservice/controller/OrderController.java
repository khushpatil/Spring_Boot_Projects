package com.orderservice.orderservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderservice.orderservice.dto.OrderRequest;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @PostMapping
    public void placeOrder(@RequestBody OrderRequest orderReq) {

    }
}
