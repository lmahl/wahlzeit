package org.wahlzeit.model.exceptions;

/**
 * An exception that indicates, that a conversion from one object type to another has failed
 */
public class ConversionFailedException extends ContractPostconditionViolatedException{
	private final static String MSG = "Conversion failed ";

	public ConversionFailedException(String s){
		super(MSG + s);
	}

	public ConversionFailedException(String message, Throwable cause){
		super(MSG + message, cause);
	}
}
