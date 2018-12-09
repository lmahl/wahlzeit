package org.wahlzeit.model.exceptions;

public class ContractPostconditionViolatedException extends Exception{
	private final static String MSG = "Failed to fulfill contract because of violated postcondition: ";

	public ContractPostconditionViolatedException(String s){
		super(MSG + s);
	}

	public ContractPostconditionViolatedException(String message, Throwable cause){
		super(MSG + message, cause);
	}
}
