
package com.flipkart.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers_table")
public class CustomerEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int c_id;
	private String c_name;
	private String c_email;
	
	@OneToMany(mappedBy = "c_id", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
//	@OneToMany(mappedBy = "c_id", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	private List<OrderEntity> orderEntity;
	// for one customer multiple orders so list
	
	public List<OrderEntity> getOrderEntity()
	{
		return orderEntity;
	}

	public void setOrderEntity(List<OrderEntity> orderEntity)
	{
		this.orderEntity = orderEntity;
	}

	public int getC_id()
	{
		return c_id;
	}

	public void setC_id(int c_id)
	{
		this.c_id = c_id;
	}

	public String getC_name()
	{
		return c_name;
	}

	public void setC_name(String c_name)
	{
		this.c_name = c_name;
	}

	public String getC_email()
	{
		return c_email;
	}

	public void setC_email(String c_email)
	{
		this.c_email = c_email;
	}

}
