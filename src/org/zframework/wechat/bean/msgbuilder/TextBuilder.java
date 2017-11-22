package org.zframework.wechat.bean.msgbuilder;


import org.zframework.wechat.bean.message.WechatMessage;
import org.zframework.wechat.support.WechatConsts;

/**
 * 文本消息builder
 * 
 * <pre>
 * 用法: WxCustomMessage m = WxCustomMessage.TEXT().content(...).toUser(...).build();
 * </pre>
 * 
 * @author antgan
 *
 */
public final class TextBuilder extends BaseBuilder<TextBuilder> {
	private String content;

	public TextBuilder() {
		this.msgType = WechatConsts.CUSTOM_MSG_TEXT;
	}

	public TextBuilder content(String content) {
		this.content = content;
		return this;
	}

	public WechatMessage build() {
		WechatMessage m = super.build();
		m.setContent(this.content);
		return m;
	}
}
