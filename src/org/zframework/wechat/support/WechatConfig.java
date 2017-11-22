package org.zframework.wechat.support;

import java.io.*;
import java.util.Properties;


import org.zframework.wechat.bean.WechatAccessToken;
import org.zframework.wechat.exception.WechatErrorException;
import org.zframework.wechat.util.StringUtils;

/**
 * 微信全局配置对象-从配置文件读取
 * @author antgan
 * @datetime 2016/12/14
 *
 */
public class WechatConfig {
	private static final String configFile = "/wechat.properties";
	private volatile  String configPath = "";
	private static WechatConfig config = null;
	
	//配置文件读取项
	private volatile String appId;
	private volatile String appSecret;
	private volatile String token;
	private volatile String aesKey;
	private volatile String mchId;
	private volatile String apiKey;
	
	//内存更新
	private volatile String accessToken;
	private volatile long expiresTime;
	private volatile String jsapiTicket;
	private volatile long jsapiTicketExpiresTime;

	private Properties p;
	
	public WechatConfig() {
		//写读配置文件代码
		p = new Properties();
		configPath = this.getClass().getResource("/").getPath()+"org/zframework/conf/wechat.properties";
		System.out.println(configPath);
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
	
	/**
	 * 同步获取/加载单例
	 * @return
	 */
	public static synchronized WechatConfig getInstance(){
		if(config == null){
			config = new WechatConfig();
		}
		return config;
	}
	public void savePreperty(String key,String value){
		FileOutputStream fos = null;
		p.setProperty(key,value);
		try {
			fos = new FileOutputStream(configPath);
			p.store(fos,"save it");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public String getAccessToken() {
		return accessToken;
	}
	
	public boolean isAccessTokenExpired() {
		return System.currentTimeMillis() > this.expiresTime;
	}

	public void expireAccessToken() {
		this.expiresTime = 0;
	}

	public synchronized void updateAccessToken(WechatAccessToken accessToken) {
		updateAccessToken(accessToken.getAccess_token(), accessToken.getExpires_in());
	}

	public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
		this.accessToken = accessToken;
		this.expiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000l;
	}

	public String getJsapiTicket() {
		return jsapiTicket;
	}

	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}

	public long getJsapiTicketExpiresTime() {
		return jsapiTicketExpiresTime;
	}

	public void setJsapiTicketExpiresTime(long jsapiTicketExpiresTime) {
		this.jsapiTicketExpiresTime = jsapiTicketExpiresTime;
	}

	public boolean isJsapiTicketExpired() {
		return System.currentTimeMillis() > this.jsapiTicketExpiresTime;
	}

	public synchronized void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {
		this.jsapiTicket = jsapiTicket;
		// 预留200秒的时间
		this.jsapiTicketExpiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000l;
	}
	
	public void expireJsapiTicket() {
		this.jsapiTicketExpiresTime = 0;
	}

	
	//getter


	public String getAppId() {
		return appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public String getToken() {
		return token;
	}
	
	public String getAesKey() {
		return aesKey;
	}
	
	public String getMchId() {
		return mchId;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public String toString() {
		return "WechatConfig [appId=" + appId + ", appSecret=" + appSecret + ", token=" + token + ", aesKey=" + aesKey
				+ ", mchId=" + mchId + ", apiKey=" + apiKey + ", accessToken=" + accessToken + ", expiresTime="
				+ expiresTime + ", jsapiTicket=" + jsapiTicket + ", jsapiTicketExpiresTime=" + jsapiTicketExpiresTime
				+ "]";
	}

	
}
