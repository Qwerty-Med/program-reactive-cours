package com.reactive;

import com.reactive.controller.ProductsController;
import com.reactive.model.Product;
import com.reactive.service.ProductService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class MicroserviceCrudProductsReactiveApplicationTests {


	@Autowired
	ProductService productService;

	@Test
	@Order(4)
	void testProductsCategory() {

		StepVerifier.create(productService.productCategory("alimentattion"))
				.expectNextMatches(p->p.getName().equals("milk"))
						.expectNextMatches(p->p.getName().equals("arroz"))
								.expectNextMatches(p->p.getName().equals("tail"))
				.expectNextMatches(p->p.getName().equals("boot"))
				.expectNextMatches(p->p.getName().equals("hegs"))
				.verifyComplete();


	}

	@Test
	@Order(3)
	void testProductsCategory2() {

		StepVerifier.create(productService.productCategory("alimentattion").collectList())
				.assertNext(products -> {
					var names = products.stream()
							.map(Product::getName)
							.toList();

					assertTrue(names.contains("milk"));
					assertTrue(names.contains("arroz"));
					assertTrue(names.contains("tail"));
				})
				.verifyComplete();
	}

	@Test
	@Order(5)
	void testDeleteProducts() {
		StepVerifier.create(productService.deleteProduct(1022))
				.expectNextMatches(p->p.getName().equals("milk"))
				.verifyComplete();

	}

	@Test
	@Order(2)
	void testAltaProdducts() {
		Product product =  new Product(10052, "Meet", "alimrentattion", 1.4500, 5050);
		StepVerifier.create(productService.saveProduct(product))
				.expectComplete()
				.verify();

	}

	@Test
	@Order(1)
	void testCatalog() {
		StepVerifier.create(productService.catalog())
				.expectNextCount(8)
				.expectComplete();

	}

}
