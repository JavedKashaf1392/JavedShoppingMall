package com.javed.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
@Entity
@Data
@Table(name="state")
public class State {
	
	@Id
	@GeneratedValue
	@Column(name="COUNTRY_ID")
	private Integer countryId;
	
	@Column(name="COUNTRY_CODE")
	private String countryCode;
	
	@Column(name="COUNTRY_NAME")
	private String countryName;
	
	@Transient
	private Integer stateId;
	
	@Transient
	private Integer cityId;

}
