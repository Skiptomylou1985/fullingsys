package org.zframework.plugins.datadict;

import java.util.List;
import java.util.Map;

import org.zframework.orm.support.IDataDict;
/**
 * 缓存数据字典类
 * @author HYF
 *
 */
public class CacheDataDictImpl implements IDataDict{

	@Override
	public Object get(String key) {
		return "2";
	}

	@Override
	public boolean sync() {
		return false;
	}

	@Override
	public List<Object> getList(String key) {
		return null;
	}

	@Override
	public Map<String, Object> getMap(String key) {
		return null;
	}

	@Override
	public void put(String key, Object data) {
		
	}

}
