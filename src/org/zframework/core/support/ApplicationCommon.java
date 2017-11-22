package org.zframework.core.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.zframework.orm.cache.ICacheProvider;
import org.zframework.orm.support.IDataDict;
import org.zframework.web.entity.system.type.IPRoleType;
import org.zframework.web.support.DefaultCacheProvider;
import org.zframework.web.support.DataDictDefaultImpl;

/**
 * 系统全局变量配置类
 * @author HYF
 *
 */
public final class ApplicationCommon implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4723677111800151534L;
	/**
	 * 项目BasePath
	 */
	public static String BASEPATH = "";
	/**
	 * 后台用户session
	 */
	public final static String SESSION_ADMIN = "ADMINSESSION";
	/**
	 * 前台用户session
	 */
	public final static String SESSION_USER = "USERSESSION";
	/**
	 * 系统超级管理员用户名
	 * 不可删除该用户
	 */
	public final static String SYSTEM_ADMIN = "superadmin";
	/**
	 * 加密Key
	 * 没有此Key，无法从密文获取明文密码
	 */
	public final static String DEC_KEY = "hyf@fullingsys";
	
	/**
	 * 登陆验证次数
	 * 存储在Session中的名称
	 */
	public final static String LOGIN_DENIED_NUMBER = "LoginDeniedNumber";
	/**
	 * 是否验证通过
	 * 用于执行关键性操作且需要验证用户密码
	 * 存储在Session中的名称
	 */
	public final static String ALLOW_ACCESS = "AllowAccess";
	/**
	 * 记录验证通过的时间
	 * 用于下次验证时进行时间比较
	 * 存储在Session中的名称
	 */
	public final static String ALLOW_ACCESS_TIMESPAN = "AllowAccess_TIMESPAN";

	/**
	 * 站点类型
	 */
	public final static String TYPE_STATION = "station";
	public final static String TYPE_CITY="city";
	public final static String TYPE_PROVINCE="province";

	/**
	 * 人员类型
	 */
	public final static String TYPE_USER = "user";
	public final static String TYPE_SUBMITTER="submitter";

	/**
	 * 工单状态类型
	 */
	public final static int STATUS_ADDED= 0;
	public final static int STATUS_SERVICED= 1;
	public final static int STATUS_COMFIRMED= 2;
	public final static int STATUS_CANCELED= 3;
	public final static int STATUS_REJECTED= 4;

	/**
	 * 工单状态类型
	 */
	public final static String ACTION_ADD = "add";
	public final static String ACTION_SERVICE = "service";
	public final static String ACTION_CONFIRM = "confirm";
	public final static String ACTION_CANCEL = "cancel";
	public final static String ACTION_REJECT = "reject";
	public final static String ACTION_PUSH = "push";
	/**
	 * 系统公用型数据
	 */
	public static Map<String,String> SYSCOMMONS = new HashMap<String, String>();
	/**
	 * 系统登陆的用户名集合
	 */
	public static Set<String> LOGIN_USERS = new HashSet<String>();
	/**
	 * 记录已经登陆的用户
	 */
	public static Map<String,HttpSession> LOGIN_SESSIONS = new HashMap<String, HttpSession>();
	/**
	 * IP列表
	 */
	public static List<String> IP_LIST = new ArrayList<String>();
	/**
	 * IP规则开关
	 */
	public static boolean IPROLE = false;
	/**
	 * ip安全侧率
	 * Allow： 只允许列表中的IP登陆
	 * Deny: 不允许列表中的IP登陆
	 */
	public static IPRoleType IPROLETYPE = IPRoleType.Deny;
	/**
	 * 图标样式集合
	 */
	public static List<String> ICONCLS_LIST = new ArrayList<String>();
	
	/**
	 * 数据字典
	 */
	public static IDataDict DATADICT = ApplicationContextHelper.getInstance().createBean(DataDictDefaultImpl.class);
	
	/**
	 * 存放缓存
	 */
	public static ICacheProvider APPLICATIONCACHE = ApplicationContextHelper.getInstance().createBean(DefaultCacheProvider.class);
}