package mhao.threewalnut.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mhao.threewalnut.bean.Config;
import mhao.threewalnut.bean.Result;
import mhao.threewalnut.util.AESUtil;
import mhao.threewalnut.util.AESUtil.AESException;
import mhao4j.DynamicMobile;
import mhao4j.model.MhaoResult;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * app端接口
 * @author lami corp.
 *
 */
@Controller
@RequestMapping("/phone")
public class PhoneController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(PhoneController.class);
	
	/**
	 * @param uid
	 * @param house_uid
	 * @return
	 * modify in 1.5存储过程更改为p_get_houseowner_phone_v2
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("houseowner")
	@ResponseBody
	public Map<String,Object> houseowner(@RequestParam String uid,@RequestParam String house_uid){
		Map<String,Object> rs = new HashMap<String, Object>();;
		try {
			Map<String,Object> inParams = new HashMap<String,Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_house_uid", house_uid);
			logger.info("get phone of house_uid:{}",house_uid);
			rs = this.storedProcedureTemplate.call("p_get_houseowner_phone", inParams);
			List<Map<String, Object>> phones = (List<Map<String, Object>>) rs.get("phones");
			List<Map<String,Object>> tmp = new ArrayList<Map<String,Object>>();
			if(phones.size()==0){
				logger.info("当前业主{}没有联系电话",house_uid);
				rs.put("code", 10001);
				rs.put("msg", "当前业主没有联系电话");
				return rs;
			}
			int display_count=0;
			for(Map<String, Object> phone : phones){
				if(display_count==3){
					break;
				}
				String contact_phone = (String) phone.get("contact_phone");
				int callTimes = 0;
				if(phone.get("callTimes")!=null){
					callTimes=(Integer)phone.get("callTimes");
				};
				int answerTimes = 0;
				if(phone.get("answerTimes")!=null){
					answerTimes= (Integer)phone.get("answerTimes");
				}
				String remark = "电话号码";
				if(phone.get("remark")!=null){
					remark = (String) phone.get("remark");
				}
				logger.info("contact_phone:{}",contact_phone);
				String original_phone = AESUtil.aesDecrypt(contact_phone, Config.getPropertyAsString("aes_salt_key"));
				String mask_phone = original_phone.substring(0, 3)+"xxxxx"+original_phone.substring(original_phone.length()-3);
				phone.clear();
				phone.put("encrypt_phone", contact_phone);
				phone.put("call_times", callTimes);
				phone.put("answer_times", answerTimes);
				phone.put("mask_phone", mask_phone);
				phone.put("remark", StringUtils.rightPad(remark, 5, ' '));
				tmp.add(phone);
				display_count+=1;
			}
			rs.put("phones",tmp);
			rs.put("code", 0);
			return rs;
		} catch(AESException e){
			e.printStackTrace();
			logger.error(e.getMessage());
			rs.put("code", 10001);
			rs.put("msg", e.getMessage());
			rs.put("phones", new ArrayList<Map<String,Object>>());
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			rs.put("code", 10001);
			rs.put("msg", e.getMessage());
			rs.put("phones", new ArrayList<Map<String,Object>>());
			return rs;
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("customer")
	@ResponseBody
	public Map<String,Object> customer(@RequestParam String uid,@RequestParam String customer_uid){
		Map<String,Object> rs = new HashMap<String, Object>();;
		try {
			Map<String,Object> inParams = new HashMap<String,Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_customer_uid", customer_uid);
			logger.info("get phone of customer_uid:{}",customer_uid);
			rs = this.storedProcedureTemplate.call("p_get_customer_phone", inParams);
			List<Map<String, Object>> phones = (List<Map<String, Object>>) rs.get("phones");
			List<Map<String,Object>> tmp = new ArrayList<Map<String,Object>>();
			if(phones.size()==0){
				logger.info("当前客户{}没有联系电话",customer_uid);
				rs.put("code", 10001);
				rs.put("msg", "当前客户没有联系电话");
				return rs;
			}
			int display_count=0;
			for(Map<String, Object> phone : phones){
				if(display_count==3){
					break;
				}
				String contact_phone = (String) phone.get("contact_phone");
				int callTimes = 0;
				if(phone.get("callTimes")!=null){
					callTimes=(Integer)phone.get("callTimes");
				};
				int answerTimes = 0;
				if(phone.get("answerTimes")!=null){
					answerTimes= (Integer)phone.get("answerTimes");
				}
				String remark = "电话号码";
				if(phone.get("remark")!=null){
					remark = (String) phone.get("remark");
				}
				logger.info("contact_phone:{}",contact_phone);
				String original_phone = AESUtil.aesDecrypt(contact_phone, Config.getPropertyAsString("aes_salt_key"));
				String mask_phone = original_phone.substring(0, 3)+"xxxxx"+original_phone.substring(original_phone.length()-3);
				phone.clear();
				phone.put("encrypt_phone", contact_phone);
				phone.put("call_times", callTimes);
				phone.put("answer_times", answerTimes);
				phone.put("mask_phone", mask_phone);
				phone.put("remark", StringUtils.rightPad(remark, 5, ' '));
				tmp.add(phone);
				display_count+=1;
			}
			rs.put("phones",tmp);
			rs.put("code", 0);
			return rs;
		} catch(AESException e){
			e.printStackTrace();
			logger.error(e.getMessage());
			rs.put("code", 10001);
			rs.put("msg", e.getMessage());
			rs.put("phones", new ArrayList<Map<String,Object>>());
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			rs.put("code", 10001);
			rs.put("msg", e.getMessage());
			rs.put("phones", new ArrayList<Map<String,Object>>());
			return rs;
		}
	}
	
	/**
	 * FIXME 需要解码encrypt_phone
	 * 保密号呼叫
	 * @param uid
	 * @param encrypt_phone
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("bindDynamicMobile")
	@ResponseBody
	public Map<String,Object> bindDynamicMobile(@RequestParam String uid,@RequestParam String encrypt_phone){
		Map<String,Object> rs = new HashMap<String, Object>();
		try {
			Map<String,Object> inParams = new HashMap<String,Object>();
			inParams.put("i_uid", uid);
			Map<String, Object> rss = this.storedProcedureTemplate.call("p_get_user", inParams);
			List<Map<String, Object>> users = (List<Map<String, Object>>) rss.get("user");
			if(users.size()<=0){
				logger.error("找不到user");
			}
			Map<String, Object> user = users.get(0);
			String vm = (String) user.get("businessPhone");
			String tm = (String) user.get("phone");
			String fm = AESUtil.aesDecrypt(encrypt_phone, Config.getPropertyAsString("aes_salt_key"));
			logger.info("encrypt_phone:{}",encrypt_phone);
			logger.info("descrypt_phone:{}",fm);
			MhaoResult result = DynamicMobile.bindDynamicMobile(vm, tm, fm);
			try {
				if(result.getResult() == 0 || result.getResult() == -26){
					String dm = result.asJson().getString("value");
					logger.info("保密号生成成功。{} {}",uid,encrypt_phone+"_"+vm+"_"+dm+"_"+tm);
					rs.put("code", 0);
					rs.put("msg", Result.MSG_SUCCESS);
					rs.put("value", dm);
					return rs;
				}else{
					logger.error("保密号生成失败。{} {} {}",new Object[]{uid,encrypt_phone+"_"+vm+"_"+tm,result});
					rs.put("code", 10001);
					rs.put("msg", "失败");
					return rs;
				}
			} catch (Exception e) {
				logger.error(result.asString());
				rs.put("code", 10001);
				rs.put("msg", e.getMessage());
				return rs;
			}
		} catch(AESException e){
			e.printStackTrace();
			logger.error(e.getMessage());
			rs.put("code", 10001);
			rs.put("msg", e.getMessage());
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			rs = new HashMap<String, Object>();
			rs.put("code", 10001);
			rs.put("msg", e.getMessage());
			return rs;
		}
	}
}
