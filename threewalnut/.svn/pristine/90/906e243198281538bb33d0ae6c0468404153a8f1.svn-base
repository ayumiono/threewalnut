package mhao.threewalnut.bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Config {
	
	private static Properties _config = new Properties();
	
	static{
		InputStream is = Config.class.getClassLoader().getResourceAsStream("config_cn.properties");
		try {
			_config.load(is);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public static Object putProperty(String key,Object value){
		return _config.put(key, value);
	}
	
	public static String getPropertyAsString(String key){
		 return (String)_config.get(key);
	}
	
	public static Integer getPropertyAsInt(String key){
		return Integer.parseInt(getPropertyAsString(key));
	}
}
