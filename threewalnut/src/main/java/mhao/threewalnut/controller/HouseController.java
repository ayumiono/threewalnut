package mhao.threewalnut.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mhao.threewalnut.bean.Config;
import mhao.threewalnut.bean.Result;
import mhao.threewalnut.dao.ProcedureException;
import mhao.threewalnut.util.AESUtil;
import mhao.threewalnut.util.DateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 房源开发-业主管理
 * @author lami corp.
 *
 */
@Controller
@RequestMapping("/merchant/house")
public class HouseController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(HouseController.class);
	
	
	/**
	 * 业主管理列表
	 * @param uid
	 * @param accept_service
	 * @param has_weixin
	 * @param community_name
	 * @param listing_type
	 * @param listing_status
	 * @param rent_type
	 * @param building_name
	 * @param room_no
	 * @param rent_price_lowest
	 * @param rent_price_highest
	 * @param sale_price_lowest
	 * @param sale_price_highest
	 * @param floor_lowest
	 * @param floor_highest
	 * @param acreage_min
	 * @param acreage_max
	 * @param house_layout	房型
	 * @param direction
	 * @param entrust_date_start
	 * @param entrust_date_end
	 * @param city
	 * @param district
	 * @param area
	 * @param region_uid
	 * @param dept_uid
	 * @param position_uid
	 * @param page_index
	 * @param page_size
	 * @return
	 */
	@RequestMapping("list4c")
	@ResponseBody
	public Result c_list(
			@RequestParam String uid,
			@RequestParam(required=false) String accept_service,
			@RequestParam(required=false) String has_weixin,
			@RequestParam(required=false) String community_name,
			@RequestParam(required=false) Integer listing_type,
			@RequestParam(required=false) String listing_status,
			@RequestParam(required=false) Integer rent_type,
			@RequestParam(required=false) String building_name,
			@RequestParam(required=false) String room_no,
			@RequestParam(required=false) Float rent_price_lowest,
			@RequestParam(required=false) Float rent_price_highest,
			@RequestParam(required=false) Float sale_price_lowest,
			@RequestParam(required=false) Float sale_price_highest,
			@RequestParam(required=false) Integer floor_lowest,
			@RequestParam(required=false) Integer floor_highest,
			@RequestParam(required=false) Float acreage_min,
			@RequestParam(required=false) Float acreage_max,
			@RequestParam(required=false) Integer house_layout,
			@RequestParam(required=false) String direction,
			@DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam(required=false) Date entrust_date_start,
			@DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam(required=false) Date entrust_date_end,
			@DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam(required=false) Date start_time_start,
			@DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam(required=false) Date start_time_end,
			@RequestParam(required=false) String city,
			@RequestParam(required=false) String district,
			@RequestParam(required=false) String area,
			@RequestParam(required=false) String region_uid,
			@RequestParam(required=false) String dept_uid,
			@RequestParam(required=false) String position_uid,
			@RequestParam(required=false) String tags,
			@RequestParam(required=false) String order_field,@RequestParam(required=false) Integer order,
			@RequestParam(required=false) Integer query_flag,
			@RequestParam Integer page_index,@RequestParam Integer page_size) {
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_community_name", community_name);
			inParams.put("i_listing_type", listing_type);
			inParams.put("i_listing_status", listing_status);
			inParams.put("i_rent_type", rent_type);
			inParams.put("i_building_name", building_name);
			inParams.put("i_room_no", room_no);
			inParams.put("i_has_weixin", has_weixin);
			inParams.put("i_accept_service", accept_service);
			
			inParams.put("i_rent_price_lowest", rent_price_lowest);
			inParams.put("i_rent_price_highest", rent_price_highest);
			inParams.put("i_sale_price_lowest", sale_price_lowest);
			inParams.put("i_sale_price_highest", sale_price_highest);
			inParams.put("i_floor_lowest", floor_lowest);
			inParams.put("i_floor_highest", floor_highest);
			inParams.put("i_acreage_min", acreage_min);
			inParams.put("i_acreage_max", acreage_max);
			inParams.put("i_house_layout", house_layout);
			inParams.put("i_direction", direction);
			inParams.put("i_entrust_date_start", entrust_date_start);
			inParams.put("i_entrust_date_end", entrust_date_end);
			inParams.put("i_start_time_start", start_time_start);
			inParams.put("i_start_time_end", start_time_end);
			inParams.put("i_city", city);
			inParams.put("i_district", district);
			inParams.put("i_area", area);
			inParams.put("i_region_uid", region_uid);
			inParams.put("i_dept_uid", dept_uid);
			inParams.put("i_position_uid", position_uid);
			inParams.put("i_tags", tags);
			inParams.put("i_order_field", order_field);
			inParams.put("i_order", order);
			inParams.put("i_query_flag", query_flag);
			inParams.put("i_page_index", page_index);
			inParams.put("i_page_size", page_size);
			Map<String, Object> rs =  this.storedProcedureTemplate.call("p_get_houses_4c", inParams);
			List<Object> result = new ArrayList<Object>();
			result.add(rs.get("houseList"));
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
	 * 业主管理列表
	 * @param uid
	 * @param accept_service
	 * @param has_weixin
	 * @param community_name
	 * @param listing_type
	 * @param rent_type
	 * @param building_name
	 * @param room_no
	 * @param dept_uid
	 * @param position_uid
	 * @param page_index
	 * @param page_size
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public Result list(@RequestParam String uid,@RequestParam(required=false) String accept_service,@RequestParam(required=false) String has_weixin,@RequestParam(required=false) String community_name,
			@RequestParam(required=false) Integer listing_type,@RequestParam(required=false) String listing_status,@RequestParam(required=false) String rent_type,@RequestParam(required=false) String building_name,@RequestParam(required=false) String room_no,
			@RequestParam(required=false) String dept_uid,@RequestParam(required=false) String position_uid,@RequestParam Integer page_index,@RequestParam Integer page_size) {
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_community_name", community_name);
			inParams.put("i_listing_type", listing_type);
			inParams.put("i_listing_status", listing_status);
			inParams.put("i_rent_type", rent_type);
			inParams.put("i_building_name", building_name);
			inParams.put("i_room_no", room_no);
			inParams.put("i_has_weixin", has_weixin);
			inParams.put("i_accept_service", accept_service);
			inParams.put("i_dept_uid", dept_uid);
			inParams.put("i_position_uid", position_uid);
			inParams.put("i_page_index", page_index);
			inParams.put("i_page_size", page_size);
			Map<String, Object> rs =  this.storedProcedureTemplate.call("p_get_houses_4app", inParams);
			List<Object> result = new ArrayList<Object>();
			result.add(rs.get("houseList"));
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
	 * 业主管理列表(多余的接口，兼容app,需要将app接口名换成/merchant/house/list)
	 * @param uid
	 * @param accept_service
	 * @param has_weixin
	 * @param community_name
	 * @param listing_type
	 * @param rent_type
	 * @param building_name
	 * @param room_no
	 * @param dept_uid
	 * @param position_uid
	 * @param page_index
	 * @param page_size
	 * @return
	 */
	@RequestMapping("app_list")
	@ResponseBody
	public Result list4app(@RequestParam String uid,@RequestParam(required=false) String accept_service,@RequestParam(required=false) String has_weixin,@RequestParam(required=false) String community_name,
			@RequestParam(required=false) Integer listing_type,@RequestParam(required=false) String listing_status,@RequestParam(required=false) String rent_type,@RequestParam(required=false) String building_name,@RequestParam(required=false) String room_no,
			@RequestParam(required=false) String dept_uid,@RequestParam(required=false) String position_uid,@RequestParam Integer page_index,@RequestParam Integer page_size) {
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_community_name", community_name);
			inParams.put("i_listing_type", listing_type);
			inParams.put("i_listing_status", listing_status);
			inParams.put("i_rent_type", rent_type);
			inParams.put("i_building_name", building_name);
			inParams.put("i_room_no", room_no);
			inParams.put("i_has_weixin", has_weixin);
			inParams.put("i_accept_service", accept_service);
			inParams.put("i_dept_uid", dept_uid);
			inParams.put("i_position_uid", position_uid);
			inParams.put("i_page_index", page_index);
			inParams.put("i_page_size", page_size);
			Map<String, Object> rs =  this.storedProcedureTemplate.call("p_get_houses_4app", inParams);
			List<Object> result = new ArrayList<Object>();
			result.add(rs.get("houseList"));
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
	 * 异步获取分页信息
	 * @param uid
	 * @param accept_service
	 * @param has_weixin
	 * @param community_name
	 * @param listing_type
	 * @param listing_status
	 * @param rent_type
	 * @param building_name
	 * @param room_no
	 * @param dept_uid
	 * @param position_uid
	 * @param page_index
	 * @param page_size
	 * @return
	 */
	@RequestMapping("async_pageinfo")
	@ResponseBody
	public Result pageinfo(@RequestParam String uid,
			@RequestParam(required=false) String accept_service,
			@RequestParam(required=false) String has_weixin,
			@RequestParam(required=false) String community_name,
			@RequestParam(required=false) Integer listing_type,
			@RequestParam(required=false) String listing_status,
			@RequestParam(required=false) Integer rent_type,
			@RequestParam(required=false) String building_name,
			@RequestParam(required=false) String room_no,
			@RequestParam(required=false) Float rent_price_lowest,
			@RequestParam(required=false) Float rent_price_highest,
			@RequestParam(required=false) Float sale_price_lowest,
			@RequestParam(required=false) Float sale_price_highest,
			@RequestParam(required=false) Integer floor_lowest,
			@RequestParam(required=false) Integer floor_highest,
			@RequestParam(required=false) Float acreage_min,
			@RequestParam(required=false) Float acreage_max,
			@RequestParam(required=false) Integer house_layout,
			@RequestParam(required=false) String direction,
			@DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam(required=false) Date entrust_date_start,
			@DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam(required=false) Date entrust_date_end,
			@DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam(required=false) Date start_time_start,
			@DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam(required=false) Date start_time_end,
			@RequestParam(required=false) String city,
			@RequestParam(required=false) String district,
			@RequestParam(required=false) String area,
			@RequestParam(required=false) String region_uid,
			@RequestParam(required=false) String dept_uid,
			@RequestParam(required=false) String position_uid,
			@RequestParam(required=false) String tags,
			@RequestParam(required=false) Integer query_flag,
			@RequestParam(required=false) Integer page_size){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_community_name", community_name);
			inParams.put("i_listing_type", listing_type);
			inParams.put("i_listing_status", listing_status);
			inParams.put("i_rent_type", rent_type);
			inParams.put("i_building_name", building_name);
			inParams.put("i_room_no", room_no);
			inParams.put("i_has_weixin", has_weixin);
			inParams.put("i_accept_service", accept_service);
			
			inParams.put("i_rent_price_lowest", rent_price_lowest);
			inParams.put("i_rent_price_highest", rent_price_highest);
			inParams.put("i_sale_price_lowest", sale_price_lowest);
			inParams.put("i_sale_price_highest", sale_price_highest);
			inParams.put("i_floor_lowest", floor_lowest);
			inParams.put("i_floor_highest", floor_highest);
			inParams.put("i_acreage_min", acreage_min);
			inParams.put("i_acreage_max", acreage_max);
			inParams.put("i_house_layout", house_layout);
			inParams.put("i_direction", direction);
			inParams.put("i_entrust_date_start", entrust_date_start);
			inParams.put("i_entrust_date_end", entrust_date_end);
			inParams.put("i_start_time_start", start_time_start);
			inParams.put("i_start_time_end", start_time_end);
			inParams.put("i_city", city);
			inParams.put("i_district", district);
			inParams.put("i_area", area);
			inParams.put("i_region_uid", region_uid);
			inParams.put("i_dept_uid", dept_uid);
			inParams.put("i_position_uid", position_uid);
			inParams.put("i_query_flag", query_flag);
			inParams.put("i_page_size", page_size);
			inParams.put("i_tags", tags);
			Map<String, Object> rs =  this.storedProcedureTemplate.call("p_get_houses_pageinfo", inParams);
			List<Object> result = new ArrayList<Object>();
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
	 * 挂牌
	 * @param uid
	 * @param house_uid
	 * @param type
	 * @param fit
	 * @param mating
	 * @param gear
	 * @param appliance
	 * @param reside
	 * @param entrusted_date
	 * @param delive_date
	 * @param source
	 * @param status
	 * @param usage
	 * @param launch_time
	 * @param tags
	 * @param certificate
	 * @param comment
	 * @param rent_type
	 * @param rent_price
	 * @param sale_price
	 * @return
	 */
	@RequestMapping("listing")
	@ResponseBody
	public Result listing(@RequestParam String uid,
			@RequestParam String house_uid,
			@RequestParam Integer type,
			@RequestParam String fit,
			@RequestParam String mating,
			@RequestParam String gear,
			@RequestParam String appliance,
			@RequestParam String reside,
			@DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam Date entrusted_date,
			@DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam Date delive_date,
			@RequestParam String source,
			@RequestParam String status,
			@RequestParam String usage,
			@RequestParam String launch_time,
			@RequestParam String tags,
			@RequestParam String certificate,
			@RequestParam String comment,
			@RequestParam(required=false) Integer rent_type,
			@RequestParam(required=false) Float rent_price,
			@RequestParam(required=false) Float sale_price
			) {
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_house_uid", house_uid);
			inParams.put("i_type", type);
			inParams.put("i_rent_type", rent_type);
			inParams.put("i_rent_price", rent_price);
			inParams.put("i_sale_price", sale_price);
			inParams.put("i_fit", fit);
			inParams.put("i_mating", mating);
			inParams.put("i_gear", gear);
			inParams.put("i_appliance", appliance);
			inParams.put("i_reside", reside);
			inParams.put("i_entrusted_date", entrusted_date);
			inParams.put("i_delive_date", delive_date);
			inParams.put("i_source", source);
			inParams.put("i_status", status);
			inParams.put("i_usage", usage);
			inParams.put("i_launch_time", launch_time);
			inParams.put("i_tags", tags);
			inParams.put("i_certificate", certificate);
			inParams.put("i_comment", comment);
			this.storedProcedureTemplate.call("p_add_house_listing", inParams);
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
	 * 业主信息(左部分)
	 * @param uid
	 * @param house_uid
	 * @return
	 */
	@RequestMapping("get")
	@ResponseBody
	public Result get(@RequestParam String uid,@RequestParam String house_uid){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_house_uid", house_uid);
			Map<String, Object> rs = this.storedProcedureTemplate.call("p_get_house", inParams);
			List<Object> result = new ArrayList<Object>();
			result.add(rs.get("house"));
			result.add(rs.get("img_url"));
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
	 * 业主信息(右部分)since1.5
	 * @param uid
	 * @param house_uid
	 * @return
	 */
	@RequestMapping("get_contact")
	@ResponseBody
	public Result getcontact(@RequestParam String uid,@RequestParam String house_uid){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_house_uid", house_uid);
			Map<String, Object> rs = this.storedProcedureTemplate.call("p_get_houseowner_phone", inParams);
			List<Map<String,Object>> phones = (List<Map<String, Object>>) rs.get("phones");
			int index=1;
			for(Map<String,Object> contact : phones){
				String contact_phone = (String)contact.get("contact_phone");
				contact.put("contact_secrectphone",contact_phone);
				if((Boolean)contact.get("v_perm_see")){
					String original_phone = AESUtil.aesDecrypt(contact_phone, Config.getPropertyAsString("aes_salt_key"));
					contact.put("contact_phone", original_phone);
				}else{
					String original_phone = AESUtil.aesDecrypt(contact_phone, Config.getPropertyAsString("aes_salt_key"));
					String mask_phone = original_phone.substring(0, 3)+"xxxxx"+original_phone.substring(original_phone.length()-3);
					contact.put("contact_phone", mask_phone);
				}
				if(contact.get("remark") == null){
					String remark = "电话号码"+index;
					contact.put("remark", remark);
					index+=1;
				}
			}
			return new Result(phones);
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	
	/**
	 * update业主信息
	 * @param uid
	 * @param house_uid
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public Result update(@RequestParam String uid,
			@RequestParam String house_uid,
			@RequestParam(required=false) String name,
			@RequestParam(required=false) String phone,
			@RequestParam(required=false) String weixin,
			@RequestParam(required=false) Integer accept_service,
			@RequestParam(required=false) Integer bedroom,
			@RequestParam(required=false) Integer livingroom,
			@RequestParam(required=false) Integer washroom,
			@RequestParam(required=false) Integer balcony,
			@RequestParam(required=false) String direction,
			@RequestParam(required=false) Float acreage,
			@RequestParam(required=false) Integer type,
			@RequestParam(required=false) Integer rent_type,
			@RequestParam(required=false) Float rent_price,
			@RequestParam(required=false) Float sale_price,
			@RequestParam(required=false) String fit,
			@RequestParam(required=false) String mating,
			@RequestParam(required=false) String gear,
			@RequestParam(required=false) String appliance,
			@RequestParam(required=false) String reside,
			@DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam(required=false) Date entrusted_date,
			@DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam(required=false) Date delive_date,
			@RequestParam(required=false) String source,
			@RequestParam(required=false) String status,
			@RequestParam(required=false) String usage,
			@RequestParam(required=false) String launch_time,
			@RequestParam(required=false) String tags,
			@RequestParam(required=false) String certificate,
			@RequestParam(required=false) String comment,
			@RequestParam(required=false) String building_uid,
			@RequestParam(required=false) String unit,
			@RequestParam(required=false) String floor,
			@RequestParam(required=false) String room){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_house_uid", house_uid);
			inParams.put("i_name", name);
			inParams.put("i_phone", phone);
			inParams.put("i_weixin", weixin);
			inParams.put("i_accept_service", accept_service);
			inParams.put("i_bedroom", bedroom);
			inParams.put("i_livingroom", livingroom);
			inParams.put("i_washroom", washroom);
			inParams.put("i_balcony", balcony);
			inParams.put("i_direction", direction);
			inParams.put("i_acreage", acreage);
			inParams.put("i_rent_type", rent_type);
			inParams.put("i_rent_price", rent_price);
			inParams.put("i_sale_price", sale_price);
			inParams.put("i_fit", fit);
			inParams.put("i_mating", mating);
			inParams.put("i_gear", gear);
			inParams.put("i_appliance", appliance);
			inParams.put("i_reside", reside);
			inParams.put("i_entrusted_date", entrusted_date);
			inParams.put("i_delive_date", delive_date);
			inParams.put("i_source", source);
			inParams.put("i_status", status);
			inParams.put("i_usage", usage);
			inParams.put("i_launch_time", launch_time);
			inParams.put("i_tags", tags);
			inParams.put("i_certificate", certificate);
			inParams.put("i_comment", comment);
			inParams.put("i_building_uid", building_uid);
			inParams.put("i_unit", unit);
			inParams.put("i_floor", floor);
			inParams.put("i_room", room);
			inParams.put("i_type", type);
			this.storedProcedureTemplate.call("p_update_house", inParams);
			return new Result(Result.RESULT_CODE_SUCCESS);
		} catch (ProcedureException e) {
			logger.error(e.getMessage(), e.getCause());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	/**
	 * 下架
	 * @param uid
	 * @param house_uid
	 * @param reason
	 * @return
	 */
	@RequestMapping("close")
	@ResponseBody
	public Result close(@RequestParam String uid,@RequestParam String house_uid,@RequestParam String reason){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_house_uid", house_uid);
			inParams.put("i_reason", reason);
			this.storedProcedureTemplate.call("p_close_house_listing", inParams);
			return new Result(Result.RESULT_CODE_SUCCESS);
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	public static void main(String[] args) {
		DateUtil.parser("0000-00-00", "yyyy-MM-dd");
	}
}
