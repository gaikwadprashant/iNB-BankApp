package com.inbbank.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the BRANCHMANAGER database table.
 * 
 */
@Entity
@Table(name="BRANCHMANAGER")
@NamedQuery(name="Branchmanager.findAll", query="SELECT b FROM Branchmanager b")
public class Branchmanager implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=50)
	private String id;

	@Column(length=50)
	private String address;

	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@Column(length=50)
	private String email;

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

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="BRANCHID")
	private Branch branch;

	public Branchmanager() {
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



	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

}