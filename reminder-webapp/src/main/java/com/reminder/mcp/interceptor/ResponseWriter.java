package com.reminder.mcp.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reminder.mcp.model.ResponseModel;
import com.reminder.util.log.JsonMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import static com.reminder.mcp.model.DispatchInterceptorConstant.DEFAULT_CHAR_SET;
import static com.reminder.mcp.model.DispatchInterceptorConstant.DEFAULT_CONTENT_TYPE;

public class ResponseWriter {

	
	public static boolean writeToResponse(Object obj, HttpServletResponse response) {
		
		ObjectMapper mapper = JsonMapping.getMapper();
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			String ret = "";
			if(obj.getClass().isEnum()){
				@SuppressWarnings("rawtypes")
				Enum e = (Enum) obj;
				Map<String, String> map = new HashMap<String, String>();
				map.put("name", e.name());
				map.put("code", String.valueOf(e.ordinal()));
				ret = mapper.writeValueAsString(map);
			}else{
				ret = mapper.writeValueAsString(obj);
			}
			//logger.info("response write : "+ret);
			byte[] bytes = ret.getBytes(Charset.forName("utf-8"));
			response.setContentLength(bytes.length);
			out.write(bytes);
			out.flush();
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					//e.printStackTrace();
				}
			}
		}
	}
	
	public static void writeErrResponse(HttpServletResponse response, int code, Object msg) {
		writeToResponse(new ResponseModel(code, msg), response);
	}

	public static void writeResponseHeader(HttpServletResponse response){
		response.setContentType(DEFAULT_CONTENT_TYPE);
		response.setCharacterEncoding(DEFAULT_CHAR_SET);
	}
	
}
