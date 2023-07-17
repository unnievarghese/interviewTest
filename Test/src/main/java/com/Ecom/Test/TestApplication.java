package com.Ecom.Test;

import com.Ecom.Test.Model.Product;
import com.Ecom.Test.Model.Sale;
import com.Ecom.Test.Repository.ProductRepository;
import com.Ecom.Test.Repository.SaleRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);

		ProductRepository productRepository = (ProductRepository) SpringApplicationContext.getBean("productRepository");

		Product product = new Product();
		product.setName("galaxy s20");
		product.setPrice(new BigDecimal("65000.75"));
		product.setDescription("best android phone ever");
		product.setQuantity(10);
		productRepository.save(product);

		Product product1 = new Product();
		product1.setName("galaxy s10");
		product1.setPrice(new BigDecimal("75000.65"));
		product1.setDescription("best android phone.");
		product1.setQuantity(10);
		productRepository.save(product1);

		SaleRepository saleRepository = (SaleRepository) SpringApplicationContext.getBean("saleRepository");

		Sale sale = new Sale();
		sale.setSaleDate(LocalDate.now());
		sale.setQuantity(2);
		sale.setProductDetails(product);
		saleRepository.save(sale);

		Sale sale1 = new Sale();
		sale1.setProductDetails(product1);
		sale1.setQuantity(2);
		sale1.setSaleDate(LocalDate.now());
		saleRepository.save(sale1);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SpringApplicationContext springApplicationContext(){
		return new SpringApplicationContext();
	}


}
