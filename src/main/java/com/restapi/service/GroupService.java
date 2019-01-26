package com.restapi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.restapi.dao.GroupDAO;
import com.restapi.model.Group;

public class GroupService {

	private GroupDAO dao = new GroupDAO();

	public List<Group> getGroups(String company_id) {
		List<Group> res = null;
		try{
			res = dao.getGroups(Integer.parseInt(company_id)); 
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Group getGroup(String group_id) {
		Group res = null;
		int id = Integer.parseInt(group_id);
		try{
			res = dao.getGroup(id) ; 
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Group createGroup(String company_id, String group_name, String group_introduction, String group_fanclub,
			String group_start_day) {
		Group res = null;
		Group dto = null;
		if(!company_id.equals("") && 
				!group_name.equals("") &&
				!group_introduction.equals("") &&
				!group_fanclub.equals("") &&
				!group_start_day.equals("")){
			dto = new Group();
			dto.setCompany_id(Integer.parseInt(company_id));
			dto.setGroup_name(group_name);
			dto.setGroup_introduction(group_introduction);
			dto.setGroup_fanclub(group_fanclub);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			group_start_day = format.format( new Date());
			Date date = null;
			try {
				date = format.parse(group_start_day);
			} catch (ParseException e) {e.printStackTrace(); }
			dto.setGroup_start_day(date);
		}
		
		try{
			res = dao.createGroup(dto) ; 
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	public Group updateGroup(String group_id, String group_name, String group_introduction,
			String group_fanclub) {
		Group res = null;
		Group dto = null;
		if(!group_id.equals("") && 
				!group_name.equals("") &&
				!group_introduction.equals("") &&
				!group_fanclub.equals("")){
			dto = new Group();
			dto.setGroup_id(Integer.parseInt(group_id));
			dto.setGroup_name(group_name);
			dto.setGroup_introduction(group_introduction);
			dto.setGroup_fanclub(group_fanclub);
		}
		
		try{
			res = dao.updateGroup(dto) ; 
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	public List<Group> deleteGroup(String group_id) {
		List<Group> res = null;
		int id = Integer.parseInt(group_id);
		try{
			res = dao.deleteGroup(id) ; 
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
		
	}
}
