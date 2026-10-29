package com.flipkart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.flipkart.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer>
{
	
	// this query joins Customer & Orders (left join) 
	@Query("SELECT DISTINCT c FROM CustomerEntity c LEFT JOIN FETCH c.orderEntity")
    List<CustomerEntity> findAllWithOrders();
}
