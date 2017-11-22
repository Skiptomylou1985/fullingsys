package org.zframework.web.service.business;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zframework.core.support.ApplicationCommon;
import org.zframework.orm.dao.BaseHibernateDao;
import org.zframework.web.entity.business.WorkOrder;
import org.zframework.web.service.BaseService;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by hyf on 2017/9/6.
 */
@Service
public class WorkOrderService extends BaseService<WorkOrder>{
    @Autowired
    private BaseHibernateDao baseDao;
    private final Logger logger = Logger.getLogger(this.getClass());

    public WorkOrder getByID(Integer id){
        return  baseDao.getBy(WorkOrder.class,Restrictions.eq("id",id));
    }
    public WorkOrder getByCode(String code){
        return  baseDao.getBy(WorkOrder.class,Restrictions.eq("code",code));
    }
    public Integer countUndoByPetroCode(String petroCode){
        List<Criterion> criterions = new ArrayList<Criterion>();
        criterions.add(Restrictions.eq("petroCode",petroCode));
        criterions.add(Restrictions.in("status",new Integer[]{ApplicationCommon.STATUS_ADDED,ApplicationCommon.STATUS_REJECTED}));
        return count(criterions.toArray(new Criterion[]{}));
    }


}
