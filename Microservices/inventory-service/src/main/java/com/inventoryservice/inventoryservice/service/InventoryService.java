package com.inventoryservice.inventoryservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventoryservice.inventoryservice.dto.InventoryResponse;
import com.inventoryservice.inventoryservice.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepo;

    @Transactional(readOnly = true)
    public List<InventoryResponse> inStock(List<String> skuCode) {
        return inventoryRepo.findBySkuCodeIn(skuCode)
                .stream().map(inventory -> InventoryResponse.builder()
                        .skuCode(inventory.getSkuCode())
                        .isInStock(inventory.getQuantity() > 0)
                        .build())
                .toList();
    }
}
