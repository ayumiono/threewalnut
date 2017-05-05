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
 * 业主管理-带看纪录
 * @author lami corp.
 *
 */
@Controller
public class HouseWatchController extends BaseController {
private static final Logger logger = LoggerFactory.getLogger(HouseWatchController.class);
	
//	@RequestMapping("/merchant/house_watch/list")
	@ResponseBody
	public Result list(@RequestParam String uid,@RequestParam String house_uid,@RequestParam Integer page_index,@RequestParam Integer page_size){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_house_uid", house_uid);
			inParams.put("i_page_index", page_index);
			inParams.put("i_page_size", page_size);
			Map<String,Object> rs = this.storedProcedureTemplate.call("p_get_house_watchs", inParams);
			List<Object> result = new ArrayList<Object>();
			result.add(rs.get("watchs"));
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
	
	
	@RequestMapping("/merchant/house_watch/list")
	@ResponseBody
	public Result list(@RequestParam String uid,@RequestParam String house_uid,@DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam Date start_time,@DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam Date end_time){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_house_uid", house_uid);
			inParams.put("i_start_time", start_time);
			inParams.put("i_end_time", end_time);
			Map<String,Object> rs = this.storedProcedureTemplate.call("p_get_house_watchs", inParams);
			List<Object> result = new ArrayList<Object>();
			result.add(rs.get("watchs"));
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
