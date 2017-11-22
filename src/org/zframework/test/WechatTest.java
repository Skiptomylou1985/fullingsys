package org.zframework.test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.zframework.core.support.ApplicationCommon;
import org.zframework.orm.dao.BaseHibernateDao;
import org.zframework.web.entity.business.WechatUser;
import org.zframework.web.entity.business.WorkLog;
import org.zframework.web.entity.business.WorkOrder;
import org.zframework.web.service.business.WorkLogService;
import org.zframework.web.service.business.WorkOrderService;
import org.zframework.wechat.bean.message.XmlOutTextMessage;
import org.zframework.wechat.support.WechatConfig;
import org.zframework.wechat.support.WechatConsts;
import org.zframework.wechat.support.WechatSupport;

import java.io.*;
import java.net.URLEncoder;


/**
 * Created by hyf on 2017/9/9.
 */
public class WechatTest {

    private  WechatSupport support = WechatSupport.getInstance();

    @Test
    public void test(){
        WechatConfig config = WechatConfig.getInstance();
        config.savePreperty("wx.mchId","mchid");
    }

    @Test
    public void  setMenu(){
        String baseUrl = WechatConsts.URL_OAUTH2_GET_CODE.replace("APPID",support.getAppId()).replace("SCOPE",WechatConsts.OAUTH2_SCOPE_BASE);
        String url = null;
        try {
            url = baseUrl.replace("STATE","list").replace("REDIRECT_URI", URLEncoder.encode("http://www.fullingsys.cn/sys/wechat/workorder/list","utf8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        JSONObject json1 = new JSONObject();
        json1.put("type","view");
        json1.put("name","全部工单");
        json1.put("url",url);

        try {
            url = baseUrl.replace("STATE","undolist").replace("REDIRECT_URI",URLEncoder.encode("http://www.fullingsys.cn/sys/wechat/workorder/list","utf8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        JSONObject json2 = new JSONObject();
        json2.put("type","view");
        json2.put("name","待处理工单");
        json2.put("url",url);


        try {
            url = baseUrl.replace("STATE","add").replace("REDIRECT_URI",URLEncoder.encode("http://www.fullingsys.cn/sys/wechat/workorder/add","utf8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        JSONObject json3 = new JSONObject();
        json3.put("type","view");
        json3.put("name","报修");
        json3.put("url",url);

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(json1);
        jsonArray.add(json2);
        jsonArray.add(json3);

        JSONObject jsonMain = new JSONObject();
        jsonMain.element("button",jsonArray);

        //WechatSupport support = WechatSupport.getInstance();

        String urlMenu = WechatConsts.URL_CREATE_MENU.replace("ACCESS_TOKEN",support.getAccessToken());

        System.out.println(urlMenu);
        System.out.println("menu info :"+jsonMain.toString());
        System.out.println("setmenu:"+support.doHttpsPost(urlMenu,"POST","text/json",jsonMain.toString()));
    }

    @Test
    public void  getUserList(){
        String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=";
        //WechatSupport support = WechatSupport.getInstance();
        String resp = support.doHttpsPost(url.replace("ACCESS_TOKEN",support.getAccessToken()),"POST","text/json","");
        System.out.println(resp);
    }


    @Test
    public void getQR() throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("expire_seconds",604800);
        jsonObject.put("action_name","QR_STR_SCENE");
        jsonObject.element("action_info",new JSONObject().element("scene",new JSONObject().element("scene_str","user")));

        WechatSupport support = WechatSupport.getInstance();
        String url = WechatConsts.URL_GET_QR_CODE.replace("ACCESS_TOKEN",support.getAccessToken());
        String resp = support.doHttpsPost(url,"POST","json",jsonObject.toString());

        JSONObject respJson = JSONObject.fromObject(resp);
        System.out.println(respJson);

    }


    @Test
    public void  getMsgTemplateList(){
        String url = WechatConsts.URL_TEMPLATE_GET_LIST.replace("ACCESS_TOKEN",support.getAccessToken());
        String resp = support.doHttpsGet(url);
        System.out.println(resp);
    }








}
