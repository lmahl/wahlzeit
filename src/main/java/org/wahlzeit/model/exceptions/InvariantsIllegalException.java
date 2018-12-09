package org.wahlzeit.model.exceptions;

/**
 * Exception that indicates, that class invariants have been violated
 */
public class InvariantsIllegalException extends IllegalStateException {

	private final static String MSG = "Class reached invalid state: ";

	public InvariantsIllegalException(String s){
		super(MSG + s);
	}

	public InvariantsIllegalException(String message, Throwable cause){
		super(MSG + message, cause);
	}
}
