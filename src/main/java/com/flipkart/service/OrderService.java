
package com.flipkart.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flipkart.entity.CustomerEntity;
import com.flipkart.entity.OrderEntity;
import com.flipkart.entity.ProductEntity;
import com.flipkart.exception.CustomerNotFoundException;
import com.flipkart.exception.ProductNotFoundException;
import com.flipkart.exception.ProductOutOfStockException;
import com.flipkart.repository.CustomerRepository;
import com.flipkart.repository.OrderRepository;
import com.flipkart.repository.ProductRepository;
import com.flipkart.request.OrderRequest;
import com.flipkart.request.ProductReqForOrder;
import com.flipkart.response.OrderResponse;

import jakarta.transaction.Transactional;

@Service
public class OrderService
{

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CustomerRepository customerRepository;

	/**
	 * Placing an order
	 * 
	 * @param orderRequest
	 * @return
	 */

	@Transactional
	public List<OrderResponse> placeOrder(OrderRequest orderRequest)
	{
		// creating for response (ignore 1st)
		List<OrderResponse> orderResponseForCustomer = new ArrayList<OrderResponse>();

		int requestCustomerId = orderRequest.getCustomerId();
// is customer there?
		boolean customerExistsById = customerRepository.existsById(requestCustomerId);

		// for setting customer id in response
		Optional<CustomerEntity> byId = customerRepository.findById(requestCustomerId);
		CustomerEntity customerEntity = byId.get();

		System.out.println("requestCustomerId : " + requestCustomerId);
		System.out.println("customerExistsById : " + customerExistsById);

		if (customerExistsById)
		{
			// product there?
			List<ProductReqForOrder> productReqForOrder = orderRequest.getProductReqForOrder();

			// fetch one by one because we need each field data
			for (ProductReqForOrder eachProduct : productReqForOrder)
			{

				// product id & quantity
				int requestedProductIdOfEach = eachProduct.getProductId();

				boolean productExistsById = productRepository.existsById(requestedProductIdOfEach);

				if (productExistsById)
				{
					// need to check stock
					Optional<ProductEntity> allProductFields = productRepository.findById(requestedProductIdOfEach);

					ProductEntity productEntity = allProductFields.get();

					// from database stock
					int availableStock = productEntity.getP_stock();

					// from request body, how many (quantity) products you need?
					int productQuantity = eachProduct.getProductQuantity();

					if (availableStock > productQuantity)
					{
						OrderEntity orderEntity = new OrderEntity();

						// 0. set order Quantity
						orderEntity.setO_quantity(productQuantity);

						// 1. set date
						orderEntity.setO_date(LocalDate.now());

						// 2. set orderNum
//							// Generates a random integer from 1 to 999
//							Random random = new Random();
//							String orderNum = "OR" +random.nextInt(999) +1 ;
//						
//						orderEntity.setO_number(orderNum);

						String orderNum = String.format("OR%03d", new Random().nextInt(999) + 1);
						orderEntity.setO_number(orderNum);

						// 3. set total amount = product price * quantity

						int productPrice = productEntity.getP_price();

						orderEntity.setO_totalAmount(productQuantity * productPrice);

						// reduce the stock in product table and update , don't forget to save
						availableStock = availableStock - productQuantity;

						productEntity.setP_stock(availableStock);
						productRepository.save(productEntity);

						// 4. set order status

						orderEntity.setO_status("Order Placed");

						// 5. set product id
						orderEntity.setProductId(requestedProductIdOfEach);

						// 6.set customer_id
						orderEntity.setC_id(customerEntity);

						// save
						OrderEntity save = orderRepository.save(orderEntity);

						// set the response from order table data
						OrderResponse orderResponse = new OrderResponse();

						orderResponse.setOrderId(save.getO_id());
						orderResponse.setOrderDate(save.getO_date());
						orderResponse.setOrderIdStatus(save.getO_status());
						orderResponse.setOrderNumber(save.getO_number());
						orderResponse.setOrderTotalAmount(save.getO_totalAmount());
						orderResponse.setProductId(save.getProductId());
						orderResponse.setOrderQuantity(save.getO_quantity());

						// for list of orders
						orderResponseForCustomer.add(orderResponse);

					} else
					{
						throw new ProductOutOfStockException(
								"Product exist but OUT OF STOCK. In future you will get notification");
					}
				} else
				{
					throw new ProductNotFoundException("Product does not exist");
				}
			}
		}

		else
		{
			throw new CustomerNotFoundException("Customer does not exist");
		}

		// return "Order placed"; // if return type is string

		return orderResponseForCustomer;

	}

	// fetchAllOrdersOfAllCustomer - one c all o

	public String fetchAllOrdersOfAllCustomerWithNplusOne()
	{
		// get all customers
		Iterable<CustomerEntity> allOrdersByAllCustomer = customerRepository.findAll();

		// looping to get 1 by 1 customer
		for (CustomerEntity eachCustomerEntity : allOrdersByAllCustomer)
		{
			 int customerId = eachCustomerEntity.getC_id();
			 
			 System.out.println("Parent : " + customerId);

			 // all orders of that each/ one customer
			List<OrderEntity> listOrderEntity = eachCustomerEntity.getOrderEntity();

			//looping throw each order of that 1 customer
			for (OrderEntity childOrderEntity : listOrderEntity)
			{
//				System.out.println("Child of the : "+customerId );	 // triggers 1+N problem
				System.out.println("Child of the : "+customerId + " Your orders all below :" + childOrderEntity);	
				
			}
			System.out.println("----------------------------------------------------------------------------");
		}

		return "Task success with N+1 problem";

	}
	
	
	
	public String fetchAllOrdersOfAllCustomer() {
		
		List<CustomerEntity> allWithOrders = customerRepository.findAllWithOrders(); // replaced findAll and observe the return type
		
		for (CustomerEntity customerEntity : allWithOrders)
		{
			int individualCustomerId = customerEntity.getC_id();
			
			System.out.println(" Parent: individualCustomerId :" + (individualCustomerId));
			
			//from customer entity i got orderEntity
			List<OrderEntity> orderEntity = customerEntity.getOrderEntity();
			
			for (OrderEntity individualOrder : orderEntity)
			{
				System.out.println(" order details : Child of " + (individualCustomerId));
			}
			
			System.out.println("::*****************************************************************************************************************::");
			
		}
		return "Done";
	}
}
