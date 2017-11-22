package org.zframework.wechat.bean;

import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 微信AccessToken，微信API接口调用凭证
 * @author antgan
 *
 */
public class WechatAccessToken {
	private String access_token;
	private int expires_in = -1;
	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	
	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public static WechatAccessToken fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, WechatAccessToken.class);
	}

	@Override
	public String toString() {
		return "WechatAccessToken [access_token=" + access_token + ", expires_in=" + expires_in + "]";
	}
	
}
