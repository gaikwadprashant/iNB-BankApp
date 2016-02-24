package com.inbbank.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;


/**
 * The persistent class for the BRANCH database table.
 * 
 */
@Entity
@Table(name="BRANCH")
@NamedQuery(name="Branch.findAll", query="SELECT b FROM Branch b")
public class Branch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=50)
	private String id;

	@Column(length=50)
	private String address;

	@Column(length=50)
	private String branchName;

	@Column(precision=10)
	private BigDecimal contact;

	@Column(length=50)
	private String ifscCode;

	//bi-directional many-to-one association to Branchmanager
	@OneToMany(mappedBy="branch", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Branchmanager> branchmanagers;

	//bi-directional many-to-one association to Customer
	@OneToMany(mappedBy="branch")
	private Set<Customer> customers;

	public Branch() {
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


	public String getBranchName() {
		return branchName;
	}


	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}


	public BigDecimal getContact() {
		return contact;
	}


	public void setContact(BigDecimal contact) {
		this.contact = contact;
	}


	public String getIfscCode() {
		return ifscCode;
	}


	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}


	public Set<Branchmanager> getBranchmanagers() {
		return this.branchmanagers;
	}

	public void setBranchmanagers(Set<Branchmanager> branchmanagers) {
		this.branchmanagers = branchmanagers;
	}

	public Branchmanager addBranchmanager(Branchmanager branchmanager) {
		getBranchmanagers().add(branchmanager);
		branchmanager.setBranch(this);

		return branchmanager;
	}

	public Branchmanager removeBranchmanager(Branchmanager branchmanager) {
		getBranchmanagers().remove(branchmanager);
		branchmanager.setBranch(null);

		return branchmanager;
	}

	public Set<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	public Customer addCustomer(Customer customer) {
		getCustomers().add(customer);
		customer.setBranch(this);

		return customer;
	}

	public Customer removeCustomer(Customer customer) {
		getCustomers().remove(customer);
		customer.setBranch(null);

		return customer;
	}

}