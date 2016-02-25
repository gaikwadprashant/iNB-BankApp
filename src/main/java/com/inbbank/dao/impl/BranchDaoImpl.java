package com.inbbank.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inbbank.dao.BranchDao;
import com.inbbank.model.Branch;
import com.inbbank.util.GenerateUUID;

@Service
public class BranchDaoImpl implements BranchDao {

	@Autowired
	SessionFactory sessionFactory;

	public Branch getBranchById() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean createBranch(Branch branch) throws Exception {
			branch.setId(GenerateUUID.getRendomString());
			sessionFactory.getCurrentSession().save(branch);
		return true;
	}

	public List<Branch> getBranches() throws Exception {
		String query =" from Branch"; 
		List<Branch> list = sessionFactory.getCurrentSession().createQuery(query).list();
		return list;
	}

}
