package com.orderservice.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orderservice.orderservice.dto.ItemDto;
import com.orderservice.orderservice.dto.OrderRequest;
import com.orderservice.orderservice.dto.OrderResponse;
import com.orderservice.orderservice.model.Item;
import com.orderservice.orderservice.model.Order;
import com.orderservice.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepo;

    public void createOrder(OrderRequest orderReq) {
        Order order = new Order();
        order.setOrderNum(UUID.randomUUID().toString());

        List<Item> cart = orderReq.getCartDto().stream().map(ItemDto -> maptodto(ItemDto)).toList();

        order.setCart(cart);
        orderRepo.save(order);

    }

    private Item maptodto(ItemDto itemDto) {
        Item item = new Item();
        item.setSkucode(itemDto.getSkucode());
        item.setQuantity(itemDto.getQuantity());
        item.setPrice(itemDto.getPrice());

        return item;
    }

    public List<OrderResponse> getOrders() {
        List<OrderResponse> allorders = orderRepo.findAll().stream()
                .map(Order -> maptoResponse(Order))
                .toList();

        return allorders;
    }

    private OrderResponse maptoResponse(Order order) {
        OrderResponse orderRes = new OrderResponse();
        orderRes.setOrderNum(order.getOrderNum());
        orderRes.setCart(order.getCart());

        return orderRes;
    }
}