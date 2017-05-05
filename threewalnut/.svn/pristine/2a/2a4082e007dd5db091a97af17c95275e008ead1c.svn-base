package mhao.threewalnut.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mhao.threewalnut.bean.Result;
import mhao.threewalnut.dao.ProcedureException;
import mhao.threewalnut.util.GsonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * 业绩报表
 * @author lami corp.
 *
 */
@Controller
@RequestMapping("/merchant/report")
public class ReportController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
	
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("deptlist")
	@ResponseBody
	public Result deptlist(@RequestParam String uid,@DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam(required=false) Date start,@DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam(required=false) Date end){
		try {
			Map<String,Object> inParams = new HashMap<String,Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_start", start);
			inParams.put("i_end", end);
			Map<String,Object> rs = this.storedProcedureTemplate.call("p_get_report_dept", inParams);
			return new Result(rs.get("deptList"));
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	/**
	 * 报表部门树状结构
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("app_deptlist")
	@ResponseBody
	public Result deptlist_app(@RequestParam String uid,@DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam(required=false) Date start,@DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam(required=false) Date end){
		try {
			Map<String,Object> inParams = new HashMap<String,Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_start", start);
			inParams.put("i_end", end);
			Map<String,Object> rs = this.storedProcedureTemplate.call("p_get_report_dept", inParams);
			//tree wraper
			List<Map<String,Object>> nodelist = (List<Map<String, Object>>) rs.get("deptList");
			
			DeptTreeNode root = new DeptTreeNode(null);
			root.level=-1;
			DeptTreeNode lastNode = root;
			for(Map<String,Object> data : nodelist){
				DeptTreeNode me = new DeptTreeNode(data);
				me.level = DeptTreeNode.countSubStr((String)me.data.get("text"));
				if(me.text!=null) me.text=me.text.replace("-", "");
				DeptTreeNode myParent = DeptTreeNode.findMyParent(me, lastNode);
				me.parent = myParent;
				if(myParent.children==null){
					myParent.children = new ArrayList<DeptTreeNode>();
					if(myParent!=root){
						myParent.children.add(new DeptTreeNode("不限",null));
					}
				}
				myParent.children.add(me);
				lastNode = me;
			}
			//tree node clear
			return new Result(GsonUtil.toGson(root.children));
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	class DeptTreeNodeSerializer implements JsonSerializer<DeptTreeNode>{

		@Override
		public JsonElement serialize(DeptTreeNode src, Type typeOfSrc,
				JsonSerializationContext context) {
			JsonObject jsonObject = new JsonObject();
			JsonArray children = new JsonArray();
			for(DeptTreeNode child : src.children){
				JsonObject obj = new JsonObject();
				for(Entry<String, Object> entry : child.data.entrySet()){
					obj.addProperty(entry.getKey(), (String)entry.getValue());
				}
				children.add(obj);
			}
			jsonObject.add("children", children);
			return jsonObject;
		}
		
	}
	
	static class DeptTreeNode{
		 String text;
		 String uid;
		 List<DeptTreeNode> children;
		 transient int level;
		 transient DeptTreeNode parent;
		 transient Map<String,Object> data;
		 
		 public DeptTreeNode(String text,String uid){
			 this.text=text;
			 this.uid=uid;
		 }
		
		public DeptTreeNode(Map<String,Object> data){
			this.data=data;
			if(data!=null){
				this.text = (String) data.get("text");
				this.uid = (String) data.get("uid");
			}
		}
		
		static Pattern pattern = Pattern.compile("-");
		
		static int countSubStr(String text){
			int count = 0;
			Matcher matcher = pattern.matcher(text);
			while(matcher.find()){
				count++;
			}
			return count;
		}
		static DeptTreeNode findMyParent(DeptTreeNode me,DeptTreeNode lastNode){
			DeptTreeNode tmp = lastNode;
			while(tmp.level>=me.level){
				tmp = tmp.parent;
			}
			return tmp;
		}
	}
	
	/**
	 * 获取任务报表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("business")
	@ResponseBody
	public Result business(@RequestParam String uid,@RequestParam String dept_uid){
		try {
			Map<String,Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_dept_uid", dept_uid);
			Map<String,Object> rs = this.storedProcedureTemplate.call("p_report_business", inParams);
			return new Result(rs.get("businessRpt"));
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	/**
	 * 获取业主报表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("house")
	@ResponseBody
	public Result house(@RequestParam String uid,@RequestParam(required=false) String dept_uid,@DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam(required=false) Date start,@DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam(required=false) Date end){
		try {
			Map<String,Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_dept_uid", dept_uid);
			inParams.put("i_start", start);
			inParams.put("i_end", end);
			Map<String,Object> rs = this.storedProcedureTemplate.call("p_report_house", inParams);
			return new Result(rs.get("houseRpt"));
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	/**
	 * app默认展示上一周业务部业主报表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("house_init")
	@ResponseBody
	public Result house_init(@RequestParam String uid){
		try {
			Map<String,Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			Map<String,Object> rs = this.storedProcedureTemplate.call("p_report_house_app_init", inParams);
			return new Result(rs.get("rpt"));
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	/**
	 * 获取客户报表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("customer")
	@ResponseBody
	public Result customer(@RequestParam String uid,@RequestParam String dept_uid,@DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam(required=false)  Date start,@DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam(required=false)  Date end){
		try {
			Map<String,Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_dept_uid", dept_uid);
			inParams.put("i_start", start);
			inParams.put("i_end", end);
			Map<String,Object> rs = this.storedProcedureTemplate.call("p_report_customer", inParams);
			return new Result(rs.get("customerRpt"));
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	/**
	 * app默认展示上一周业务部门客户报表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("customer_init")
	@ResponseBody
	public Result customer_init(@RequestParam String uid){
		try {
			Map<String,Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			Map<String,Object> rs = this.storedProcedureTemplate.call("p_report_customer_app_init", inParams);
			return new Result(rs.get("rpt"));
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("employee_path")
	@ResponseBody
	public Result employee_path(@RequestParam String uid,@RequestParam String query_uid,@RequestParam(required=false) Integer query_flag){
		try {
			Map<String,Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_query_uid", query_uid);
			inParams.put("i_query_flag", query_flag);
			Map<String,Object> rs = this.storedProcedureTemplate.call("p_get_employee_path", inParams);
			return new Result(rs.get("employeePath"));
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
}
