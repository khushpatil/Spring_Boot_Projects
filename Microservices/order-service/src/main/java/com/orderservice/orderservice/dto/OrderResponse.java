package com.orderservice.orderservice.dto;

import java.util.List;

import com.orderservice.orderservice.model.Item;

import lombok.Data;

@Data
public class OrderResponse {
    private String orderNum;
    private List<Item> cart;
}
