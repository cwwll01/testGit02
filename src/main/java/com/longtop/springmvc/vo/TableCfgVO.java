package com.longtop.springmvc.vo;

import java.util.List;


public class TableCfgVO {
	private String tableName;
	private List<FieldCfgVO> list;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<FieldCfgVO> getList() {
		return list;
	}
	public void setList(List<FieldCfgVO> list) {
		this.list = list;
	}
	
	
	
}
