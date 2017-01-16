package com.xiaojun.util;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * gson处理工具
 * @author xiaojun
 * @email  lxjluoxiaojun@163.com
 * @date   2017年1月16日
 */
public class GSONUtils {
	private static Gson gson;

	/**
	 * 将对象转换为json字符串。 如果对象（或其持有的对象）的某个属性为日期类型， 转换时将使用本系统默认的日期字符串格式
	 * "yyyy-MM-dd HH:mm:ss"。
	 * 
	 * @param obj
	 *            需要准换的对象。
	 * @param isPrettyPrinting
	 *            一个转换规则标志，该标志表明：“是否要将转换的结果按照大家习惯的分行缩进方式进行排版。”
	 * @return 返回转换后的Json 字符串
	 */
	public static String toJson(Object obj, boolean isPrettyPrinting) {
		GsonBuilder gsonBuilder = new GsonBuilder();

		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");

		if (isPrettyPrinting) {
			gsonBuilder.setPrettyPrinting();
		}

		Gson gson = gsonBuilder.create();

		String json = gson.toJson(obj);
		return json;
	}

	public static Gson getGson() {
		if (gson == null) {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm");
			return gsonBuilder.create();
		} else {
			return gson;
		}
	}

	/**
	 * String型jSon转成map
	 * 
	 * @param jsonStr
	 * @return Map<String, String>
	 * 
	 */
	public static Map<String, String> fromJsonToMapStr(String jsonStr) {
		return GSONUtils.getGson().fromJson(jsonStr, new TypeToken<Map<String, String>>() {
		}.getType());
	}

	/**
	 * String型jSon转成map
	 * 
	 * @param jsonStr
	 * @return Map<String, String>
	 * 
	 */
	public static Map<String, Object> fromJsonToMapObj(String jsonStr) {
		return GSONUtils.getGson().fromJson(jsonStr, new TypeToken<Map<String, Object>>() {
		}.getType());
	}

	/**
	 * String型jSon,去对应的key值
	 * 
	 * @param jsonStr,key
	 * @return String
	 */
	public static String getJsonValueStr(String jsonStr, String key) {
		String object = null;
		Map<String, Object> map = fromJsonToMapObj(jsonStr);
		if (map != null && map.size() > 0) {
			if (key != null && !"".equals(key)) {
				object = map.get(key) == null ? "" : map.get(key).toString();
			}
		}
		return object;
	}

	/**
	 * String型jSon,去对应的key值
	 * 
	 * @param jsonStr,key
	 * @return String
	 */
	public static String getJsonValueStr2(String jsonStr, String key) {
		String object = null;
		Map<String, String> map = fromJsonToMapStr(jsonStr);
		if (map != null && map.size() > 0) {
			if (key != null && !"".equals(key)) {
				object = map.get(key).toString();
			}
		}
		return object;
	}

	/**
	 * String型jSon转成 Object
	 * 
	 * @param <T>
	 * @param jsonStr
	 * @return
	 * @return Object
	 * 
	 */
	public static <T> Object fromJsonToObject(String jsonStr, Class<T> classOfT) {
		return GSONUtils.getGson().fromJson(jsonStr, classOfT);
	}

}
