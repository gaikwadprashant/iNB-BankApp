package com.inbbank.model;

import java.io.Serializable;

import javax.persistence.*;


import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the CUSTOMER database table.
 * 
 */
@Entity
@Table(name="CUSTOMER")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@Column(unique=true, nullable=false, length=50)
	private String id;

	@Column(length=50)
	private String address;

	@Column(length=50)
	private String applicationStatus;

	@Column(length=50)
	private String authorizedImageName;

	@Column(length=50)
	private String authorizedImageText;

	@Column(precision=10)
	private BigDecimal customerId;

	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@Column(length=50)
	private String email;

	@Column(precision=10)
	private BigDecimal enqId;

	@Column(length=50)
	private String firstName;

	@Column(length=50)
	private String lastName;

	@Column(length=50)
	private String password;

	@Column(precision=10)
	private BigDecimal phone;

	@Column(length=50)
	private String userName;

	//bi-directional many-to-one association to Account
	@OneToMany(mappedBy="customer",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Set<Account> accounts;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BRANCHID")
	private Branch branch;

	
	public Customer() {
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getApplicationStatus() {
		return applicationStatus;
	}


	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}


	public String getAuthorizedImageName() {
		return authorizedImageName;
	}


	public void setAuthorizedImageName(String authorizedImageName) {
		this.authorizedImageName = authorizedImageName;
	}


	public String getAuthorizedImageText() {
		return authorizedImageText;
	}


	public void setAuthorizedImageText(String authorizedImageText) {
		this.authorizedImageText = authorizedImageText;
	}


	public BigDecimal getCustomerId() {
		return customerId;
	}


	public void setCustomerId(BigDecimal customerId) {
		this.customerId = customerId;
	}


	public Date getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public BigDecimal getEnqId() {
		return enqId;
	}


	public void setEnqId(BigDecimal enqId) {
		this.enqId = enqId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public BigDecimal getPhone() {
		return phone;
	}


	public void setPhone(BigDecimal phone) {
		this.phone = phone;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public Set<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public Account addAccount(Account account) {
		getAccounts().add(account);
		account.setCustomer(this);

		return account;
	}

	public Account removeAccount(Account account) {
		getAccounts().remove(account);
		account.setCustomer(null);

		return account;
	}
	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	

}