package com.persistent.wedis.enums;

/**
 * 
 * @author chandra_areti
 *
 */
public enum AuthRequestStatus {
	INIT("init"), SHARED_ATTRIBUTES("shared_attributes");

	private String sharedValue;

	AuthRequestStatus(String sharedValue) {
		this.sharedValue = sharedValue;
	}

	public String getAuthRequestStatus() {
		return this.sharedValue;
	}
}
