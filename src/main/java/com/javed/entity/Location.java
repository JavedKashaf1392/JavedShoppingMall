package com.javed.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name="location")
public class Location {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="CITY_ID")
	private Integer cityId;
	
	@Column(name="COUNTRY_ID")
	private Integer countryId;
	
	@Column(name="STATE_ID")
	private Integer stateId;
	

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE",updatable = false)
	private Date createdDate;
    
    @UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date")
	private Date updatedDate;

}
