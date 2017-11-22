package org.zframework.wechat.bean.xmlbuilder;


import org.zframework.wechat.bean.message.XmlOutMusicMessage;

/**
 * 音乐消息builder
 * 
 * @author antgan
 *
 */
public final class MusicBuilder extends BaseBuilder<MusicBuilder, XmlOutMusicMessage> {
	private String title;
	private String description;
	private String musicUrl;
	private String hQMusicUrl;
	private String thumbMediaId;

	public MusicBuilder title(String title) {
		this.title = title;
		return this;
	}

	public MusicBuilder description(String description) {
		this.description = description;
		return this;
	}
	
	public MusicBuilder musicUri(String musicUrl){
		this.musicUrl = musicUrl;
		return this;
	}
	
	public MusicBuilder hQMusicUrl(String hQMusicUrl){
		this.hQMusicUrl = hQMusicUrl;
		return this;
	}
	
	public MusicBuilder thumbMediaId(String thumbMediaId){
		this.thumbMediaId = thumbMediaId;
		return this;
	}
	

	public XmlOutMusicMessage build() {
		XmlOutMusicMessage m = new XmlOutMusicMessage();
		setCommon(m);
		m.setTitle(title);
		m.setDescription(description);
		m.setMusicUrl(musicUrl);
		m.sethQMusicUrl(hQMusicUrl);
		m.setThumbMediaId(thumbMediaId);
		return m;
	}
}
