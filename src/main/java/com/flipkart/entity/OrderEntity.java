
package com.flipkart.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders_table")
public class OrderEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int o_id;

	private String o_number; // random
	private LocalDate o_date; // use LocalDate Library API
	private int o_totalAmount; // random
	private String o_status; // OrderPlaced, Out for Delivery, order delivered
	
	private int o_quantity; // 

	private int productId;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private CustomerEntity c_id;

	// We are connecting Order Entity to Customer Entity via customer id.

	public int getProductId()
	{
		return productId;
	}

	public void setProductId(int productId)
	{
		this.productId = productId;
	}

	public CustomerEntity getC_id()
	{
		return c_id;
	}

	public void setC_id(CustomerEntity c_id)
	{
		this.c_id = c_id;
	}

	public int getO_id()
	{
		return o_id;
	}

	public void setO_id(int o_id)
	{
		this.o_id = o_id;
	}

	public String getO_number()
	{
		return o_number;
	}

	public void setO_number(String o_number)
	{
		this.o_number = o_number;
	}

	public LocalDate getO_date()
	{
		return o_date;
	}

	public void setO_date(LocalDate o_date)
	{
		this.o_date = o_date;
	}

	public int getO_totalAmount()
	{
		return o_totalAmount;
	}

	public void setO_totalAmount(int o_totalAmount)
	{
		this.o_totalAmount = o_totalAmount;
	}

	public String getO_status()
	{
		return o_status;
	}

	public void setO_status(String o_status)
	{
		this.o_status = o_status;
	}
	
	public int getO_quantity()
	{
		return o_quantity;
	}

	public void setO_quantity(int o_quantity)
	{
		this.o_quantity = o_quantity;
	}

	@Override
	public String toString()
	{
		return "OrderEntity [o_id=" + o_id + ", o_number=" + o_number + ", o_date=" + o_date + ", o_totalAmount="
				+ o_totalAmount + ", o_status=" + o_status + ", o_quantity=" + o_quantity + ", productId=" + productId
				+ ", c_id=" + c_id + "]";
	}

}
