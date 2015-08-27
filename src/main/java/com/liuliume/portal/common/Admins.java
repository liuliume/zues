package com.liuliume.portal.common;

import java.util.List;

public class Admins {

	private Integer admin_id;

	private String admin_name;

	private String name;

	private String token;

	private List<Integer> groups;

	public Integer getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}

	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<Integer> getGroups() {
		return groups;
	}

	public void setGroups(List<Integer> groups) {
		this.groups = groups;
	}
}
