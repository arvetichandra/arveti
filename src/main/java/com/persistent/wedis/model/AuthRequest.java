package com.persistent.wedis.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author chandra_areti
 *
 */

@Entity
@Table(name = "auth_requests")
public class AuthRequest {

	@Id
	@Column(name = "auth_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer authId;
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "vender_code")
	private Long venderCode;
	@Column(name = "status")
	private String statusCode;
	@Column(name = "unique_auth_code")
	private String uniqueAuthCode;
	@Column(name = "expiry_date")
	private Date expiryDate;
	@Column(name = "shared_attributes")
	private String sharedAttributes;

	public AuthRequest() {

	}

	public Integer getAuthId() {
		return authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getVenderCode() {
		return venderCode;
	}

	public void setVenderCode(Long venderCode) {
		this.venderCode = venderCode;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getUniqueAuthCode() {
		return uniqueAuthCode;
	}

	public void setUniqueAuthCode(String uniqueAuthCode) {
		this.uniqueAuthCode = uniqueAuthCode;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getSharedAttributes() {
		return sharedAttributes;
	}

	public void setSharedAttributes(String sharedAttributes) {
		this.sharedAttributes = sharedAttributes;
	}

}
