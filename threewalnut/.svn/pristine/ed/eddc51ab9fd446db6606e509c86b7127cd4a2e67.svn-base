package mhao.threewalnut.controller;

import java.io.File;
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
import mhao.threewalnut.util.MD5Util;
import mhao4j.util.SpConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 处理密号平台回执
 * @author lami corp.
 *
 */
@Controller
public class MhaoNotifyController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(MhaoNotifyController.class);
	
	@RequestMapping("/notifyCall")
	@ResponseBody
	public Result notifyCall(@RequestParam String id,@RequestParam String seqId,@RequestParam Long timestamp,
			@RequestParam String virtualMobile,@RequestParam String from,@RequestParam String to,@RequestParam String dm,
			@RequestParam Long callStart,@RequestParam Long callAnswer,@RequestParam Long callEnd,@RequestParam Long mid,@RequestParam String fmBlong,
			@RequestParam String tmBlong,@RequestParam Integer call,@RequestParam Integer status,@RequestParam String sign){
		try {
			logger.info("话单通知处理开始...");
			String mhao_key = SpConfig.getValue("key");
			StringBuilder sb = new StringBuilder();
			sb.append(mhao_key).append(id).append(seqId).append(timestamp).append(virtualMobile).append(from).append(to).append(status);
			logger.info("key param:{}",sb.toString());
			String _sign = MD5Util.MD5(sb.toString()).toLowerCase();
			logger.info("sign from mhao api:{}",sign);
			logger.info("sign:{}",_sign);
			if(!_sign.equalsIgnoreCase(sign)){
				return new Result(Result.RESULT_CODE_FAILE,Result.MSG_SIGN_ERROR);
			}
			if(status!=0){
				logger.info("通话没有正常接听");
//				return new Result(Result.RESULT_CODE_FAILE,"处理成功");
			}
			if(call==1){
				from = AESUtil.aesEncrypt(from, Config.getPropertyAsString("aes_salt_key"));
				logger.info("encrypt from number:{}",from);
			}else{
				to = AESUtil.aesEncrypt(to, Config.getPropertyAsString("aes_salt_key"));
				logger.info("encrypt to number:{}",to);
			}
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_virtualMoblie", virtualMobile);
			inParams.put("i_from", from);
			inParams.put("i_to", to);
			inParams.put("i_dm", dm);
			inParams.put("i_callStart", callStart == null ? null : new Date(callStart));
			inParams.put("i_callAnswer", callAnswer == null ? null : new Date(callAnswer));
			inParams.put("i_callEnd", callEnd == null ? null : new Date(callEnd));
			inParams.put("i_mid", mid);
			inParams.put("i_fmBlong", fmBlong);
			inParams.put("i_tmBlong", tmBlong);
			inParams.put("i_call", call);
			inParams.put("i_status", status);
			this.storedProcedureTemplate.call("p_add_phone_call", inParams);
			logger.info("话单通知处理完成.seqId:{},fm:{},to:{},dm:{},mid:{}",new Object[]{seqId,from,to,dm,mid});
			return new Result(Result.RESULT_CODE_SUCCESS);
		} catch(AESException e){
			e.printStackTrace();
			logger.error(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE);
		} catch (ProcedureException e) {
			logger.error(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE);
		} catch (Exception e){
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	@RequestMapping("/notifyMtRece")
	@ResponseBody
	public Result notifyMtRece(){
		return null;
	}
	
	@RequestMapping("/notifyMo")
	@ResponseBody
	public Result notifyMo(@RequestParam String id,@RequestParam Long timestamp,@RequestParam String virtualMobile,
			@RequestParam String from,@RequestParam String text,@RequestParam String to,@RequestParam String dm,
			@RequestParam Integer call,@RequestParam Integer msgId,@RequestParam String sign){
		return null;
	}
	
	@RequestMapping("/notifyMediaStreamUrl")
	@ResponseBody
	public Result notifyMediaStreamUrl(@RequestParam String id,@RequestParam Long timestamp,@RequestParam String readUrl,
			@RequestParam Long mid,@RequestParam String tag,@RequestParam String sign){
		try {
			logger.debug("通话录音通知处理开始.");
			String mhao_key = SpConfig.getValue("key");
			StringBuilder sb = new StringBuilder();
			sb.append(mhao_key).append(id).append(timestamp).append(readUrl).append(mid);
			String _sign = MD5Util.MD5(sb.toString()).toLowerCase();
			if(!_sign.equalsIgnoreCase(sign)){
				return new Result(Result.RESULT_CODE_FAILE,Result.MSG_SIGN_ERROR);
			}
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_readUrl", readUrl);
			inParams.put("i_mid", mid);
			inParams.put("i_tag", tag);
			this.storedProcedureTemplate.call("p_update_call_record_url", inParams);
			logger.info("通话录音通知处理完成.mid:{},readUrl:{}",mid,readUrl);
			return new Result(Result.RESULT_CODE_SUCCESS);
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	@RequestMapping("/notifySecretRelationshipUrl")
	@ResponseBody
	public Result notifySecretRelationshipUrl(@RequestParam String id,@RequestParam Long timestamp,@RequestParam String vm,
			@RequestParam String tm,@RequestParam String dm,@RequestParam String fm,@RequestParam Integer type,@RequestParam Integer main,
			@RequestParam String sign){
		return null;
	}
	
	@RequestMapping("/updateCallRecordStatus")
	@ResponseBody
	public Result updateCallRecordStatus(@RequestParam String call_uid){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", call_uid);
			this.storedProcedureTemplate.call("p_update_call_record_status", inParams);
			return new Result(Result.RESULT_CODE_SUCCESS);
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("getCallRecordUrl")
	@ResponseBody
	public Result getCallRecordUrl(){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			Map<String, Object> rs = this.storedProcedureTemplate.call("p_get_call_record_url", inParams);
			List<Map<String,Object>> records = (List<Map<String, Object>>) rs.get("records");
			List<Map<String, Object>> new_records = new ArrayList<Map<String,Object>>();
			for(Map<String,Object> record : records){
				Map<String, Object> new_record = new HashMap<String, Object>();
				new_record.put("host", "101.95.49.63");
				String tag = (String) record.get("tag");
				Integer mid = (Integer) record.get("mid");
				String timestamp = System.currentTimeMillis()+"";
				StringBuilder sb = new StringBuilder();
				sb.append(SpConfig.getValue("key")).append(SpConfig.getValue("id")).append(timestamp).append(mid);
				String sign = MD5Util.MD5(sb.toString()).toLowerCase();
				String path = "/media/getMediaFile.do?id="+SpConfig.getValue("id")+"&timestamp="+timestamp+"&mid="+mid+"&tag="+tag+"&sign="+sign;
				new_record.put("path", path);
				new_record.put("virtualMoblie", record.get("virtualMoblie"));
				new_record.put("callDate", record.get("callDate"));
				new_record.put("callTime", record.get("callTime"));
				new_record.put("call_uid", record.get("uid"));
				new_records.add(new_record);
			}
			return new Result(new_records);
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	
	public static void main(String[] args) {
		File test = new File("D://record//test1//test2");
		test.mkdirs();
	}
	
}
