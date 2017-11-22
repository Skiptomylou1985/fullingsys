package org.zframework.wechat.bean.xmlbuilder;


import org.zframework.wechat.bean.message.XmlOutVoiceMessage;

/**
 * 语音消息builder
 * 
 * @author antgan
 */
public final class VoiceBuilder extends BaseBuilder<VoiceBuilder, XmlOutVoiceMessage> {

	private String mediaId;

	public VoiceBuilder mediaId(String mediaId) {
		this.mediaId = mediaId;
		return this;
	}

	public XmlOutVoiceMessage build() {
		XmlOutVoiceMessage m = new XmlOutVoiceMessage();
		setCommon(m);
		m.setMediaId(mediaId);
		return m;
	}

}
