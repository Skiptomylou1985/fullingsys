package org.zframework.web.controller.business;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zframework.core.support.ApplicationCommon;
import org.zframework.core.util.ObjectUtil;
import org.zframework.core.util.StringUtil;
import org.zframework.orm.query.PageBean;
import org.zframework.web.controller.BaseController;
import org.zframework.web.entity.business.WorkOrder;
import org.zframework.web.entity.system.Role;
import org.zframework.web.entity.system.Unit;
import org.zframework.web.entity.system.User;
import org.zframework.web.entity.system.type.RoleType;
import org.zframework.web.service.admin.system.UserService;
import org.zframework.web.service.business.WorkOrderService;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hyf on 2017/9/6.
 */
@Controller
@RequestMapping(value = "/business/workorder")
public class WorkOrderController extends BaseController<WorkOrder>{

    @Autowired
    private WorkOrderService workOrderService ;
    @Autowired
    private UserService userService ;

    @RequestMapping(method = RequestMethod.GET)
    public  String index(Model model){
        return  "business/workorder/index";
    }
    @RequestMapping(value="/list",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> list(PageBean pageBean, HttpServletRequest request) throws ParseException {

        getCriterionsFromRequest(pageBean,request);
         User user = (User) request.getSession().getAttribute(ApplicationCommon.SESSION_ADMIN);
        List<Role> roles = user.getRoles();
        List<Unit> units = user.getUnits();
        RoleType roleType = RoleType.GENERAL;
        for (Role role : roles){
            if(role.getType() == RoleType.SYSTEM){
                roleType = roleType.SYSTEM;
                break;
            }
        }
        if(roleType == RoleType.SYSTEM){
            for(Unit unit : units){
                if(!unit.getUnitType().equals(ApplicationCommon.TYPE_STATION)){
                    pageBean.addCriterion(Restrictions.like("code","%"+unit.getCode()+"%"));
                }
            }
        }else {
            pageBean.addCriterion(Restrictions.eq("operatorId",user.getId()));
        }

        Map<String,Object> logMap = new HashMap<String, Object>();

        List<WorkOrder> list=workOrderService.listByPage(pageBean);
        logMap.put("rows", list);
        logMap.put("total", pageBean.getTotalCount());
        return logMap;
    }

    @RequestMapping(value = "/detail",method = {RequestMethod.GET,RequestMethod.POST})
    public String detail(Model model,Integer id){
        if(ObjectUtil.isNotNull(id)){
            WorkOrder order = workOrderService.getByID(id);
            model.addAttribute("order",order);
        }
       return "business/workorder/detail";
    }

    private void  getCriterionsFromRequest(PageBean pageBean,HttpServletRequest request) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //toolbar中快速检索框
        if(!StringUtil.isEmpty(request.getParameter("name"))&& !StringUtil.isEmpty(request.getParameter("value"))){
            pageBean.addCriterion(Restrictions.like(request.getParameter("name"), "%"+request.getParameter("value")+"%"));
        }
        //提交时间
        if (!StringUtil.isEmpty(request.getParameter("subDateBegin"))) {
            pageBean.addCriterion(Restrictions.gt("submitDate",sdf.parse(request.getParameter("subDateBegin"))));
        }
        if (!StringUtil.isEmpty(request.getParameter("subDateEnd"))) {
            pageBean.addCriterion(Restrictions.lt("submitDate",sdf.parse(request.getParameter("subDateEnd"))));
        }
        //维修时间
        if (!StringUtil.isEmpty(request.getParameter("serviceDateBegin"))) {
            pageBean.addCriterion(Restrictions.gt("serviceDate",sdf.parse(request.getParameter("serviceDateBegin"))));
        }
        if (!StringUtil.isEmpty(request.getParameter("serviceDateEnd"))) {
            pageBean.addCriterion(Restrictions.lt("serviceDate",sdf.parse(request.getParameter("serviceDateEnd"))));
        }
        //验收时间
        if (!StringUtil.isEmpty(request.getParameter("confirmDateBegin"))) {
            pageBean.addCriterion(Restrictions.gt("confirmDate",sdf.parse(request.getParameter("confirmDateBegin"))));
        }
        if (!StringUtil.isEmpty(request.getParameter("confirmDateEnd"))) {
            pageBean.addCriterion(Restrictions.lt("confirmDate",sdf.parse(request.getParameter("confirmDateEnd"))));
        }

        //站点
        if (!StringUtil.isEmpty(request.getParameter("station"))) {
            pageBean.addCriterion(Restrictions.like("stationName","%"+request.getParameter("station")+"%"));
        }

        if (!StringUtil.isEmpty(request.getParameter("status"))) {
            pageBean.addCriterion(Restrictions.eq("status",Integer.parseInt(request.getParameter("status"))));
        }

        //提交人
        if (!StringUtil.isEmpty(request.getParameter("submitter"))) {
            pageBean.addCriterion(Restrictions.like("submitter","%"+request.getParameter("submitter")+"%"));
        }

        //维修人
        if (!StringUtil.isEmpty(request.getParameter("operator"))) {
            pageBean.addCriterion(Restrictions.like("operator","%"+request.getParameter("operator")+"%"));
        }





    }

}
