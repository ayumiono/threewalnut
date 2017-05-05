package mhao.threewalnut.util;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
@SuppressWarnings({ "unchecked", "rawtypes" })
public class GsonUtil {
	private static Gson getGson() {
		return new GsonBuilder().disableHtmlEscaping().create();
	}
	
	public static String toGson(Object obj) {
		return getGson().toJson(obj);
	}
	public static Object fromGson(String json,Class classOfT){
		return getGson().fromJson(json, classOfT);
	}
	public static <T> T fromGson(String json,Type typeOfT){
		return getGson().fromJson(json, typeOfT);
	}
	public static String toGson(Object obj,Type targetType) {
		Gson gson = new Gson();
		return gson.toJson(obj, targetType);
	}
	
	public static Map<String,String> getMapGson(String json){
		return getGson().fromJson(json, new TypeToken<Map<String,String>>() {}.getType() );
	}
	
	public static List<String> getListGson(String json){
		return getGson().fromJson(json, new TypeToken<List<String>>() {}.getType() );
	}
	
	public static Set<String> getSetGson(String json){
		return getGson().fromJson(json, new TypeToken<Set<String>>() {}.getType() );
	}
}
