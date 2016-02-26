package com.inbbank.service;

import java.util.List;

import com.inbbank.model.Branch;
import com.inbbank.wsentity.WSBranch;

public interface BranchService {

	Branch getBranchById();

	WSBranch createBranch(Branch branch)  throws Exception;

	List<Branch> getBranches() throws Exception;
}
