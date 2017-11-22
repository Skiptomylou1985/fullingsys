package org.zframework.wechat.bean.xmlbuilder;

import org.zframework.wechat.bean.message.XmlOutMessage;
import org.zframework.wechat.bean.message.XmlOutNewsMessage;
import org.zframework.wechat.bean.message.XmlOutNewsMessage.Item;

import java.util.ArrayList;
import java.util.List;



/**
 * 图文消息builder
 * 
 * @author antgan
 */
public final class NewsBuilder extends BaseBuilder<NewsBuilder, XmlOutMessage> {

	protected final List<XmlOutNewsMessage.Item> articles = new ArrayList<Item>();

	public NewsBuilder addArticle(Item item) {
		this.articles.add(item);
		return this;
	}

	public XmlOutMessage build() {
		XmlOutNewsMessage m = new XmlOutNewsMessage();
		for (Item item : articles) {
			m.addArticle(item);
		}
		setCommon(m);
		return m;
	}

}
