package org.zframework.wechat.bean.xmlbuilder;


import org.zframework.wechat.bean.message.XmlOutTextMessage;

/**
 * 文本消息builder
 * 
 * @author antgan
 *
 */
public final class TextBuilder extends BaseBuilder<TextBuilder, XmlOutTextMessage> {
	private String content;

	public TextBuilder content(String content) {
		this.content = content;
		return this;
	}

	public XmlOutTextMessage build() {
		XmlOutTextMessage m = new XmlOutTextMessage();
		setCommon(m);
		m.setContent(this.content);
		return m;
	}
}
