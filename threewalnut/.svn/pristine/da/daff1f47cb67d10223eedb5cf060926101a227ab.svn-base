package mhao.threewalnut.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import mhao.threewalnut.bean.Result;
import mhao.threewalnut.dao.ProcedureException;
import mhao.threewalnut.util.DateUtil;
import mhao.threewalnut.util.MD5Util;
import mhao.threewalnut.util.TokenUtil;
import mhao4j.BindMobile;
import mhao4j.model.MhaoResult;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  该controller可能抽出到单独授权服务
 * @Title: LoginController.java<br>
 * @package: mhao.threewalnut.controller<br>
 * @Description: 登录并初始化数据<br>
 * @author gbwl<br>
 * @date 2016年6月24日 上午11:19:15<br>
 */
@Controller
public class LoginController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value="/getToken")
	@ResponseBody
	public Map<String,Object> getToken(HttpServletRequest request) {
		String phone = request.getParameter("username");
		String password = request.getParameter("password");
		String mid = request.getParameter("mid");
		Map<String,Object> inParams = new HashMap<String, Object>();
		inParams.put("i_phone", phone);
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			Map<String, Object> rs = storedProcedureTemplate.call("p_get_user_by_phone", inParams);
			String uid = (String) rs.get("uid");
			String _phone = (String) rs.get("phone");
			String _password = (String) rs.get("password");
			String businessPhone = (String) rs.get("businessPhone");
			if(!_password.equals(password)){
				result.put("code", Result.RESULT_CODE_FAILE);
				result.put("msg", "密码错误");
				return result;
			}
			String token = TokenUtil.generateToken(uid, businessPhone, _phone, mid, DateUtil.addDate(new Date(), 1));
			result.put("token",token);
			result.put("code", Result.RESULT_CODE_SUCCESS);
			result.put("msg", "操作成功");
			return result;
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			result.put("code", Result.RESULT_CODE_FAILE);
			result.put("msg", e.getMessage());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result.put("code", Result.RESULT_CODE_FAILE);
			result.put("msg", e.getMessage());
			return result;
		}
	}
	
	/**
	 * 绑定员工
	 * 先尝试绑定，再添加数据库记录
	 * @param uid
	 * @param position_uid
	 * @param name
	 * @param phone
	 * @return
	 */
	@RequestMapping("/user/bound")
	@ResponseBody
	public Result bound(@RequestParam String uid,@RequestParam String position_uid,@RequestParam String name,@RequestParam String phone){
		try {
			Map<String,Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_position_uid", position_uid);
			Map<String, Object> rs = this.storedProcedureTemplate.call("p_get_businessphone_from_position", inParams);
			String virtualMobile = (String) rs.get("i_business_phone");
			String license = UUID.randomUUID().toString();//生成license
			inParams.put("i_name", name);
			inParams.put("i_phone", phone);
			inParams.put("i_license", license);
			if(StringUtils.isNotBlank(virtualMobile)){
				//检查该商务号是否存在绑定
				MhaoResult check = BindMobile.searchBindMobile(virtualMobile);
				if(check.getResult()==0){
					//已经存在绑定
					return new Result(Result.RESULT_CODE_FAILE,"商务号已经被其他人使用");
				}
				logger.info("Mhao SDK searchBindMobile Result:{}",check);
				MhaoResult bind = BindMobile.bindMobile(license, phone, virtualMobile, 0);
				if(bind.getResult()==0){
					logger.info("商务号绑定成功,{}",bind);
				}else{
					logger.error("商务号绑定失败，{}",bind);
					return new Result(Result.RESULT_CODE_FAILE,"商务号绑定失败");
				}
			}
			boolean success = false;
			try {
				this.storedProcedureTemplate.call("p_add_user", inParams);
				success = true;
				logger.info("新增用户uid:"+license);
				return new Result(Result.RESULT_CODE_SUCCESS);
			}catch (ProcedureException e) {
				logger.info(e.getMessage());
				return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
				return new Result(Result.RESULT_CODE_FAILE);
			} finally {
				if(!success){
					//解绑商务号
					logger.info("数据库记录添加失败,开始回滚商务号绑定");
					MhaoResult rollback = BindMobile.unbindMobile(virtualMobile, phone);
					logger.info("Mhao SDK unbindMobile Result:{}",rollback);
					if(rollback.getResult()!=0){
						logger.error("商务号回滚失败,{}",rollback);
					}
				}
			}
		} catch(ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	/**
	 * 解绑员工
	 * @param uid
	 * @param position_uid
	 * @param name
	 * @param phone
	 * @return
	 */
	@RequestMapping("/user/unbound")
	@ResponseBody
	public Result unbound(@RequestParam String uid,@RequestParam String phone){
		try {
			Map<String,Object> inParams = new HashMap<String, Object>();
			String businessPhone = null;
			String disable_uid = "";
			inParams.put("i_phone", phone);
			try {
				Map<String, Object> rs = this.storedProcedureTemplate.call("p_get_user_by_phone", inParams);
				disable_uid = (String) rs.get("uid");
				businessPhone = (String) rs.get("businessPhone");
				inParams.clear();
				inParams.put("i_uid", uid);
				inParams.put("i_disable_uid", disable_uid);
			} catch (ProcedureException e) {
				logger.info(e.getMessage());
				return new Result(Result.RESULT_CODE_FAILE,"过期数据");
			}catch (Exception e) {
				e.printStackTrace();
				return new Result(Result.RESULT_CODE_FAILE,"过期数据");
			}
			if(StringUtils.isNotBlank(businessPhone)){
				MhaoResult mhaoResult = BindMobile.unbindMobile(businessPhone, phone);
				if(mhaoResult.getResult() == 0){
					logger.info("商务号解绑成功");
				}else{
					logger.error("商务号vm:{},tm:{}解绑失败,{}",new Object[]{businessPhone,phone,mhaoResult});
					return new Result(Result.RESULT_CODE_FAILE,"解绑职位失败(商务号解绑失败)");
				}
			}
			boolean success = false;
			try {
				this.storedProcedureTemplate.call("p_disable_user", inParams);
				success = true;
			}catch (ProcedureException e){
				logger.info(e.getMessage());
				return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
			}catch (Exception e) {
				e.printStackTrace();
				return new Result(Result.RESULT_CODE_FAILE);
			}finally{
				if(!success){
					logger.info("数据库记录更新失败,开始回滚商务号绑定");
					MhaoResult rollback = BindMobile.bindMobile(disable_uid, phone, businessPhone, 0);
					logger.info("Mhao SDK bindMobile Result:{}",rollback);
					if(rollback.getResult()!=0){
						logger.error("商务号回滚失败,{}",rollback);
					}
				}
			}
			return new Result(Result.RESULT_CODE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	/**
	 * 修改密码
	 * @param uid
	 * @param password
	 * @param old_password
	 * @return
	 */
	@RequestMapping("/user/changepassword")
	@ResponseBody
	public Result changepassword(@RequestParam String uid,@RequestParam String password,@RequestParam String old_password){
		try {
			Map<String,Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_old_password", old_password);
			String md5_password = MD5Util.MD5(password).toLowerCase();
			inParams.put("i_password", md5_password);
			this.storedProcedureTemplate.call("p_change_password", inParams);
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
	 * 修改密码
	 * @param uid
	 * @param password
	 * @param old_password
	 * @return
	 */
	@RequestMapping("/user/resetpassword")
	@ResponseBody
	public Result resetpassword(@RequestParam String uid,@RequestParam String phone){
		try {
			Map<String,Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_phone", phone);
			this.storedProcedureTemplate.call("p_reset_password", inParams);
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
