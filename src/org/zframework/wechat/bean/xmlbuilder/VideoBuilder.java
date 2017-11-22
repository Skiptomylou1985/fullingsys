package org.zframework.wechat.bean.xmlbuilder;


import org.zframework.wechat.bean.message.XmlOutVideoMessage;

/**
 * 视频消息builder
 * 
 * @author antgan
 *
 */
public final class VideoBuilder extends BaseBuilder<VideoBuilder, XmlOutVideoMessage> {

	private String mediaId;
	private String title;
	private String description;

	public VideoBuilder title(String title) {
		this.title = title;
		return this;
	}

	public VideoBuilder description(String description) {
		this.description = description;
		return this;
	}

	public VideoBuilder mediaId(String mediaId) {
		this.mediaId = mediaId;
		return this;
	}

	public XmlOutVideoMessage build() {
		XmlOutVideoMessage m = new XmlOutVideoMessage();
		setCommon(m);
		m.setTitle(title);
		m.setDescription(description);
		m.setMediaId(mediaId);
		return m;
	}

}
