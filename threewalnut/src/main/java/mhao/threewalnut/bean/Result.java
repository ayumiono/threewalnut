package mhao.threewalnut.bean;


/**
 * @Title: Result.java<br>
 * @package: mhao.threewalnut.bean<br>
 * @Description: 返回Bean对象<br>
 * @author gbwl<br>
 * @date 2016年6月24日 上午11:22:13<br>
 */
public class Result {

	private Integer			code;
	private String			msg;
	private Object	result;
	
	/** 成功 */
	public static final int RESULT_CODE_SUCCESS	= 0;
	public static final int RESULT_CODE_FAILE	= 10001;
	
	public static final String MSG_SIGN_ERROR = "签名错误";
	public static final String MSG_SUCCESS = "操作成功";
	public static final String MSG_FAILE = "操作失败";
	
	public Result(Integer code,String msg){
		this.code = code;
		this.msg = msg;
	}
	public Result(Integer code){
		this.code = code;
		if(code == RESULT_CODE_FAILE){
			this.msg = MSG_FAILE;
		}
		if(code == RESULT_CODE_SUCCESS){
			this.msg = MSG_SUCCESS;
		}
	}
	public Result(Integer code, String msg, Object result) {
		this.code = code;
		this.msg = msg;
		this.result = result;
	}
	public Result(Object result){
		this.code = RESULT_CODE_SUCCESS;
		this.msg = MSG_SUCCESS;
		this.result = result;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "Result [code=" + code + ", msg=" + msg + ", result=" + result + "]";
	}
}
