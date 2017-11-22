package org.zframework.wechat.util.xml;

/**
 * MediaId 转换器
 * @author antgan
 *
 */
public class XStreamMediaIdConverter extends XStreamCDataConverter {
	@Override
	public String toString(Object obj) {
		return "<MediaId>" + super.toString(obj) + "</MediaId>";
	}
}
