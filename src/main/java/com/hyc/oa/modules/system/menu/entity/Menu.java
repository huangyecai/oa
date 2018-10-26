package com.hyc.oa.modules.system.menu.entity;

import com.hyc.oa.common.base.entity.BaseEntity;

/**
* <p>Title: Menu</p>  
* <p>Description: 菜单实体</p>  
* @author hyc  
* @date 2018年10月26日
*/
public class Menu extends BaseEntity<Menu>{

	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;

	private String name;

	private String description;

	private String url;

	private String parentId;

	private String code;

	private Integer sortNum;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}


	 
}
