package org.zframework.web.controller.business;

import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zframework.core.util.ObjectUtil;
import org.zframework.core.web.support.WebResult;
import org.zframework.orm.query.PageBean;
import org.zframework.web.entity.business.CarLog;
import org.zframework.web.entity.business.OrderLog;
import org.zframework.web.entity.business.PayLog;
import org.zframework.web.entity.business.TradeLog;
import org.zframework.web.service.business.WorkTradeService;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/business/oilstation")
public class WorkTradeLogController {

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	@Autowired
	private WorkTradeService worktradeservice ;

	@RequestMapping(value="/add/trade", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addTradeLog(@RequestBody TradeLog tradeLog) throws ParseException {

		tradeLog.setUpdateTime(new Date());
		worktradeservice.addTradeLog(tradeLog);
		LOGGER.info("addTradeLog -->> success -->> " + tradeLog.toString());
		return WebResult.success();
	}

	@RequestMapping(value="/list", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getTradeLogList(@RequestBody PageBean pageBean) throws ParseException {
		Map<String,Object> logMap = new HashMap<String, Object>();

		List<T> list = worktradeservice.getTradeLogList(pageBean);
		logMap.put("rows", list);
		logMap.put("total", pageBean.getTotalCount());

		LOGGER.info("getTradeLogList -->> success");
		return logMap;
	}

	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateTradeLogById(@RequestBody TradeLog tradeLog) throws ParseException {

		if(ObjectUtil.isNotNull(tradeLog.getId())){
			tradeLog.setUpdateTime(new Date());
			worktradeservice.updateTradeLogById(tradeLog);
			LOGGER.info("updateTradeLogById -->> success");
		}
		return WebResult.success();
	}

	@RequestMapping(value="/add/paylog", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addPayLog(@RequestBody PayLog payLog) throws ParseException {

		payLog.setUpdateTime(new Date());
		worktradeservice.addPayLog(payLog);
		LOGGER.info("addPayLog -->> success -->> " + payLog.toString());
		return WebResult.success();
	}

	@RequestMapping(value="/add/carlog", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addCarLog(@RequestBody CarLog carLog) throws ParseException {

		carLog.setUpdateTime(new Date());
		worktradeservice.addCarLog(carLog);
		LOGGER.info("addCarLog -->> success -->> " + carLog.toString());
		return WebResult.success();
	}

	@RequestMapping(value="/add/orderlog", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addOrderLog(@RequestBody OrderLog orderLog) throws ParseException {

		orderLog.setUpdateTime(new Date());
		worktradeservice.addOrderLog(orderLog);
		LOGGER.info("addOrderLog -->> success -->> " + orderLog.toString());
		return WebResult.success();
	}

}
