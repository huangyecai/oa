package com.hyc.oa.common.utils;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * 常量

* <p>Title: ConstantUtils</p>  

* <p>Description: </p>  

* @author hyc  

* @date 2018年12月5日
 */
public class ConstantUtils {
	public static final String JAVA_TYPE_STRING="String";
	public static final String JAVA_TYPE_INT="int";
	public static final String JAVA_TYPE_LONG="long";
	public static final String JAVA_TYPE_DOUBLE="double";
	public static final String JAVA_TYPE_DATE="Date";
	
	public static final String JAVA_MYSQL_TYPE_STRING="VARCHAR";
	public static final String JAVA_MYSQL_TYPE_INT="Integer";
	public static final String JAVA_MYSQL_TYPE_LONG="LONG";
	public static final String JAVA_MYSQL_TYPE_DOUBLE="Double";
	public static final String JAVA_MYSQL_TYPE_DATE="Timestamp";
	
	public static final String MYSQL_TYPE_INT="int";
	public static final String MYSQL_TYPE_BIGINT="bigint";
	public static final String MYSQL_TYPE_FLOAT="float";
	public static final String MYSQL_TYPE_DOUBLE="double";
	public static final String MYSQL_TYPE_NUMERIC="numeric";
	public static final String MYSQL_TYPE_DECIMAL="decimal";
	public static final String MYSQL_TYPE_DATE="date";
	public static final String MYSQL_TYPE_DATETIME="datetime";
	public static final String MYSQL_TYPE_TIMESTAMP="timestamp";	
	public static final String MYSQL_TYPE_YEAR="year";
	
	public static String toJavaType(String dataType ,String columnType){
		String type = JAVA_TYPE_STRING;
		if ( dataType.equals( MYSQL_TYPE_BIGINT ) ){
			type = JAVA_TYPE_LONG;
		}else if (dataType.indexOf(MYSQL_TYPE_INT) != -1 || dataType.equals( MYSQL_TYPE_YEAR) ){
			type = JAVA_TYPE_INT;
		}else if ( dataType.equals ( MYSQL_TYPE_FLOAT) || dataType.equals( MYSQL_TYPE_DOUBLE)){
			type = JAVA_TYPE_DOUBLE;
		}else if (dataType.equals(MYSQL_TYPE_DECIMAL) || dataType.equals(MYSQL_TYPE_NUMERIC)){
			type = JAVA_TYPE_DOUBLE;
			if ( columnType.indexOf(",0)")!=-1){
				type = JAVA_TYPE_LONG;
			}
		}else if ( dataType.equals(MYSQL_TYPE_DATE) || dataType.equals(MYSQL_TYPE_DATETIME) 
				|| dataType.equals(MYSQL_TYPE_TIMESTAMP)){
			type = JAVA_TYPE_DATE;	
		}
		return type;
	}
	
	public static String toMapperType(String dataType ,String columnType){
		String type = JAVA_MYSQL_TYPE_STRING;
		if ( dataType.equals( MYSQL_TYPE_BIGINT ) ){
			type = JAVA_MYSQL_TYPE_LONG;
		}else if (dataType.indexOf(MYSQL_TYPE_INT) != -1 || dataType.equals( MYSQL_TYPE_YEAR) ){
			type = JAVA_MYSQL_TYPE_INT;
		}else if ( dataType.equals ( MYSQL_TYPE_FLOAT) || dataType.equals( MYSQL_TYPE_DOUBLE)){
			type = JAVA_MYSQL_TYPE_DOUBLE;
		}else if (dataType.equals(MYSQL_TYPE_DECIMAL) || dataType.equals(MYSQL_TYPE_NUMERIC)){
			type = JAVA_MYSQL_TYPE_DOUBLE;
			if ( columnType.indexOf(",0)")!=-1){
				type = JAVA_MYSQL_TYPE_LONG;
			}
		}else if ( dataType.equals(MYSQL_TYPE_DATE) || dataType.equals(MYSQL_TYPE_DATETIME) 
				|| dataType.equals(MYSQL_TYPE_TIMESTAMP)){
			type = JAVA_MYSQL_TYPE_DATE;	
		}
		return type;
	}
	/** 有效 **/
	public static final int ALIVE = 1;
	
	/** 禁止 **/
	public static final int FORBID = 2;
	
	/** 无效 **/
	public static final int DEAD = 3;
	
	public static final Map<Integer, String> statusDescMap = new HashMap<Integer, String>();
	public static final Map<Integer, String> statusMap = new HashMap<Integer, String>();
	static {
		statusMap.put(ALIVE, "有效");
		statusMap.put(FORBID, "禁止");
		statusMap.put(DEAD, "无效");
		
		statusDescMap.put(ALIVE, "<font color='green'>有效</font>");
		statusDescMap.put(FORBID, "<font color='blue'>禁止</font>");
		statusDescMap.put(DEAD, "<font color='red'>无效</font>");
	}
	
	/** 开发的项目路径 */
	public static final String SYSTEM_PATH = System.getProperty("user.dir").replaceAll("\\\\", "/") + "/";
	/** 开发包的默认路径 */
	public static final String JAVA_PACKAGE_PATH = "com.hyc.oa.modules";
	public static final String PACKAGE_PATH = "src/main/java/";
	/** 开发html的默认路径 */
	public static final String HTML_PATH = "src/main/resources/templates/";
	/** 开发java文件的默认路径 */
	public static final String CLASS_PATH = "src/main/java/";
	/** 开发mapper文件的默认路径 */
	public static final String MAPPER_PATH = "src/main/resources/mapper/";
}
