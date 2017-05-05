package mhao.threewalnut.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.object.StoredProcedure;

public class StoredProcedureTemplate {
	
	private static final Logger logger = LoggerFactory.getLogger(StoredProcedureTemplate.class);
	
	Map<String, StoredProcedure> procedures = new HashMap<String,StoredProcedure>();
	
	public void setProcedures(Map<String,StoredProcedure> procedures){
		this.procedures = procedures;
	}
	
	public Map<String,Object> call(String procedureName,Map<String,Object> inParams) throws ProcedureException,Exception{
		try {
			for(Entry<String, ?> entry : inParams.entrySet()){
				Object _value = transactSQLInjection(entry.getValue());
				inParams.put(entry.getKey(), _value);
			}
			logger.info("procedure sql:{}	params:{}",procedureName,inParams);
			return this.procedures.get(procedureName).execute(inParams);
		} catch (Throwable e) {
			if(e.getCause() instanceof SQLException){
				SQLException tmp = (SQLException)e.getCause();
				if(tmp.getSQLState().equals("10001")){
					throw new ProcedureException(tmp.getMessage(),tmp);
				}else{
					throw new Exception(tmp.getMessage(),tmp);
				}
			}else{
				throw new Exception(e.getMessage(),e);
			}
		}
	}
	
	private static Object transactSQLInjection(Object str) {
		if(str!=null){
			if(str instanceof java.lang.String){
				if(str.toString().toLowerCase().indexOf("script")>0){
					Pattern p = Pattern.compile("script", Pattern.CASE_INSENSITIVE);
					Matcher m = p.matcher(str.toString());
					str = m.replaceAll(" ");
				}
				return str.toString().replaceAll("([';])+"," ");
			}			
		}
		return str;
	}
	
	/**
	 * StoredProcedure为抽象类，需要定义subClass
	 * @author lami corp.
	 */
	public static class MyStoredProcedure extends StoredProcedure{
		
		@Override
		public void onCompileInternal(){
			logger.info(getSql()+"初始化完成.");
		}
	}
}
