package org.zframework.wechat.bean.message;

import org.zframework.wechat.exception.AesException;
import org.zframework.wechat.support.WechatConfig;
import org.zframework.wechat.bean.xmlbuilder.ImageBuilder;
import org.zframework.wechat.bean.xmlbuilder.MusicBuilder;
import org.zframework.wechat.bean.xmlbuilder.NewsBuilder;
import org.zframework.wechat.bean.xmlbuilder.TextBuilder;
import org.zframework.wechat.bean.xmlbuilder.VideoBuilder;
import org.zframework.wechat.bean.xmlbuilder.VoiceBuilder;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import org.zframework.wechat.util.crypto.WechatBizMsgCrypt;
import org.zframework.wechat.util.xml.XStreamCDataConverter;
import org.zframework.wechat.util.xml.XStreamTransformer;

/**
 * <pre>
 * 被动回复消息--消息体超类
 * 
 * 详情:http://mp.weixin.qq.com/wiki/1/6239b44c206cab9145b1d52c67e6c551.html
 * </pre>
 * @author antgan
 *
 */

@XStreamAlias("xml")
public abstract class XmlOutMessage {

	@XStreamAlias("ToUserName")
	@XStreamConverter(value = XStreamCDataConverter.class)
	protected String toUserName;

	@XStreamAlias("FromUserName")
	@XStreamConverter(value = XStreamCDataConverter.class)
	protected String fromUserName;

	@XStreamAlias("CreateTime")
	protected Long createTime;

	@XStreamAlias("MsgType")
	@XStreamConverter(value = XStreamCDataConverter.class)
	protected String msgType;

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String toXml() {
		return XStreamTransformer.toXml((Class) this.getClass(), this);
	}

	/**
	 * 转换成加密的xml格式
	 * @return
	 * @throws AesException 
	 */
	public static String encryptMsg(WechatConfig wxconfig, String replyMsg, String timeStamp, String nonce) throws AesException {
		WechatBizMsgCrypt pc = new WechatBizMsgCrypt(WechatConfig.getInstance().getToken(), WechatConfig.getInstance().getAesKey(), WechatConfig.getInstance().getAppId());
		return pc.encryptMsg(replyMsg, timeStamp, nonce);
	}

	/**
	 * 获得文本消息builder
	 * 
	 * @return
	 */
	public static TextBuilder TEXT() {
		return new TextBuilder();
	}

	/**
	 * 获得图片消息builder
	 * 
	 * @return
	 */
	public static ImageBuilder IMAGE() {
		return new ImageBuilder();
	}

	/**
	 * 获得语音消息builder
	 * 
	 * @return
	 */
	public static VoiceBuilder VOICE() {
		return new VoiceBuilder();
	}

	/**
	 * 获得视频消息builder
	 * 
	 * @return
	 */
	public static VideoBuilder VIDEO() {
		return new VideoBuilder();
	}

	/**
	 * 获得图文消息builder
	 * 
	 * @return
	 */
	public static NewsBuilder NEWS() {
		return new NewsBuilder();
	}
	
	/**
	 * 获取音乐消息builder
	 * @return
	 */
	public static MusicBuilder MUSIC(){
		return new MusicBuilder();
	}

	@Override
	public String toString() {
		return "XmlOutMessage [toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime="
				+ createTime + ", msgType=" + msgType + "]";
	}

}
