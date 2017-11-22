package org.zframework.web.entity.business;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hyf on 2017/9/21.
 */
@Entity
@Table(name = "bus_log")
public class WorkLog {
    private static final long serialVersionUID = 1538302653864710741L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_bus_log")
    @SequenceGenerator(name = "seq_bus_log", sequenceName = "seq_bus_log")
    private Integer id;

    @Column(name = "ordercode")
    private String orderCode;

    @Column
    private String name;

    @Column(name = "openid")
    private String openId;

    @Column(name = "createtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column
    private String action;

    @Column
    private String content;

    @Column(name = "beforestatus")
    private Integer beforeStatus;

    @Column(name = "afterstatus")
    private Integer afterStatus;

    @Column(name = "usertype")
    private String userType;

    @Column(name = "result")
    private String result;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getBeforeStatus() {
        return beforeStatus;
    }

    public void setBeforeStatus(Integer beforeStatus) {
        this.beforeStatus = beforeStatus;
    }

    public Integer getAfterStatus() {
        return afterStatus;
    }

    public void setAfterStatus(Integer afterStatus) {
        this.afterStatus = afterStatus;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
