package org.zframework.web.entity.business;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bus_heartbeatlog")
@JsonIgnoreProperties(ignoreUnknown = true)
public class HeartbeatLog implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="seq_bus_content")
    @SequenceGenerator(name="seq_bus_content",sequenceName="seq_bus_content")
    private Integer id;
    @Column(name = "stationcode")
    private String stationCode;
    @Column(name = "stationname")
    private String stationName;
    @Column(name = "heartbeattime")
    private String heartbeatTime;

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

    public String getHeartbeatTime() {
        return heartbeatTime;
    }

    public void setHeartbeatTime(String heartbeatTime) {
        this.heartbeatTime = heartbeatTime;
    }

    @Override
    public String toString() {
        return "HeartbeatLog{" +
                "id=" + id +
                ", stationCode='" + stationCode + '\'' +
                ", stationName='" + stationName + '\'' +
                ", heartbeatTime='" + heartbeatTime + '\'' +
                '}';
    }
}
