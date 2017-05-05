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
 * 系统设置-mac地址
 * @author lami corp.
 *
 */
@Controller
@RequestMapping("/merchant/mac")
public class MacController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(MacController.class);

	@RequestMapping("list")
	@ResponseBody
	public Result list(@RequestParam String uid){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			Map<String,Object> rs =  this.storedProcedureTemplate.call("p_get_avliable_macs", inParams);
			return new Result(rs.get("macList"));
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	@RequestMapping("add")
	@ResponseBody
	public Result add(@RequestParam String uid, @RequestParam String mac){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("mac", mac);
			this.storedProcedureTemplate.call("p_add_avilable_mac", inParams);
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
