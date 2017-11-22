package org.zframework.wechat.bean.msgbuilder;


import org.zframework.wechat.bean.message.WechatMessage;
import org.zframework.wechat.support.WechatConsts;

/**
 * 图片消息builder
 * <pre>
 * 用法: WxCustomMessage m = WxCustomMessage.IMAGE().mediaId(...).toUser(...).build();
 * </pre>
 * 
 * @author antgan
 *
 */
public final class ImageBuilder extends BaseBuilder<ImageBuilder> {
  private String mediaId;

  public ImageBuilder() {
    this.msgType = WechatConsts.CUSTOM_MSG_IMAGE;
  }

  public ImageBuilder mediaId(String media_id) {
    this.mediaId = media_id;
    return this;
  }

  public WechatMessage build() {
    WechatMessage m = super.build();
    m.setMediaId(this.mediaId);
    return m;
  }
}
