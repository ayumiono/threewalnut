package mhao.threewalnut.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mhao.threewalnut.bean.Result;
import mhao.threewalnut.dao.ProcedureException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 客源管理-跟进记录
 * @author lami corp.
 *
 */
@Controller
@RequestMapping("/merchant/customer_follow")
public class CustomerFollowController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerFollowController.class);
	
//	@RequestMapping("list")
	@ResponseBody
	@Deprecated
	public Result list(@RequestParam String uid,@RequestParam String customer_uid,@RequestParam String query_month,@RequestParam Integer page_index,@RequestParam Integer page_size){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_customer_uid", customer_uid);
			inParams.put("i_query_month", query_month);
			inParams.put("i_page_index", page_index);
			inParams.put("i_page_size", page_size);
			Map<String,Object> rs = this.storedProcedureTemplate.call("p_get_customer_follows", inParams);
			List<Object> result = new ArrayList<Object>();
			result.add(rs.get("follows"));
			result.add(rs.get("pageInfo"));
			result.add(rs.get("rightCode"));
			return new Result(result);
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	@RequestMapping("list")
	@ResponseBody
	public Result list(@RequestParam String uid,@RequestParam String customer_uid,@RequestParam String query_month_start,@RequestParam String query_month_end){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_customer_uid", customer_uid);
			inParams.put("i_query_month_start", query_month_start);
			inParams.put("i_query_month_end", query_month_end);
			Map<String,Object> rs = this.storedProcedureTemplate.call("p_get_customer_follows", inParams);
			List<Object> result = new ArrayList<Object>();
			result.add(rs.get("follows"));
			result.add(rs.get("rightCode"));
			return new Result(result);
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	/**
	 * 添加跟进记录
	 * @param uid
	 * @param customer_uid
	 * @param follow_time
	 * @param comment
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public Result add(@RequestParam String uid,@RequestParam String customer_uid,@DateTimeFormat(pattern="yyyy-MM-dd HH:mm") @RequestParam Date follow_time,@RequestParam(required=false) String comment){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_customer_uid", customer_uid);
			inParams.put("i_follow_time", follow_time);
			inParams.put("i_comment", comment);
			this.storedProcedureTemplate.call("p_add_customer_follow", inParams);
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
	 * 编辑跟进记录
	 * @param uid
	 * @param follow_uid
	 * @param comment
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public Result update(@RequestParam String uid,@RequestParam String follow_uid,@RequestParam(required=false) String comment){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_follow_uid", follow_uid);
			inParams.put("i_comment", comment);
			this.storedProcedureTemplate.call("p_update_customer_follow", inParams);
			return new Result(Result.RESULT_CODE_SUCCESS);
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
}
