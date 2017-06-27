package com.inforshuttle.mybatis.dto;

import java.io.Serializable;
import java.util.Date;

public class UserDto implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userid;
    private String username;
    private String password;
    private String address;
    private Date createtime;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @Override
    public String toString() {
        return "User [userid=" + userid + ", username=" + username + ", password="+password+", address="+address+", createtime="+createtime+"]";
    }
}
