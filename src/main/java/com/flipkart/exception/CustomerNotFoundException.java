
package com.flipkart.exception;

public class CustomerNotFoundException extends RuntimeException
{
	public CustomerNotFoundException(String message)
	{
		super(message);
	}
}
