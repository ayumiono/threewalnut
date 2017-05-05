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
@RequestMapping("/merchant/houseowner_follow")
public class HouseownerFollowController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(HouseownerFollowController.class);
	
	/**
	 * 跟进纪录列表
	 * @param uid
	 * @param houseowner_uid
	 * @param query_month
	 * @param page_index
	 * @param page_size
	 * @return
	 * deprecated since 1.5
	 */
//	@RequestMapping("list")
	@ResponseBody
	@Deprecated
	public Result list(@RequestParam String uid,@RequestParam String house_uid,@RequestParam String query_month,@RequestParam Integer page_index,@RequestParam Integer page_size){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_house_uid", house_uid);//客户端参数名为houseowner_uid应该改成house_uid FIXME
			inParams.put("i_query_month", query_month);
			inParams.put("i_page_index", page_index);
			inParams.put("i_page_size", page_size);
			Map<String, Object> rs = this.storedProcedureTemplate.call("p_get_houseowner_follows", inParams);
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
	
	/**
	 * 跟进纪录列表
	 * @param uid
	 * @param houseowner_uid
	 * @param query_month_start	格式必须为yyyy-mm
	 * @param query_month_end	格式必须为yyyy-mm
	 * @return
	 * 增加跨月查询功能
	 * 去除分页
	 */
	@RequestMapping("list")
	@ResponseBody
	public Result list(@RequestParam String uid,@RequestParam String house_uid,@RequestParam String query_month_start,@RequestParam String query_month_end){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_house_uid", house_uid);//客户端参数名为houseowner_uid应该改成house_uid FIXME
			inParams.put("i_query_month_start", query_month_start);
			inParams.put("i_query_month_end", query_month_end);
//			inParams.put("i_page_index", page_index);
//			inParams.put("i_page_size", page_size);
			Map<String, Object> rs = this.storedProcedureTemplate.call("p_get_houseowner_follows", inParams);
			List<Object> result = new ArrayList<Object>();
			result.add(rs.get("follows"));
//			result.add(rs.get("pageInfo"));
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
	 * 更新跟进纪录
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
			this.storedProcedureTemplate.call("p_update_houseowner_follow", inParams);
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
	 * 添加跟进记录
	 * @param uid
	 * @param house_uid
	 * @param comment
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public Result add(@RequestParam String uid,@RequestParam String house_uid,@RequestParam(required=false) String comment){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_house_uid", house_uid);
			inParams.put("i_comment", comment);
			this.storedProcedureTemplate.call("p_add_houseowner_follow", inParams);
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
