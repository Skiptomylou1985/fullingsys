package org.zframework.web.entity.business;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bus_carrecordlog")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarRecordLog implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="seq_bus_content")
    @SequenceGenerator(name="seq_bus_content",sequenceName="seq_bus_content")
    private Integer log_id;

    @Column(name = "id")
    private Integer id;
    @Column(name = "stationcode")
    private String stationCode;
    @Column(name = "stationname")
    private String stationName;
    @Column(name = "carnumber")
    private String carNumber;
    @Column(name = "carnumcolor")
    private String carNumColor;
    @Column(name = "carlogo")
    private String carLogo;
    @Column(name = "subcarlogo")
    private String subCarLogo;
    @Column(name = "carcolor")
    private String carColor;
    @Column(name = "arrivetime")
    private String arriveTime;

    public Integer getLog_id() {
        return log_id;
    }

    public void setLog_id(Integer log_id) {
        this.log_id = log_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarNumColor() {
        return carNumColor;
    }

    public void setCarNumColor(String carNumColor) {
        this.carNumColor = carNumColor;
    }

    public String getCarLogo() {
        return carLogo;
    }

    public void setCarLogo(String carLogo) {
        this.carLogo = carLogo;
    }

    public String getSubCarLogo() {
        return subCarLogo;
    }

    public void setSubCarLogo(String subCarLogo) {
        this.subCarLogo = subCarLogo;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    @Override
    public String toString() {
        return "CarRecordLog{" +
                "id=" + id +
                ", stationCode='" + stationCode + '\'' +
                ", stationName='" + stationName + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", carNumColor='" + carNumColor + '\'' +
                ", carLogo='" + carLogo + '\'' +
                ", subCarLogo='" + subCarLogo + '\'' +
                ", carColor='" + carColor + '\'' +
                ", arriveTime=" + arriveTime +
                '}';
    }
}
