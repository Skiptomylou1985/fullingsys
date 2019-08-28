package org.zframework.web.service.business;

import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zframework.core.util.ObjectUtil;
import org.zframework.orm.dao.BaseHibernateDao;
import org.zframework.orm.query.PageBean;
import org.zframework.web.entity.business.*;
import org.zframework.web.service.BaseService;

import java.util.List;

@Service
public class WorkTradeService extends BaseService<T> {
	private final Logger LOGGER = Logger.getLogger(this.getClass());

	@Autowired
	private BaseHibernateDao baseDao;

	public int addTradeLog(TradeLog tradeLog){
		baseDao.save(tradeLog);
		return 1;
	}

	public int addOrderLog(OrderLog orderLog){
		baseDao.save(orderLog);
		return 1;
	}

	public int addCarLog(CarLog carLog){
		baseDao.save(carLog);
		return 1;
	}

	public int addPayLog(PayLog payLog){
		baseDao.save(payLog);
		return 1;
	}

	public List<T> getTradeLogList(PageBean pageBean){
		return super.listByPage(pageBean);
	}

	public int updateTradeLogById(TradeLog tradeLog){
		baseDao.update(tradeLog);
		return 1;
	}

	public int addCarRecordLog(CarRecordLog carLog){
		baseDao.save(carLog);
		return 1;
	}

	public int addStationHeartbeatLog(HeartbeatLog heartbeatLog){
		baseDao.save(heartbeatLog);
		return 1;
	}
}
