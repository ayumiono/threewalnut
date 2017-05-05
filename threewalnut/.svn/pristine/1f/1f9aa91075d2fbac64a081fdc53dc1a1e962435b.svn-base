package mhao.threewalnut.controller;

import java.io.UnsupportedEncodingException;
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

/**
 * 栋座信息
 * @author lami corp.
 *
 */
@Controller
@RequestMapping("/merchant/building")
public class BuildingController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(BuildingController.class);
	
	@RequestMapping("list")
	@ResponseBody
	public Result list(@RequestParam String uid, @RequestParam(required=false) String community_uid, 
			@RequestParam(required=false) String name,@RequestParam Integer page_index,@RequestParam Integer page_size){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid",uid);
			inParams.put("i_community_uid",community_uid);
			inParams.put("i_keywords",name);
			inParams.put("i_page_index",page_index);
			inParams.put("i_page_size",page_size);
			Map<String, Object> rs = this.storedProcedureTemplate.call("p_get_buildings", inParams);
			List<Object> result = new ArrayList<Object>();
			result.add(rs.get("buildingList"));
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
	
	/**
	 * 添加栋座
	 * @param uid
	 * @param community_uid
	 * @param names
	 * @param house_type
	 * @param floor
	 * @param unit
	 * @param floor_count
	 * @param room
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public Result add(@RequestParam String uid,@RequestParam String community_uid,@RequestParam String names,
			@RequestParam String house_type,
			@RequestParam(required=false) String floor,
			@RequestParam(required=false) String unit,
			@RequestParam(required=false) Integer floor_count,
			@RequestParam(required=false) String room
			){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_community_uid", community_uid);
			inParams.put("i_names", names);
			inParams.put("i_house_type", house_type);
			inParams.put("i_floor", floor);
			inParams.put("i_unit", unit);
			inParams.put("i_room", room);
			inParams.put("i_floor_count", floor_count);
			this.storedProcedureTemplate.call("p_add_buildings", inParams);
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
	 * 删除栋座
	 * @param uid
	 * @param building_uid
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("delete")
	@ResponseBody
	public Result delete(@RequestParam String uid,@RequestParam String building_uid) throws UnsupportedEncodingException{
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_building_uid", building_uid);
			this.storedProcedureTemplate.call("p_delete_building", inParams);
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
	 * 删除栋座
	 * @param uid
	 * @param building_uid
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("get")
	@ResponseBody
	public Result get(@RequestParam String uid,@RequestParam String building_uid) throws UnsupportedEncodingException{
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_building_uid", building_uid);
			Map<String,Object> rs = this.storedProcedureTemplate.call("p_get_building", inParams);
			return new Result(rs.get("building"));
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
}
