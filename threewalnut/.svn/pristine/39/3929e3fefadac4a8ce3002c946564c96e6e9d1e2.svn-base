package mhao.threewalnut.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.google.gson.reflect.TypeToken;


/**
 * 保存系统规则
 * @author lami corp.
 *
 */
@Controller
@RequestMapping("merchant/config")
public class ConfigController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(ConfigController.class);

	@RequestMapping("update")
	@ResponseBody
	public Result update(@RequestParam String uid,@RequestParam String configs){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			Type typeOfT = new TypeToken<ArrayList<Map<String, String>>>(){}.getType();
			ArrayList<Map<String, String>> rss = GsonUtil.fromGson(configs, typeOfT);
			for(Map<String, String> _config : rss){
				String i_key = _config.get("key");
				String i_value = _config.get("value"); 
				inParams.put("i_key", i_key);
				inParams.put("i_value", i_value);
				this.storedProcedureTemplate.call("p_update_config", inParams);
			}
			return new Result(Result.RESULT_CODE_SUCCESS);
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	@RequestMapping("tagsInfo")
	@ResponseBody
	public Result tagsinfo(@RequestParam String uid){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			Map<String, Object> result = this.storedProcedureTemplate.call("p_get_full_tagsinfo", inParams);
			return new Result(result.get("tags"));
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
}
