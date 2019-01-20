package com.restapi.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="company")
public class Company {
	private int company_id;
	private String company_name;
	private String company_representative;
	private String company_contract_info;
	private String company_address;
	
	public int getCompany_id() {
		return company_id;
	}
	@XmlElement
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
	public String getCompany_name() {
		return company_name;
	}
	@XmlElement
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	
	public String getCompany_representative() {
		return company_representative;
	}
	@XmlElement
	public void setCompany_representative(String company_representative) {
		this.company_representative = company_representative;
	}
	
	public String getCompany_contract_info() {
		return company_contract_info;
	}
	@XmlElement
	public void setCompany_contract_info(String company_contract_info) {
		this.company_contract_info = company_contract_info;
	}
	
	public String getCompany_address() {
		return company_address;
	}
	@XmlElement
	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}
	
}
