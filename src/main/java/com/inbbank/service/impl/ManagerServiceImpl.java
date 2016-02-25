package com.inbbank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inbbank.dao.BranchmanagerDao;
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

}
