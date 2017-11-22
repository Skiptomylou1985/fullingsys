package org.zframework.wechat.service;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.zframework.wechat.support.WechatConsts;
import org.zframework.wechat.support.WechatSupport;

/**
 * Created by hyf on 2017/9/24.
 */
@Service
public class MessageService {
    private WechatSupport support = WechatSupport.getInstance();

    public String sendJsonMsg(String toUserName,String content){
        String url = WechatConsts.URL_OPENID_SEND_ALL.replace("ACCECC_TOKEN", support.getAccessToken());
       // String content = "";
        JSONObject json = new JSONObject();
        json.put("touser",toUserName);
        json.put("msgtype","text");
        JSONObject jsonContent = new JSONObject();
        jsonContent.put("content",content);
        json.element("text",jsonContent);
        return support.doHttpsPost(url,"POST","text/json",json.toString());
    }
    public String sentModelMsg(String modelType){
        return "";
    }

}
