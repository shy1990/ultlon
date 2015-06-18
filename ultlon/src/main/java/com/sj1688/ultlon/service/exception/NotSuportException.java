package com.sj1688.ultlon.service.exception;

public class NotSuportException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotSuportException() {
		super();
	}

	public NotSuportException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotSuportException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotSuportException(String message) {
		super(message);
	}

	public NotSuportException(Throwable cause) {
		super(cause);
	}
	
}
