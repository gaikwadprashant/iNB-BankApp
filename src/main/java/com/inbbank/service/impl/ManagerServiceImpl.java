package com.inbbank.service.impl;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inbbank.dao.BranchmanagerDao;
import com.inbbank.exception.InvalidUserException;
import com.inbbank.model.Branchmanager;
import com.inbbank.service.ManagerService;
import com.inbbank.util.GenerateUUID;
import com.inbbank.wsentity.WSBranchManager;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private BranchmanagerDao branchmanagerDao;

	@Autowired
	private DozerBeanMapper mapper;

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public WSBranchManager createBranchManager(Branchmanager branchmanager) throws Exception {

		branchmanager.setId(GenerateUUID.getRendomString());
		branchmanagerDao.createBranchManager(branchmanager);
		WSBranchManager wsBranchManager = mapper.map(branchmanager, WSBranchManager.class);
		return wsBranchManager;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, isolation = Isolation.REPEATABLE_READ)
	public List<Branchmanager> getBranchManagers() throws Exception {
		return branchmanagerDao.getBranchManagers();
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, isolation = Isolation.REPEATABLE_READ)
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
