package com.restapi.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class Group {

	private int group_id;
	private int company_id;
	private String group_name;
	private String group_introduction;
	private String group_fanclub;
	private Date group_start_day;
	
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getGroup_introduction() {
		return group_introduction;
	}
	public void setGroup_introduction(String group_introduction) {
		this.group_introduction = group_introduction;
	}
	public String getGroup_fanclub() {
		return group_fanclub;
	}
	public void setGroup_fanclub(String group_fanclub) {
		this.group_fanclub = group_fanclub;
	}
	public Date getGroup_start_day() {
		return group_start_day;
	}
	public void setGroup_start_day(Date group_start_day) {
		this.group_start_day = group_start_day;
	}
}
