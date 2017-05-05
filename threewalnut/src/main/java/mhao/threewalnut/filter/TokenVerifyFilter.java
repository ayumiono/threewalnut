package mhao.threewalnut.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import mhao.threewalnut.bean.Result;
import mhao.threewalnut.util.GsonUtil;
import mhao.threewalnut.util.TokenUtil;

public class TokenVerifyFilter implements Filter {
	
	private static final Logger logger = LoggerFactory.getLogger(TokenVerifyFilter.class);
	
	List<String> ignore;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String igore_url = filterConfig.getInitParameter("ignore");
		String[] igore_arr = igore_url.split(",");
		if(igore_arr.length>0){
			this.ignore = new ArrayList<String>();
			for(String url : igore_arr){
				this.ignore.add(url.trim());
			}
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		for(String i_url : ignore){
			if(req.getRequestURI().indexOf(i_url)>-1){
				chain.doFilter(req, resp);
				return;
			}
		}
		if(req.getHeader("x-access-token") == null){
			Result result = new Result(10009,"访问拒绝");
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("application/json");
			resp.getWriter().write(GsonUtil.toGson(result));
			resp.getWriter().flush();
			resp.getWriter().close();
			return;
		}else if(TokenUtil.validToken(req.getHeader("x-access-token"))==null){
			Result result = new Result(10009,"访问拒绝");
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("application/json");
			resp.getWriter().write(GsonUtil.toGson(result));
			resp.getWriter().flush();
			resp.getWriter().close();
			return;
		}else{
			//添加操作日志
			ServletRequest reqWraper = new TokenParamHttpServletRequestWraper(req);
			String uri = req.getRequestURI();
			Map<String, String[]> paramMap = reqWraper.getParameterMap();
			String c_type = "pc";
			if(req.getHeader("app")!=null){
				c_type = "app";
			}
			logger.info("客户端{}	操作{}	请求参数:{}",new Object[]{c_type,uri,JSON.toJSON(paramMap).toString()});
			chain.doFilter(reqWraper, resp);
			return;
		}
	}

	@Override
	public void destroy() {

	}

}
