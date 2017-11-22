package org.zframework.wechat.bean.message;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import org.zframework.wechat.support.WechatConsts;
import org.zframework.wechat.util.xml.XStreamMediaIdConverter;

/**
 * <pre>
 * 被动回复消息--回复图片消息体
 * 
 * 详情:http://mp.weixin.qq.com/wiki/1/6239b44c206cab9145b1d52c67e6c551.html
 * </pre>
 * @author antgan
 *
 */
@XStreamAlias("xml")
public class XmlOutImageMessage extends XmlOutMessage {

	@XStreamAlias("Image")
	@XStreamConverter(value = XStreamMediaIdConverter.class)
	private String mediaId;

	public XmlOutImageMessage() {
		this.msgType = WechatConsts.XML_MSG_IMAGE;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

}
