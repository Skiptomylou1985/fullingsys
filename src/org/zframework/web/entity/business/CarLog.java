package org.zframework.web.entity.business;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "bus_carlog")
public class CarLog implements Serializable {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO,generator="seq_bus_content")
	@SequenceGenerator(name="seq_bus_content",sequenceName="seq_bus_content")
	private Integer log_id;

	//
	@Column(name = "id")
	private Integer id;
	//
	@Column(name = "carnumber")
	private String carNumber;
	//
	@Column(name = "carnumcolor")
	private Integer carNumColor;
	//
	@Column(name = "cartype")
	private Integer carType;
	//
	@Column(name = "carlogo")
	private Integer carLogo;
	//
	@Column(name = "subcarlogo")
	private Integer subCarLogo;
	//
	@Column(name = "carcolor")
	private Integer carColor;
	//
	@Column(name = "arrivetime")
	private String arriveTime;
	//
	@Column(name = "leavetime")
	private String leaveTime;
	//
	@Column(name = "nozzleno")
	private Integer nozzleNo;
	//
	@Column(name = "picpath")
	private String picPath;
	//
	@Column(name = "begintime")
	private String beginTime;
	//
	@Column(name = "endtime")
	private String endTime;
	//
	@Column(name = "oiltype")
	private Integer oilType;
	// 加油量
	@Column(name = "volume")
	private double volume;
	// 金额
	@Column(name = "realamount")
	private double realAmount;
	// 交易序列号
	@Column(name = "tradesn")
	private String tradeSn;
	// 提枪泵码
	@Column(name = "startread")
	private double startRead;
	// 挂枪泵码
	@Column(name = "endread")
	private double endRead;
	// 油品编码
	@Column(name = "meterialcode")
	private String meterialCode;
	// 油品单价
	@Column(name = "oilprice")
	private double oilPrice;
	// 油站编码
	@Column(name = "station_code")
	private String stationCode;
	// 油站名称
	@Column(name = "station_name")
	private String stationName;
	// 修改时间
	@Column(name = "update_time")
	private Date updateTime;

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

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public Integer getCarNumColor() {
		return carNumColor;
	}

	public void setCarNumColor(Integer carNumColor) {
		this.carNumColor = carNumColor;
	}

	public Integer getCarType() {
		return carType;
	}

	public void setCarType(Integer carType) {
		this.carType = carType;
	}

	public Integer getCarLogo() {
		return carLogo;
	}

	public void setCarLogo(Integer carLogo) {
		this.carLogo = carLogo;
	}

	public Integer getSubCarLogo() {
		return subCarLogo;
	}

	public void setSubCarLogo(Integer subCarLogo) {
		this.subCarLogo = subCarLogo;
	}

	public Integer getCarColor() {
		return carColor;
	}

	public void setCarColor(Integer carColor) {
		this.carColor = carColor;
	}

	public String getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}

	public String getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}

	public Integer getNozzleNo() {
		return nozzleNo;
	}

	public void setNozzleNo(Integer nozzleNo) {
		this.nozzleNo = nozzleNo;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getOilType() {
		return oilType;
	}

	public void setOilType(Integer oilType) {
		this.oilType = oilType;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(double realAmount) {
		this.realAmount = realAmount;
	}

	public String getTradeSn() {
		return tradeSn;
	}

	public void setTradeSn(String tradeSn) {
		this.tradeSn = tradeSn;
	}

	public double getStartRead() {
		return startRead;
	}

	public void setStartRead(double startRead) {
		this.startRead = startRead;
	}

	public double getEndRead() {
		return endRead;
	}

	public void setEndRead(double endRead) {
		this.endRead = endRead;
	}

	public String getMeterialCode() {
		return meterialCode;
	}

	public void setMeterialCode(String meterialCode) {
		this.meterialCode = meterialCode;
	}

	public double getOilPrice() {
		return oilPrice;
	}

	public void setOilPrice(double oilPrice) {
		this.oilPrice = oilPrice;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "CarLog{" +
				"log_id=" + log_id +
				", id=" + id +
				", carNumber='" + carNumber + '\'' +
				", carNumColor=" + carNumColor +
				", carType=" + carType +
				", carLogo=" + carLogo +
				", subCarLogo=" + subCarLogo +
				", carColor=" + carColor +
				", arriveTime='" + arriveTime + '\'' +
				", leaveTime='" + leaveTime + '\'' +
				", nozzleNo=" + nozzleNo +
				", picPath='" + picPath + '\'' +
				", beginTime='" + beginTime + '\'' +
				", endTime='" + endTime + '\'' +
				", oilType=" + oilType +
				", volume=" + volume +
				", realAmount=" + realAmount +
				", tradeSn='" + tradeSn + '\'' +
				", startRead=" + startRead +
				", endRead=" + endRead +
				", meterialCode='" + meterialCode + '\'' +
				", oilPrice=" + oilPrice +
				", stationCode='" + stationCode + '\'' +
				", stationName='" + stationName + '\'' +
				", updateTime=" + updateTime +
				'}';
	}
}
