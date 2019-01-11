package com.hyc.oa.modules.login.entity;

import java.util.Date;

public class Login {
     /**
     *  用户id 
     */
    private String userId;

     /**
     *  密码 
     */
    private String password;

     /**
     *  系统认证标识，标识是否为注册用户,0标识未激活，1标识已激活 
     */
    private int authFlag;

     /**
     *  最后登陆IP 
     */
    private String loginIp;

     /**
     *  最后登陆时间 
     */
    private Date loginDate;

     /**
     *  创建者 
     */
    private String createBy;

     /**
     *  创建时间 
     */
    private Date createDate;

     /**
     *  更新者 
     */
    private String updateBy;

     /**
     *  更新时间 
     */
    private Date updateDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId( String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password) {
        this.password = password;
    }

    public int getAuthFlag() {
        return authFlag;
    }

    public void setAuthFlag( int authFlag) {
        this.authFlag = authFlag;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp( String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate( Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy( String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate( Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy( String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate( Date updateDate) {
        this.updateDate = updateDate;
    }

}