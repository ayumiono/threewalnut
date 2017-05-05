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
 * 客源开发-客户关联
 * @author lami corp.
 *
 */
@Controller
@RequestMapping("/merchant/stranger")
public class StrangerController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(StrangerController.class);
	
	@RequestMapping("list")
	@ResponseBody
	public Result list(@RequestParam String uid, @RequestParam Integer type,
			@RequestParam(required=false) Integer page_index,@RequestParam(required=false) Integer page_size){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_type", type);
			inParams.put("i_page_size", page_size);
			inParams.put("i_page_index", page_index);
			Map<String,Object> rs =  this.storedProcedureTemplate.call("p_get_strangers", inParams);
			if(type!=0){
				return new Result(rs.get("strangers"));
			}else{
				List<Object> result = new ArrayList<Object>();
				result.add(rs.get("strangers"));
				result.add(rs.get("pageInfo"));
				return new Result(result);
			}
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	/**
	 * 关联客户
	 */
	@RequestMapping("tocustomer")
	@ResponseBody
	public Result tocustomer(@RequestParam String uid,
			@RequestParam(required=false) String customer_uid,
			@RequestParam String stranger_uid,
			@RequestParam(required=false) String type,
			@RequestParam(required=false) Integer listing_type,
			@RequestParam(required=false) Integer rent_type,
			@RequestParam(required=false) String name,
			@RequestParam(required=false) String phone,
			@RequestParam(required=false) Integer bedroom,
			@RequestParam(required=false) Integer livingroom,
			@RequestParam(required=false) Integer washroom,
			@RequestParam(required=false) Integer balcony,
			@RequestParam(required=false) Float acreage_min,
			@RequestParam(required=false) Float acreage_max,
			@RequestParam(required=false) Float price_min,
			@RequestParam(required=false) Float price_max,
			@RequestParam(required=false) String city,
			@RequestParam(required=false) String district,
			@RequestParam(required=false) String address,
			@RequestParam(required=false) String usage,
			@RequestParam(required=false) String status,
			@RequestParam(required=false) String source,
			@RequestParam(required=false) Integer quality,
			@RequestParam(required=false) String pay_type,
			@DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam(required=false) Date entrusted_date,
			@RequestParam(required=false) String comment){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_customer_uid", customer_uid);
			inParams.put("i_stranger_uid", stranger_uid);
			inParams.put("i_type", type);
			inParams.put("i_listing_type", listing_type);
			inParams.put("i_rent_type", rent_type);
			inParams.put("i_name", name);
			inParams.put("i_phone", phone);
			inParams.put("i_bedroom", bedroom);
			inParams.put("i_livingroom", livingroom);
			inParams.put("i_washroom", washroom);
			inParams.put("i_balcony", balcony);
			inParams.put("i_acreage_min", acreage_min);
			inParams.put("i_acreage_max", acreage_max);
			inParams.put("i_price_min", price_min);
			inParams.put("i_price_max", price_max);
			inParams.put("i_city", city);
			inParams.put("i_district", district);
			inParams.put("i_address", address);
			inParams.put("i_usage", usage);//
			inParams.put("i_status", status);//
			inParams.put("i_source", source);//
			inParams.put("i_quality", quality);
			inParams.put("i_entrusted_date", entrusted_date);
			inParams.put("i_pay_type", pay_type);//
			inParams.put("i_comment", comment);
			Map<String,Object> rs =  this.storedProcedureTemplate.call("p_stranger_to_customer", inParams);
			return new Result(rs.values());
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	/**
	 * 关联业主
	 */
	@RequestMapping("tohouseowner")
	@ResponseBody
	public Result tohouseowner(@RequestParam String uid,
			@RequestParam String house_uid,
			@RequestParam String stranger_uid,
			@RequestParam Integer option){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_house_uid", house_uid);
			inParams.put("i_stranger_uid", stranger_uid);
			inParams.put("i_option", option);
			this.storedProcedureTemplate.call("p_stranger_to_houseowner", inParams);
			return new Result(Result.RESULT_CODE_SUCCESS);
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	@RequestMapping("mark")
	@ResponseBody
	public Result mark(@RequestParam String uid, @RequestParam String stranger_uid, @RequestParam Integer type,
			@RequestParam String name,@RequestParam(required=false) String comment){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_stranger_uid", stranger_uid);
			inParams.put("i_type", type);
			inParams.put("i_name", name);
			inParams.put("i_comment", comment);
			this.storedProcedureTemplate.call("p_mark_stranger", inParams);
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
