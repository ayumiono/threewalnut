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

/**
 * 房源开发-楼盘管理
 * @author lami corp.
 *
 */
@Controller
@RequestMapping("/merchant/community")
public class CommunityController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommunityController.class);

	@RequestMapping("list")
	@ResponseBody
	public Result list(@RequestParam String uid, @RequestParam int page_index, @RequestParam int page_size,@RequestParam(required=false) String keyword){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_name", keyword);
			inParams.put("i_page_index", page_index);
			inParams.put("i_page_size", page_size);
			Map<String,Object> rs =  this.storedProcedureTemplate.call("p_get_communities", inParams);
			List<Object> result = new ArrayList<Object>();
			result.add(rs.get("communityList"));
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
	 * 单个楼盘数据（编辑展示用）
	 * @param uid
	 * @param community_uid
	 * @return
	 */
	@RequestMapping("get")
	@ResponseBody
	public Result get(@RequestParam String uid, @RequestParam String community_uid){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_community_uid", community_uid);
			Map<String,Object> rs =  this.storedProcedureTemplate.call("p_get_community", inParams);
			List<Object> result = new ArrayList<Object>();
			result.add(rs.get("community"));
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
	
	@RequestMapping("update")
	@ResponseBody
	public Result update(@RequestParam String uid,
			@RequestParam String community_uid,
			@RequestParam String city,
			@RequestParam String district,
			@RequestParam String area,
			@RequestParam String name,
			@RequestParam String address,
			@RequestParam(required=false) Float acreage,
			@RequestParam(required=false) Integer house_count,
			@RequestParam(required=false) Integer built,
			@RequestParam(required=false) String ownership,
			@RequestParam(required=false) String descrip){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_community_uid", community_uid);
			inParams.put("i_city", city);
			inParams.put("i_district", district);
			inParams.put("i_area", area);
			inParams.put("i_name", name);
			inParams.put("i_address", address);
			inParams.put("i_acreage", acreage);
			inParams.put("i_house_count", house_count);
			inParams.put("i_built", built);
			inParams.put("i_ownership", ownership);
			inParams.put("i_descrip", descrip);
			Map<String,Object> rs =  this.storedProcedureTemplate.call("p_update_community", inParams);
			List<Object> result = new ArrayList<Object>();
			result.add(rs.get("community"));
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
	 * 添加楼盘
	 * @param uid
	 * @param city
	 * @param district
	 * @param area
	 * @param name
	 * @param address
	 * @param acreage
	 * @param house_count
	 * @param built
	 * @param ownership
	 * @param descrip
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public Result add(@RequestParam String uid, @RequestParam String city,@RequestParam String district,
			@RequestParam String area,@RequestParam String name,@RequestParam String address,
			@RequestParam(required=false) Float acreage,@RequestParam(required=false) Integer house_count,
			@RequestParam(required=false) Integer built,@RequestParam(required=false) String ownership,
			@RequestParam(required=false) String descrip){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_city", city);
			inParams.put("i_district", district);
			inParams.put("i_area", area);
			inParams.put("i_name", name);
			inParams.put("i_address", address);
			inParams.put("i_acreage", acreage);
			inParams.put("i_house_count", house_count);
			inParams.put("i_built", built);
			inParams.put("i_ownership", ownership);
			inParams.put("i_descrip", descrip);
			Map<String,Object> rs =  this.storedProcedureTemplate.call("p_add_community", inParams);
			return new Result(rs.get("community"));
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	/**
	 * 删除楼盘（编辑展示用）
	 * @param uid
	 * @param community_uid
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public Result delete(@RequestParam String uid, @RequestParam String community_uid){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_community_uid", community_uid);
			this.storedProcedureTemplate.call("p_disable_community", inParams);
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
