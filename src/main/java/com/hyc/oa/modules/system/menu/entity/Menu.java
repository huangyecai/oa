package com.hyc.oa.modules.system.menu.entity;

import java.util.List;

import com.hyc.oa.common.base.entity.BaseEntity;
import com.hyc.oa.common.utils.Attributes;
import com.hyc.oa.common.utils.TreeNode;

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
	
	private transient Menu parent;

	private transient Menu parentMenu;

	private transient List<Menu> subMenuList;
	
	private transient List<Menu> children;
	
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

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public Menu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}

	public List<Menu> getSubMenuList() {
		return subMenuList;
	}

	public void setSubMenuList(List<Menu> subMenuList) {
		this.subMenuList = subMenuList;
	}


	 @Override
	public String toString() {
		String result = "{id:" + this.id + ",text:" + this.name + ",url:" +this.url;
				if (subMenuList !=null && subMenuList .size() > 0) {
					result += ",children:" + this.subMenuList;
				}
				result += "}";
		return result;
	}
	 
	public TreeNode toTreeNode() {
		TreeNode node = new TreeNode();
		node.setId(this.id);
		node.setName(this.name);
		node.setText(name);
		node.setUri(this.url);
		Attributes attributes = new Attributes();
		attributes.setUrl(this.url);
		node.setAttributes(attributes);
		return node;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	
}
