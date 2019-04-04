package org.zframework.web.entity.business;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "bus_tradelog")
public class TradeLog implements Serializable {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO,generator="seq_bus_content")
	@SequenceGenerator(name="seq_bus_content",sequenceName="seq_bus_content")
	private int log_id;

	@Column(name = "id")
	private int id;
	// 油枪号
	@Column(name = "nozzleno")
	private int nozzleNo;
	// 物料编码
	@Column(name = "meterialcode")
	private String meterialCode;
	// 加油量
	@Column(name = "volume")
	private double volume;
	// 交易金额
	@Column(name = "realamount")
	private double realAmount;
	// 单价
	@Column(name = "price")
	private double price;
	// 提枪时间
	@Column(name = "starttime")
	private String startTime;
	// 挂枪时间
	@Column(name = "endtime")
	private String endTime;
	// 提枪泵码
	@Column(name = "startread")
	private double startRead;
	// 挂枪泵码
	@Column(name = "endread")
	private double endRead;
	// 车牌号
	@Column(name = "carnumber")
	private String carNumber;
	// 车辆品牌
	@Column(name = "carbrand")
	private int carBrand;
	// 车辆子品牌
	@Column(name = "subbrand")
	private int subBrand;
	// 车型
	@Column(name = "cartype")
	private int carType;
	// 车辆颜色
	@Column(name = "carcolor")
	private int carColor;
	// 车牌颜色
	@Column(name = "carnumcolor")
	private int carNumColor;
	@Column(name = "realcarbrand")
	private String realCarBrand;
	@Column(name = "realsubbrand")
	private String realSubBrand;
	@Column(name = "oilname")
	private String oilName;
	@Column(name = "oilcode")
	private String oilCode;
	@Column(name = "oilclass")
	private String oilClass;
	// 油站编码
	@Column(name = "station_code")
	private String stationCode;
	// 油站名称
	@Column(name = "station_name")
	private String stationName;
	// 修改时间
	@Column(name = "update_time")
	private Date updateTime;

	public int getLog_id() {
		return log_id;
	}

	public void setLog_id(int log_id) {
		this.log_id = log_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNozzleNo() {
		return nozzleNo;
	}

	public void setNozzleNo(int nozzleNo) {
		this.nozzleNo = nozzleNo;
	}

	public String getMeterialCode() {
		return meterialCode;
	}

	public void setMeterialCode(String meterialCode) {
		this.meterialCode = meterialCode;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getStartTime() {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try{
			date = dateFormat.parse(startTime);
		}catch (Exception e){

		}
		return date;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try{
			date = dateFormat.parse(startTime);
		}catch (Exception e){

		}
		return date;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public int getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(int carBrand) {
		this.carBrand = carBrand;
	}

	public int getSubBrand() {
		return subBrand;
	}

	public void setSubBrand(int subBrand) {
		this.subBrand = subBrand;
	}

	public int getCarType() {
		return carType;
	}

	public void setCarType(int carType) {
		this.carType = carType;
	}

	public int getCarColor() {
		return carColor;
	}

	public void setCarColor(int carColor) {
		this.carColor = carColor;
	}

	public int getCarNumColor() {
		return carNumColor;
	}

	public void setCarNumColor(int carNumColor) {
		this.carNumColor = carNumColor;
	}

	public String getRealCarBrand() {
		return realCarBrand;
	}

	public void setRealCarBrand(String realCarBrand) {
		this.realCarBrand = realCarBrand;
	}

	public String getRealSubBrand() {
		return realSubBrand;
	}

	public void setRealSubBrand(String realSubBrand) {
		this.realSubBrand = realSubBrand;
	}

	public String getOilName() {
		return oilName;
	}

	public void setOilName(String oilName) {
		this.oilName = oilName;
	}

	public String getOilCode() {
		return oilCode;
	}

	public void setOilCode(String oilCode) {
		this.oilCode = oilCode;
	}

	public String getOilClass() {
		return oilClass;
	}

	public void setOilClass(String oilClass) {
		this.oilClass = oilClass;
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
		return "TradeLog{" +
				"id=" + id +
				", nozzleNo=" + nozzleNo +
				", meterialCode='" + meterialCode + '\'' +
				", volume=" + volume +
				", realAmount=" + realAmount +
				", price=" + price +
				", startTime=" + startTime +
				", endTime=" + endTime +
				", startRead=" + startRead +
				", endRead=" + endRead +
				", carNumber='" + carNumber + '\'' +
				", carBrand=" + carBrand +
				", subBrand=" + subBrand +
				", carType=" + carType +
				", carColor=" + carColor +
				", carNumColor=" + carNumColor +
				", realCarBrand='" + realCarBrand + '\'' +
				", realSubBrand='" + realSubBrand + '\'' +
				", oilName='" + oilName + '\'' +
				", oilCode='" + oilCode + '\'' +
				", oilClass='" + oilClass + '\'' +
				", stationCode='" + stationCode + '\'' +
				", stationName='" + stationName + '\'' +
				", updateTime=" + updateTime +
				'}';
	}
}
