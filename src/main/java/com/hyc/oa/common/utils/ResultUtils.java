package com.hyc.oa.common.utils;

import java.util.HashMap;
import java.util.Map;

public class ResultUtils {
	
	public static Map<String, Object> success() {
    	return result(200,"success","");
	}
	
	public static Map<String, Object> success(Object obj) {
		return result( 200 , "success" , obj);
	}

	public static Map<String, Object> success(String message,Object obj) {
		return result( 200 , message , obj);
	}
	
	public static Map<String, Object> error() {
		return result(500,"error","");
	}
	
	public static Map<String, Object> error(Object obj) {
		return result( 500 , "error" , obj);
	}
	
	public static Map<String, Object> error(String message) {
		return result( 500 , message , "");
	}

	public static Map<String, Object> error(String message,Object obj) {
		return result( 500 , message , obj);
	}
	public static Map<String, Object> result(int status,String message,Object obj) {
		Map<String, Object> map =new HashMap<>();
		map.put("status", status);
		map.put("message", message);
		map.put("date", obj);
		return map;
	}

}
