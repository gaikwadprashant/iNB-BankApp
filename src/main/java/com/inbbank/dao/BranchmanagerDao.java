package com.inbbank.dao;

import java.util.List;

import com.inbbank.model.Branchmanager;

public interface BranchmanagerDao {
	public String createBranchManager(Branchmanager branchmanager);

	public List<Branchmanager> getBranchManagers();
}
