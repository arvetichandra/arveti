package com.persistent.wedis.model;

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
@Table(name = "vender")
public class Vender {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vender_code")
	private Long venderId;
	@Column(name = "vender_name")
	private String venderName;
	@Column(name = "email")
	private String email;
	@Column(name = "phone")
	private String phone;
	@Column(name = "sector")
	private String sector;
	@Column(name = "requiredAttributes")
	private String requiredAttributes;

	public Vender() {

	}

	public Long getVenderId() {
		return venderId;
	}

	public void setVenderId(Long venderId) {
		this.venderId = venderId;
	}

	public String getVenderName() {
		return venderName;
	}

	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getRequiredAttributes() {
		return requiredAttributes;
	}

	public void setRequiredAttributes(String requiredAttributes) {
		this.requiredAttributes = requiredAttributes;
	}

}
