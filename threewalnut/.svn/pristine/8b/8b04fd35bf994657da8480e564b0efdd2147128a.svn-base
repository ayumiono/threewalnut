package mhao.threewalnut.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mhao.threewalnut.bean.Result;
import mhao.threewalnut.dao.ProcedureException;
import mhao.threewalnut.util.GsonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统设置-员工管理
 * @author lami corp.
 *
 */
@Controller
@RequestMapping("merchant/dept")
public class DeptController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(DeptController.class);
	
	/**
	 * 获取部门、职位列表
	 * @param pid
	 * @param uid
	 * @return
	 */
	@RequestMapping("getchildren")
	@ResponseBody
	public Result getchildren(@RequestParam String uid,@RequestParam(required=false) String pid){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_dept_pid", pid);
			Map<String,Object> rs = this.storedProcedureTemplate.call("p_get_dept_children", inParams);
			List<Object> result = new ArrayList<Object>();
			result.add(rs.get("deptList"));
			result.add(rs.get("positionList"));
			result.add(rs.get("pageInfo"));
			return new Result(result);
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	/**添加部门
	 * @param uid
	 * @param pid
	 * @param name
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public Result add(@RequestParam String uid, @RequestParam String pid,@RequestParam String name){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_dept_pid", pid);
			inParams.put("i_dept_name", name);
			Map<String,Object> rs = this.storedProcedureTemplate.call("p_add_dept", inParams);
			return new Result(rs.get("dept_uid"));
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	/**
	 * 删除部门
	 * @param uid
	 * @param dept_uid
	 * @return
	 */
	@RequestMapping("disable")
	@ResponseBody
	public Result disable(@RequestParam String uid,@RequestParam String dept_uid){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_dept_uid", dept_uid);
			this.storedProcedureTemplate.call("p_disable_dept", inParams);
			return new Result(Result.RESULT_CODE_SUCCESS);
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	/**
	 * 修改部门
	 * @param dept_uid
	 * @param uid
	 * @param name
	 * @return
	 */
	@RequestMapping("updatename")
	@ResponseBody
	public Result updatename(@RequestParam String uid,@RequestParam String dept_uid,@RequestParam String name){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_dept_uid", dept_uid);
			inParams.put("i_dept_name", name);
			this.storedProcedureTemplate.call("p_update_dept_name", inParams);
			return new Result(Result.RESULT_CODE_SUCCESS);
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	/**
	 * 区域列表
	 * @param dept_uid
	 * @param uid
	 * @param name
	 * @return
	 */
	@RequestMapping("region_list")
	@ResponseBody
	public Result regionlist(@RequestParam String uid){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			Map<String,Object> rs = this.storedProcedureTemplate.call("p_get_regions", inParams);
			return new Result(rs.get("regions"));
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	/**
	 * 区域列表
	 * @param dept_uid
	 * @param uid
	 * @param name
	 * @return
	 */
	@RequestMapping("app_region_list")
	@ResponseBody
	public Result regionlist_app(@RequestParam String uid){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			Map<String,Object> rs = this.storedProcedureTemplate.call("p_get_regions", inParams);
			List<Map<String,Object>> regions = (List<Map<String, Object>>) rs.get("regions");
			List<Map<String,Object>> dataWraper = new ArrayList<Map<String,Object>>();
			Map<String,Object> nolimit = new HashMap<String, Object>();
			nolimit.put("text", "不限");
			dataWraper.add(nolimit);
			for(Map<String,Object> region : regions){
				String r_uid = (String) region.get("uid");
				inParams.put("i_uid", uid);
				inParams.put("i_dept_pid", r_uid);
				rs = this.storedProcedureTemplate.call("p_get_dept", inParams);
				//拼装数据
				Map<String,Object> node = new HashMap<String,Object>();
				node.put("text", region.get("name"));
				node.put("value", r_uid);
				List<Map<String,Object>> dept_data = (List<Map<String, Object>>) rs.get("depts");
				List<Map<String,Object>> dept_data_tmp = new ArrayList<Map<String,Object>>();
				if(dept_data.size()>0){
					Map<String,Object> tmp = new HashMap<String, Object>();
					tmp.put("text", "不限");
					dept_data_tmp.add(tmp);
				}
				for(Map<String,Object> dept : dept_data){
					Map<String,Object> tmp = new HashMap<String, Object>();
					tmp.put("text", dept.get("text"));
					tmp.put("value", dept.get("uid"));
					dept_data_tmp.add(tmp);
				}
				node.put("children",dept_data_tmp);
				dataWraper.add(node);
			}
			System.out.println(GsonUtil.toGson(dataWraper));
			return new Result(dataWraper);
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	/**
	 * 区域列表
	 * @param dept_uid
	 * @param uid
	 * @param name
	 * @return
	 */
	@RequestMapping("dept_list")
	@ResponseBody
	public Result deptlist(@RequestParam String uid,@RequestParam String dept_pid){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_dept_pid", dept_pid);
			Map<String,Object> rs = this.storedProcedureTemplate.call("p_get_dept", inParams);
			return new Result(rs.get("depts"));
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	/**
	 * 区域列表
	 * @param dept_uid
	 * @param uid
	 * @param name
	 * @return
	 */
	@RequestMapping("root_dept")
	@ResponseBody
	public Result deptroot(@RequestParam String uid){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			Map<String,Object> rs = this.storedProcedureTemplate.call("p_get_dept_root", inParams);
			return new Result(rs.get("root_dept"));
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
}
