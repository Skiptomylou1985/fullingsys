package org.zframework.wechat.exception;

/**
 * 微信异常
 * 
 * @author antgan
 *
 */
public class WechatErrorException extends Exception {

	private static final long serialVersionUID = -6357149550353160810L;

	private WechatError error;

	public WechatErrorException(WechatError error) {
		super(error.toString());
		this.error = error;
	}
	
	public WechatErrorException(String msg){
		super(msg);
	}

	public WechatError getError() {
		return error;
	}

}
