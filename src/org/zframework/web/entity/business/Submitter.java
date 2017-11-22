package org.zframework.web.entity.business;

import org.zframework.web.entity.business.WorkOrder;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyf on 2017/9/21.
 */
@Entity
@Table(name = "bus_submitter")
public class Submitter implements Serializable{
    private static final long serialVersionUID = 1538302653864710741L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_bus_submitter")
    @SequenceGenerator(name = "seq_bus_submitter", sequenceName = "seq_bus_submitter")
    private Integer id;

    @Column
    private  String name;

    @Column(name = "openid")
    private  String openId;

    @Column
    private String cellphone;

    @Column
    private Integer gender;

    @Column(name = "nickname")
    private String nickName;

    @Column(name = "createtime")
    private String createTime;

    @Column
    private  String province;

    @Column
    private  String city;

    @Column
    private  Integer status;


    //private List<WorkOrder> workOrderList = new ArrayList<WorkOrder>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.createTime = formater.format(createTime);
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}
