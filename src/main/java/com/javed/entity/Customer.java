package com.javed.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="Customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	
	@Id
	@GeneratedValue
	@Column(name="customerId")
	private Integer id;
	
	@Column(name="customer_fullName")
	private String fullname;
	
	@Column(name="customer_lastName")
	private String lastname;
	
	@Column(name="customer_Email")
	private String email;
	
	@Column(name="customer_password")
	private String pazzword;
	
	@Transient
	private String confirmpazzword;
	@Column(name="State")
	private String state;
	
	@Column(name="city")
	private String city;
	
	@Column(name="location")
	private String location;
	
	@Column(name="customer_active")
	private boolean active=true;
	
	  @Column(name = "reset_password_token")
	  private String resetPasswordToken;
	
	
//	/*@Column(name="account_non_locked")*/
//	/*@Column(columnDefinition="tinyint(1) default 1")*/
//	@Column(name = "accountNonLocked", nullable = false, columnDefinition = "boolean default true")
//	private boolean accountNonLocked=true;
		
	
//	/*@Column( columnDefinition = "int default 1")*/
//	@Column(columnDefinition = "integer default 0")
//	/*@Column(name = "failed_attempt", columnDefinition = "int default 1")*/
//	private Integer failedAttempt=0;
	
//	@CreationTimestamp
//	@Temporal(TemporalType.DATE)
//	@Column(name="lock_time")
//	private Date lockTime;
	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name="Created_Date",updatable =  false)
	private Date createDate;
	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name="Update_Date",insertable =  false)
	private Date updateDate;
	
	
//	@ElementCollection(fetch = FetchType.EAGER)
//	@CollectionTable(
//			name="Security_Roles",
//			joinColumns = @JoinColumn(name="id"))
//	@Column(name="role")
//	private List<String> roles;
	
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "user_roles_tab", joinColumns = @JoinColumn(name = "id_join_col"))
	@Column(name = "usr_role_col")
	private Set<String> roles;
	
	
	
	
	
	

}
