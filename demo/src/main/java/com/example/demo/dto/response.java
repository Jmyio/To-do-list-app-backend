package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

public class response {
	private List<UserInfo> userinfo;
	private List<Lists> lists;
	private List<Object> taskslist = new ArrayList<>();
	
	public List<UserInfo> getUserInfo() {
		return userinfo;
	}
	
	public List<Lists> getLists() {
		return lists;
	}
	
	public List<Object> getListsTasks() {
		return taskslist;
	}
	
	public void setUserInfo(List<UserInfo> userinfo) {
		this.userinfo = userinfo;
	}
	
	public void setLists(List<Lists> lists) {
		this.lists = lists;
	}	
	
	public void addListsTasksInto(List<ListsTasks> liststasks) {
		taskslist.add(liststasks);
	}
}
