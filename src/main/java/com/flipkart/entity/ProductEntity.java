
package com.flipkart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products_table")
public class ProductEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int p_id;
	private String p_name;
	private int p_price;
	private int p_stock;

	public int getP_id()
	{
		return p_id;
	}

	public void setP_id(int p_id)
	{
		this.p_id = p_id;
	}

	public String getP_name()
	{
		return p_name;
	}

	public void setP_name(String p_name)
	{
		this.p_name = p_name;
	}

	public int getP_price()
	{
		return p_price;
	}

	public void setP_price(int p_price)
	{
		this.p_price = p_price;
	}

	public int getP_stock()
	{
		return p_stock;
	}

	public void setP_stock(int p_stock)
	{
		this.p_stock = p_stock;
	}

}
