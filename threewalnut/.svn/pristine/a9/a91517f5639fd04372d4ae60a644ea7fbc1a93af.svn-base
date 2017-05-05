package mhao.threewalnut.controller;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mhao.threewalnut.dao.StoredProcedureTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@Autowired
	protected StoredProcedureTemplate storedProcedureTemplate;
	
	public String getUid(HttpServletRequest request) {
		return (String) request.getSession().getAttribute("uid");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void printParam(HttpServletRequest request){
		Map<String, Object> map = request.getParameterMap();
		StringBuffer sb = new StringBuffer();
		Map.Entry entry;
		String key = "";
		String value = "";
		if (map != null && map.size() > 0) {
			Iterator entries = map.entrySet().iterator();
			while (entries.hasNext()) {
				entry = (Map.Entry) entries.next();
				key = (String) entry.getKey();
				Object valueObj = entry.getValue();
				if (null == valueObj) {
					value = "";
				} else if (valueObj instanceof String[]) {
					String[] values = (String[]) valueObj;
					for (int i = 0; i < values.length; i++) {
						value = values[i] + ",";
					}
					value = value.substring(0, value.length() - 1);
				} else {
					value = valueObj.toString();
				}
				sb.append(key).append(":").append(value).append(";");
			}
		}
		logger.info(sb.toString());
		return;
	}
}
