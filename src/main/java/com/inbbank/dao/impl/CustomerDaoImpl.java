package com.inbbank.dao.impl;



import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inbbank.dao.CustomerDao;
import com.inbbank.model.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	SessionFactory sessionFactory;
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean createCustomer(Customer customer) throws Exception {
		
		// TODO Auto-generated method stub
		System.out.println("wewe");
		try{
			sessionFactory.getCurrentSession().save(customer);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return true;
	}
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<Customer> getCustomer() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		criteria.createAlias("branch", "branch");
		List<Customer> custmerList = criteria.list();
		return custmerList;
	}
	
	
	public String unregistereduserEmail(String email) throws Exception {
		
		String falseData = "{alreadyExists : false}";
		String trueData = "{alreadyExists : true}";
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		cr.add(Restrictions.ilike("email", email));
		List<Customer> list = cr.list();
		if(list != null && ! list.isEmpty()){
			return trueData;
		}
		return falseData;
		
	}

}
