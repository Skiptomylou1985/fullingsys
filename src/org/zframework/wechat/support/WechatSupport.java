package org.zframework.wechat.support;

import net.sf.json.JSONObject;
import org.zframework.core.util.ObjectUtil;
import org.zframework.core.util.Sha1Util;
import org.zframework.web.entity.business.WechatUser;
import org.zframework.web.entity.business.WorkOrder;
import org.zframework.wechat.exception.WechatErrorException;
import org.zframework.wechat.util.StringUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hyf on 2017/9/24.
 */
public class WechatSupport {
    private static final String configFile = "/wechat.properties";
    private  String configPath = "";
    private  String accessToken = "" ;
    private  int tokenExpierDelay = 7200;
    private  Calendar tokenTimeStamp = Calendar.getInstance();
    private  String appId = "";
    private  String appSecret = "";
    private  String token = "";
    private  String aesKey = "";
    private  String mchId = "";
    private  String apiKey = "";
    private  volatile String authCode = "";
    private  volatile String tempOpenId = "";

    private String addMsgId = "AImZNhZu36pbx8YKzM6M7n9Z_PARSREFfzOePXawB2Y";
    private String assignMsgId = "RA7D0RT396kEsvKwshuangDEQHaDg79UM-5mC0sLapA";
    private String completeMsgId = "In8gLCycDbZpx1YGjp5mprFphJfbDBjUcos-RjuP-Ek";
    private String confirmMsgId = "xvPaaa9nStQA9fIIesf5anuLn1rC6Qgf1knczpJB6yU";
    private String pushMsgId = "9Ubl5WGDlXCZTjDEwK4M7COl2ujdqsi35SXBFZyKoQ0";
    private String cancelMsgId = "2EWgURTBVI5X8l42HTZgoVBQ6f1H31srP_X2ukNWlmo";
    private String dayMsgId = "WkrR9e-tMxhhQET25smnsCEeyh0Xw-ePGVOErPt6ONc";

    private static WechatSupport ourInstance = new WechatSupport();

    public static WechatSupport getInstance() {
        return ourInstance;
    }

