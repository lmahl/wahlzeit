package org.wahlzeit.model.exceptions;

public class FailedToCreateInstanceException extends Exception {
	private final static String MSG = "";

	public FailedToCreateInstanceException(String s){
		super(MSG + s);
	}

	public FailedToCreateInstanceException(String message, Throwable cause){
		super(MSG + message, cause);
	}
}
