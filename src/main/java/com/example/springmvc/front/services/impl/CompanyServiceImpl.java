package com.example.springmvc.front.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springmvc.front.dao.CompanyDao;
import com.example.springmvc.front.model.Company;
import com.example.springmvc.front.services.ICompanyService;

/**
 * 
 * @author henryyue
 *
 */
@Service("companyService")
public class CompanyServiceImpl implements ICompanyService{

	@Autowired
	private CompanyDao companyDao;
	
	public CompanyDao getCompanyDao() {
		return companyDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	public Company getCompanyById(Integer id) {
		return this.companyDao.getCompanyById(id);
	}

}
