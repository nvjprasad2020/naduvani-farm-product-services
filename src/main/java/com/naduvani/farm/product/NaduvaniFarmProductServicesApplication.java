package com.naduvani.farm.product;

import com.naduvani.farm.product.model.entity.Category;
import com.naduvani.farm.product.model.entity.Product;
import com.naduvani.farm.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@RequiredArgsConstructor
@EnableCaching
public class NaduvaniFarmProductServicesApplication implements CommandLineRunner {

	private final ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(NaduvaniFarmProductServicesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		productRepository.deleteAll();
		Product product1 = new Product("PRD_001", "Snake Plant", 12, Category.PLANT_INDOOR);
		Product product2 = new Product("PRD_002", "Orchid", 15, Category.PLANT_INDOOR);
		productRepository.save(product1);
		productRepository.save(product2);
	}
}
