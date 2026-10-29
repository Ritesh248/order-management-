
package com.flipkart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flipkart.request.CustomerRequest;
import com.flipkart.response.CustomerResponse;
import com.flipkart.service.CustomerService;

@RestController
@RequestMapping("customer")
public class CustomerController
{
	@Autowired
	CustomerService customerService;

	
	// CREATE A CUSTOMER
	
	@PostMapping("addcustomer")
	public CustomerResponse addCustomer(@RequestBody CustomerRequest customerRequest)
	{

		CustomerResponse customerResponse = customerService.createCustomer(customerRequest);

		return customerResponse;
	}

	// GET 1 CUSTOMER BY ID
	
	@GetMapping("viewcustomer/{cid}")
	public CustomerResponse viewCustomerById(@PathVariable("cid") int id)
	{

		CustomerResponse fetchCustomerById = customerService.fetchCustomerById(id);

		return fetchCustomerById;
	}

	// GET ALL CUSTOMERS - user click on url
	
	@GetMapping("viewallcustomer")
	public List<CustomerResponse> viewAllCustomer()
	{

		List<CustomerResponse> fetchAllCustomer = customerService.fetchAllCustomer();

		return fetchAllCustomer;
	}
}
