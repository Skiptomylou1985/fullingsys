package org.zframework.wechat.bean.msgbuilder;

import java.util.ArrayList;
import java.util.List;

import org.zframework.wechat.bean.message.WechatMessage;
import org.zframework.wechat.support.WechatConsts;

/**
 * 图文消息builder
 * 
 * <pre>
 * 用法:
 * WxCustomMessage m = WxCustomMessage.NEWS().addArticle(article).toUser(...).build();
 * </pre>
 * 
 * @author antgan
 *
 */
public final class NewsBuilder extends BaseBuilder<NewsBuilder> {

	private List<WechatMessage.WxArticle> articles = new ArrayList<WechatMessage.WxArticle>();

	public NewsBuilder() {
		this.msgType = WechatConsts.CUSTOM_MSG_NEWS;
	}

	public NewsBuilder addArticle(WechatMessage.WxArticle article) {
		this.articles.add(article);
		return this;
	}

	public WechatMessage build() {
		WechatMessage m = super.build();
		m.setArticles(this.articles);
		return m;
	}
}
