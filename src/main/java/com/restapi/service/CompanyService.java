package com.restapi.service;

import com.restapi.dao.*;
import java.util.List;

import com.restapi.model.Company;

public class CompanyService {
	
	private CompanyDAO dao = new CompanyDAO();
	
	public List<Company> getCompanies() {
		List<Company> res = null;
		try{
			res = dao.getCompanies() ; 
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Company getCompany(String company_id) {
		Company res = null;
		int id = Integer.parseInt(company_id);
		try{
			res = dao.getCompany(id) ; 
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Company createCompany(String company_name, String company_representative, String company_contract_info,
			String company_address) {
		Company res = null;
		Company dto = null;
		if(!company_name.equals("") && 
				!company_representative.equals("") &&
				!company_contract_info.equals("") &&
				!company_address.equals("")){
			dto = new Company();
			dto.setCompany_name(company_name);
			dto.setCompany_representative(company_representative);
			dto.setCompany_contract_info(company_contract_info);
			dto.setCompany_address(company_address);
		}
		
		try{
			res = dao.createCompany(dto) ; 
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Company updateCompany(String company_id, String company_name, String company_representative,
			String company_contract_info, String company_address) {
		Company res = null;
		Company dto = null;
		if(!company_id.equals("") && !company_id.equals("0") && 
				!company_name.equals("") && 
				!company_representative.equals("") &&
				!company_contract_info.equals("") &&
				!company_address.equals("")){
			dto = new Company();
			dto.setCompany_id(Integer.parseInt(company_id));
			dto.setCompany_name(company_name);
			dto.setCompany_representative(company_representative);
			dto.setCompany_contract_info(company_contract_info);
			dto.setCompany_address(company_address);
		}
		
		try{
			res = dao.updateCompany(dto) ; 
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public List<Company> deleteCompany(String company_id) {
		List<Company> res = null;
		int id = Integer.parseInt(company_id);
		try{
			res = dao.deleteCompany(id) ; 
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

}
