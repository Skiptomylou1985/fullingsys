package org.zframework.wechat.bean.msgbuilder;


import org.zframework.wechat.bean.message.WechatMessage;
import org.zframework.wechat.support.WechatConsts;

/**
 * 视频消息builder
 * <pre>
 * 用法: WxCustomMessage m = WxCustomMessage.VIDEO()
 *                              .mediaId(...)
 *                              .title(...)
 *                              .thumbMediaId(..)
 *                              .description(..)
 *                              .toUser(...)
 *                              .build();
 * </pre>
 * @author antgan
 *
 */
public final class VideoBuilder extends BaseBuilder<VideoBuilder> {
  private String mediaId;
  private String title;
  private String description;
  private String thumbMediaId;

  public VideoBuilder() {
    this.msgType = WechatConsts.CUSTOM_MSG_VIDEO;
  }

  public VideoBuilder mediaId(String mediaId) {
    this.mediaId = mediaId;
    return this;
  }

  public VideoBuilder title(String title) {
    this.title = title;
    return this;
  }

  public VideoBuilder description(String description) {
    this.description = description;
    return this;
  }

  public VideoBuilder thumbMediaId(String thumb_media_id) {
    this.thumbMediaId = thumb_media_id;
    return this;
  }

  public WechatMessage build() {
    WechatMessage m = super.build();
    m.setMediaId(this.mediaId);
    m.setTitle(title);
    m.setDescription(description);
    m.setThumbMediaId(thumbMediaId);
    return m;
  }
}
