package com.inbbank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inbbank.dao.BranchmanagerDao;
import com.inbbank.exception.InvalidUserException;
import com.inbbank.model.Branchmanager;
import com.inbbank.service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private BranchmanagerDao branchmanagerDao;

	public void createBranchManager(Branchmanager branchmanager) throws Exception {
		branchmanagerDao.createBranchManager(branchmanager);
	}

	public List<Branchmanager> getBranchManagers() throws Exception {
		return branchmanagerDao.getBranchManagers();
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Branchmanager authenticateBranchManager(String userName, String password, String branchName)
			throws InvalidUserException {
		List<Branchmanager> branchManagers = branchmanagerDao.getBMByUserNameAndPassword(userName, password,
				branchName);
		if (branchManagers.isEmpty()) {
			throw new InvalidUserException("USERNAME OR PASSWORD INCORRECT");
		}
		return branchManagers.get(0);

	}

}
