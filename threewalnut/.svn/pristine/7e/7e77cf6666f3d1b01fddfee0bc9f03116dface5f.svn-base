package mhao.threewalnut.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mhao.threewalnut.bean.Result;
import mhao.threewalnut.dao.ProcedureException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/merchant/user")
public class UserController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping("checkin")
	@ResponseBody
	public Result checkin(@RequestParam String uid,@RequestParam String mac){
		Map<String,Object> inParams = new HashMap<String, Object>();
		inParams.put("i_mac", mac);
		inParams.put("i_uid", uid);
		try {
			Map<String, Object> rs = storedProcedureTemplate.call("p_check_in", inParams);
			List<Object> result = new ArrayList<Object>();
			result.add(rs.get("menuTreeData"));
			result.add(rs.get("systemRule"));
			result.add(rs.get("city"));
			result.add(rs.get("user"));
			return new Result(result);
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	@RequestMapping("fuzzy_query_employee")
	@ResponseBody
	public Result fuzzy_query_employee(@RequestParam String uid,@RequestParam(required=false) String name){
		Map<String,Object> inParams = new HashMap<String, Object>();
		inParams.put("i_uid", uid);
		inParams.put("i_name", name);
		try {
			Map<String, Object> rs = storedProcedureTemplate.call("p_fuzzy_query_employee", inParams);
			List<Object> result = new ArrayList<Object>();
			result.add(rs.get("employee"));
			return new Result(result);
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
}
