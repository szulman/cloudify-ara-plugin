package com.uc4.ara.common.exception;

import com.uc4.importexportservice.ErrorCode;

public class ImportExportServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5779375720728985480L;

	private ErrorCode errorCode;

	public ImportExportServiceException(String message) {
		super(message);
	}

	public ImportExportServiceException(ErrorCode errorCode) {
		super(errorCode.value());
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public ImportExportServiceException() {
		super();
	}
}
