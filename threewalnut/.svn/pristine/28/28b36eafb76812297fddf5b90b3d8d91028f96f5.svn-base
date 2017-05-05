package mhao.threewalnut.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mhao.threewalnut.bean.Config;
import mhao.threewalnut.bean.Result;
import mhao.threewalnut.dao.ProcedureException;
import mhao.threewalnut.util.GsonUtil;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/merchant/picture")
public class PictureController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(PictureController.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/**
	 * 添加数据库记录
	 * @param uid
	 * @param class_name
	 * @param obj_uid
	 * @param file_name
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public Result add(@RequestParam String uid,@RequestParam String class_name,@RequestParam String obj_uid,@RequestParam String file_name){
		try {
			Map<String,Object> inParams = new HashMap<String,Object>();
			inParams.put("i_uid", uid);
			inParams.put("i_class_name", class_name);
			inParams.put("i_obj_uid", obj_uid);
			inParams.put("i_file_name", file_name);
			this.storedProcedureTemplate.call("p_add_picture", inParams);
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
	 * 图片上传
	 * @param request
	 * @param response
	 */
	@RequestMapping("upload")
	public void upload(HttpServletRequest request,HttpServletResponse response){
		String class_name = "";
		String obj_uid = "";
		try {
			if(ServletFileUpload.isMultipartContent(request)){
				DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setSizeThreshold( 1024 );
                ServletFileUpload upload = new ServletFileUpload( factory );
                upload.setHeaderEncoding( "UTF-8" );
                List<FileItem> items = upload.parseRequest( request );
                for(FileItem item : items){
                	if (item.isFormField()){
                		if(item.getFieldName().equals("obj_uid")){
                			obj_uid = item.getString();
                		}else if(item.getFieldName().equals("class_name")){
                			class_name = item.getString();
                		}
                	}else{
                		if(StringUtils.isNotBlank(obj_uid) && StringUtils.isNotBlank(class_name)){
                			if(class_name.equals("house_listing")){
                				List<Map<String, Object>> rs = jdbcTemplate.query("select * from house_listing where house_uid=? and endTime is null", new Object[]{obj_uid}, new ColumnMapRowMapper());
                				if(rs.size()<=0){
                					logger.error("上传图片时找不到挂牌信息");
                					response.setStatus(400);
                        			return;
                				}
                				obj_uid = (String) rs.get(0).get("uid");
                			}
                			String _dir = Config.getPropertyAsString("pic_dir");
                			_dir = request.getSession().getServletContext().getRealPath(_dir);
                			File child = new File(_dir+"/"+class_name+"/"+obj_uid);
                			child.mkdirs();
                			String filename = item.getName();
                			System.out.println();
                			String extension = filename.substring(filename.indexOf(".")+1);
                			String file_name = UUID.randomUUID().toString()+"."+extension;
                			File pic = new File(child,file_name);
                			item.write(pic);
                			response.setStatus(200);
                			response.setCharacterEncoding("utf-8");
                			response.setContentType("application/json");
                			Map<String,String> rs = new HashMap<String, String>();
                			rs.put("file_name", file_name);
                			response.getWriter().write(GsonUtil.toGson(rs));
                			response.getWriter().flush();
                			response.getWriter().close();
                			logger.info("图片上传成功{},{},{}",new Object[]{class_name,obj_uid,file_name});
                		}else{
                			response.setStatus(400);
                			return;
                		}
                	}
                }
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(400);
			return;
		}
	}
	
}
