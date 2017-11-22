package org.zframework.web.entity.system;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="sys_unit")
public class Unit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 942006292807925468L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="seq_sys_unit")
	@SequenceGenerator(name="seq_sys_unit",sequenceName="seq_sys_unit")
	private int id;
	@NotNull
	@NotEmpty
	@Column(name = "type")
	private String unitType;
	@Column
	@NotNull
	@NotEmpty
	@Length(min=1,max=50)
	private String name;
	@Column
	@Length(min=0,max=50)
	private String code;
	@Column
	@Length(min=0,max=200)
	private String address;
	@Column
	@Length(min=0,max=20)
	private String contactor;
	@Column(name = "contactphone")
	@Length(min=0,max=20)
	private String contactPhone;
	@Length(min=0,max=50)
	@Column(name = "petrocode")
	private String petroCode;
	@Column
	private int parentId;
	/*
	 * 该单位下的用户列表
	 */
	@ManyToMany(cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinTable(name="sys_userunit",joinColumns={@JoinColumn(name="unitid")},inverseJoinColumns={@JoinColumn(name="userid")})
	@OrderBy("id asc")
	private List<User> users = new ArrayList<User>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	//	public UnitType getUnitType() {
//		return unitType;
//	}
//	public void setUnitType(UnitType unitType) {
//		this.unitType = unitType;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactor() {return contactor;}
	public void setContactor(String contactor) {this.contactor = contactor;}
	public String getContactPhone() {return contactPhone;}
	public void setContactPhone(String contactPhone) {this.contactPhone = contactPhone;}
	public String getPetroCode() {return petroCode;}
	public void setPetroCode(String petroCode) {this.petroCode = petroCode;}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
}