    private WechatSupport() {

        tokenTimeStamp.setTime(new Date());
        Properties p = new Properties();
        configPath = this.getClass().getResource("/").getPath()+"org/zframework/conf/wechat.properties";
        InputStream inStream = null;
        try {
            inStream = new FileInputStream(configPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(inStream == null){
            try {
                throw new WechatErrorException("根目录找不到文件");
            } catch (WechatErrorException e) {
                e.printStackTrace();
            }
        }
        try {
            p.load(inStream);
            this.appId = p.getProperty("wx.appId");
            if(StringUtils.isNotBlank(this.appId)) this.appId = this.appId.trim();
            this.appSecret = p.getProperty("wx.appSecret");
            if(StringUtils.isNotBlank(this.appSecret)) this.appSecret = this.appSecret.trim();
            this.token = p.getProperty("wx.token");
            if(StringUtils.isNotBlank(this.token)) this.token = this.token.trim();
            this.aesKey = p.getProperty("wx.aesKey");
            if(StringUtils.isNotBlank(this.aesKey)) this.aesKey = this.aesKey.trim();
            this.mchId = p.getProperty("wx.mchId");
            if(StringUtils.isNotBlank(this.mchId)) this.mchId = this.mchId.trim();
            this.apiKey = p.getProperty("wx.apiKey");
            if(StringUtils.isNotBlank(this.apiKey)) this.apiKey = this.apiKey.trim();
            inStream.close();
        } catch (IOException e) {
            try {
                throw new WechatErrorException("load wechat.properties error,class根目录下找不到wx.properties文件");
            } catch (WechatErrorException e1) {
                e1.printStackTrace();
            }
        }
        System.out.println("load wechat.properties success");

    }

    public String getAppId() {
        return appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getTempOpenId() {
        return tempOpenId;
    }

    public void setTempOpenId(String tempOpenId) {
        this.tempOpenId = tempOpenId;
    }

    public String getAccessToken() {
        Calendar calNow = Calendar.getInstance();
        calNow.setTime(new Date());
        calNow.add(Calendar.SECOND,0- tokenExpierDelay);

        //判断TOKEN是否过期,过期重新申请,没过期则返回已有TOKEN
        if(calNow.after(tokenTimeStamp) || accessToken == ""){
            String urlString = WechatConsts.URL_GET_ACCESSTOEKN.replace("APPID",appId).replace("APPSECRET",appSecret);

            String resp = doHttpsGet(urlString);
            System.out.println(resp);
            if(!"fail".equals(resp)){
                JSONObject json = JSONObject.fromObject(resp);
                accessToken = json.getString("access_token");
                tokenExpierDelay = json.getInt("expires_in");
                tokenTimeStamp.setTime(new Date());
            }
        }
        return accessToken;
    }
    public String doHttpsPost(String urlString,String methodType,String contentType,String content){
        HttpsURLConnection hul = null;
        OutputStream os =  null;
        String respStr ="";
        String line = "";
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            hul = (HttpsURLConnection) url.openConnection();
            hul.setRequestMethod(methodType);
            hul.setRequestProperty("Content-Type", contentType);
            hul.setDoOutput(true);
            os = hul.getOutputStream();
            os.write(content.getBytes("UTF-8"));
            os.flush();
            os.close();

            reader = new BufferedReader(new InputStreamReader(hul.getInputStream(),"UTF-8"));
            while((line = reader.readLine())!=null){//遍历信息内容
                respStr += line;
            }
            reader.close();
            hul.disconnect();
            return  respStr;
        } catch (Exception e) {
            e.printStackTrace();
            try{
                if(os != null)
                    os.close();
                if(hul != null)
                    hul.disconnect();
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            return "fail";
        }
    }

    public String doHttpsGet(String urlString){
        HttpsURLConnection hul = null;
        String respStr ="";
        String line = "";
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            hul = (HttpsURLConnection) url.openConnection();
            hul.setRequestMethod("GET");
            reader = new BufferedReader(new InputStreamReader(hul.getInputStream(),"UTF-8"));
            while((line = reader.readLine())!=null){//遍历信息内容
                respStr += line;
            }
            reader.close();
            hul.disconnect();
            return  respStr;
        } catch (Exception e) {
            e.printStackTrace();
            try{
                if(hul != null)
                    hul.disconnect();
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            return "fail";
        }
    }
    /**
     * 验证access
     */
    public boolean checkSignature(String signature,String timestamp,String nonce) {

        List<String> ss = new ArrayList<String>();
        try {
            ss.add(timestamp);
            ss.add(nonce);
            ss.add(this.token);

            Collections.sort(ss);
            StringBuilder builder = new StringBuilder();
            for(String s : ss) {
                builder.append(s);
            }
            return signature.equalsIgnoreCase(Sha1Util.sha1(builder.toString()));
        }
        catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }

    public WechatUser getWechatUserByOpenId(String openId){
        WechatUser wechatUser = new WechatUser();
        String url = WechatConsts.URL_GET_USER_INFO.replace("ACCESS_TOKEN",this.getAccessToken())
                .replace("OPENID",openId);
        String weInfo = this.doHttpsGet(url);
        System.out.println("WechatSupport--> getWechatUserByOpenId--> get wechat user info ----->"+weInfo);
        try {
            wechatUser = WechatUser.fromJson(weInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  wechatUser;
    }
    public String getOpenIdByAuthCode(String authCode){
        if(ObjectUtil.isNotNull(authCode) && ObjectUtil.isNotEmpty(authCode)){
            String resp = this.doHttpsGet(WechatConsts.URL_OAUTH2_GET_ACCESSTOKEN
                    .replace("APPID",this.appId)
                    .replace("SECRET",this.appSecret)
                    .replace("CODE",authCode));
            System.out.println("WechatSupport--> getOpenIdByAuthCode-->URL_OAUTH2_GET_ACCESSTOKEN response ----->" + resp);
            JSONObject jsonObject = JSONObject.fromObject(resp);
            if(ObjectUtil.isNotNull(jsonObject.get("openid"))){
                return jsonObject.get("openid").toString();
            }
        }
        return "";
    }
    public String convertFaultType(Integer type){
        String faultType = "其他故障";
        switch (type){
            case 1:
                faultType = "设备故障";
                break;
            case 2:
                faultType = "网络故障";
                break;
            case 3:
                faultType = "软件故障";
                break;
            default:
                break;
        }
        return faultType;
    }

    public boolean sendAddMsgToUser(WorkOrder order){
        String url = WechatConsts.URL_TEMPLATE_SEND.replace("ACCESS_TOKEN",this.getAccessToken());
        JSONObject jsonObject  = new JSONObject();
        jsonObject.put("touser",order.getOperatorOpenId());
        jsonObject.put("template_id",addMsgId);
        jsonObject.put("url","www.fullingsys.cn/sys/wechat/workorder/detail?orderCode="+order.getCode()+"&userType=user");

        JSONObject json1 = new JSONObject();
        json1.element("first",new JSONObject().element("value","您有新的报修信息需要处理"));
        json1.element("keyword1",new JSONObject().element("value",order.getStationName()));
        json1.element("keyword2",new JSONObject().element("value",order.getSubmitter()));
        json1.element("keyword3",new JSONObject().element("value",order.getCellPhone()));
        json1.element("keyword4",new JSONObject().element("value",convertFaultType(order.getFaultType())));
        json1.element("keyword5",new JSONObject().element("value",order.getContent()));
        json1.element("remark",new JSONObject().element("value",""));
        jsonObject.element("data",json1);
        System.out.println("sendAddMsgToUser ---->"+jsonObject.toString());
        String resp = this.doHttpsPost(url,"POST","json",jsonObject.toString());
        System.out.println(resp);
        JSONObject object = JSONObject.fromObject(resp);
        if("ok".equals(object.get("errmsg"))){
            return true;
        }
        return false;
    }
    public boolean sendAssignMsgToSubmitter(WorkOrder order){
        String url = WechatConsts.URL_TEMPLATE_SEND.replace("ACCESS_TOKEN",this.getAccessToken());
        JSONObject jsonObject  = new JSONObject();
        jsonObject.put("touser",order.getSubmitOpenId());
        jsonObject.put("template_id",assignMsgId);
        jsonObject.put("url","www.fullingsys.cn/sys/wechat/workorder/detail?orderCode="+order.getCode()+"&userType=submitter");

        JSONObject json1 = new JSONObject();
        json1.element("first",new JSONObject().element("value","你的报修单已经派工"));
        json1.element("keyword1",new JSONObject().element("value",order.getCode()));
        json1.element("keyword2",new JSONObject().element("value",order.getOperator()));
        json1.element("keyword3",new JSONObject().element("value",order.getOperatorPhone()));
        json1.element("keyword4",new JSONObject().element("value",new Date()));
        json1.element("remark",new JSONObject().element("value",""));
        jsonObject.element("data",json1);
        System.out.println("sendAssignMsgToSubmitter ---->"+jsonObject.toString());
        String resp = this.doHttpsPost(url,"POST","json",jsonObject.toString());
        System.out.println(resp);
        JSONObject object = JSONObject.fromObject(resp);
        if("ok".equals(object.get("errmsg"))){
            return true;
        }
        return false;
    }
    public boolean sendCompleteMsgToSubmitter(WorkOrder order){
        String url = WechatConsts.URL_TEMPLATE_SEND.replace("ACCESS_TOKEN",this.getAccessToken());
        JSONObject jsonObject  = new JSONObject();
        jsonObject.put("touser",order.getSubmitOpenId());
        jsonObject.put("template_id",completeMsgId);
        jsonObject.put("url","www.fullingsys.cn/sys/wechat/workorder/detail?orderCode="+order.getCode()+"&userType=submitter");

        JSONObject json1 = new JSONObject();
        json1.element("first",new JSONObject().element("value","您好，您报修的故障已经处理完毕"));
        json1.element("keyword1",new JSONObject().element("value",order.getContent()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        json1.element("keyword2",new JSONObject().element("value",sdf.format(order.getServiceDate())));
        json1.element("remark",new JSONObject().element("value","请您进行验收确认,并对维修人员的服务进行评价"));
        jsonObject.element("data",json1);
        System.out.println("sendCompleteMsgToSubmitter ---->"+jsonObject.toString());
        String resp = this.doHttpsPost(url,"POST","json",jsonObject.toString());
        System.out.println(resp);
        JSONObject object = JSONObject.fromObject(resp);
        if("ok".equals(object.get("errmsg"))){
            return true;
        }
        return false;
    }

    public boolean sendRejectMsgToUser(WorkOrder order){
        String url = WechatConsts.URL_TEMPLATE_SEND.replace("ACCESS_TOKEN",this.getAccessToken());
        JSONObject jsonObject  = new JSONObject();
        jsonObject.put("touser",order.getOperatorOpenId());
        jsonObject.put("template_id",addMsgId);
        jsonObject.put("url","www.fullingsys.cn/sys/wechat/workorder/detail?orderCode="+order.getCode()+"&userType=user");

        JSONObject json1 = new JSONObject();
        json1.element("first",new JSONObject().element("value","您提交的维修完成工单被退回"));
        json1.element("keyword1",new JSONObject().element("value",order.getStationName()));
        json1.element("keyword2",new JSONObject().element("value",order.getSubmitter()));
        json1.element("keyword3",new JSONObject().element("value",order.getCellPhone()));
        json1.element("keyword4",new JSONObject().element("value",convertFaultType(order.getFaultType())));
        json1.element("keyword5",new JSONObject().element("value",order.getContent()));
        json1.element("remark",new JSONObject().element("value","用户已将您的维修完成工单驳回,请联系用户解决相关问题"));
        jsonObject.element("data",json1);
        System.out.println("sendRejectMsgToUser ---->"+jsonObject.toString());
        String resp = this.doHttpsPost(url,"POST","json",jsonObject.toString());
        JSONObject object = JSONObject.fromObject(resp);
        if("ok".equals(object.get("errmsg"))){
            return true;
        }
        return false;
    }
    public boolean sendCancelMsgToUser(WorkOrder order){
        String url = WechatConsts.URL_TEMPLATE_SEND.replace("ACCESS_TOKEN",this.getAccessToken());
        JSONObject jsonObject  = new JSONObject();
        jsonObject.put("touser",order.getOperatorOpenId());
        jsonObject.put("template_id",cancelMsgId);
        jsonObject.put("url","www.fullingsys.cn/sys/wechat/workorder/detail?orderCode="+order.getCode()+"&userType=user");

        JSONObject json1 = new JSONObject();
        json1.element("first",new JSONObject().element("value","用户撤销维修申请通知"));
        json1.element("keyword1",new JSONObject().element("value",order.getCode()));
        json1.element("keyword2",new JSONObject().element("value",order.getContent()));
        json1.element("keyword3",new JSONObject().element("value","已解决"));
        json1.element("keyword4",new JSONObject().element("value",order.getSubmitter()));
        json1.element("keyword5",new JSONObject().element("value",order.getCellPhone()));
        json1.element("remark",new JSONObject().element("value","用户已撤销"+order.getStationName()+"的报修申请"));
        jsonObject.element("data",json1);
        System.out.println("sendCancelMsgToUser ---->"+jsonObject.toString());
        String resp = this.doHttpsPost(url,"POST","json",jsonObject.toString());
        System.out.println(resp);
        JSONObject object = JSONObject.fromObject(resp);
        if("ok".equals(object.get("errmsg"))){
            return true;
        }
        return false;
    }
    public boolean sendConfirmMsgToUser(WorkOrder order){
        String url = WechatConsts.URL_TEMPLATE_SEND.replace("ACCESS_TOKEN",this.getAccessToken());
        JSONObject jsonObject  = new JSONObject();
        jsonObject.put("touser",order.getOperatorOpenId());
        jsonObject.put("template_id",confirmMsgId);
        jsonObject.put("url","www.fullingsys.cn/sys/wechat/workorder/detail?orderCode="+order.getCode()+"&userType=user");

        JSONObject json1 = new JSONObject();
        json1.element("first",new JSONObject().element("value","您好，"+order.getStationName()+"维修服务客户已验收评价！"));
        json1.element("keyword1",new JSONObject().element("value",order.getSubmitter()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        json1.element("keyword2",new JSONObject().element("value",sdf.format(order.getServiceDate())));
        //json1.element("keyword2",new JSONObject().element("value",order.getServiceDate()));
        json1.element("keyword3",new JSONObject().element("value",order.getScore()));
        json1.element("remark",new JSONObject().element("value","感谢您的辛苦工作"));
        jsonObject.element("data",json1);
        System.out.println("sendConfirmMsgToUser ---->"+jsonObject.toString());
        String resp = this.doHttpsPost(url,"POST","json",jsonObject.toString());
        System.out.println(resp);
        JSONObject object = JSONObject.fromObject(resp);
        if("ok".equals(object.get("errmsg"))){
            return true;
        }
        return false;
    }
    public boolean sendPushMsgToUser(WorkOrder order){
        String url = WechatConsts.URL_TEMPLATE_SEND.replace("ACCESS_TOKEN",this.getAccessToken());
        JSONObject jsonObject  = new JSONObject();
        jsonObject.put("touser",order.getOperatorOpenId());
        jsonObject.put("template_id",pushMsgId);
        jsonObject.put("url","www.fullingsys.cn/sys/wechat/workorder/detail?orderCode="+order.getCode()+"&userType=user");

        JSONObject json1 = new JSONObject();
        json1.element("first",new JSONObject().element("value","您好，您收到1条催单通知"));
        json1.element("keyword1",new JSONObject().element("value",order.getCode()));
        json1.element("keyword2",new JSONObject().element("value",order.getSubmitter()));
        json1.element("keyword3",new JSONObject().element("value",order.getCellPhone()));
        json1.element("keyword4",new JSONObject().element("value",order.getStationName()));
        json1.element("remark",new JSONObject().element("value","相关故障对用户造成较大影响,请尽快前往解决"));
        jsonObject.element("data",json1);
        System.out.println("sendPushMsgToUser ---->"+jsonObject.toString());
        String resp = this.doHttpsPost(url,"POST","json",jsonObject.toString());
        System.out.println(resp);
        JSONObject object = JSONObject.fromObject(resp);
        if("ok".equals(object.get("errmsg"))){
            return true;
        }
        return false;
    }

    public boolean sendDayMsgToUser(String openId, Integer orderCount){
        String url = WechatConsts.URL_TEMPLATE_SEND.replace("ACCESS_TOKEN",this.getAccessToken());
        JSONObject jsonObject  = new JSONObject();
        jsonObject.put("touser",openId);
        jsonObject.put("template_id",dayMsgId);
        jsonObject.put("url","www.fullingsys.cn/sys/wechat/workorder/undolist?openId="+openId);

        JSONObject json1 = new JSONObject();
        json1.element("first",new JSONObject().element("value","您还有"+orderCount+"条未完成的报修任务."));
        json1.element("keyword1",new JSONObject().element("value","维修任务"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        json1.element("keyword2",new JSONObject().element("value",sdf.format(new Date())));
        json1.element("remark",new JSONObject().element("value","请尽快完成!"));
        jsonObject.element("data",json1);
        System.out.println("sendPushMsgToUser ---->"+jsonObject.toString());
        String resp = this.doHttpsPost(url,"POST","json",jsonObject.toString());
        System.out.println(resp);
        JSONObject object = JSONObject.fromObject(resp);
        if("ok".equals(object.get("errmsg"))){
            return true;
        }

        return  false;
    }


}
