package org.zframework.core.util;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * Created by hyf on 2017/9/22.
 */
public class XmlUtil {
    Document document;//文档对象
    Element rootElement;//根元素
    Element sendRootElement;//二级根元素
    String rootElementString;//根元素字符串
    //String sendRootElementString;//二级根元素字符串

    private static final String TIME_DATE_PATTERN = "yyyy-MM-dd hh:mm:ss"; // 2015-4-23 15:20:00

    /**
     * <p>Description:设定xml元素的根元素,命名上要以s结尾，以表示一个集合</p>
     * @param element
     */
    public void setRootElement(String element){
        this.rootElementString=element;
        document=DocumentHelper.createDocument();
        rootElement=document.addElement(element);
    }

    /**
     * <p>Description:设定xml叶元素的名称和值</p>
     * @param element
     * @param value
     */
    public void setElement(String element,String value){
        Element temp=rootElement.addElement(element);
        temp.setText(value);
    }

    /**
     * <p>Description:设定xml元素的List</p>
     * @param list
     */
    public void setElementList(List<Map<String,String>> list){
        for(Map<String,String> map:list){
            //sendRootElement=rootElement.addElement(sendRootElementString);
            Iterator<String> iter=map.keySet().iterator();
            while(iter.hasNext()){
                String key = iter.next();
                String elementValue = map.get(key);

                System.out.println("key+++=="+key);
                System.out.println("elementValue+++=="+elementValue);

                Element temp=rootElement.addElement(key);
                if(elementValue == null)
                    temp.setText("");
                else
                    temp.setText(elementValue);
            }
        }
    }



    /**
     * <p>Description:生成xml的字符串形式</p>
     * @return
     */
    public String getParseString() throws Exception{
        byte[] xmlByte=null;
        String result="";
        try {
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            OutputFormat format = OutputFormat.createCompactFormat();
            //format.setEncoding("GBK");
            format.setEncoding("UTF-8");
            XMLWriter writer=new XMLWriter(bos,format);
            writer.write(document);
            xmlByte = bos.toByteArray();
            result=new String(xmlByte);
        }catch (Exception e) {
            throw new Exception("生成xml出错:"+e.getMessage());
        }
        return result;
    }

    /**
     * <p>Description:设定要解析的xml字符串</p>
     * @param str
     */
    public void setParseString(String str) throws Exception{
        try {
            document=DocumentHelper.parseText(str);
            rootElement=document.getRootElement();
            rootElementString=rootElement.getName();
           // System.out.println("rootElement :: " + rootElement);
           // System.out.println("rootElementString :: " + rootElementString);
        } catch (DocumentException e) {
            throw new Exception("解析XML字符串错误:"+e.getMessage());
        }catch (Exception e) {
            throw new Exception("读取XML字符串错误:"+e.getMessage());
        }
    }

    /**
     * <p>Description:取得xml叶元素</p>
     * @param element
     * @return
     */
    public String getElement(String element){
        String url="//"+rootElementString + "/"+element;
        //System.out.println(url);
        Node node = document.selectSingleNode(url);
        if(node == null){
            return null;
        }else{
            return node.getText();
        }


    }

    /**
     * <p>Description:取得xml的枝元素，封装在一个map中</p>
     * @param element
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Map<String,String>> getElementList(String element){
        List<Map<String,String>> content = new ArrayList<Map<String,String>>();
        List<Element> list = document.selectNodes("//"+rootElementString + "/" + element);
        for(Iterator<Element> iter = list.iterator();iter.hasNext();){
            Element ele = iter.next();//取得application元素
            Map<String,String> map=new HashMap<String, String>();
            for(Iterator<Element> iter2 = ele.elementIterator();iter2.hasNext();){
                Element ele2 = iter2.next();
                map.put(ele2.getName(),ele2.getText());
            }
            content.add(map);
        }
        return content;
    }
}
