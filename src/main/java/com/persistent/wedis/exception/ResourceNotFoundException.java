package com.persistent.wedis.exception;

import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public final class ResourceNotFoundException extends NestedRuntimeException {

	/**
	 * SerialVersionUID
	 */
	private static final long serialVersionUID = -9040534546410167898L;

	public ResourceNotFoundException(String msg) {
		super(msg);
	}

	public ResourceNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}