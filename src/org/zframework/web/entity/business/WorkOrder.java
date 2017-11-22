package org.zframework.web.entity.business;

import org.codehaus.jackson.map.ObjectMapper;

import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hyf on 2017/9/6.
 */
@Entity
@Table(name = "bus_workorder")
public class WorkOrder implements Serializable{
    private static final long serialVersionUID = 3509941384851901401L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="seq_bus_content")
    @SequenceGenerator(name="seq_bus_content",sequenceName="seq_bus_content")
    private int id;
    //工单编号
    @Column
    private String code;
    //工单状态
    @Column(name = "status")
    private int status;
    //工单所属单位权限系统ID
    @Column(name = "unitid")
    private int unitId;
    //维修人员系统id
    @Column(name = "operatorid")
    private int operatorId;
    //维修人员微信openid
    @Column(name = "operatoropenid")
    private String operatorOpenId;
    //提交人微信openid
    @Column(name = "submitopenid")
    private  String submitOpenId;
    //提交人手机号
    @Column(name = "cellphone")
    private  String cellPhone;
    //站点石油系统编码
    @Column(name = "petrocode")
    private  String petroCode;
    //提交人
    @Column(name = "submitter")
    private  String submitter;
    //提交日期
    @Column(name = "submitdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date submitDate;
    //运维人员
    @Column(name = "operator")
    private  String operator;
    //维修完成日期
    @Column(name = "servicedate")
    @Temporal(TemporalType.TIMESTAMP)
    private  Date serviceDate;
    //提交人确认维修完成日期
    @Column(name = "confirmdate")
    @Temporal(TemporalType.TIMESTAMP)
    private  Date confirmDate;
    //省份
    @Column(name = "province")
    private  String province;
    //城市
    @Column(name = "city")
    private  String city;
    //站点名
    @Column(name = "stationname")
    private  String stationName;
    //是否推送到维修人员
    @Column(name = "ispush")
    private  int isPush;
    //故障类型 1设备故障 2网络故障 3 软件故障 0其他故障
    @Column(name = "faulttype")
    private  int faultType;
    //故障内容
    @Column(name = "content")
    private  String content;
    //评分
    @Column(name = "score")
    private  int score;

    //推送日期
    @Column(name = "pushdate")
    @Temporal(TemporalType.TIMESTAMP)
    private  Date pushDate;

    //运维人员联系电话
    @Column(name = "operatorphone")
    private  String operatorPhone;

    //维修记录
    @Column(name = "log")
    private String log;

    public String toJson() {

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public String getSubmitOpenId() {return submitOpenId;}

    public void setSubmitOpenId(String submitOpenId) {this.submitOpenId = submitOpenId;}

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getPetroCode() {
        return petroCode;
    }

    public void setPetroCode(String petroCode) {
        this.petroCode = petroCode;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(java.util.Date time) {
        this.submitDate = time;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(java.util.Date time) {
        this.serviceDate = time;
    }
    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(java.util.Date time) {
        this.confirmDate = time;
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

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getIsPush() {
        return isPush;
    }

    public void setIsPush(int isPush) {
        this.isPush = isPush;
    }

    public int getFaultType() {
        return faultType;
    }

    public void setFaultType(int faultType) {
        this.faultType = faultType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getPushDate() {
        return pushDate;
    }

    public void setPushDate(Date pushDate) {
        this.pushDate = pushDate;
    }

    public String getOperatorPhone() {
        return operatorPhone;
    }

    public void setOperatorPhone(String operatorPhone) {
        this.operatorPhone = operatorPhone;
    }

    public String getOperatorOpenId() {
        return operatorOpenId;
    }

    public void setOperatorOpenId(String operatorOpenId) {
        this.operatorOpenId = operatorOpenId;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
