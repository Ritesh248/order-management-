
package com.flipkart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flipkart.entity.ProductEntity;
import com.flipkart.repository.ProductRepository;
import com.flipkart.request.ProductRequest;
import com.flipkart.response.ProductResponse;

@Service
public class ProductService
{
	@Autowired
	ProductRepository productRepository;
	
	/**
	 * Adding product 
	 * @param productRequest
	 * @return productResponse
	 */

	public ProductResponse addProduct(ProductRequest productRequest)
	{

		ProductEntity productEntity = new ProductEntity();
		
		productEntity.setP_name(productRequest.getProductName());
		productEntity.setP_stock(productRequest.getProductQuantity());
		productEntity.setP_price(productRequest.getProductPrice());

		ProductEntity saveProduct = productRepository.save(productEntity);

		ProductResponse productResponse = new ProductResponse();

		productResponse.setProductId(saveProduct.getP_id());
		productResponse.setProductName(saveProduct.getP_name());
		productResponse.setProductQuantity(saveProduct.getP_stock());
		productResponse.setProductPrice(saveProduct.getP_price());

		return productResponse;
	}
}
