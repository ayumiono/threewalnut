package mhao.threewalnut.controller;

import java.util.HashMap;
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
 * 商圈信息
 * @author lami corp.
 *
 */
@Controller
@RequestMapping("/merchant/area")
public class AreaController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(AreaController.class);
	
	@RequestMapping("list")
	@ResponseBody
	public Result list(@RequestParam String uid, @RequestParam String city,@RequestParam String district){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_city", city);
			inParams.put("i_district", district);
			Map<String,Object> rs = this.storedProcedureTemplate.call("p_get_areas", inParams);
			return new Result(rs.get("areaList"));
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
}
