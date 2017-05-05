package mhao.threewalnut.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mhao.threewalnut.bean.Config;
import mhao.threewalnut.dao.ProcedureException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * 版本控制，自动更新
 * @author lami corp.
 *
 */
@Controller
public class VersionController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(VersionController.class);
	
	@RequestMapping("wgtVerCheck")
	@ResponseBody
	public Map<String,Object> wgtVerCheck(HttpServletRequest request){
		Map<String,Object> inParams = new HashMap<String, Object>();
		try {
			Map<String, Object> rs;
			rs = this.storedProcedureTemplate.call("p_get_wgt_version", inParams);
			String wgt_version = (String) rs.get("wgt_version");
			String wgt_url = (String) rs.get("wgt_url");
			inParams.clear();
			inParams.put("code", 0);
			inParams.put("version", wgt_version);
			inParams.put("url", wgt_url);
			return inParams;
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			inParams.put("code", 1);
			return inParams;
		} catch (Exception e) {
			e.printStackTrace();
			inParams.put("code", 1);
			return inParams;
		}
		
	}
	
	@RequestMapping({"version"})
	@ResponseBody
	public Map<String, Object> version(HttpServletRequest request)
	  {
	    try{
	      Map<String,Object> inParams = new HashMap<String,Object>();
	      Map<String,Object> rs = this.storedProcedureTemplate.call("p_get_app_version", inParams);
	      return rs;
	    } catch (Exception e) {
	      e.printStackTrace();
	    }return null;
	  }
	
	@RequestMapping("version_v1_5_0")
	@ResponseBody
	public Manifest version_v1_5_0(HttpServletRequest request){
		try {
			Map<String,Object> inParams = new HashMap<String, Object>();
			Map<String,Object> rs = this.storedProcedureTemplate.call("p_get_app_version_v1_5_0", inParams);
			String version = (String) rs.get("version");
			String exec_name = (String) rs.get("exec_name");
			String mac_url = (String) rs.get("mac_url");
			String win_url = (String) rs.get("win_url");
			Manifest manifest = new Manifest();
			manifest.name=exec_name;
			manifest.version = version;
			manifest.description="";
			Map<String,Map<String, String>> packages = new HashMap<String, Map<String,String>>();
			Map<String,String> pack = new HashMap<String, String>();
			pack.put("url", mac_url);
			packages.put("mac", pack);
			pack.put("url", win_url);
			packages.put("win", pack);
			manifest.packages = packages;
			return manifest;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	class Manifest{
		public String name;//用于生成执行文件后缀exe或app
		public String description;
		public String version;
		public Map<String,Map<String,String>> packages;
		public Manifest(){
		}
	}
	
	@RequestMapping("download")
	public void download(HttpServletRequest request,HttpServletResponse response){
		try {
			InputStream fis = null;
			String os = request.getParameter("os");
			if(StringUtils.isBlank(os)){
				fis = request.getSession().getServletContext().getResourceAsStream(Config.getPropertyAsString("app_package_path"));
			} else if(os.equals("win")){
				fis = request.getSession().getServletContext().getResourceAsStream(Config.getPropertyAsString("app_package_path"));
			}else if(os.equals("mac")){
				fis = request.getSession().getServletContext().getResourceAsStream(Config.getPropertyAsString("app_package_path_mac"));
			} else {
				fis = request.getSession().getServletContext().getResourceAsStream(Config.getPropertyAsString("app_package_path"));
			}
			byte[] buffer = new byte[1024];
			response.setContentType("multipart/form-data");
			response.addHeader("Content-Disposition", "attachment;filename=app.exe");
			while(fis.read(buffer)>0){
				response.getOutputStream().write(buffer);
			}
			response.getOutputStream().flush();
			response.getOutputStream().close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
		}
	}
}
