
package com.flipkart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flipkart.request.ProductRequest;
import com.flipkart.response.ProductResponse;
import com.flipkart.service.ProductService;

@RestController
@RequestMapping("product")
public class ProductController
{
	@Autowired
	ProductService productService;
	
	//Add an product

	@PostMapping("addproduct")
	public ProductResponse addProducts(@RequestBody ProductRequest productRequest)
	{
		System.out.println(" R " + (productRequest));
		
		ProductResponse productResponse = productService.addProduct(productRequest);

		return productResponse;
	}
}
