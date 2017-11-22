package org.zframework.wechat.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.tools.ant.taskdefs.ManifestTask;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.zframework.core.support.ApplicationCommon;
import org.zframework.core.util.ObjectUtil;
import org.zframework.web.entity.business.Submitter;
import org.zframework.web.entity.business.WechatUser;
import org.zframework.web.entity.business.WorkOrder;
import org.zframework.web.entity.system.Unit;
import org.zframework.web.entity.system.User;
import org.zframework.web.service.admin.system.LogService;
import org.zframework.web.service.admin.system.SubmitterService;
import org.zframework.web.service.admin.system.UnitService;
import org.zframework.web.service.admin.system.UserService;
import org.zframework.web.service.business.WorkLogService;
import org.zframework.web.service.business.WorkOrderService;
import org.zframework.wechat.support.WechatConsts;
import org.zframework.wechat.support.WechatSupport;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hyf on 2017/9/9.
 */
@Controller
@RequestMapping("/wechat/workorder")
public class WechatWorkOrderController {
    @Autowired
    private LogService logService;

    @Autowired
    private UserService userService;

    @Autowired
    private WorkOrderService orderService;

    @Autowired
    SubmitterService submitterService;

    @Autowired
    UnitService unitService;
    @Autowired
    private  WorkLogService workLogService;

    private WechatSupport support = WechatSupport.getInstance();

    private Logger logger = Logger.getLogger(this.getClass());

