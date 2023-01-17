package com.app.admin.result;

public final class ServerError extends Result {

	final String errorMessage;

	public ServerError(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}



