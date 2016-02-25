package com.inbbank.dao;

import java.util.List;

import com.inbbank.model.Branch;

public interface BranchDao {

	boolean createBranch(Branch branch) throws Exception;

	List<Branch> getBranches() throws Exception;

}
