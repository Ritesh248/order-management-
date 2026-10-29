
package com.flipkart.request;

import java.util.List;

public class OrderRequest
{
	private int customerId;
	List<ProductReqForOrder> productReqForOrder;

	public int getCustomerId()
	{
		return customerId;
	}

	public void setCustomerId(int customerId)
	{
		this.customerId = customerId;
	}

	public List<ProductReqForOrder> getProductReqForOrder()
	{
		return productReqForOrder;
	}

	public void setProductReqForOrder(List<ProductReqForOrder> productReqForOrder)
	{
		this.productReqForOrder = productReqForOrder;
	}

}
