package org.zframework.wechat.controller;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zframework.core.util.ObjectUtil;
import org.zframework.web.entity.business.Submitter;
import org.zframework.web.entity.business.WechatUser;
import org.zframework.web.entity.system.User;
import org.zframework.web.service.admin.system.SubmitterService;
import org.zframework.web.service.admin.system.UserService;
import org.zframework.wechat.bean.message.XmlMessage;
import org.zframework.wechat.bean.message.XmlOutNewsMessage;
import org.zframework.wechat.bean.message.XmlOutTextMessage;
import org.zframework.wechat.service.MessageService;
import org.zframework.wechat.support.WechatConsts;
import org.zframework.wechat.support.WechatSupport;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * Created by hyf on 2017/9/24.
 */
@Controller
@RequestMapping("/wechat/message")
public class MessageController {

    @Autowired
    private UserService userService;

    @Autowired
    private SubmitterService submitterService;

    private WechatSupport support = WechatSupport.getInstance();

    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String index(HttpServletRequest request,String signature,String timestamp,String nonce) throws IOException {


        String content = getContentFromReq(request);
        XmlMessage message = XmlMessage.fromXml(content);
        //推送event事件
        if (WechatConsts.XML_MSG_EVENT.equals(message.getMsgType())){
            //根据openid获取用户信息
            WechatUser wechatUser ;

            if(WechatConsts.EVT_CLICK.equals(message.getEvent())){
                return "SUCCESS";
            }else if(WechatConsts.EVT_SCAN.equals(message.getEvent()) || WechatConsts.EVT_SUBSCRIBE.equals(message.getEvent()) ){
                wechatUser = support.getWechatUserByOpenId(message.getFromUserName());
                System.out.println("get EventKey:"+message.getEventKey());
                if (WechatConsts.TYPE_SUBSCRIBE_USER.equals(message.getEventKey()) ||
                        WechatConsts.TYPE_USER.equals(message.getEventKey())){
                    System.out.println("into user proc");
                    User user = userService.getByOpenId(wechatUser.getOpenid());
                    //判断用户是否存在
                    if (ObjectUtil.isNotNull(user)){ //已存在

                        if(!user.getNickName().equals(wechatUser.getNickname())){ //昵称已修改,更新信息
                            user.setNickName(wechatUser.getNickname());
                            userService.update(user);
                        }
                    }else { //不存在,新建用户
                        user = new User();
                        user.setNickName(wechatUser.getNickname());
                        user.setOpenId(wechatUser.getOpenid());
                        user.setCreateTime(new Date());
                        user.setEnabled(2);
                        userService.save(user);

                    }

                }else if(WechatConsts.TYPE_SUBSCRIBE_SUBMMITER.equals(message.getEventKey()) ||
                        WechatConsts.TYPE_SUBMMITER.equals(message.getEventKey())){
                    System.out.println("into submitter proc");
                    Submitter submitter = submitterService.getByOpenID(wechatUser.getOpenid());
                    if (ObjectUtil.isNotNull(submitter)){ //已存在
                        System.out.println("submitter is exist");
                        if(!submitter.getNickName().equals(wechatUser.getNickname())){ //昵称已修改,更新信息
                            submitter.setNickName(wechatUser.getNickname());
                            submitterService.update(submitter);
                        }
                    }else { //不存在,新建用户
                        System.out.println("submitter is not exist");
                        submitter = new Submitter();
                        submitter.setNickName(wechatUser.getNickname());
                        submitter.setOpenId(wechatUser.getOpenid());
                        submitter.setCreateTime(new Date());
                        submitter.setStatus(wechatUser.getSubscribe());
                        submitterService.save(submitter);
                    }
                }
               return welcomeMsg(message);


            }else if(WechatConsts.EVT_UNSUBSCRIBE.equals(message.getEvent())){
                return "SUCCESS";
            }else {
                return  "SUCCESS";
            }
        }

        if (ObjectUtil.isNotEmpty(signature) && ObjectUtil.isNotNull(signature)){
            if(support.checkSignature(signature,timestamp,nonce)) {
                String echoStr = request.getParameter("echostr");
                if (ObjectUtil.isNotNull(echoStr)){
                    return echoStr;
                }
            }
        }
        return "SUCCESS";
    }
    public String getContentFromReq(HttpServletRequest request){
        StringBuilder sb = null;
        BufferedReader reader = null;
        try{
            //注意对方传过来的编码格式，否则xml不能解析
            reader = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
            String line = null;
            sb = new StringBuilder();
            while((line = reader.readLine())!=null){
                sb.append(line);
            }
            System.out.println("HttpServletRequest content:"+sb.toString());
            reader.close();
            return sb.toString();
        }catch(Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
    private String WelcomeArticle(XmlMessage message){
        XmlOutNewsMessage outNewsMessage = new XmlOutNewsMessage();
        outNewsMessage.setFromUserName(message.getToUserName());
        outNewsMessage.setToUserName(message.getFromUserName());
        outNewsMessage.setCreateTime(message.getCreateTime());
        outNewsMessage.setMsgType(WechatConsts.XML_MSG_NEWS);
        XmlOutNewsMessage.Item item = new XmlOutNewsMessage.Item();
        item.setTitle("这是一个测试连接");
        item.setUrl("www.fullingsys.cn/sys/wechat/add");
        item.setDescription("1111111111111111111111111");
        outNewsMessage.addArticle(item);
        System.out.println("outNewsMessage ------>"+outNewsMessage.toXml());
        return outNewsMessage.toXml();
    }

    private  String welcomeMsg(XmlMessage message){
        XmlOutTextMessage outTextMessage = new XmlOutTextMessage();
        outTextMessage.setFromUserName(message.getToUserName());
        outTextMessage.setToUserName(message.getFromUserName());
        outTextMessage.setCreateTime(message.getCreateTime());
        outTextMessage.setMsgType(WechatConsts.XML_MSG_TEXT);
        outTextMessage.setContent("欢迎使用富林思博油站运维服务系统");
        return outTextMessage.toXml();
    }
    private void proc(WechatUser wechatUser,String userType){

    }

}
