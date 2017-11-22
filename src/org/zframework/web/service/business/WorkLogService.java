package org.zframework.web.service.business;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zframework.core.support.ApplicationCommon;
import org.zframework.orm.dao.BaseHibernateDao;
import org.zframework.web.entity.business.WorkLog;
import org.zframework.web.entity.business.WorkOrder;
import org.zframework.web.service.BaseService;

import java.util.Date;
import java.util.List;

/**
 * Created by hyf on 2017/9/21.
 */
@Service
public class WorkLogService extends BaseService<WorkLog> {
//    @Autowired
//    private BaseHibernateDao baseDao;
    private final Logger logger = Logger.getLogger(this.getClass());

    public void  recordAction(WorkOrder order,String action, Integer beforeStatus,String userType,String result,String content){
        WorkLog log = new WorkLog();
        log.setOrderCode(order.getCode());
        log.setAction(action);
        log.setCreateTime(new Date());
        log.setBeforeStatus(beforeStatus);
        log.setAfterStatus(order.getStatus());
        log.setUserType(userType);
        log.setContent(content);
        if(ApplicationCommon.TYPE_USER.equals(userType)){
            log.setName(order.getOperator());
            log.setOpenId(order.getOperatorOpenId());
        }else {
            log.setName(order.getSubmitter());
            log.setOpenId(order.getSubmitOpenId());
        }
        baseDao.save(log);
    }

    public void recordAction(WorkOrder order,String action, Integer beforeStatus,String userType,String result){
        this.recordAction(order,action,beforeStatus,userType,result,"");
    }


    public List<WorkLog> listByOrderCode(String orderCode){
        return super.list(Restrictions.eq("orderCode",orderCode), Order.asc("createTime"));
    }


}