    @RequestMapping(value = "/test",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String test(){
//        int a = 1/0;
//        return "";
        WorkOrder order = orderService.getByCode("wd00010001000520171106234916");
        workLogService.recordAction(order, ApplicationCommon.ACTION_ADD,-1,ApplicationCommon.TYPE_SUBMITTER,"success");
        workLogService.recordAction(order, ApplicationCommon.ACTION_SERVICE,0,ApplicationCommon.TYPE_USER,"success");
        workLogService.recordAction(order, ApplicationCommon.ACTION_CONFIRM,1,ApplicationCommon.TYPE_SUBMITTER,"success");
        workLogService.recordAction(order, ApplicationCommon.ACTION_CANCEL,1,ApplicationCommon.TYPE_SUBMITTER,"success");
        workLogService.recordAction(order, ApplicationCommon.ACTION_REJECT,2,ApplicationCommon.TYPE_SUBMITTER,"success");
        return "success";

    }
    @RequestMapping(value = "/add",method = {RequestMethod.GET,RequestMethod.POST})
    public String add(Model model,HttpServletRequest request,String code, String state){
        String openId = "";
        System.out.println("function add --> Code:"+code + "   state:"+state);
        System.out.println("session code:"+ request.getSession().getAttribute("code"));
        System.out.println("session openid:"+ request.getSession().getAttribute("openid"));

        if(code.equals((String)request.getSession().getAttribute("code"))){
            System.out.println("code repeat");
            openId = (String)request.getSession().getAttribute("openid");
        }else {
            openId = support.getOpenIdByAuthCode(code);
        }
        request.getSession().setAttribute("code",code);
        request.getSession().setAttribute("openid",openId);
        if (ObjectUtil.isEmpty(openId)){
            return "wechat/error";
        }
        User user = userService.getByOpenId(openId);
        if(ObjectUtil.isNotNull(user)){
            return "wechat/workorder/nouse";
        }

        WechatUser wechatUser = support.getWechatUserByOpenId(openId);
        Submitter sub = submitterService.getByOpenID(openId);
        if(ObjectUtil.isNotNull(sub)){ //提报人已存在
            if (!sub.getNickName().equals(wechatUser.getNickname())){
                sub.setProvince(wechatUser.getProvince());
                sub.setCity(wechatUser.getCity());
                sub.setGender(wechatUser.getSex());
                sub.setNickName(wechatUser.getNickname());
                submitterService.update(sub);
            }
        }else {
            sub = new Submitter();
            sub.setProvince(wechatUser.getProvince());
            sub.setCity(wechatUser.getCity());
            sub.setGender(wechatUser.getSex());
            sub.setNickName(wechatUser.getNickname());
            sub.setOpenId(openId);
            sub.setCreateTime(new Date());
            submitterService.save(sub);
        }
        List<Unit> unitList = unitService.list(Restrictions.eq("unitType",ApplicationCommon.TYPE_STATION));
        JSONArray units = new JSONArray();
        for (Unit unit:unitList){
            JSONObject unitJson = new JSONObject();
            unitJson.put("petroCode",unit.getPetroCode());
            unitJson.put("stationName",unit.getName());
            unitJson.put("parentId",unit.getParentId());
            unitJson.put("id",unit.getId());
            units.add(unitJson);
        }
        model.addAttribute("units",units.toString());
        model.addAttribute("submitterOpenId",openId);
        return "wechat/workorder/add";

    }
    @RequestMapping(value = "/doAdd",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String doAdd(HttpServletRequest request, @ModelAttribute("form")WorkOrder workOrder, String openId){


        if(orderService.countUndoByPetroCode(workOrder.getPetroCode()) > 0){
            return getFail("orderRepeat");
        }

        workOrder.setSubmitOpenId(openId);
        workOrder.setSubmitDate(new Date());
        Unit unit = unitService.getUnitByPetroCode_NoLazyUser(workOrder.getPetroCode());
        workOrder.setStatus(ApplicationCommon.STATUS_ADDED);
        workOrder.setIsPush(0);
        workOrder.setUnitId(unit.getId());
        workOrder.setOperator(unit.getUsers().get(0).getRealName());
        workOrder.setOperatorId(unit.getUsers().get(0).getId());
        workOrder.setOperatorPhone(unit.getUsers().get(0).getMobile());
        workOrder.setOperatorOpenId(unit.getUsers().get(0).getOpenId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderCode = "wd"+unit.getCode()+ sdf.format(new Date());
        workOrder.setCode(orderCode);
//        if(support.sendAddMsgToUser(workOrder))
//        {
//            workOrder.setIsPush(1);
//            workOrder.setPushDate(new Date());
//        }

        logger.info("WechatWorkOrderController-->doadd-->"+workOrder.toJson());

        orderService.save(workOrder);
        return getSuccess(workOrder.getCode());

    }
    @RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})
    public String list(Model model,String code, String state){
        System.out.println("function list --> code:"+code+ "   state:"+state);
        String openId = "";
       // System.out.println("WechatWorkOrderController-->list---> Code:"+code + "   state:"+state);
        if(code.equals(support.getAuthCode())){
            System.out.println("code repeat");
            openId = support.getTempOpenId();
        }else {
            openId= support.getOpenIdByAuthCode(code);
        }
        support.setAuthCode(code);
        support.setTempOpenId(openId);
        if (ObjectUtil.isNotEmpty(openId)){
            User user = userService.getByOpenId(openId);
            if(ObjectUtil.isNotNull(user)) {

                List<Criterion> criterions = new ArrayList<Criterion>();
                criterions.add(Restrictions.eq("operatorId", user.getId()));
                if ("undolist".equals(state)){
                    criterions.add(Restrictions.in("status",new Integer[]{ApplicationCommon.STATUS_ADDED,ApplicationCommon.STATUS_REJECTED}));
                }
                List<WorkOrder> orderList = orderService.list(criterions.toArray(new Criterion[]{}), Order.desc("id"));
                JSONArray jsonArray = new JSONArray();
                Integer num = 0;
                for(WorkOrder order : orderList){
                    JSONObject object = JSONObject.fromObject(order);
                    jsonArray.add(object);
                    num++;
                    if (num > 300)
                        break;
                }
                model.addAttribute("state",state);
                model.addAttribute("type","user");
                model.addAttribute("count",orderList.size());
                model.addAttribute("name",user.getRealName());
                model.addAttribute("orders",jsonArray.toString());
            }else {
                Submitter submitter = submitterService.getByOpenID(openId);
                if(ObjectUtil.isNotNull(submitter)){
                    List<Criterion> criterions = new ArrayList<Criterion>();
                    criterions.add(Restrictions.eq("submitOpenId", submitter.getOpenId()));
                    if ("undolist".equals(state)){
                        criterions.add(Restrictions.eq("status",1)); //状态1,已维修,待评价
                    }
                    List<WorkOrder> orderList = orderService.list(criterions.toArray(new Criterion[]{}), Order.desc("submitDate"));
                    JSONArray jsonArray = new JSONArray();
                    for(WorkOrder order : orderList){
                        JSONObject object = JSONObject.fromObject(order);
                        jsonArray.add(object);
                    }
                    model.addAttribute("state",state);
                    model.addAttribute("type","submitter");
                    model.addAttribute("count",orderList.size());
                    model.addAttribute("name",submitter.getName());
                    model.addAttribute("orders",jsonArray.toString());
                }
            }
        }else {
            return "wechat/error";
        }
        return "wechat/workorder/orderlist";
    }
    @RequestMapping(value = "/undolist",method = {RequestMethod.GET,RequestMethod.POST})
    public String undoList(Model model,String openId){
        System.out.println("function undoList --> openId:"+openId);
        User user = userService.getByOpenId(openId);
        List<Criterion> criterions = new ArrayList<Criterion>();
        criterions.add(Restrictions.eq("operatorOpenId", openId));
        criterions.add(Restrictions.in("status",new Integer[]{ApplicationCommon.STATUS_ADDED,ApplicationCommon.STATUS_REJECTED}));
        List<WorkOrder> orderList = orderService.list(criterions.toArray(new Criterion[]{}), Order.desc("id"));
        JSONArray jsonArray = new JSONArray();
        Integer num = 0;
        for(WorkOrder order : orderList){
            JSONObject object = JSONObject.fromObject(order);
            jsonArray.add(object);
            num++;
            if (num > 300)
                break;
        }
        System.out.println("orderlist size : "+orderList.size());
        model.addAttribute("state","undolist");
        model.addAttribute("type","user");
        model.addAttribute("count",orderList.size());
        model.addAttribute("name",user.getRealName());
        model.addAttribute("orders",jsonArray.toString());
        return "wechat/workorder/orderlist";
    }


    @RequestMapping(value = "/detail",method = {RequestMethod.GET,RequestMethod.POST})
    public String detail(HttpServletRequest request,Model model,String orderCode,String userType){
        WorkOrder order = orderService.getByCode(orderCode);
        model.addAttribute("order",order);
        model.addAttribute("userType",userType);
        return  "wechat/workorder/detail";
    }
    @RequestMapping(value = "/complete",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String complete( HttpServletRequest request,String orderCode,String log){
        System.out.println("function complete --> orderCode:"+orderCode+"   log: "+log);
        if(ObjectUtil.isNotNull(orderCode) && ObjectUtil.isNotEmpty(orderCode) ){
            WorkOrder order = orderService.getByCode(orderCode);
            if(ObjectUtil.isNotNull(order)){
                order.setLog(log);
                order.setStatus(ApplicationCommon.STATUS_SERVICED);
                order.setServiceDate(new Date());
                order.setIsPush(0);
                orderService.update(order);
                return getSuccess("");
            }
            return getFail("未查询到工单");
        }
        return  getFail("工单编码无效!");
    }

    @RequestMapping(value = "/cancel",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String cancel(String orderCode){
        System.out.println("function cancel --> orderCode:"+orderCode);
        if(ObjectUtil.isNotNull(orderCode) && ObjectUtil.isNotEmpty(orderCode) ){
            WorkOrder order = orderService.getByCode(orderCode);
            if(ObjectUtil.isNotNull(order)){
                order.setStatus(ApplicationCommon.STATUS_CANCELED);
                order.setIsPush(0);
                orderService.update(order);
                return getSuccess("");
            }
            return getFail("未查询到工单");
        }
        return  getFail("工单编码无效!");
    }
    @RequestMapping(value = "/confirm",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String confirm(String orderCode,Integer score){
        System.out.println("function confirm --> orderCode:"+orderCode+"   score: "+score);
        if(ObjectUtil.isNotNull(orderCode) && ObjectUtil.isNotEmpty(orderCode) ){
            WorkOrder order = orderService.getByCode(orderCode);
            if(ObjectUtil.isNotNull(order)){
                order.setStatus(ApplicationCommon.STATUS_COMFIRMED);
                order.setScore(score);
                order.setConfirmDate(new Date());
                order.setIsPush(0);
                orderService.update(order);
                return getSuccess("");
            }
            return getFail("未查询到工单");
        }
        return  getFail("工单编码无效!");
    }
    @RequestMapping(value = "/push",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String push(String orderCode){
        System.out.println("function push --> orderCode:"+orderCode);
        if(ObjectUtil.isNotNull(orderCode) && ObjectUtil.isNotEmpty(orderCode) ){
            WorkOrder order = orderService.getByCode(orderCode);
            if(ObjectUtil.isNotNull(order)){
                if(ObjectUtil.isNotNull(order.getPushDate())){
                    Long timeDif = (new Date()).getTime() - order.getPushDate().getTime();
                    if(timeDif/(1000*60*60) < 24){
                        return getFail("24小时内已发送催单通知,请勿重复催单");
                    }
                }
                User user = userService.getById(order.getOperatorId());
                if(support.sendPushMsgToUser(order)){
                    order.setIsPush(1);
                    order.setPushDate(new Date());
                }else {
                    return getFail("催单发送失败,请稍后再试");
                }
                orderService.update(order);
                return getSuccess("");
            }
            return getFail("未查询到工单");
        }
        return  getFail("工单编码无效!");
    }
    @RequestMapping(value = "/reject",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String reject(String orderCode){
        System.out.println("function reject --> orderCode:"+orderCode);
        if(ObjectUtil.isNotNull(orderCode) && ObjectUtil.isNotEmpty(orderCode) ){
            WorkOrder order = orderService.getByCode(orderCode);
            if(ObjectUtil.isNotNull(order)){
                order.setStatus(ApplicationCommon.STATUS_REJECTED);
                order.setIsPush(0);
                orderService.update(order);
                return getSuccess("");
            }
            return getFail("未查询到工单");
        }
        return  getFail("工单编码无效!");
    }

    private String getFail(String content){
        JSONObject json = new JSONObject();
        json.put("result",false);
        json.put("content",content);
        return json.toString();
    }

    private String getSuccess(String content){
        JSONObject json = new JSONObject();
        json.put("result",true);
        json.put("content",content);
        return json.toString();
    }



}
