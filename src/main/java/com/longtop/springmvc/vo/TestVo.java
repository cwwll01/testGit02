package com.longtop.springmvc.vo;

import java.util.List;

public class TestVo {

	private String name;
	private String age;
	
	private List<FbVO> list;
	
	
	public List<FbVO> getList() {
		return list;
	}
	public void setList(List<FbVO> list) {
		this.list = list;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	
}
