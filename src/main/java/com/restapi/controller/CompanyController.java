package com.restapi.controller;

import com.restapi.model.*;
import com.restapi.service.*;

import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;

@Path("/Companies")
public class CompanyController {
	
	private CompanyService service = new CompanyService();

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Company> getCompanies(){
		System.out.println("@GET");
		List<Company> res = null;
		res = service.getCompanies();
		return res;
	}
	
	@Path("{company_id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Company getCompany(@PathParam("company_id") String company_id){
		System.out.println("@GET + id");
		Company res = null;
		res = service.getCompany(company_id);
		return res;
	}
	
	@POST
//	@Produces(MediaType.TEXT_XM)
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_XML)
	public Company createCompany(@FormParam("company_name") String company_name,
			@FormParam("company_representative") String company_representative,
			@FormParam("company_contract_info") String company_contract_info,
			@FormParam("company_address") String company_address){
		System.out.println("@POST");
		Company res = null;
		res = service.createCompany(company_name,company_representative,company_contract_info,company_address);
		return res;
	}
	
	@Path("{company_id}")
	@PUT
//	@Produces(MediaType.TEXT_XML)
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_XML)
	public Company updateCompany(@PathParam("company_id") String company_id,
			@FormParam("company_name") String company_name,
			@FormParam("company_representative") String company_representative,
			@FormParam("company_contract_info") String company_contract_info,
			@FormParam("company_address") String company_address){
		System.out.println("@PUT");
		Company res = null;
		res = service.updateCompany(company_id,company_name,company_representative,company_contract_info,company_address);
		return res;
	}
	
	@Path("{company_id}")
	@DELETE
	@Produces(MediaType.APPLICATION_XML)
	public List<Company> deleteCompany(@PathParam("company_id") String company_id){
		System.out.println("@DELETE");
		List<Company> res = null;
		res = service.deleteCompany(company_id);
		return res;
	}
}
