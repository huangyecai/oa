package com.hyc.oa.common.base.entity;

import com.hyc.oa.modules.user.entity.User;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * hyc
 * 
 * @param <T>
 */
public abstract class BaseEntity<T> implements Serializable {
	/**
	 * 实体编号（唯一标识）
	 */
	protected String id;
	/**
	 * 备注
	 */
	protected String remarks;
	/**
	 * 创建者
	 */
	protected User createBy;
	/**
	 * 创建日期
	 */
	protected Date createDate;
	/**
	 * 更新者
	 */
	protected User updateBy;
	/**
	 * 更新日期
	 */
	protected Date updateDate;
	/**
	 * 删除标记（0：正常；1：删除）
	 */
	private int status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public User getCreateBy() {
		return createBy;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	 

	public int getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 插入之前执行方法，需要手动调用
	 */
	public void preInsert() {

		this.setId(UUID.randomUUID().toString().replaceAll("-", ""));

		User user = new User(); // UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getId())) {
			this.updateBy = user;
			this.createBy = user;
		}
		this.updateDate = new Date();
		this.createDate = this.updateDate;
	}

	/**
	 * 更新之前执行方法，需要手动调用
	 */
	public void preUpdate() {
		User user = new User(); // UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getId())) {
			this.updateBy = user;
		}
		this.updateDate = new Date();
	}
}
