package com.inbbank.service;

import java.util.List;

import com.inbbank.exception.InvalidUserException;
import com.inbbank.model.Branchmanager;
import com.inbbank.wsentity.WSBranchManager;

public interface ManagerService {
	public WSBranchManager createBranchManager(Branchmanager branchmanager) throws Exception;

	public List<Branchmanager> getBranchManagers() throws Exception;

	public Branchmanager authenticateBranchManager(String userName, String password, String branchName)
			throws InvalidUserException;
}
