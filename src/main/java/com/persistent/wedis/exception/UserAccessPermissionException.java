package com.persistent.wedis.exception;

import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public final class UserAccessPermissionException extends NestedRuntimeException {

	/**
	 * SerialVersionUID
	 */
	private static final long serialVersionUID = 1219966139577079133L;

	public UserAccessPermissionException(String msg) {
		super(msg);
	}

	public UserAccessPermissionException(String msg, Throwable cause) {
		super(msg, cause);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}