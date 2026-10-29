
package com.flipkart.response;

import java.time.LocalDate;

public class OrderResponse
{
	private int orderId;
	private String orderNumber;
	private LocalDate orderDate;
	private int orderTotalAmount;
	private String orderIdStatus;
	private int productId;
	private int orderQuantity;

	public int getOrderQuantity()
	{
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity)
	{
		this.orderQuantity = orderQuantity;
	}

	public int getOrderId()
	{
		return orderId;
	}

	public void setOrderId(int orderId)
	{
		this.orderId = orderId;
	}

	public String getOrderNumber()
	{
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber)
	{
		this.orderNumber = orderNumber;
	}

	public LocalDate getOrderDate()
	{
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate)
	{
		this.orderDate = orderDate;
	}

	public int getOrderTotalAmount()
	{
		return orderTotalAmount;
	}

	public void setOrderTotalAmount(int orderTotalAmount)
	{
		this.orderTotalAmount = orderTotalAmount;
	}

	public String getOrderIdStatus()
	{
		return orderIdStatus;
	}

	public void setOrderIdStatus(String orderIdStatus)
	{
		this.orderIdStatus = orderIdStatus;
	}

	public int getProductId()
	{
		return productId;
	}

	public void setProductId(int productId)
	{
		this.productId = productId;
	}

}
