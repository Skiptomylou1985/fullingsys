package org.zframework.web.service.admin.system;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zframework.orm.dao.BaseHibernateDao;
import org.zframework.web.entity.business.Submitter;
import org.zframework.web.service.BaseService;

/**
 * Created by hyf on 2017/9/21.
 */
@Service
public class SubmitterService extends BaseService<Submitter> {

    @Autowired
    private BaseHibernateDao baseDao;
    /**
     * 根据openid获取提交人
     * @param openid
     * @return
     */
    public Submitter getByOpenID(String openid){
        return baseDao.getBy(Submitter.class, Restrictions.eq("openId", openid));
    }

    /**
     * 根据openid获取提交人
     * 延迟加载属性立即加载
     * @param openid
     * @return
     */
//    public Submitter getByOpenID_NoLazy(String openid){
//        Submitter submitter = getByOpenID(openid);
//        if(ObjectUtil.isNotNull(submitter)){
//            Hibernate.initialize(submitter.getWorkOrderList());
//        }
//        return  submitter;
//    }



}
