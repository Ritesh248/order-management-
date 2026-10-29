
package com.flipkart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flipkart.request.OrderRequest;
import com.flipkart.response.OrderResponse;
import com.flipkart.service.OrderService;

@RestController
@RequestMapping("orders")
public class OrderController
{
	@Autowired
	OrderService orderService;

	// CREATE AN ORDER FOR A CUSTOMER

	@PostMapping("placeorder")
	public List<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest)
	{

		List<OrderResponse> placeOrder = orderService.placeOrder(orderRequest);

		return placeOrder;
	}

	// GET ALL ORDER OF A CUSTOMER - N+1 problem (15 parents , 1 query to get all parents , each parent fires 1 query to get child(order) details so 1 + 15.

	@GetMapping("allorderdetails")
	public String viewAllOrdersWithNplusOne()
	{
		String fetchAllOrdersOfAllCustomer = orderService.fetchAllOrdersOfAllCustomerWithNplusOne();
		return fetchAllOrdersOfAllCustomer;
	}

	// GET ALL ORDER OF A CUSTOMER - Solving N+1 problem
	
	@GetMapping("allorders")
	public String viewAllOrders() {
		
		String fetchAllOrdersOfAllCustomer = orderService.fetchAllOrdersOfAllCustomer();
		return fetchAllOrdersOfAllCustomer;
		
	}
}
