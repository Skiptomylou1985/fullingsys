package org.zframework.wechat.util.xml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


import com.thoughtworks.xstream.XStream;
import org.zframework.wechat.bean.message.*;

/**
 * 指定类的专属XStream
 * @author antgan
 *
 */
public class XStreamTransformer {

	protected static final Map<Class, XStream> CLASS_2_XSTREAM_INSTANCE = configXStreamInstance();

	/**
	 * xml -> pojo
	 *
	 * @param clazz
	 * @param xml
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromXml(Class<T> clazz, String xml) {
		T object = (T) CLASS_2_XSTREAM_INSTANCE.get(clazz).fromXML(xml);
		return object;
	}

	@SuppressWarnings("unchecked")
	public static <T> T fromXml(Class<T> clazz, InputStream is) {
		T object = (T) CLASS_2_XSTREAM_INSTANCE.get(clazz).fromXML(is);
		return object;
	}

	/**
	 * pojo -> xml
	 *
	 * @param clazz
	 * @param object
	 * @return
	 */
	public static <T> String toXml(Class<T> clazz, T object) {
		return CLASS_2_XSTREAM_INSTANCE.get(clazz).toXML(object);
	}

	private static Map<Class, XStream> configXStreamInstance() {
		Map<Class, XStream> map = new HashMap<Class, XStream>();
		map.put(XmlMessage.class, config_WxXmlMessage());
		map.put(XmlOutNewsMessage.class, config_WxXmlOutNewsMessage());
		map.put(XmlOutTextMessage.class, config_WxXmlOutTextMessage());
		map.put(XmlOutImageMessage.class, config_WxXmlOutImageMessage());
		map.put(XmlOutVideoMessage.class, config_WxXmlOutVideoMessage());
		map.put(XmlOutVoiceMessage.class, config_WxXmlOutVoiceMessage());
		return map;
	}

	private static XStream config_WxXmlMessage() {
		XStream xstream = XStreamInitializer.getInstance();
		xstream.processAnnotations(XmlMessage.class);
		xstream.processAnnotations(XmlMessage.ScanCodeInfo.class);
		xstream.processAnnotations(XmlMessage.SendPicsInfo.class);
		xstream.processAnnotations(XmlMessage.SendPicsInfo.Item.class);
		xstream.processAnnotations(XmlMessage.SendLocationInfo.class);
		return xstream;
	}

	private static XStream config_WxXmlOutImageMessage() {
		XStream xstream = XStreamInitializer.getInstance();
		xstream.processAnnotations(XmlOutMessage.class);
		xstream.processAnnotations(XmlOutImageMessage.class);
		return xstream;
	}

	private static XStream config_WxXmlOutNewsMessage() {
		XStream xstream = XStreamInitializer.getInstance();
		xstream.processAnnotations(XmlOutMessage.class);
		xstream.processAnnotations(XmlOutNewsMessage.class);
		xstream.processAnnotations(XmlOutNewsMessage.Item.class);
		return xstream;
	}

	private static XStream config_WxXmlOutTextMessage() {
		XStream xstream = XStreamInitializer.getInstance();
		xstream.processAnnotations(XmlOutMessage.class);
		xstream.processAnnotations(XmlOutTextMessage.class);
		return xstream;
	}

	private static XStream config_WxXmlOutVideoMessage() {
		XStream xstream = XStreamInitializer.getInstance();
		xstream.processAnnotations(XmlOutMessage.class);
		xstream.processAnnotations(XmlOutVideoMessage.class);
		xstream.processAnnotations(XmlOutVideoMessage.Video.class);
		return xstream;
	}

	private static XStream config_WxXmlOutVoiceMessage() {
		XStream xstream = XStreamInitializer.getInstance();
		xstream.processAnnotations(XmlOutMessage.class);
		xstream.processAnnotations(XmlOutVoiceMessage.class);
		return xstream;
	}

}
