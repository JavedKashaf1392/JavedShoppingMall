package com.javed.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Entity
@Table(name = "PRODUCT")
@Data
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PRODUCT_ID")
	private Integer productId;

	@Column(name = "PRODUCT_NAME")
	private String productName;

	@Column(name = "PRODUCT_PRICE")
	private double productPrice;

	@Column(name = "PRODUCT_QUANTITY")
	private double productQyt;

	@Column(name = "PRODUCT_DISCOUNT")
	private double discount;

	@Column(name = "IS_AVAAILABLE")
	private boolean isAvailable=true;

	@Transient
	private MultipartFile image;

	@Column(name = "PRODUCT_CATEGORY")
	private String productCategory;

	@Column(name = "PRODUCT_DESCRIPTION",columnDefinition="text")
	private String description;

	@Column(name = "FINAL_PRICE")
	private double finalPrice;

	@Column(name = "PRODUCT_SIZE")
	private float size;
	
	@Column(name="View")
	private Integer view=0;

	@Column(name = "PRODUCT_TAX")
	private double tax;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", updatable = false)
	private Date createDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE", insertable = false)
	private Date updatedDate;

}
