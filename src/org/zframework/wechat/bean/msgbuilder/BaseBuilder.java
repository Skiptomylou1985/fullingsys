package org.zframework.wechat.bean.msgbuilder;


import org.zframework.wechat.bean.message.WechatMessage;
import org.zframework.wechat.support.WechatConsts;


public class BaseBuilder<T> {
	protected String msgType;
	protected String agentId;
	protected String toUser;
	protected String toParty;
	protected String toTag;
	protected String safe;

	public T agentId(String agentId) {
		this.agentId = agentId;
		return (T) this;
	}

	public T toUser(String toUser) {
		this.toUser = toUser;
		return (T) this;
	}

	public T toParty(String toParty) {
		this.toParty = toParty;
		return (T) this;
	}

	public T toTag(String toTag) {
		this.toTag = toTag;
		return (T) this;
	}

	public T safe(String safe) {
		this.safe = safe;
		return (T) this;
	}

	public WechatMessage build() {
		WechatMessage m = new WechatMessage();
		m.setAgentId(this.agentId);
		m.setMsgType(this.msgType);
		m.setToUser(this.toUser);
		m.setToParty(this.toParty);
		m.setToTag(this.toTag);
		m.setSafe((this.safe == null || "".equals(this.safe)) ? WechatConsts.CUSTOM_MSG_SAFE_NO : this.safe);
		return m;
	}

}
