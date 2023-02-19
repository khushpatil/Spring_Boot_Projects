package com.orderservice.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import com.orderservice.orderservice.dto.InventoryResponse;
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

    private final WebClient webClient;

    private final OrderRepository orderRepo;

    public void createOrder(OrderRequest orderReq) {
        Order order = new Order();
        order.setOrderNum(UUID.randomUUID().toString());

        List<Item> cart = orderReq.getCartDto().stream().map(ItemDto -> maptodto(ItemDto)).toList();
        order.setCart(cart);

        List<String> skuCodes = order.getCart().stream().map(Item -> Item.getSkuCode()).toList();

        InventoryResponse[] result = webClient.get()
                .uri("http://localhost:8082/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductsInStock = Arrays.stream(result).allMatch(InventoryResponse::isInStock);

        if (allProductsInStock)
            orderRepo.save(order);
        else {
            throw new IllegalArgumentException("Product is not in stock");
        }

    }

    private Item maptodto(ItemDto itemDto) {
        Item item = new Item();
        item.setSkuCode(itemDto.getSkuCode());
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