package com.inbbank.service;

import java.util.List;

import com.inbbank.model.Branchmanager;

public interface ManagerService {
	public void createBranchManager(Branchmanager branchmanager) throws Exception;

	public List<Branchmanager> getBranchManagers() throws Exception;
}
