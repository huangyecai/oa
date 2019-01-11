package com.hyc.oa.modules.user.entity;

import com.hyc.oa.common.base.entity.BaseEntity;
import com.hyc.oa.modules.department.entity.Department;
import com.hyc.oa.modules.role.entity.Role;

import org.apache.catalina.core.ApplicationContext;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.Set;

public class User extends BaseEntity<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 用户id（登录表用）
	 */
	private String userId;
	/**
	 * 姓氏
	 */
	private String familyName;

	/**
	 * 名字
	 */
	private String firstName;

	/**
	 * 企业id(租户)
	 */
	private String enterpriseId;

	/**
	 * 归属部门
	 */
	private String organizationId;

	/**
	 * 用户头像
	 */
	private String photo;

	/**
	 * 性别
	 */
	private int sex;

	/**
	 * 显示手机
	 */
	private String mobile;

	/**
	 * 联系电话
	 */
	private String telephone;
	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 微信账号
	 */
	private String weixin;

	/**
	 * qq账号
	 */
	private String qq;

	/**
	 * 办公地址
	 */
	private String officeAddress;

	/**
	 * 职位
	 */
	private String job;

	/**
	 * 工号
	 */
	private String jobNumber;

	/**
	 * 学历
	 */
	private String education;

	/**
	 * 毕业学校
	 */
	private String school;

	/**
	 * 专业
	 */
	private String major;

	/**
	 * 毕业日期
	 */
	private String graduationDate;

	/**
	 * 籍贯
	 */
	private String natives;

	/**
	 * 民族
	 */
	private String nation;

	/**
	 * 身份证号
	 */
	private String identity;

	/**
	 * 护照号码
	 */
	private String passport;

	/**
	 * 社保号码
	 */
	private String social;

	/**
	 * 银行账号
	 */
	private String bankAccount;

	/**
	 * 生日
	 */
	private Date birthday;

	/**
	 * 婚姻状况
	 */
	private String marriage;

	/**
	 * 家庭住址
	 */
	private String address;

	/**
	 * 紧急联系人
	 */
	private String emergencyContact;

	/**
	 * 紧急联系电话
	 */
	private String emergencyPhone;

	/**
	 * 座机
	 */
	private String telPhone;

	/**
	 * 是否已上传过头像，0：未上传，1：已上传
	 */
	private int isUpload;

	/**
	 * 联系人列表及信息
	 */
	private String linkList;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 是否激活
	 */
	private int authFlag;
	/**
	 * 登录ip
	 */
	private String loginIP;
	/**
	 * 登陆时间
	 */
	private Date loginDate;
	
	
	private transient Set<Role> roles;

	/**
	 * 密码
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 密码
	 * 
	 * @return
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 激活
	 * 
	 * @return
	 */
	public int getAuthFlag() {
		return authFlag;
	}

	/**
	 * 激活
	 * 
	 * @return
	 */
	public void setAuthFlag(int authFlag) {
		this.authFlag = authFlag;
	}

	/**
	 * 登录IP
	 * 
	 * @return
	 */
	public String getLoginIP() {
		return loginIP;
	}

	/**
	 * 登录IP
	 * 
	 * @param loginIP
	 */
	public void setLoginIp(String loginIP) {
		this.loginIP = loginIP;
	}

	/**
	 * 登录时间
	 * 
	 * @param
	 */
	public Date getLoginDate() {
		return loginDate;
	}

	/**
	 * 登录时间
	 * 
	 * @param
	 */
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	/**
	 * 姓氏
	 * 
	 * @return family_name 姓氏
	 */
	public String getFamilyName() {
		return familyName;
	}

	/**
	 * 姓氏
	 * 
	 * @param familyName 姓氏
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	/**
	 * 名字
	 * 
	 * @return first_name 名字
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 名字
	 * 
	 * @param firstName 名字
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * 企业id(租户)
	 * 
	 * @return enterprise_id 企业id(租户)
	 */
	public String getEnterpriseId() {
		return enterpriseId;
	}

	/**
	 * 企业id(租户)
	 * 
	 * @param enterpriseId 企业id(租户)
	 */
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	/**
	 * 归属部门
	 * 
	 * @return organization_id 归属部门
	 */
	public String getOrganizationId() {
		return organizationId;
	}

	/**
	 * 归属部门
	 * 
	 * @param organizationId 归属部门
	 */
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	/**
	 * 姓名
	 * 
	 * @return name 姓名
	 */
	public String getName() {
		if (StringUtils.isNotBlank(this.firstName) || StringUtils.isNotBlank(this.familyName)) {
			return this.familyName + this.firstName;
		}
		return "";
	}

	/**
	 * 显示手机
	 * 
	 * @return mobile 显示手机
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 显示手机
	 * 
	 * @param mobile 显示手机
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 用户头像
	 * 
	 * @return photo 用户头像
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * 用户头像
	 * 
	 * @param photo 用户头像
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
	 * 性别
	 * 
	 * @return sex 性别
	 */
	public int getSex() {
		return sex;
	}

	/**
	 * 性别
	 * 
	 * @param sex 性别
	 */
	public void setSex(int sex) {
		this.sex = sex;
	}

	/**
	 * 邮箱
	 * 
	 * @return email 邮箱
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 邮箱
	 * 
	 * @param email 邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 微信账号
	 * 
	 * @return weixin 微信账号
	 */
	public String getWeixin() {
		return weixin;
	}

	/**
	 * 微信账号
	 * 
	 * @param weixin 微信账号
	 */
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	/**
	 * qq账号
	 * 
	 * @return qq qq账号
	 */
	public String getQq() {
		return qq;
	}

	/**
	 * qq账号
	 * 
	 * @param qq qq账号
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}

	/**
	 * 办公地址
	 * 
	 * @return office_address 办公地址
	 */
	public String getOfficeAddress() {
		return officeAddress;
	}

	/**
	 * 办公地址
	 * 
	 * @param officeAddress 办公地址
	 */
	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	/**
	 * 职位
	 * 
	 * @return job 职位
	 */
	public String getJob() {
		return job;
	}

	/**
	 * 职位
	 * 
	 * @param job 职位
	 */
	public void setJob(String job) {
		this.job = job;
	}

	/**
	 * 工号
	 * 
	 * @return job_number 工号
	 */
	public String getJobNumber() {
		return jobNumber;
	}

	/**
	 * 工号
	 * 
	 * @param jobNumber 工号
	 */
	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	/**
	 * 学历
	 * 
	 * @return education 学历
	 */
	public String getEducation() {
		return education;
	}

	/**
	 * 学历
	 * 
	 * @param education 学历
	 */
	public void setEducation(String education) {
		this.education = education;
	}

	/**
	 * 毕业学校
	 * 
	 * @return school 毕业学校
	 */
	public String getSchool() {
		return school;
	}

	/**
	 * 毕业学校
	 * 
	 * @param school 毕业学校
	 */
	public void setSchool(String school) {
		this.school = school;
	}

	/**
	 * 专业
	 * 
	 * @return major 专业
	 */
	public String getMajor() {
		return major;
	}

	/**
	 * 专业
	 * 
	 * @param major 专业
	 */
	public void setMajor(String major) {
		this.major = major;
	}

	/**
	 * 毕业日期
	 * 
	 * @return graduation_date 毕业日期
	 */
	public String getGraduationDate() {
		return graduationDate;
	}

	/**
	 * 毕业日期
	 * 
	 * @param graduationDate 毕业日期
	 */
	public void setGraduationDate(String graduationDate) {
		this.graduationDate = graduationDate;
	}

	/**
	 * 籍贯
	 * 
	 * @return natives 籍贯
	 */
	public String getNatives() {
		return natives;
	}

	/**
	 * 籍贯
	 * 
	 * @param natives 籍贯
	 */
	public void setNatives(String natives) {
		this.natives = natives;
	}

	/**
	 * 民族
	 * 
	 * @return nation 民族
	 */
	public String getNation() {
		return nation;
	}

	/**
	 * 民族
	 * 
	 * @param nation 民族
	 */
	public void setNation(String nation) {
		this.nation = nation;
	}

	/**
	 * 身份证号
	 * 
	 * @return identity 身份证号
	 */
	public String getIdentity() {
		return identity;
	}

	/**
	 * 身份证号
	 * 
	 * @param identity 身份证号
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}

	/**
	 * 护照号码
	 * 
	 * @return passport 护照号码
	 */
	public String getPassport() {
		return passport;
	}

	/**
	 * 护照号码
	 * 
	 * @param passport 护照号码
	 */
	public void setPassport(String passport) {
		this.passport = passport;
	}

	/**
	 * 社保号码
	 * 
	 * @return social 社保号码
	 */
	public String getSocial() {
		return social;
	}

	/**
	 * 社保号码
	 * 
	 * @param social 社保号码
	 */
	public void setSocial(String social) {
		this.social = social;
	}

	/**
	 * 银行账号
	 * 
	 * @return bank_account 银行账号
	 */
	public String getBankAccount() {
		return bankAccount;
	}

	/**
	 * 银行账号
	 * 
	 * @param bankAccount 银行账号
	 */
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	/**
	 * 生日
	 * 
	 * @return birthday 生日
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * 生日
	 * 
	 * @param birthday 生日
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * 婚姻状况
	 * 
	 * @return marriage 婚姻状况
	 */
	public String getMarriage() {
		return marriage;
	}

	/**
	 * 婚姻状况
	 * 
	 * @param marriage 婚姻状况
	 */
	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	/**
	 * 家庭住址
	 * 
	 * @return address 家庭住址
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 家庭住址
	 * 
	 * @param address 家庭住址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 紧急联系人
	 * 
	 * @return emergency_contact 紧急联系人
	 */
	public String getEmergencyContact() {
		return emergencyContact;
	}

	/**
	 * 紧急联系人
	 * 
	 * @param emergencyContact 紧急联系人
	 */
	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	/**
	 * 紧急联系电话
	 * 
	 * @return emergency_phone 紧急联系电话
	 */
	public String getEmergencyPhone() {
		return emergencyPhone;
	}

	/**
	 * 紧急联系电话
	 * 
	 * @param emergencyPhone 紧急联系电话
	 */
	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

	/**
	 * 座机
	 * 
	 * @return tel_phone 座机
	 */
	public String getTelPhone() {
		return telPhone;
	}

	/**
	 * 座机
	 * 
	 * @param telPhone 座机
	 */
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	/**
	 * 是否已上传过头像，0：未上传，1：已上传
	 * 
	 * @return is_upload 是否已上传过头像，0：未上传，1：已上传
	 */
	public int getIsUpload() {
		return isUpload;
	}

	/**
	 * 是否已上传过头像，0：未上传，1：已上传
	 * 
	 * @param isUpload 是否已上传过头像，0：未上传，1：已上传
	 */
	public void setIsUpload(int isUpload) {
		this.isUpload = isUpload;
	}

	/**
	 * 联系人列表及信息
	 * 
	 * @return link_list 联系人列表及信息
	 */
	public String getLinkList() {
		return linkList;
	}

	/**
	 * 联系人列表及信息
	 * 
	 * @param linkList 联系人列表及信息
	 */
	public void setLinkList(String linkList) {
		this.linkList = linkList;
	}

	/**
	 * 用户id
	 * 
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 用户id
	 * 
	 * @param userId
	 */

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	 

	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}
	
	/** 部门 **/
	private transient Department organization;

	public Department getOrganization() {
		return organization;
	}

	public void setOrganization(Department organization) {
		this.organization = organization;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
}
