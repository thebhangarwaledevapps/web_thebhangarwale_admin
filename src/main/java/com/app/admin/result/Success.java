package com.app.admin.result;

public final class Success<D> extends Result {

	final D data;

	public Success(D data) {
		super();
		this.data = data;
	}

	public D getData() {
		return data;
	}
	
}

