package org.zframework.schedule;


import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.zframework.core.support.ApplicationCommon;
import org.zframework.web.entity.business.WorkOrder;
import org.zframework.web.entity.system.User;
import org.zframework.web.service.admin.system.UserService;
import org.zframework.web.service.business.WorkOrderService;
import org.zframework.wechat.support.WechatSupport;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by hyf on 2017/11/7.
 */
@Component
public class MsgSchedule {
    @Autowired
    private WorkOrderService orderService;

    @Autowired
    private UserService userService;

    private WechatSupport support = WechatSupport.getInstance();

    private boolean msgScheduleOpen = false;

    // TODO: 2017/11/9 临时注释,在测试系统中不开启定时任务

    public MsgSchedule(){

    }

    @Scheduled(cron="0 0 8  * * ? ")
//    @Scheduled(cron="0/30 * * * * ? ")
    public void dayMsg(){
//        System.out.println("dodoodododododo");
        List<User> users = userService.list();
        for (User user:users){
            List<Criterion> criterions = new ArrayList<Criterion>();
            criterions.add(Restrictions.eq("operatorId",user.getId()));
            criterions.add(Restrictions.in("status",new Integer[]{ApplicationCommon.STATUS_ADDED,ApplicationCommon.STATUS_REJECTED}));
            Integer count = orderService.count(criterions.toArray(new Criterion[]{}));
            if(count > 0){
                support.sendDayMsgToUser(user.getOpenId(),count);
            }
        }
    }
//    @Scheduled(cron="0/10 * * * * ? ")
    public void templateMsg(){
        List<WorkOrder> list = orderService.list(Restrictions.eq("isPush",0));
        if(list.size() > 0){
            for (WorkOrder order :list){
                if (order.getIsPush() == 0){
                    if(doMsgPush(order)){
                        order.setIsPush(1);
                        order.setPushDate(new Date());
                        orderService.update(order);
                    }
                }
            }
        }
    }

    private boolean  doMsgPush(WorkOrder order){
        switch (order.getStatus()){
            case ApplicationCommon.STATUS_ADDED:
               return support.sendAddMsgToUser(order);
            case ApplicationCommon.STATUS_SERVICED:
               return support.sendCompleteMsgToSubmitter(order);
            case ApplicationCommon.STATUS_CANCELED:
                return support.sendCancelMsgToUser(order);
            case ApplicationCommon.STATUS_REJECTED:
                return support.sendRejectMsgToUser(order);
            case ApplicationCommon.STATUS_COMFIRMED:
                return support.sendConfirmMsgToUser(order);
            default:
                return true;
        }
    }
}
