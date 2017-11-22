package org.zframework.web.controller.admin.system;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zframework.core.util.ObjectUtil;
import org.zframework.wechat.support.WechatConsts;
import org.zframework.wechat.support.WechatSupport;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hyf on 2017/10/30.
 */
@Controller
@RequestMapping(value="/admin/wechatsetting")
public class WechatSettingController {
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST})
    public String index(){
        return "admin/system/wechatsetting/index";
    }
    @RequestMapping(value = "getQRTicket",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> getQRTicket(HttpServletRequest request){

        System.out.println("getQRTicket");
        Map<String,Object> ticketMap = new HashMap<String, Object>();
        Integer time;
        if(ObjectUtil.isNotNull(request.getParameter("time"))&& ObjectUtil.isNotEmpty(request.getParameter("time"))){

            try {
                time = 60*Integer.parseInt(request.getParameter("time"));

            }catch (Exception e){
                e.printStackTrace();
                time = 3600;
            }

        }else {
            time = 3600;
        }
        JSONObject jsonObject = new JSONObject();
        if("userTempQR".equals(request.getParameter("type"))){
            jsonObject.put("expire_seconds",time);
            jsonObject.put("action_name", WechatConsts.QR_STR_SCENE);
            jsonObject.element("action_info",new JSONObject().element("scene",new JSONObject().element("scene_str","user")));

        }else if("userFixQR".equals(request.getParameter("type"))){
            jsonObject.put("action_name",WechatConsts.QR_LIMIT_STR_SCENE);
            jsonObject.element("action_info",new JSONObject().element("scene",new JSONObject().element("scene_str","user")));

        }else if("subTempQR".equals(request.getParameter("type"))){
            jsonObject.put("expire_seconds",time);
            jsonObject.put("action_name",WechatConsts.QR_STR_SCENE);
            jsonObject.element("action_info",new JSONObject().element("scene",new JSONObject().element("scene_str","submitter")));
        }else {
            jsonObject.put("action_name",WechatConsts.QR_LIMIT_STR_SCENE);
            jsonObject.element("action_info",new JSONObject().element("scene",new JSONObject().element("scene_str","submitter")));
        }
        WechatSupport support = WechatSupport.getInstance();
        String resp = support.doHttpsPost(WechatConsts.URL_GET_QR_CODE.replace("ACCESS_TOKEN",support.getAccessToken()),"POST","json",jsonObject.toString());
        System.out.println("get ticket response ----->"+resp);
        JSONObject respJson = JSONObject.fromObject(resp);
        if (ObjectUtil.isNotNull(respJson.get("ticket"))){
            ticketMap.put("result","success");
            ticketMap.put("ticket",respJson.get("ticket"));
        } else{
            ticketMap.put("result","fail");
        }
        return  ticketMap;
    }
}
