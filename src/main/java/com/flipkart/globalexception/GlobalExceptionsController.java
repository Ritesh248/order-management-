
package com.flipkart.globalexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.flipkart.exception.CustomerNotFoundException;
import com.flipkart.exception.ExceptionResponse;
import com.flipkart.exception.ProductNotFoundException;
import com.flipkart.exception.ProductOutOfStockException;

@RestControllerAdvice
public class GlobalExceptionsController
{
	@ExceptionHandler(ProductNotFoundException.class) // like try
	public ResponseEntity<ExceptionResponse> handingException(ProductNotFoundException pnf) // like catch
	{

		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setErrorCode("ER00P1");
		exceptionResponse.setErrorMessage(pnf.getMessage());
		ResponseEntity<ExceptionResponse> body = ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
		return body;
	}

	@ExceptionHandler(ProductOutOfStockException.class)
	public ResponseEntity<ExceptionResponse>  handingException(ProductOutOfStockException pos)
	{ 
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setErrorCode("ER00P2");
		exceptionResponse.setErrorMessage(pos.getMessage());
		
		ResponseEntity<ExceptionResponse> body = ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
		return body;
	}
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ExceptionResponse>  handingException(CustomerNotFoundException cos)
	{ 
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setErrorCode("ER00C1");
		exceptionResponse.setErrorMessage(cos.getMessage());
		
		ResponseEntity<ExceptionResponse> body = ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
		return body;
	}
}
