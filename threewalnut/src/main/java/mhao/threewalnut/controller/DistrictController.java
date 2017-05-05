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

@Controller
@RequestMapping("/merchant/district")
public class DistrictController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(DistrictController.class);

	@RequestMapping("list")
	@ResponseBody
	public Result list(@RequestParam String uid,@RequestParam String city){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_city", city);
			Map<String,Object> rs =  this.storedProcedureTemplate.call("p_get_districts", inParams);
			return new Result(rs.get("districtList"));
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
}
