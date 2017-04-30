package com.example.springmvc.front.dao;

import com.example.springmvc.front.model.Company;

/**
 * 
 * @author henryyue
 *
 */
public interface CompanyDao {

	public Company getCompanyByUserId(Integer userid);

	public Company getCompanyById(Integer id);
}
