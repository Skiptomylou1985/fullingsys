package org.zframework.web.entity.business;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "bus_orderlog")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderLog implements Serializable {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO,generator="seq_bus_content")
	@SequenceGenerator(name="seq_bus_content",sequenceName="seq_bus_content")
	private Integer log_id;
	//
	@Column(name = "id")
	private Integer id;
	// 交易类型
	@Column(name = "transtype")
	private String transType;
	//
	@Column(name = "transcode")
	private String transCode;
	// 商品编码
	@Column(name = "meterialcode")
	private String meterialCode;
	// 交易数量
	@Column(name = "volume")
	private double volume;
	// 交易金额
	@Column(name = "realamount")
	private double realAmount;
	// 交易单价
	@Column(name = "price")
	private double price;
	// 支付时间
	@Column(name = "paytime")
	private String payTime;
	// 营业日期
	@Column(name = "settledate")
	private String settleDate;
	// 油枪号
	@Column(name = "nozzleno")
	private Integer nozzleNo;
	// 提枪泵码
	@Column(name = "startread")
	private double startRead;
	// 挂枪泵码
	@Column(name = "endread")
	private double endRead;
	// 支付票号
	@Column(name = "billno")
	private Integer billNo;
	// 支付项目标识，即交易序号
	@Column(name = "billitemid")
	private Integer billItemId;
	// pos机编号
	@Column(name = "posno")
	private String posNo;
	// 交易类型
	@Column(name = "statustype")
	private String statusType;
	// 交易支付编码
	@Column(name = "paycode")
	private String payCode;
	// 关联tradelog交易表id
	@Column(name = "tradeid")
	private Integer tradeId;
	// 油站编码
	@Column(name = "station_code")
	private String stationCode;
	// 油站名称
	@Column(name = "station_name")
	private String stationName;
	// 修改时间
	@Column(name = "update_time")
	private Date updateTime;

	private int upload;

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

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
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

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public Date getSettleDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try{
			date = dateFormat.parse(settleDate);
		}catch (Exception e){

		}
		return date;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	public Integer getNozzleNo() {
		return nozzleNo;
	}

	public void setNozzleNo(Integer nozzleNo) {
		this.nozzleNo = nozzleNo;
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

	public Integer getBillNo() {
		return billNo;
	}

	public void setBillNo(Integer billNo) {
		this.billNo = billNo;
	}

	public Integer getBillItemId() {
		return billItemId;
	}

	public void setBillItemId(Integer billItemId) {
		this.billItemId = billItemId;
	}

	public String getPosNo() {
		return posNo;
	}

	public void setPosNo(String posNo) {
		this.posNo = posNo;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	public Integer getTradeId() {
		return tradeId;
	}

	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
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

	public int getUpload() {
		return upload;
	}

	public void setUpload(int upload) {
		this.upload = upload;
	}

	@Override
	public String toString() {
		return "OrderLog{" +
				"log_id=" + log_id +
				", id=" + id +
				", transType='" + transType + '\'' +
				", transCode='" + transCode + '\'' +
				", meterialCode='" + meterialCode + '\'' +
				", volume=" + volume +
				", realAmount=" + realAmount +
				", price=" + price +
				", payTime=" + payTime +
				", settleDate='" + settleDate + '\'' +
				", nozzleNo=" + nozzleNo +
				", starTread=" + startRead +
				", endRead=" + endRead +
				", billNo=" + billNo +
				", billItemId=" + billItemId +
				", posNo='" + posNo + '\'' +
				", statusType='" + statusType + '\'' +
				", payCode='" + payCode + '\'' +
				", tradeId=" + tradeId +
				", stationCode='" + stationCode + '\'' +
				", stationName='" + stationName + '\'' +
				", updateTime=" + updateTime +
				'}';
	}
}
