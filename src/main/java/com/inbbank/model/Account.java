package com.inbbank.model;

import java.io.Serializable;

import javax.persistence.*;



import java.math.BigDecimal;


/**
 * The persistent class for the ACCOUNT database table.
 * 
 */
@Entity
@Table(name="ACCOUNT")
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=50)
	private String id;

	@Column(precision=20)
	private BigDecimal accountNumber;

	@Column(length=50)
	private String accountType;

	@Column(precision=10, scale=2)
	private BigDecimal balance;

	@Column(precision=10, scale=2)
	private BigDecimal interestRate;

	//bi-directional many-to-one association to Customer
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinColumn(name="CUSTID")
	private Customer customer;

	public Account() {
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public BigDecimal getAccountNumber() {
		return accountNumber;
	}



	public void setAccountNumber(BigDecimal accountNumber) {
		this.accountNumber = accountNumber;
	}



	public String getAccountType() {
		return accountType;
	}



	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}



	public BigDecimal getBalance() {
		return balance;
	}



	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}



	public BigDecimal getInterestRate() {
		return interestRate;
	}



	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}


	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}