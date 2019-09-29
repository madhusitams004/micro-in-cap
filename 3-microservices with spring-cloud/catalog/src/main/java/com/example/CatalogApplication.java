package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.example.model.Item;
import com.example.repository.ItemRepository;


@EnableDiscoveryClient
@SpringBootApplication
public class CatalogApplication{

	public static void main(String[] args) {
		SpringApplication.run(CatalogApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ItemRepository itemRepository,Environment environment) {
		return args -> {

			Item vegItem = new Item();
			vegItem.setId(1L);
			vegItem.setName("Veg");
			vegItem.setPrice(100.00);
			vegItem.setCanBuy(true);
			vegItem.setImgPath("/images/veg.png");
			vegItem.setDescription("veg is yummy & healthy");

			Item nonVegItem = new Item();
			nonVegItem.setId(2L);
			nonVegItem.setName("Non-Veg");
			nonVegItem.setPrice(120.00);
			nonVegItem.setCanBuy(true);
			nonVegItem.setImgPath("/images/non-veg.png");
			nonVegItem.setDescription("non-veg is yummy & but not always healthy");

			itemRepository.save(vegItem);
			itemRepository.save(nonVegItem);
			

		};
	}

}
