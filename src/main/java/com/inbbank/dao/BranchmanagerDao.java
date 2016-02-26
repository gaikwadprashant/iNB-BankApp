package com.inbbank.dao;

import java.util.List;

import com.inbbank.model.Branchmanager;

public interface BranchmanagerDao {
	public void createBranchManager(Branchmanager branchmanager);

	public List<Branchmanager> getBranchManagers();

	public List<Branchmanager> getBMByUserNameAndPassword(String userName, String password, String branchName);
}
