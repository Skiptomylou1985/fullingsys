package org.zframework.wechat.exception;

import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 微信错误码说明 http://mp.weixin.qq.com/wiki/10/6380dc743053a91c544ffd2b7c959166.html
 * 
 * @author antgan
 *
 */
public class WechatError {

	private int errcode;

	private String errmsg;

	public WechatError() {
	}
	
	public WechatError(int errcode, String errmsg) {
		this.errcode = errcode;
		this.errmsg = errmsg;
	}
	
	public int getErrcode() {
		return errcode;
	}


	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}


	public String getErrmsg() {
		return errmsg;
	}


	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}


	public static WechatError fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(json, WechatError.class);
	}


	@Override
	public String toString() {
		return "WechatError [errcode=" + errcode + ", errmsg=" + errmsg + "]";
	}

	

}
