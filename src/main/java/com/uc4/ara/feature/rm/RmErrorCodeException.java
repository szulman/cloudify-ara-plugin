package com.uc4.ara.feature.rm;

import com.uc4.importexportservice.ErrorCode;

public class RmErrorCodeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3315050922860467826L;

	private ErrorCode errorCode;

	public RmErrorCodeException(ErrorCode errorCode) {
		super(errorCode.value());
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}
}
