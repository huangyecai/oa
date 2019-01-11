package com.hyc.oa.modules.user.entity;

import java.util.Date;

import com.hyc.oa.modules.role.entity.Role;

public class UserRole {
     /**
     *  主键id（uuid） 
     */
    private String id;

     /**
     *  用户id 
     */
    private String userId;

     /**
     *  角色id 
     */
    private String roleId;

     /**
     *  创建时间 
     */
    private Date createDate;

     /**
     *  创建者 
     */
    private String createBy;
    
    
    private transient Role role;

    public String getId() {
        return id;
    }

    public void setId( String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId( String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId( String roleId) {
        this.roleId = roleId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate( Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy( String createBy) {
        this.createBy = createBy;
    }

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

    
}