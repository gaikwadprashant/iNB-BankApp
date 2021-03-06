package com.inbbank.wsentity;

import java.math.BigDecimal;
import java.util.Date;

public class WSBranchManager {
	private String id;
	private String address;
	private Date dateOfBirth;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private BigDecimal phone;
	private String userName;
	private WSBranch branch;
	private WSBranch branchPOJO;

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

	public WSBranch getBranch() {
		return branch;
	}

	public void setBranch(WSBranch branch) {
		this.branch = branch;
	}

	public WSBranch getBranchPOJO() {
		return branchPOJO;
	}

	public void setBranchPOJO(WSBranch branchPOJO) {
		this.branchPOJO = branchPOJO;
	}

}
