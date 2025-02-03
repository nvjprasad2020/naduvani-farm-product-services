package com.naduvani.farm.product;

import com.naduvani.farm.product.model.entity.Category;
import com.naduvani.farm.product.model.entity.Product;
import com.naduvani.farm.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.naduvani.farm")
@RequiredArgsConstructor
public class NaduvaniFarmProductServicesApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(NaduvaniFarmProductServicesApplication.class, args);
	}

	private final ProductRepository productRepository;


	@Override
	public void run(String... args) throws Exception {
//		productRepository.deleteAll();
//		Product product1 = new Product("PRD_001", "Snake Plant", 12, Category.PLANT_INDOOR);
//		Product product2 = new Product("PRD_002", "Orchid", 15, Category.PLANT_INDOOR);
//		productRepository.save(product1);
//		productRepository.save(product2);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
}
