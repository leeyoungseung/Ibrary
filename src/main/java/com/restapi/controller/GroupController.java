package com.restapi.controller;

import com.restapi.model.*;
import com.restapi.service.*;

import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;

@Path("/Companies/{company_id}/Groups")
public class GroupController {

	private GroupService service = new GroupService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Group> getGroups(@PathParam("company_id") String company_id){
		System.out.println("Group => @GET");
		List<Group> res = null;
		res = service.getGroups(company_id);
		return res;
	}
	
	@Path("{group_id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Group getGroup(@PathParam("group_id") String group_id){
		System.out.println("Group => @GET + id");
		Group res = null;
		res = service.getGroup(group_id);
		return res;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Group createGroup(@FormParam("company_id") String company_id,
			@FormParam("group_name") String group_name,
			@FormParam("group_introduction") String group_introduction,
			@FormParam("group_fanclub") String group_fanclub,
			@FormParam("group_start_day") String group_start_day){
		System.out.println("Group => @POST");
		Group res = null;
		res = service.createGroup(company_id, group_name, group_introduction, group_fanclub, group_start_day);
		return res;
	}
	
	@Path("{group_id}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Group updateGroup(
			@PathParam("group_id") String group_id,
			@FormParam("group_name") String group_name,
			@FormParam("group_introduction") String group_introduction,
			@FormParam("group_fanclub") String group_fanclub
			){
		System.out.println("Group => @PUT");
		Group res = null;
		res = service.updateGroup(group_id, group_name, group_introduction, group_fanclub);
		return res;
	}
	
	@Path("{group_id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public List<Group> deleteGroup(@PathParam("group_id") String group_id){
		System.out.println("Group => @DELETE");
		List<Group> res = null;
		res = service.deleteGroup(group_id);
		return res;
	}
	
	
}
