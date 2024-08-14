package org.jsp.merchantbootapp.controller;

import org.jsp.merchantbootapp.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
	private ProductService productService;
}