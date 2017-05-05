package mhao.threewalnut.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

/**
 * 运营分析
 * @author lami corp.
 *
 */
@Controller
@RequestMapping("/merchant/operations")
public class OperationsAnalysisController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(OperationsAnalysisController.class);
	
	@RequestMapping("houseowner_follow_list")
	@ResponseBody
	public Result houseowner_follow(@RequestParam String uid,@RequestParam String query_month_start,@RequestParam String query_month_end,
			@RequestParam(required=false) String region_uid,@RequestParam(required=false) String dept_uid,@RequestParam(required=false) String position_uid,
			@RequestParam(required=false) Integer status,@RequestParam(required=false) Integer type,@RequestParam(required=false) String new_value,
			@RequestParam(required=false) String order_field,@RequestParam(required=false) Integer order,
			@RequestParam(required=false) Integer page_index,@RequestParam(required=false) Integer page_size){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_start", query_month_start);
			inParams.put("i_end", query_month_end);
			inParams.put("i_region_uid", region_uid);
			inParams.put("i_dept_uid", dept_uid);
			inParams.put("i_position_uid", position_uid);
			inParams.put("i_status", status);
			inParams.put("i_type", type);
			inParams.put("i_new_value", new_value);
			inParams.put("i_order_field", order_field);
			inParams.put("i_order", order);
			inParams.put("i_page_index", page_index);
			inParams.put("i_page_size", page_size);
			Map<String, Object> rs = this.storedProcedureTemplate.call("p_get_houseowner_follow_operations", inParams);
			List<Object> result = new ArrayList<Object>();
			result.add(rs.get("followList"));
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
	
	@RequestMapping("operations_type")
	@ResponseBody
	public Result operations_type(@RequestParam String uid){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			Map<String, Object> rs = this.storedProcedureTemplate.call("p_get_operations_type", inParams);
			List<Map<String, Object>> parent = (List<Map<String, Object>>) rs.get("parent");
			List<Map<String, Object>> children = (List<Map<String, Object>>) rs.get("children");
			for(Map<String, Object> p : parent){
				List<Map<String,Object>> my_children = new ArrayList<Map<String,Object>>();
				String p_id = (String) p.get("uid");
				Iterator<Map<String,Object>> itreator = children.iterator();
				while(itreator.hasNext()){
					Map<String,Object> c = itreator.next();
					if(((String)c.get("parent_uid")).equals(p_id)){
						c.remove("parent_uid");
						my_children.add(c);
						itreator.remove();
					}
				}
				p.remove("uid");
				p.put("children", my_children);
			}
			return new Result(parent);
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	@RequestMapping("customer_watch_list")
	@ResponseBody
	public Result customer_watch(@RequestParam String uid,@RequestParam String query_month_start,@RequestParam String query_month_end,
			@RequestParam(required=false) String dept_uid,@RequestParam(required=false) String position_uid,
			@RequestParam(required=false) Integer listing_type,@RequestParam(required=false) String type,@RequestParam(required=false) String name,
			@RequestParam(required=false) String order_field,@RequestParam(required=false) Integer order,
			@RequestParam(required=false) Integer page_index,@RequestParam(required=false) Integer page_size){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_start", query_month_start);
			inParams.put("i_end", query_month_end);
			inParams.put("i_dept_uid", dept_uid);
			inParams.put("i_position_uid", position_uid);
			inParams.put("i_listing_type", listing_type);
			inParams.put("i_type", type);
			inParams.put("i_name", name);
			inParams.put("i_order_field", order_field);
			inParams.put("i_order", order);
			inParams.put("i_page_index", page_index);
			inParams.put("i_page_size", page_size);
			Map<String, Object> rs = this.storedProcedureTemplate.call("p_get_customer_watch_operations", inParams);
			List<Object> result = new ArrayList<Object>();
			result.add(rs.get("watchList"));
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
}
