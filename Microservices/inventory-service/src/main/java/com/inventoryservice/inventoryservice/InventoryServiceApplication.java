package com.inventoryservice.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.inventoryservice.inventoryservice.model.Inventory;
import com.inventoryservice.inventoryservice.repository.InventoryRepository;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepo) {
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("Iphone 13");
			inventory.setQuantity(4);

			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("Samsung Galaxy 12");
			inventory1.setQuantity(10);

			inventoryRepo.save(inventory);
			inventoryRepo.save(inventory1);
		};

	}

}
