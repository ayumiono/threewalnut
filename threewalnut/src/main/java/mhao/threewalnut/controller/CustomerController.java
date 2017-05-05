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
import mhao.threewalnut.util.AESUtil.AESException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 客源开发-客户管理
 * @author lami corp.
 *
 */
@Controller
@RequestMapping("/merchant/customer")
public class CustomerController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	//@RequestMapping("list")
	@ResponseBody
	public Result list(@RequestParam String uid,
			@RequestParam(required=false) String name,
			@RequestParam(required=false) String employee_name,
			@RequestParam(required=false) String dept_name,
			@RequestParam(required=false) String type,
			@RequestParam(required=false) Integer listing_type, 
			@RequestParam(required=false) String status,
			@RequestParam Integer page_index, 
			@RequestParam Integer page_size,
			@RequestParam(required=false) Integer rent_type,
			@RequestParam(required=false) Integer private_common_flag){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_name", name);
			inParams.put("i_employee_name", employee_name);
			inParams.put("i_dept_name", dept_name);
			inParams.put("i_status", status);
			inParams.put("i_type", type);
			inParams.put("i_listing_type", listing_type);
			inParams.put("i_rent_type", rent_type);
			inParams.put("i_private_common_flag", private_common_flag);
			inParams.put("i_page_index", page_index);
			inParams.put("i_page_size", page_size);
			Map<String,Object> rs =  this.storedProcedureTemplate.call("p_get_customers", inParams);
			List<Object> result = new ArrayList<Object>();
			result.add(rs.get("customerList"));
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
	 * since 1.5
	 * @param uid
	 * @param customer_value
	 * @param community_value
	 * @param type
	 * @param listing_type
	 * @param status
	 * @param page_index
	 * @param page_size
	 * @param rent_type
	 * @param private_common_flag
	 * @param acreage_min
	 * @param acreage_max
	 * @param price_lowest
	 * @param price_highest
	 * @param house_layout
	 * @param entrust_date_start
	 * @param entrust_date_end
	 * @param city
	 * @param district
	 * @param area
	 * @param dept_uid
	 * @param position_uid
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public Result list(@RequestParam String uid,
			@RequestParam(required=false) String name,
			@RequestParam(required=false) String community_value,
			@RequestParam(required=false) String type,
			@RequestParam(required=false) Integer listing_type, 
			@RequestParam(required=false) String status,
			@RequestParam Integer page_index, 
			@RequestParam Integer page_size,
			@RequestParam(required=false) Integer rent_type,
			@RequestParam(required=false) Integer private_common_flag,
			@RequestParam(required=false) Float acreage_min,
			@RequestParam(required=false) Float acreage_max,
			@RequestParam(required=false) Float price_lowest,
			@RequestParam(required=false) Float price_highest,
			@RequestParam(required=false) Integer house_layout,
			@DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam(required=false) Date entrust_date_start,
			@DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam(required=false) Date entrust_date_end,
			@RequestParam(required=false) String city,
			@RequestParam(required=false) String district,
			@RequestParam(required=false) String region_uid,
			@RequestParam(required=false) String dept_uid,
			@RequestParam(required=false) String position_uid,@RequestParam(required=false) String order_field,@RequestParam(required=false) Integer order){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_name", name);
			inParams.put("i_community_value", community_value);
			inParams.put("i_status", status);
			inParams.put("i_type", type);
			inParams.put("i_listing_type", listing_type);
			inParams.put("i_rent_type", rent_type);
			inParams.put("i_private_common_flag", private_common_flag);
			inParams.put("i_acreage_min", acreage_min);
			inParams.put("i_acreage_max", acreage_max);
			inParams.put("i_price_lowest", price_lowest);
			inParams.put("i_price_highest", price_highest);
			inParams.put("i_house_layout", house_layout);
			inParams.put("i_entrust_date_start", entrust_date_start);
			inParams.put("i_entrust_date_end", entrust_date_end);
			inParams.put("i_city", city);
			inParams.put("i_district", district);
			inParams.put("i_region_uid", region_uid);
			inParams.put("i_dept_uid", dept_uid);
			inParams.put("i_position_uid", position_uid);
			inParams.put("i_order_field", order_field);
			inParams.put("i_order", order);
			inParams.put("i_page_index", page_index);
			inParams.put("i_page_size", page_size);
			Map<String,Object> rs =  this.storedProcedureTemplate.call("p_get_customers", inParams);
			List<Object> result = new ArrayList<Object>();
			result.add(rs.get("customerList"));
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
	 * 获取客户信息
	 * @param uid
	 * @param customer_uid
	 * @return
	 */
	@RequestMapping("get")
	@ResponseBody
	public Result get(@RequestParam String uid,@RequestParam String customer_uid){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_customer_uid", customer_uid);
			Map<String,Object> rs =  this.storedProcedureTemplate.call("p_get_customer", inParams);
			return new Result(rs.get("customer"));
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	
	/**
	 * 更改客源归属人
	 * @param uid
	 * @param customer_uid
	 * @return
	 */
	@RequestMapping("change_customer_belong")
	@ResponseBody
	public Result change_belong(@RequestParam String uid,@RequestParam String customer_uid, @RequestParam String position_uid){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_customer_uid", customer_uid);
			inParams.put("i_position_uid", position_uid);
			this.storedProcedureTemplate.call("p_change_customer_belong", inParams);
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
	 * 编辑客户信息
	 * @param uid
	 * @param customer_uid
	 * @param name
	 * @param phone
	 * @param bedroom
	 * @param livingroom
	 * @param washroom
	 * @param balcony
	 * @param acreage_min
	 * @param acreage_max
	 * @param price_min
	 * @param price_max
	 * @param city
	 * @param district
	 * @param address
	 * @param usage
	 * @param status
	 * @param source
	 * @param quality
	 * @param pay_type
	 * @param comment
	 * @param type
	 * @param rent_type
	 * @param entrusted_date
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public Result update(@RequestParam String uid,@RequestParam String customer_uid,
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
			@RequestParam(required=false) String comment,
			@RequestParam(required=false) String type,
			@RequestParam(required=false) Integer rent_type,
			@DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam(required=false) Date entrusted_date,
			@RequestParam(required=false) String listing_type){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_customer_uid", customer_uid);
			inParams.put("i_type", type);//
			inParams.put("i_rent_type", rent_type);
			inParams.put("i_name", name);
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
			inParams.put("i_listing_type", listing_type);
			Map<String,Object> rs =  this.storedProcedureTemplate.call("p_update_customer", inParams);
			return new Result(rs.get("customer"));
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}

	
	/**
	 * 添加客户
	 * @param uid
	 * @param listing_type
	 * @param name
	 * @param phone
	 * @param bedroom
	 * @param livingroom
	 * @param washroom
	 * @param balcony
	 * @param acreage_min
	 * @param acreage_max
	 * @param price_min
	 * @param price_max
	 * @param city
	 * @param district
	 * @param address
	 * @param usage
	 * @param status
	 * @param source
	 * @param quality
	 * @param pay_type
	 * @param comment
	 * @param rent_type
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public Result add(@RequestParam String uid,
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
			@RequestParam(required=false) String comment) {
		try {
			if(price_min == null || price_min.toString().equals("NaN")){
				price_min = null;
			}
			if(price_max == null || price_max.toString().equals("NaN")){
				price_max = null;
			}
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_type", type);
			inParams.put("i_listing_type", listing_type);
			inParams.put("i_rent_type", rent_type);
			inParams.put("i_name", name);
			inParams.put("i_phone", AESUtil.aesEncrypt(phone, Config.getPropertyAsString("aes_salt_key")));
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
			inParams.put("i_pay_type", pay_type);//
			inParams.put("i_comment", comment);
			this.storedProcedureTemplate.call("p_add_customer", inParams);
			return new Result(Result.RESULT_CODE_SUCCESS);
		} catch(AESException e){
			e.printStackTrace();
			logger.error(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE);
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	/**
	 * 添加客户电话
	 * @param uid
	 * @param customer_uid
	 * @param phone
	 * @return
	 */
	@RequestMapping("addphone")
	@ResponseBody
	public Result addphone(@RequestParam String uid,@RequestParam String customer_uid,@RequestParam String phone,@RequestParam(required=false) String remark) {
		try{
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_customer_uid", customer_uid);
			inParams.put("i_phone", AESUtil.aesEncrypt(phone, Config.getPropertyAsString("aes_salt_key")));
			inParams.put("i_remark", remark);
			this.storedProcedureTemplate.call("p_add_customer_phone", inParams);
			return new Result(Result.RESULT_CODE_SUCCESS);
		} catch(AESException e){
			e.printStackTrace();
			logger.error(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE);
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	/**
	 * 更新电话
	 * @param uid
	 * @param house_uid
	 * @param phone
	 * @return
	 */
	@RequestMapping("updatephone")
	@ResponseBody
	public Result updatephone(@RequestParam String uid,
			@RequestParam String customer_uid,
			@RequestParam String phone,
			@RequestParam(required=false) String remark,
			@RequestParam(required=false) Integer delete,
			@RequestParam(required=false) Integer up_down){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_customer_uid", customer_uid);
			inParams.put("i_phone", AESUtil.aesEncrypt(phone,Config.getPropertyAsString("aes_salt_key")));
			inParams.put("i_remark", remark);
			inParams.put("i_delete", delete);
			inParams.put("i_up_down", up_down);
			this.storedProcedureTemplate.call("p_update_customer_phone", inParams);
			return new Result(Result.RESULT_CODE_SUCCESS);
		} catch(AESException e){
			e.printStackTrace();
			logger.error(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE);
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	/**
	 * 客户电话since1.5
	 * @param uid
	 * @param customer_uid
	 * @return
	 */
	@RequestMapping("get_contact")
	@ResponseBody
	public Result getcontact(@RequestParam String uid,@RequestParam String customer_uid){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_customer_uid", customer_uid);
			Map<String, Object> rs = this.storedProcedureTemplate.call("p_get_customer_phone", inParams);
			List<Map<String,Object>> phones = (List<Map<String, Object>>) rs.get("phones");
			int index=1;
			for(Map<String,Object> contact : phones){
				String contact_phone = (String)contact.get("contact_phone");
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
	 * 客户信息页面：录入人信息，最后跟进人信息，最后修改人信息
	 */
	@RequestMapping(value="/get_time_node")
	@ResponseBody
	public Result getTimeNode(@RequestParam String uid, @RequestParam String customer_uid) {
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_customer_uid", customer_uid);
			Map<String, Object> rs = this.storedProcedureTemplate.call("p_get_customer_timenode", inParams);
			List<Object> list = new ArrayList<Object>();
			list.add(rs.get("entryMsg"));
			list.add(rs.get("followMsg"));
			list.add(rs.get("editMsg"));
			return new Result(list);
		} catch (ProcedureException e) {
			logger.error(e.getMessage(), e.getCause());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e.getCause());
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
}
