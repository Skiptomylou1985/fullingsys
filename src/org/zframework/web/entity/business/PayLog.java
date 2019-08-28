package org.zframework.web.entity.business;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "bus_paylog")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PayLog implements Serializable {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO,generator="seq_bus_content")
	@SequenceGenerator(name="seq_bus_content",sequenceName="seq_bus_content")
	private Integer log_id;
	//
	@Column(name = "id")
	private Integer id;
	// 支付票号
	@Column(name = "billno")
	private Integer billNo;
	// 支付方式
	@Column(name = "paymode")
	private String payMode;
	// 支付金额
	@Column(name = "payamount")
	private double payAmount;
	// 折扣金额
	@Column(name = "discount")
	private double discount;
	// 支付卡号
	@Column(name = "paycard")
	private String payCard;
	// 关联交易表id
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

	private Integer upload;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBillNo() {
		return billNo;
	}

	public void setBillNo(Integer billNo) {
		this.billNo = billNo;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public double getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(double payAmount) {
		this.payAmount = payAmount;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getPayCard() {
		return payCard;
	}

	public void setPayCard(String payCard) {
		this.payCard = payCard;
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

	public Integer getLog_id() {
		return log_id;
	}

	public void setLog_id(Integer log_id) {
		this.log_id = log_id;
	}

	public Integer getUpload() {
		return upload;
	}

	public void setUpload(Integer upload) {
		this.upload = upload;
	}

	@Override
	public String toString() {
		return "PayLog{" +
				"log_id=" + log_id +
				", id=" + id +
				", billNo=" + billNo +
				", payMode='" + payMode + '\'' +
				", payAmount=" + payAmount +
				", discount=" + discount +
				", payCard='" + payCard + '\'' +
				", tradeId=" + tradeId +
				", stationCode='" + stationCode + '\'' +
				", stationName='" + stationName + '\'' +
				", updateTime=" + updateTime +
				'}';
	}
}
