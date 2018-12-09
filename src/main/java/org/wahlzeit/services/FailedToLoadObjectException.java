package org.wahlzeit.services;

public class FailedToLoadObjectException extends RuntimeException {

	public FailedToLoadObjectException() {
		super();
	}


	public FailedToLoadObjectException(String s) {
		super(s);
	}


	public FailedToLoadObjectException(String message, Throwable cause) {
		super(message, cause);
	}

	public FailedToLoadObjectException(Throwable cause) {
		super(cause);
	}
}

