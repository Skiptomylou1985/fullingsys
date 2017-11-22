package org.zframework.test;
import org.junit.Test;
import org.zframework.core.support.ApplicationCommon;
import org.zframework.core.util.DecUtil;

/**
 * Created by hyf on 2017/10/27.
 */
public class WebTest {
    @Test
    public void getPassword(){
        DecUtil des = new DecUtil();
        des.genKey(ApplicationCommon.DEC_KEY);// 生成密匙
        System.out.println(des.getEncString("123456"));
    }
}
