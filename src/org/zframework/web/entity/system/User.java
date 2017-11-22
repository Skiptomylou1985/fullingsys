package org.zframework.web.entity.system;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "sys_user")
@JsonIgnoreProperties(value={"passWord"}) 
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1538302653864710741L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_sys_user")
	@SequenceGenerator(name = "seq_sys_user", sequenceName = "seq_sys_user")
	private Integer id;
	@Column
	@Length(min = 3, max = 20)
	private String loginName;
	@Column
	private String passWord;
	@Column
	@Length(min = 2, max = 20)
	private String realName;
	@Column
	@Length(min=0,max=500)
	private String address;
	@Column
	@Length(min=0,max=20)
	private String telphone;
	@Column
	@Length(min=0,max=20)
	private String mobile;
	@Column
	@Email
	private String eMail;
	@Column(name = "openid")
	@Length(min=0,max=50)
	private String openId;
	@Column(name = "nickname")
	@Length(min=0,max=50)
	private String nickName;
	@Column
	private String pageStyle;
	@Column
	private int enabled;
	@Column
	private int isonline;
	@Column
	private String lastLoginTime;
	@Column
	private String lastLoginIP;
	@Column
	private String lastLoginType;
	@Column
	private String createTime;
	/**
	 * 该用户所拥有的所有角色
	 */
	@ManyToMany(cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinTable(name="sys_userrole",joinColumns={@JoinColumn(name="userid")},inverseJoinColumns={@JoinColumn(name="roleid")})
	@OrderBy("id asc")
	private List<Role> roles = new ArrayList<Role>();
	@ManyToMany(cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinTable(name="sys_userunit",joinColumns={@JoinColumn(name="userid")},inverseJoinColumns={@JoinColumn(name="unitid")})
	@OrderBy("id asc")
	private List<Unit> units = new ArrayList<Unit>();
	
	@Transient
	private List<Resource> resources = new ArrayList<Resource>();
	@Transient
	private List<Resource> resourcesBtns = new ArrayList<Resource>();
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEMail() {
		return eMail;
	}

	public void setEMail(String eMail) {
		this.eMail = eMail;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPageStyle() {
		return pageStyle;
	}

	public void setPageStyle(String pageStyle) {
		this.pageStyle = pageStyle;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public int getEnabled() {
		return enabled;
	}

	public int getIsonline() {
		return isonline;
	}

	public void setIsonline(int isonline) {
		this.isonline = isonline;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIP() {
		return lastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}

	public String getLastLoginType() {
		return lastLoginType;
	}

	public void setLastLoginType(String lastLoginType) {
		this.lastLoginType = lastLoginType;
	}
	public String getCreateTime() {

		return createTime;
	}

	public void setCreateTime(Date createTime) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.createTime = formater.format(createTime);
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}

	public List<Resource> getResources() {
		return resources;
	}
	
	public void setResources(List<Resource> resources){
		this.resources = resources;
	}

	public List<Resource> getResourcesBtns() {
		return resourcesBtns;
	}

	public void setResourcesBtns(List<Resource> resourcesBtns) {
		this.resourcesBtns = resourcesBtns;
	}
}
