package com.longtop.springmvc.vo;

import java.io.Serializable;

public class FieldCfgVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String isKey;
	private String type;
	private String isNull;
	private String comment;
	public String getIsKey() {
		return isKey;
	}
	public void setIsKey(String isKey) {
		this.isKey = isKey;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIsNull() {
		return isNull;
	}
	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
