package mhao.threewalnut.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mhao.threewalnut.bean.Result;
import mhao.threewalnut.dao.ProcedureException;
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
 * 系统设置-员工管理
 * @author lami corp.
 *
 */
@Controller
@RequestMapping("/merchant/position")
public class PositionController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(PositionController.class);
	
	/**
	 * 职位列表
	 * @param uid
	 * @param dept_uid
	 * @param page_index
	 * @param page_size
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public Result list(@RequestParam String uid,@RequestParam String dept_uid,
			@RequestParam(required=false) Integer page_index,
			@RequestParam(required=false) Integer page_size,
			@RequestParam(required=false) Integer skip_pagination){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_dept_uid", dept_uid);
			inParams.put("i_page_index", page_index);
			inParams.put("i_page_size", page_size);
			inParams.put("i_skip_pagination", skip_pagination);
			Map<String, Object> rs = this.storedProcedureTemplate.call("p_get_positions", inParams);
			List<Object> result = new ArrayList<Object>();
			if(skip_pagination!=null){
				return new Result(rs.get("positions"));
			}else{
				result.add(rs.get("positions"));
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
	 * 部门下直属职位，不获取子部门职位
	 * @param uid
	 * @param dept_uid
	 * @param page_index
	 * @param page_size
	 * @param skip_pagination
	 * @return
	 */
	@RequestMapping("direct_positions")
	@ResponseBody
	public Result direct_positions(@RequestParam String uid,@RequestParam String dept_uid){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_dept_uid", dept_uid);
			Map<String, Object> rs = this.storedProcedureTemplate.call("p_get_direct_positions", inParams);
			return new Result(rs.get("positions"));
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	/**
	 * 职位列表
	 * @param uid
	 * @param dept_uid
	 * @param page_index
	 * @param page_size
	 * @return
	 */
	@RequestMapping("app_list")
	@ResponseBody
	public Result list_app(@RequestParam String uid,@RequestParam(required=false) String dept_uid,
			@RequestParam(required=false) Integer page_index,
			@RequestParam(required=false) Integer page_size,
			@RequestParam Integer skip_pagination){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_dept_uid", dept_uid);
			inParams.put("i_page_index", page_index);
			inParams.put("i_page_size", page_size);
			inParams.put("i_skip_pagination", skip_pagination);
			Map<String, Object> rs = this.storedProcedureTemplate.call("p_get_positions", inParams);
			List<Map<String,Object>> positions = (List<Map<String, Object>>) rs.get("positions");
			List<Map<String,Object>> dataWraper = new ArrayList<Map<String,Object>>();
			for(Map<String,Object> position : positions){
				Map<String,Object> p = new HashMap<String, Object>();
				p.put("text", position.get("name"));
				p.put("value", position.get("uid"));
				dataWraper.add(p);
			}
			return new Result(dataWraper);
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	/**
	 * 添加职位
	 * @param uid
	 * @param dept_uid
	 * @param business_phone
	 * @param role
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public Result add(@RequestParam String uid,@RequestParam String dept_uid, @RequestParam String business_phone,@RequestParam Integer role){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_dept_uid", dept_uid);
			inParams.put("i_businessPhone", business_phone);
			inParams.put("i_role", role);
			Map<String,Object> rs = this.storedProcedureTemplate.call("p_add_position", inParams);
			return new Result(rs.get("position"));
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	/**
	 * FIXME 密号由无到有，可以在已绑定员工的情况下直接添加
	 * 修改职位
	 * @param uid
	 * @param position_uid
	 * @param business_phone
	 * @param role
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("update")
	@ResponseBody
	public Result update(@RequestParam String uid,@RequestParam String position_uid, @RequestParam String business_phone,@RequestParam Integer role){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_position_uid", position_uid);
			inParams.put("i_businessPhone", business_phone);
			inParams.put("i_role", role);
			Map<String,Object> rs = this.storedProcedureTemplate.call("p_update_position", inParams);
			String user_uid = (String) rs.get("user_uid");
			if(user_uid != null){
				inParams.clear();
				inParams.put("i_uid", user_uid);
				Map<String, Object> rss = this.storedProcedureTemplate.call("p_get_user", inParams);
				Map<String, Object> user = ((List<Map<String, Object>>)rss.get("user")).get(0);
				String phone = (String) user.get("phone");
				String virtualMobile = (String) user.get("businessPhone");
				if(StringUtils.isNotBlank(business_phone) && StringUtils.isBlank(virtualMobile)){
					logger.info("绑定商务号时出错。数据异常，对应职位的商务号为空");
					//回滚数据库
					inParams.clear();
					inParams.put("i_uid", uid);
					inParams.put("i_position_uid", position_uid);
					inParams.put("i_businessPhone", null);
					inParams.put("i_role", role);
					this.storedProcedureTemplate.call("p_update_position", inParams);
					return new Result(Result.RESULT_CODE_FAILE,"修改失败,系统错误!");
				}
				boolean success = false;
				MhaoResult bind = BindMobile.bindMobile(user_uid, phone, virtualMobile, 0);//绑定商务号
				try {
					if(bind.getResult()==0){
						logger.info("商务号绑定成功，{}",bind);
						success = true;
						return new Result(Result.RESULT_CODE_SUCCESS);
					}else{
						return new Result(Result.RESULT_CODE_FAILE,"商务号绑定失败");
					}
				} catch (Exception e) {
					return new Result(Result.RESULT_CODE_FAILE,"商务号绑定失败");
				} finally{
					if(!success){
						logger.error("商务号绑定失败，{}",bind);
						//回滚数据库
						logger.info("商务号绑定失败，开始回滚数据库更新");
						inParams.clear();
						inParams.put("i_uid", uid);
						inParams.put("i_position_uid", position_uid);
						inParams.put("i_businessPhone", null);
						inParams.put("i_role", role);
						this.storedProcedureTemplate.call("p_update_position", inParams);
					}
				}
			}
			return new Result(rs.get("position"));
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
	/**
	 * 删除职位
	 * @param uid
	 * @param position_uid
	 * @return
	 */
	@RequestMapping("disable")
	@ResponseBody
	public Result disable(@RequestParam String uid,@RequestParam String position_uid){
		try {
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_position_uid", position_uid);
			Map<String,Object> rs = this.storedProcedureTemplate.call("p_disable_position", inParams);
			return new Result(rs.get("position"));
		} catch (ProcedureException e) {
			logger.info(e.getMessage());
			return new Result(Result.RESULT_CODE_FAILE,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Result.RESULT_CODE_FAILE);
		}
	}
	
}
