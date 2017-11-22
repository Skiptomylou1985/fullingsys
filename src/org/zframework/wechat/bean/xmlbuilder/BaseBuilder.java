package org.zframework.wechat.bean.xmlbuilder;

import org.zframework.wechat.bean.message.XmlOutMessage;

public abstract class BaseBuilder<BuilderType, ValueType> {

	protected String toUserName;

	protected String fromUserName;

	public BuilderType toUser(String touser) {
		this.toUserName = touser;
		return (BuilderType) this;
	}

	public BuilderType fromUser(String fromusername) {
		this.fromUserName = fromusername;
		return (BuilderType) this;
	}

	public abstract ValueType build();

	public void setCommon(XmlOutMessage m) {
		m.setToUserName(this.toUserName);
		m.setFromUserName(this.fromUserName);
		m.setCreateTime(System.currentTimeMillis() / 1000l);
	}

}
