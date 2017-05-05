package mhao.threewalnut.filter;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import mhao.threewalnut.util.TokenUtil;

/**
 * 结合Filter,包装HttpServletRequest对象,获取uid参数
 * 
 * @author lami corp.
 *
 */
public class TokenParamHttpServletRequestWraper extends
		HttpServletRequestWrapper {

	public TokenParamHttpServletRequestWraper(HttpServletRequest request) {
		super(request);
	}
	@Override
	public String getParameter(String name) {
		if (name.equals("uid")) {
			String token = super.getHeader("x-access-token");
			String uid = TokenUtil.validToken(token).getUid();
			return uid;
		} else {
			return super.getParameter(name);
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map getParameterMap() {
		Map<String, String[]> paramMap = new HashMap<String, String[]>();
		paramMap.putAll(super.getParameterMap());
		String token = super.getHeader("x-access-token");
		String uid = TokenUtil.validToken(token).getUid();
		paramMap.put("uid", new String[]{uid});
		return paramMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Enumeration getParameterNames() {
		return Collections.enumeration(this.getParameterMap().keySet());
	}
	@Override
	public String[] getParameterValues(String name) {
		if(name.equals("uid")){
			return new String[]{this.getParameter("uid")};
		}
		return super.getParameterValues(name);
	}
}
