package org.zframework.wechat.bean.message;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import org.zframework.wechat.support.WechatConsts;
import org.zframework.wechat.util.xml.XStreamCDataConverter;

/**
 * <pre>
 * 被动回复消息--回复文本消息体
 * 
 * 详情:http://mp.weixin.qq.com/wiki/1/6239b44c206cab9145b1d52c67e6c551.html
 * </pre>
 * @author antgan
 *
 */
@XStreamAlias("xml")
public class XmlOutTextMessage extends XmlOutMessage {

	@XStreamAlias("Content")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String content;

	public XmlOutTextMessage() {
		this.msgType = WechatConsts.XML_MSG_TEXT;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "XmlOutTextMessage [content=" + content + "]";
	}

	
}
