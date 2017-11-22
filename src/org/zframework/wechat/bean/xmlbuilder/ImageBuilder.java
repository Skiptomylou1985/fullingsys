package org.zframework.wechat.bean.xmlbuilder;


import org.zframework.wechat.bean.message.XmlOutImageMessage;

/**
 * 图片消息builder
 * 
 * @author antgan
 */
public final class ImageBuilder extends BaseBuilder<ImageBuilder, XmlOutImageMessage> {

	private String mediaId;

	public ImageBuilder mediaId(String media_id) {
		this.mediaId = media_id;
		return this;
	}

	public XmlOutImageMessage build() {
		XmlOutImageMessage m = new XmlOutImageMessage();
		setCommon(m);
		m.setMediaId(this.mediaId);
		return m;
	}

}
