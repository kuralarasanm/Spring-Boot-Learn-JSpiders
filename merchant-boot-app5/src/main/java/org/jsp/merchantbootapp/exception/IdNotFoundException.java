package org.jsp.merchantbootapp.exception;

public class IdNotFoundException extends RuntimeException{
	@Override
	public String getMessage() {
		return "invalid Id";
	}
}
