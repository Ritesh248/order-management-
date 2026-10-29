
package com.flipkart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flipkart.entity.CustomerEntity;
import com.flipkart.exception.CustomerNotFoundException;
import com.flipkart.repository.CustomerRepository;
import com.flipkart.request.CustomerRequest;
import com.flipkart.response.CustomerResponse;

@Service
public class CustomerService
{
	@Autowired
	CustomerRepository customerRepository;

	/**
	 * Creating New Customer
	 * 
	 * @param customerRequest
	 * @return customerResponse
	 */

	public CustomerResponse createCustomer(CustomerRequest customerRequest)
	{

		CustomerEntity customerEntity = new CustomerEntity();

		customerEntity.setC_name(customerRequest.getCustomerName());
		customerEntity.setC_email(customerRequest.getCustomerEmail());

		CustomerEntity saveCustomer = customerRepository.save(customerEntity);

		// res
		CustomerResponse customerResponse = new CustomerResponse();

		customerResponse.setCustomerId(saveCustomer.getC_id());
		customerResponse.setCustomerEmail(saveCustomer.getC_email());
		customerResponse.setCustomerName(saveCustomer.getC_name());

		return customerResponse;
	}

	/**
	 * View the customer by ID
	 * @param id
	 * @return CustomerResponse
	 */

	public CustomerResponse fetchCustomerById(int id)
	{

		boolean existsById = customerRepository.existsById(id);

		if (existsById)
		{
			Optional<CustomerEntity> byId = customerRepository.findById(id);

			CustomerEntity cEntity = byId.get();

			CustomerResponse customerResponse = new CustomerResponse();

			customerResponse.setCustomerId(cEntity.getC_id());
			customerResponse.setCustomerName(cEntity.getC_name());
			customerResponse.setCustomerEmail(cEntity.getC_email());

			return customerResponse;
		} else
		{
			throw new CustomerNotFoundException("The customer is not available");
		}

	}
	
	/**
	 * View All Customers
	 * @return List<CustomerResponse>
	 */
	
	public List<CustomerResponse> fetchAllCustomer() {
		
		Iterable<CustomerEntity> all = customerRepository.findAll();
		
		// do later : - if no customer exist throw some exception
		
		List<CustomerResponse> listOfCustomerResponse = new ArrayList<CustomerResponse>();
		
		for (CustomerEntity ce : all) {
			CustomerResponse customerResponse = new CustomerResponse();
			
			customerResponse.setCustomerId(ce.getC_id());
			customerResponse.setCustomerName(ce.getC_name());
			customerResponse.setCustomerEmail(ce.getC_email());
			
			listOfCustomerResponse.add(customerResponse);
		}
		
		return listOfCustomerResponse;
	}
}
