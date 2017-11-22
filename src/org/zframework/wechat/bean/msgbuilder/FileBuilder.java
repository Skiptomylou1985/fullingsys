package org.zframework.wechat.bean.msgbuilder;


import org.zframework.wechat.bean.message.WechatMessage;
import org.zframework.wechat.support.WechatConsts;

/**
 * 获得消息builder
 * 
 * <pre>
 * 用法: WxCustomMessage m = WxCustomMessage.FILE().mediaId(...).toUser(...).build();
 * </pre>
 * 
 * @author antgan
 *
 */
public final class FileBuilder extends BaseBuilder<FileBuilder> {
	private String mediaId;

	public FileBuilder() {
		this.msgType = WechatConsts.CUSTOM_MSG_FILE;
	}

	public FileBuilder mediaId(String media_id) {
		this.mediaId = media_id;
		return this;
	}

	public WechatMessage build() {
		WechatMessage m = super.build();
		m.setMediaId(this.mediaId);
		return m;
	}
}
