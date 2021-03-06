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
 * (关联HouseController)
 * @author lami corp.
 *
 */
@Controller
@RequestMapping("/merchant/houseowner")
public class HouseownerController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(HouseownerController.class);
	
	/**
	 * 添加业主
	 * @param uid
	 * @param name
	 * @param phone
	 * @param weixin
	 * @param accept_service
	 * @param building_uid
	 * @param unit
	 * @param floor
	 * @param room
	 * @param bedroom
	 * @param livingroom
	 * @param washroom
	 * @param balcony
	 * @param direction
	 * @param acreage
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public Result add(@RequestParam String uid,
			@RequestParam String name,
			@RequestParam String phone,
			@RequestParam(required=false) String weixin,
			@RequestParam Integer accept_service,
			@RequestParam String building_uid,
			@RequestParam String floor,
			@RequestParam String room,
			@RequestParam(required=false) String unit,
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
			@RequestParam(required=false) String comment){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_name", name);
			inParams.put("i_phone", AESUtil.aesEncrypt(phone,Config.getPropertyAsString("aes_salt_key")));
			inParams.put("i_weixin", weixin);
			inParams.put("i_accept_service", accept_service);
			inParams.put("i_building_uid", building_uid);
			inParams.put("i_floor", floor);
			inParams.put("i_room", room);
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
			Map<String, Object> rs = this.storedProcedureTemplate.call("p_add_houseowner", inParams);
			return new Result(rs.get("house"));
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
	 * 添加电话
	 * @param uid
	 * @param house_uid
	 * @param phone
	 * @return
	 */
	@RequestMapping("addphone")
	@ResponseBody
	public Result addphone(@RequestParam String uid,@RequestParam String house_uid,@RequestParam String phone,@RequestParam(required=false) String remark){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_house_uid", house_uid);
			inParams.put("i_phone", AESUtil.aesEncrypt(phone,Config.getPropertyAsString("aes_salt_key")));
			inParams.put("i_remark", remark);
			this.storedProcedureTemplate.call("p_add_houseowner_phone", inParams);
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
			@RequestParam String house_uid,
			@RequestParam String phone,
			@RequestParam(required=false) String remark,
			@RequestParam(required=false) Integer delete,
			@RequestParam(required=false) Integer up_down){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_house_uid", house_uid);
			inParams.put("i_phone", phone);
			inParams.put("i_remark", remark);
			inParams.put("i_delete", delete);
			inParams.put("i_up_down", up_down);
			this.storedProcedureTemplate.call("p_update_houseowner_phone", inParams);
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
	 * 业主信息页面：录入人信息，最后跟进人信息，最后修改人信息
	 */
	@RequestMapping(value="/get_time_node")
	@ResponseBody
	public Result getTimeNode(@RequestParam String uid, @RequestParam String house_uid) {
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_house_uid", house_uid);
			Map<String, Object> rs = this.storedProcedureTemplate.call("p_get_houseowner_timenode", inParams);
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
	
	public static void main(String[] args) {
//		System.out.println(Double.valueOf((String) ""));
		System.out.println(Double.valueOf((String) null));
//		System.out.println(new java.math.BigDecimal((String) ""));
//		System.out.println(new java.math.BigDecimal((String) null));
		System.out.println(new java.math.BigDecimal((String) "2.45"));
	}
}
