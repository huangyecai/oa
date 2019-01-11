package com.hyc.oa.common.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyc.oa.common.dao.DataBaseDao;
import com.hyc.oa.common.entity.ColumnInfo;
import com.hyc.oa.common.entity.TableInfo;
import com.hyc.oa.common.utils.ConstantUtils;
import com.hyc.oa.common.utils.Fileutils;

@Service
public class DataBaseService {
	@Autowired
	private DataBaseDao dataBaseDao;
	
	/**
	 * 数据库表列表
	 * @param tableInfo
	 * @return
	 */
	public List<TableInfo> tableList(TableInfo tableInfo){
		return dataBaseDao.tableList(tableInfo);
	}
	
	/**
	 * 表的字段 列表
	 * @param tableSchema
	 * @param tableName
	 * @return
	 */
	public List<ColumnInfo> columnList(String tableSchema,String tableName){
		return dataBaseDao.columnList(tableSchema,tableName);
	}
	
	/**
	 *	创建控制器文件
	 * @param packagePath
	 * @param tableName
	 */
	public void makeController( String rootPath , String packagePath,String tableName) {
		String tableCamelName = this.stringToCamel(tableName);
		String text = makeControllerStr(packagePath,tableCamelName);
		String filePath = rootPath+ ConstantUtils.PACKAGE_PATH  + packagePath.replace(".", "/") +"/" +tableCamelName.toLowerCase() + "/controller" + "/"+this.toUpperCaseFirstOne(tableCamelName)+"Controller.java";
		 
		Fileutils.write(filePath,text);
	}
	
	/**
	 * 创建控制器文件内容
	 * @param packagePath
	 * @param tableCamelName
	 * @return
	 */
	public String  makeControllerStr(String packagePath,String tableCamelName) {
		String className = this.toUpperCaseFirstOne(tableCamelName);
		String text = "package "+ packagePath +"." + tableCamelName.toLowerCase() + ".controller;\r\n" + 
				"\r\n" + 
				"import java.util.List;\r\n" + 
				"import java.util.Map;\r\n" + 
				"\r\n" + 
				"import javax.servlet.http.HttpServletRequest;\r\n" + 
				"\r\n" + 
				"import org.apache.commons.lang.StringUtils;\r\n" + 
				"import org.springframework.beans.factory.annotation.Autowired;\r\n" + 
				"import org.springframework.stereotype.Controller;\r\n" + 
				"import org.springframework.web.bind.annotation.RequestMapping;\r\n" + 
				"import org.springframework.web.bind.annotation.ResponseBody;\r\n" + 
				"\r\n" + 
				"import com.github.pagehelper.Page;\r\n" + 
				"import com.github.pagehelper.PageHelper;\r\n" + 
				"import com.hyc.oa.common.utils.ConstantUtils;\r\n" + 
				"import com.hyc.oa.common.utils.ResultUtils;\r\n" + 
				"import " + packagePath + "." + tableCamelName.toLowerCase() + ".entity." + className + ";\r\n" + 
				"import " + packagePath + "." + tableCamelName.toLowerCase() + ".service." + className + "Service;\r\n" + 
				"\r\n" + 
				"@Controller\r\n" + 
				"public class " + className + "Controller {\r\n" + 
				"	\r\n" + 
				"	private static final String COMMON = \"" + tableCamelName.toLowerCase() + "/\";\r\n" + 
				"    private static final String LIST = COMMON+\"list\";\r\n" + 
				"    private static final String FORM = COMMON+\"form\";\r\n" + 
				"    private static final String CREATE = COMMON+\"create\";\r\n" + 
				"    private static final String UPDATE = COMMON+\"update\";\r\n" + 
				"    private static final String DELETE = COMMON+\"delete\";\r\n" + 
				"    private static final String DETAIL = COMMON+\"detail\";\r\n" + 
				"\r\n" + 
				"    @Autowired\r\n" + 
				"    private " + className + "Service " + tableCamelName + "Service;\r\n" + 
				"    \r\n" + 
				"    /**\r\n" + 
				"     * 列表查询\r\n" + 
				"     * @param entity\r\n" + 
				"     * @param request\r\n" + 
				"     */\r\n" + 
				"	@RequestMapping(LIST)\r\n" + 
				"	public void list ( " + className + " entity , HttpServletRequest request) {\r\n" + 
				"		\r\n" + 
				"		String pageNumber = request.getParameter(\"pageNumber\");\r\n" + 
				"    	String pageSize = request.getParameter(\"pageSize\");\r\n" + 
				"    	int pn = 1;\r\n" + 
				"    	int ps = 10;\r\n" + 
				"    	\r\n" + 
				"    	if (StringUtils.isNotBlank(pageNumber)) {\r\n" + 
				"    		pn = Integer.parseInt(pageNumber);\r\n" + 
				"		}\r\n" + 
				"    	if (StringUtils.isNotBlank(pageSize)) {\r\n" + 
				"    		ps = Integer.parseInt(pageSize);\r\n" + 
				"    	}\r\n" + 
				"    	if (entity.getStatus() == null) {\r\n" + 
				"    		entity.setStatus(ConstantUtils.ALIVE);\r\n" + 
				"		}\r\n" + 
				"    	//分页并查询\r\n" + 
				"        Page page = PageHelper.startPage( pn, ps );\r\n" + 
				"    	List<" + className + "> list = " + tableCamelName + "Service.list(entity);\r\n" + 
				"    	request.setAttribute(\"page\", page);\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	/**\r\n" + 
				"	 * 进入新建/编辑页面\r\n" + 
				"	 * @param entity\r\n" + 
				"	 * @param request\r\n" + 
				"	 * @throws Exception\r\n" + 
				"	 */\r\n" + 
				"	@RequestMapping(FORM)\r\n" + 
				"	public void form ( " + className + " entity , HttpServletRequest request ) throws Exception {\r\n" + 
				"		if (StringUtils.isNotBlank(entity.getId())) {\r\n" + 
				"			entity = " + tableCamelName + "Service.get(entity.getId());\r\n" + 
				"			if (entity == null ) {\r\n" + 
				"				throw new Exception(\"数据不存在\");\r\n" + 
				"			}\r\n" + 
				"		}\r\n" + 
				"		request.setAttribute(\"entity\", entity);\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	/**\r\n" + 
				"	 * 新建\r\n" + 
				"	 */\r\n" + 
				"	@RequestMapping(CREATE)\r\n" + 
				"	@ResponseBody\r\n" + 
				"	public Map<String, Object> create(" + className + " entity, HttpServletRequest request) {\r\n" + 
				"		String result = \"\" ;\r\n" + 
				"		try {\r\n" + 
				"			result = " + tableCamelName + "Service.save(entity);\r\n" + 
				"		} catch (Exception e) {\r\n" + 
				"			e.printStackTrace();\r\n" + 
				"			return ResultUtils.error();\r\n" + 
				"		}\r\n" + 
				"		if ( StringUtils.isNotBlank(result)) {\r\n" + 
				"			return ResultUtils.success();\r\n" + 
				"		}else {\r\n" + 
				"			return ResultUtils.error();\r\n" + 
				"		}\r\n" + 
				"		\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	/**\r\n" + 
				"	 * 更新\r\n" + 
				"	 */\r\n" + 
				"	@RequestMapping(UPDATE)\r\n" + 
				"	@ResponseBody\r\n" + 
				"	public Map<String, Object> update( " + className + " entity, HttpServletRequest request) {\r\n" + 
				"		String result = \"\" ;\r\n" + 
				"		try {\r\n" + 
				"			result = " + tableCamelName + "Service.save(entity);\r\n" + 
				"		} catch (Exception e) {\r\n" + 
				"			e.printStackTrace();\r\n" + 
				"			return ResultUtils.error();\r\n" + 
				"		}\r\n" + 
				"		if ( StringUtils.isNotBlank(result)) {\r\n" + 
				"			return ResultUtils.success();\r\n" + 
				"		}else {\r\n" + 
				"			return ResultUtils.error();\r\n" + 
				"		}\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	/**\r\n" + 
				"	 * 删除\r\n" + 
				"	 */\r\n" + 
				"	@RequestMapping(DELETE)\r\n" + 
				"	@ResponseBody\r\n" + 
				"	public Map<String, Object> delete(" + className + " entity, HttpServletRequest request) {\r\n" + 
				"		boolean result = false;\r\n" + 
				"		try {\r\n" + 
				"			result = " + tableCamelName + "Service.delete(entity.getId());\r\n" + 
				"		} catch (Exception e) {\r\n" + 
				"			e.printStackTrace();\r\n" + 
				"			return ResultUtils.error();\r\n" + 
				"		}\r\n" + 
				"		\r\n" + 
				"		if ( result ) {\r\n" + 
				"			return ResultUtils.success();\r\n" + 
				"		}else {\r\n" + 
				"			return ResultUtils.error();\r\n" + 
				"		}\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	@RequestMapping(DETAIL)\r\n" + 
				"	private void detail(" + className + " entity, HttpServletRequest request) throws Exception {\r\n" + 
				"		if (StringUtils.isNotBlank(entity.getId())) {\r\n" + 
				"			entity = " + tableCamelName + "Service.get(entity.getId());\r\n" + 
				"			if (entity == null ) {\r\n" + 
				"				throw new Exception(\"数据不存在\");\r\n" + 
				"			}\r\n" + 
				"		}else {\r\n" + 
				"			throw new Exception(\"请选择正确的数据\");\r\n" + 
				"		}\r\n" + 
				"		 \r\n" + 
				"		request.setAttribute(\"entity\", entity);\r\n" + 
				"	}\r\n" + 
				"}";
		return text; 
	}
	
	/**
	 * 创建业务文件
	 * @param packagePath
	 * @param tableName
	 */
	public void makeService( String rootPath , String packagePath,String tableName) {
		String tableCamelName = this.stringToCamel(tableName);
		String text = makeServiceStr(packagePath,tableCamelName);
		String filePath = rootPath + ConstantUtils.PACKAGE_PATH  + packagePath.replace(".", "/") +"/" +tableCamelName.toLowerCase() + "/service" + "/"+this.toUpperCaseFirstOne(tableCamelName)+"Service.java";
		Fileutils.write(filePath,text);
	}
	
	/**
	 *  创建业务文件内容
	 * @param packagePath
	 * @param tableCamelName
	 * @return
	 */
	private String makeServiceStr(String packagePath, String tableCamelName) {
		String className = this.toUpperCaseFirstOne(tableCamelName);
		String text = "package " + packagePath +"." + tableCamelName.toLowerCase() +  ".service;\r\n" + 
				"\r\n" + 
				"import java.util.List;\r\n" + 
				"import java.util.UUID;\r\n" + 
				"\r\n" + 
				"import org.apache.commons.lang.StringUtils;\r\n" + 
				"import org.springframework.beans.factory.annotation.Autowired;\r\n" + 
				"import org.springframework.stereotype.Service;\r\n" + 
				"import org.springframework.transaction.annotation.Transactional;\r\n" + 
				"\r\n" + 
				"import com.hyc.oa.common.utils.ConstantUtils;\r\n" + 
				"import " + packagePath + "." + tableCamelName.toLowerCase() + ".dao." + className + "Dao;\r\n" + 
				"import " + packagePath + "." + tableCamelName.toLowerCase() + ".entity." + className + ";\r\n" + 
				"\r\n" + 
				"@Service\r\n" + 
				"public class " + className + "Service {\r\n" + 
				"	\r\n" + 
				"	@Autowired\r\n" + 
				"	private " + className + "Dao " + tableCamelName.toLowerCase() + "Dao;\r\n" + 
				"\r\n" + 
				"	/**\r\n" + 
				"	 * 列表查询\r\n" + 
				"	 * @param entity\r\n" + 
				"	 * @return\r\n" + 
				"	 */\r\n" + 
				"	public List<" + className + "> list(" + className + " entity) {\r\n" + 
				"		return " + tableCamelName.toLowerCase() + "Dao.list(entity);\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	/**\r\n" + 
				"	 * 根据id获取\r\n" + 
				"	 * @param id\r\n" + 
				"	 * @return\r\n" + 
				"	 */\r\n" + 
				"	public " + className + " get(String id) {\r\n" + 
				"		return " + tableCamelName.toLowerCase() + "Dao.get(id);\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	/**\r\n" + 
				"	 * 保存\r\n" + 
				"	 * @param entity\r\n" + 
				"	 * @return\r\n" + 
				"	 */\r\n" + 
				"	@Transactional\r\n" + 
				"	public String save(" + className + " entity) {\r\n" + 
				"		String id = entity.getId();\r\n" + 
				"		if (StringUtils.isBlank(id) || get(id) == null) {\r\n" + 
				"			create( entity );\r\n" + 
				"		}else {\r\n" + 
				"			update( entity );\r\n" + 
				"		}\r\n" + 
				"		return entity.getId();\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	/**\r\n" + 
				"	 * 更新\r\n" + 
				"	 * @param entity\r\n" + 
				"	 */\r\n" + 
				"	private void update(" + className + " entity) {\r\n" + 
				"		" + tableCamelName.toLowerCase() + "Dao.update(entity);\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	/**\r\n" + 
				"	 * 新增\r\n" + 
				"	 * @param entity\r\n" + 
				"	 */\r\n" + 
				"	private void create(" + className + " entity) {\r\n" + 
				"		entity.setId(UUID.randomUUID().toString().replaceAll(\"-\", \"\"));\r\n" + 
				"		entity.setStatus(ConstantUtils.ALIVE);\r\n" + 
				"		" + tableCamelName.toLowerCase() + "Dao.insert(entity);	\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	/**\r\n" + 
				"	 * 删除\r\n" + 
				"	 */\r\n" + 
				"	@Transactional\r\n" + 
				"	public boolean delete(String id) {\r\n" + 
				"		" + className + " entity = get(id);\r\n" + 
				"		if (entity == null || entity.getStatus() == 3) {\r\n" + 
				"			return false;\r\n" + 
				"		}\r\n" + 
				"		" + tableCamelName.toLowerCase() + "Dao.delete(id);\r\n" + 
				"		return true;\r\n" + 
				"	}\r\n" + 
				"}";
		return text;
	}

	

	/**
	 * 创建Dao文件
	 * @param packagePath
	 * @param tableName
	 */
	public void makeDao( String rootPath , String packagePath,String tableName) {
		String tableCamelName = this.stringToCamel(tableName);
		String text = makeDaoStr(packagePath,tableCamelName);
		String filePath = rootPath + ConstantUtils.PACKAGE_PATH  + packagePath.replace(".", "/") +"/" +tableCamelName.toLowerCase() + "/dao" + "/"+this.toUpperCaseFirstOne(tableCamelName)+"Dao.java";
		Fileutils.write(filePath,text);
	}
	
	/**
	 * 创建dao文件内容
	 * @param packagePath
	 * @param tableCamelName
	 * @return
	 */
	private String makeDaoStr(String packagePath, String tableCamelName) {
		String className = this.toUpperCaseFirstOne(tableCamelName);
		String text = "package " + packagePath + "." + tableCamelName.toLowerCase() + ".dao;\r\n" + 
				"\r\n" + 
				"import java.util.List;\r\n" + 
				"\r\n" + 
				"import org.apache.ibatis.annotations.Mapper;\r\n" + 
				"\r\n" + 
				"import " + packagePath + "." + tableCamelName.toLowerCase() + ".entity." + className + ";\r\n" + 
				"\r\n" + 
				"@Mapper\r\n" + 
				"public interface " + className + "Dao {\r\n" + 
				"    int delete(String id);\r\n" + 
				"\r\n" + 
				"    int insert(" + className + " entity);\r\n" + 
				"\r\n" + 
				"    int update(" + className + " entity);\r\n" + 
				"\r\n" + 
				"	List<" + className + "> list(" + className + " entity);\r\n" + 
				"\r\n" + 
				"	" + className + " get(String id);\r\n" + 
				"\r\n" + 
				"}";
		return text;
	}

	/**
	 * 创建实体文件
	 * @param packagePath
	 * @param tableName
	 */
	public void makeEntity( String rootPath , String packagePath,String tableName , List<ColumnInfo> columnsList) {
		String tableCamelName = this.stringToCamel(tableName);
		String text = makeEntityStr(packagePath,tableCamelName ,columnsList);
		String filePath = rootPath + ConstantUtils.PACKAGE_PATH  + packagePath.replace(".", "/") +"/" +tableCamelName.toLowerCase() + "/entity" + "/"+this.toUpperCaseFirstOne(tableCamelName)+".java";
		Fileutils.write(filePath,text);
	}
	
	
	private String makeEntityStr(String packagePath, String tableCamelName , List<ColumnInfo> columnsList) {
		String className = this.toUpperCaseFirstOne(tableCamelName);
		String text = "package " + packagePath + "." + tableCamelName.toLowerCase() + ".entity;\r\n" + 
				"\r\n" + 
				"import java.util.Date;\r\n" + 
				"\r\n" + 
				"public class " + className + " {\r\n" ;
		for (ColumnInfo columnInfo : columnsList) {
			text += "     /**\r\n" + 
					"     *  " + columnInfo.getColumnComment() + " \r\n"+
					"     */\r\n" +
					"    private " +  ConstantUtils.toJavaType(columnInfo.getDataType(), columnInfo.getColumnComment()) + " " + stringToCamel( columnInfo.getColumnName() ) + ";\r\n"+
					"\r\n" ; 
		}
		for (ColumnInfo columnInfo : columnsList) {
			String columnName = stringToCamel( columnInfo.getColumnName());
			text += "    public " + ConstantUtils.toJavaType(columnInfo.getDataType(), columnInfo.getColumnComment()) +  " get" + toUpperCaseFirstOne( columnName )  + "() {\r\n" + 
					"        return " + columnName + ";\r\n" + 
					"    }\r\n" + 
					"\r\n" + 
					"    public void set" + toUpperCaseFirstOne( columnName ) +"( " + ConstantUtils.toJavaType(columnInfo.getDataType(), columnInfo.getColumnComment()) +  " " + columnName + ") {\r\n" + 
					"        this." + columnName + " = " + columnName + ";\r\n" + 
					"    }\r\n" + 
					"\r\n" ;
		}
				 
		text += "}";
		return text;
	}
	
	/**
	 * 创建mapper文件
	 * @param packagePath
	 * @param tableName
	 */
	public void makeMapper( String rootPath , String packagePath,String tableName , List<ColumnInfo> columnsList) {
		String tableCamelName = this.stringToCamel(tableName);
		String text = makeMapperStr(packagePath,tableName ,columnsList);
		String filePath = rootPath + ConstantUtils.MAPPER_PATH + tableCamelName.toLowerCase() + "/" + this.toUpperCaseFirstOne(tableCamelName)+"Dao.xml";
		Fileutils.write(filePath,text);
	}

	private String makeMapperStr(String packagePath, String tableName, List<ColumnInfo> columnsList) {
		String tableCamelName = this.stringToCamel(tableName);
		String className = this.toUpperCaseFirstOne(tableCamelName);
		String BaseColumnList = "";
		String BaseColumnValueList = "";
		
		String updateSet = "";
		String wherePri = "";
		
		String text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\r\n" + 
				"<mapper namespace=\"" + packagePath + "." + tableCamelName.toLowerCase() + ".dao." + className + "Dao\">\r\n" + 
				"  <resultMap id=\"BaseResultMap\" type=\"" + packagePath + "." + tableCamelName.toLowerCase() + ".entity." + className + "\">\r\n" ;
		for (ColumnInfo columnInfo : columnsList) {
			if ("PRI".equals(columnInfo.getColumnKey())) {
				text += "    <id column=\"" + columnInfo.getColumnName() + "\" jdbcType=\"" + ConstantUtils.toMapperType(columnInfo.getDataType(), columnInfo.getColumnType()).toUpperCase()  + "\" property=\"" + stringToCamel( columnInfo.getColumnName()) + "\" />\r\n"  ;
			
				wherePri = "  where "+ columnInfo.getColumnName() + " = #{" + stringToCamel( columnInfo.getColumnName()) + "}\r\n  ";
			}else {
				text += "    <result column=\"" + columnInfo.getColumnName() + "\" jdbcType=\"" + ConstantUtils.toMapperType(columnInfo.getDataType(), columnInfo.getColumnType()).toUpperCase()   + "\" property=\"" + stringToCamel( columnInfo.getColumnName()) + "\" />\r\n"  ;
				
				updateSet += "			<if test=\"" + stringToCamel( columnInfo.getColumnName()) + " != null and " + stringToCamel( columnInfo.getColumnName()) + " !=''\" >\r\n" + 
						"				 " + columnInfo.getColumnName() + " = #{" + stringToCamel( columnInfo.getColumnName()) + "} ,\r\n" + 
						"			</if>\r\n" ; 
			}
			BaseColumnList += "," + columnInfo.getColumnName();
			BaseColumnValueList += ",#{" + stringToCamel( columnInfo.getColumnName()) +"} ";
		}
		
		text +=		"  </resultMap>\r\n" + 
				"  <sql id=\"Base_Column_List\">\r\n" ;
		
		if (BaseColumnList.startsWith(",")) {
			BaseColumnList = BaseColumnList.substring(1);
		}
		if (BaseColumnValueList.startsWith(",")) {
			BaseColumnValueList = BaseColumnValueList.substring(1);
		}
		
		

		text += BaseColumnList +"\r\n"+
				"  </sql>\r\n" + 
				"  \r\n" + 
				"  <select id=\"get\" parameterType=\"java.lang.String\" resultMap=\"BaseResultMap\">\r\n" + 
				"    select \r\n" + 
				"    <include refid=\"Base_Column_List\" />\r\n" + 
				"    from " + tableName + "\r\n" + 
				wherePri +  
				"  </select>\r\n" + 
				"	<select id=\"list\" parameterType=\"java.lang.String\" resultMap=\"BaseResultMap\">\r\n" + 
				"		select\r\n" + 
				"		<include refid=\"Base_Column_List\" />\r\n" + 
				"		from " + tableName + "\r\n" + 
				"		<where>\r\n" ;
		for (ColumnInfo columnInfo : columnsList) {
			if (!"PRI".equals(columnInfo.getColumnKey())) {
				if (ConstantUtils.JAVA_MYSQL_TYPE_STRING.equals( ConstantUtils.toMapperType(columnInfo.getDataType(), columnInfo.getColumnComment()))) {
					text += "			<if test=\"" + stringToCamel( columnInfo.getColumnName()) + " != null and " + stringToCamel( columnInfo.getColumnName()) + " !=''\" >\r\n" + 
							"				 and " + columnInfo.getColumnName() + " LIKE '%' #{" + stringToCamel( columnInfo.getColumnName()) + "} '%'\r\n" + 
							"			</if>\r\n" ; 
				}else {
					text += "			<if test=\"" + stringToCamel( columnInfo.getColumnName()) + " != null and " + stringToCamel( columnInfo.getColumnName()) + " !=''\" >\r\n" + 
							"				 and " + columnInfo.getColumnName() + " = #{" + stringToCamel( columnInfo.getColumnName()) + "} \r\n" + 
							"			</if>\r\n" ; 
				}
			}
		}
		text +=		"		</where>\r\n" + 
				"	</select>\r\n" + 
				"  \r\n" + 
				"  <delete id=\"delete\" parameterType=\"java.lang.String\">\r\n" + 
				"    update " + tableName + " set status = 3\r\n" + 
				wherePri +  
				"  </delete>\r\n" + 
				"  \r\n" + 
				"  <insert id=\"insert\" parameterType=\"" + packagePath + "." + tableCamelName.toLowerCase() + ".entity." + className + "\">\r\n" + 
				"    insert into " + tableName + " (" +
				"		<include refid=\"Base_Column_List\" />" + 
				"		)\r\n" + 
				"    values (" + BaseColumnValueList + ")\r\n" + 
				"  </insert>\r\n" + 
				"  <update id=\"update\" parameterType=\"" + packagePath + "." + tableCamelName.toLowerCase() + ".entity." + className + "\">\r\n" + 
				"    update " + tableName + "\r\n" + 
				"    <set>\r\n" +
				updateSet +
				"    </set>\r\n" + 
				wherePri + 
				"  </update>\r\n" + 
				"</mapper>";
		 
		return text;
	}


	/**
	 * 创建html文件
	 * @param packagePath
	 * @param tableName
	 */
	public void makeListJsp( String rootPath ,  String tableName , List<ColumnInfo> columnsList) {
		String tableCamelName = this.stringToCamel(tableName);
		String text = makeListJspStr( tableCamelName ,columnsList);
		String filePath = rootPath + ConstantUtils.HTML_PATH +tableCamelName.toLowerCase() + "/list.html";
		Fileutils.write(filePath,text);
	}
	
	
	private String makeListJspStr( String tableCamelName, List<ColumnInfo> columnsList) {
		
		String searchStr = "";
		String tableHeadStr = "";
		String tableBodyStr = "";
		
		for (ColumnInfo columnInfo : columnsList) {
			searchStr += "								" + columnInfo.getColumnComment() + "：<input name=\"" + stringToCamel(columnInfo.getColumnName()) + "\" th:value=\"${param." + stringToCamel(columnInfo.getColumnName()) + "}\" class=\"easyui-textbox\" style=\"width:100px;height:32px\">\r\n" ;
			tableHeadStr += 	"								<th field=\"" + stringToCamel(columnInfo.getColumnName()) + "\">" + columnInfo.getColumnComment() + "</th>\r\n" ;
			tableBodyStr +=	"<td th:text=\"${entity." + stringToCamel(columnInfo.getColumnName()) + "}\"></td>\r\n" ; 
		}
		
		String text = "<!DOCTYPE html>\r\n" + 
				"<html xmlns:th=\"http://www.thymeleaf.org\">\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"UTF-8\">\r\n" + 
				"<title>管理</title>\r\n" + 
				"	<link th:href=\"@{../../jquery-easyui-1.6.6/themes/default/easyui.css}\" href=\"../../../static/jquery-easyui-1.6.6/themes/default/easyui.css\" rel=\"stylesheet\" type=\"text/css\">\r\n" + 
				"    <link th:href=\"@{../../jquery-easyui-1.6.6/themes/icon.css}\" href=\"../../../static/jquery-easyui-1.6.6/themes/icon.css\" rel=\"stylesheet\" type=\"text/css\">\r\n" + 
				"    <script th:src=\"@{../../jquery-easyui-1.6.6/jquery.min.js}\" src=\"../../../static/jquery-easyui-1.6.6/jquery.min.js\" type=\"text/javascript\"></script>\r\n" + 
				"    <script th:src=\"@{../../jquery-easyui-1.6.6/jquery.easyui.min.js }\" src=\"../../../static/jquery-easyui-1.6.6/jquery.easyui.min.js\" type=\"text/javascript\"></script>\r\n" + 
				"    <script th:src=\"@{../../jquery-easyui-1.6.6/locale/easyui-lang-zh_CN.js }\" src=\"../../../static/jquery-easyui-1.6.6/locale/easyui-lang-zh_CN.js\" type=\"text/javascript\"></script>\r\n" + 
				"	<style type=\"text/css\">\r\n" + 
				"	.datagrid-header-inner{\r\n" + 
				"	width: 100%\r\n" + 
				"	}\r\n" + 
				"	.datagrid-view2 table{\r\n" + 
				"	width: 100%\r\n" + 
				"	}\r\n" + 
				"	</style>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"	<div class=\"easyui-panel\" style=\"width:100%;height: 100%;\" data-options=\"fit:true\" >\r\n" + 
				"		<table style=\"width: 100%;height: 100%;border: 0;\">\r\n" + 
				"			<tr>\r\n" + 
				"				<td>\r\n" + 
				"					<div id=\"tb\" style=\"padding:5px;height:auto\">\r\n" + 
				"						<div style=\"margin-bottom:5px\">\r\n" + 
				"							<a href=\"javascript:void(0);\" th:onclick=\"'parent.openDialog(\\'添加\\',\\'" + tableCamelName.toLowerCase() + "/form\\',400,\\'\\')'\" class=\"easyui-linkbutton\" iconCls=\"icon-add\" plain=\"true\"></a>\r\n" + 
				"							<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-edit\" plain=\"true\"></a>\r\n" + 
				"							<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-save\" plain=\"true\"></a>\r\n" + 
				"							<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-cut\" plain=\"true\"></a>\r\n" + 
				"							<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-remove\" plain=\"true\"></a>\r\n" + 
				"						</div>\r\n" + 
				"						<div>\r\n" + 
				"							<form id=\"searchForm\" action=\"list\" method=\"post\">\r\n" + 
				searchStr +
				"								<a href=\"#\" class=\"easyui-linkbutton\" onclick=\"$('#searchForm').submit();\" iconCls=\"icon-search\">Search</a>\r\n" + 
				"							</form>\r\n" + 
				"						</div>\r\n" + 
				"					</div>\r\n" + 
				"					<table title=\"管理\" class=\"easyui-datagrid\" fitColumns=\"true\" style=\"width:100%;height:100%\" singleSelect=\"true\" rownumbers=\"true\" toolbar='#tb'>\r\n" + 
				"						<thead>\r\n" + 
				"							<tr style=\"width: 100%\">\r\n" + 
				tableHeadStr +
				"								<th field=\"操作\">操作</th>\r\n" + 
				"							</tr>\r\n" + 
				"						</thead>\r\n" + 
				"						<tbody>\r\n" + 
				"							<th:block th:each=\"entity:${page.result}\">\r\n" + 
				"								<tr>\r\n" + 
				tableBodyStr +
				"									<td >\r\n" + 
				"										<a href=\"#\" class=\"easyui-linkbutton\" th:onclick=\"'parent.openDialog(\\'编辑\\',\\'" + tableCamelName.toLowerCase() + "/form?id=' + ${entity.id} +'\\',400,\\'\\')'\">编辑</a>\r\n" + 
				"										<a href=\"#\" class=\"easyui-linkbutton\" th:onclick=\"'parent.del(\\'" + tableCamelName.toLowerCase() + "/delete?id='+${ entity.id}+'\\')'\">删除</a>\r\n" + 
				"										<a href=\"#\" class=\"easyui-linkbutton\" th:onclick=\"'parent.openDialog(\\'详情\\',\\'" + tableCamelName.toLowerCase() + "/detail?id=' + ${entity.id} +'\\',400,\\'\\')'\">详情</a>\r\n" + 
				"									</td>\r\n" + 
				"								</tr>\r\n" + 
				"							</th:block>\r\n" + 
				"						</tbody>\r\n" + 
				"					</table>\r\n" + 
				"				</td>\r\n" + 
				"			</tr>\r\n" + 
				"			<tr>\r\n" + 
				"				<td height=\"36px\">\r\n" + 
				"					<div id=\"dataTablePagination\" class=\"easyui-pagination\" style=\"border:1px solid #ccc;\" pagePosition=\"bottom\" th:attr=\"data-options='pageNumber:'+${page.pageNum}+',total:'+${page.total}+',pageSize:'+${page.pageSize}+',onSelectPage:selectPage'\">\r\n" + 
				"					</div>\r\n" + 
				"				</td>\r\n" + 
				"			</tr>\r\n" + 
				"		</table>\r\n" + 
				"	</div>\r\n" + 
				"	<script type=\"text/javascript\">\r\n" + 
				"		function selectPage(pageNumber, pageSize){\r\n" + 
				"			window.location.href=window.location.pathname+'?pageNumber='+pageNumber+'&pageSize='+pageSize+'&'+$(\"form\").serialize();\r\n" + 
				"		}\r\n" + 
				"	</script>\r\n" + 
				"</body>\r\n" + 
				"</html>";
		return text;
	}

	
	
	/**
	 * 创建html文件
	 * @param packagePath
	 * @param tableName
	 */
	public void makeFormJsp( String rootPath ,  String tableName , List<ColumnInfo> columnsList) {
		String tableCamelName = this.stringToCamel(tableName);
		String text = makeFormJspStr(tableCamelName ,columnsList);
		String filePath = rootPath + ConstantUtils.HTML_PATH +tableCamelName.toLowerCase() + "/form.html";
		Fileutils.write(filePath,text);
	}
	
	private String makeFormJspStr(String tableCamelName, List<ColumnInfo> columnsList) {
		String formStr = "";
		String priStr = "" ;
		String priColumnName = "";
 		for (ColumnInfo columnInfo : columnsList) {
 			String columnName = this.stringToCamel(columnInfo.getColumnName());
 			if ( !"PRI".equals(columnInfo.getColumnKey()) ) {
 				String isNull = "YES".equals(columnInfo.getIsNullable()) ? "true":"false";
 				formStr += "	    		<tr>\r\n" + 
 						"	    			<td>" + columnInfo.getColumnComment() + ":</td>\r\n" + 
 						"	    			<td><input th:value=\"${entity." + columnName + "}\" class=\"easyui-validatebox\" type=\"text\" name=\"" + columnName + "\" data-options=\"required:" + isNull +"\"></input></td>\r\n" + 
 						"	    		</tr>\r\n" ;
			}else {
				priStr = "	    	<input name=\"" + columnName + "\" type=\"hidden\" th:value=\"${entity." + columnName + "}\">\r\n" ;
				priColumnName = columnName;
			}
		}
		String text = "<!DOCTYPE html>\r\n" + 
				"<html lang=\"en\" xmlns:th=\"http://www.thymeleaf.org\">\r\n" + 
				"<head>\r\n" + 
				"    <meta charset=\"UTF-8\">\r\n" + 
				"    <title>添加</title>\r\n" + 
				"    <link th:href=\"@{../../jquery-easyui-1.6.6/themes/default/easyui.css}\" href=\"../../jquery-easyui-1.6.6/themes/default/easyui.css\" rel=\"stylesheet\" type=\"text/css\">\r\n" + 
				"    <link th:href=\"@{../../jquery-easyui-1.6.6/themes/icon.css}\" href=\"../../jquery-easyui-1.6.6/themes/icon.css\" rel=\"stylesheet\" type=\"text/css\">\r\n" + 
				"    <script th:src=\"@{../../jquery-easyui-1.6.6/jquery.min.js}\" src=\"../../jquery-easyui-1.6.6/jquery.min.js\" type=\"text/javascript\"></script>\r\n" + 
				"    <script th:src=\"@{../../jquery-easyui-1.6.6/jquery.easyui.min.js }\" src=\"../../jquery-easyui-1.6.6/jquery.easyui.min.js\" type=\"text/javascript\"></script>\r\n" + 
				"    <script th:src=\"@{../../jquery-easyui-1.6.6/jquery.cookie.js }\" src=\"../../jquery-easyui-1.6.6/jquery.cookie.js\" type=\"text/javascript\"></script>\r\n" + 
				"	<script type=\"text/javascript\">\r\n" + 
				"		function err(target, message){\r\n" + 
				"			var t = $(target);\r\n" + 
				"			if (t.hasClass('textbox-text')){\r\n" + 
				"				t = t.parent();\r\n" + 
				"			}\r\n" + 
				"			var m = t.next('.error-message');\r\n" + 
				"			if (!m.length){\r\n" + 
				"				m = $('<div class=\"error-message\"></div>').insertAfter(t);\r\n" + 
				"			}\r\n" + 
				"			m.html(message);\r\n" + 
				"		}\r\n" + 
				"		function submitForm(){\r\n" + 
				"			  $('#mainForm').form('submit',{\r\n" + 
				"				onSubmit:function(){\r\n" + 
				"					return $(this).form('enableValidation').form('validate');\r\n" + 
				"				},\r\n" + 
				"				success:function(data){\r\n" + 
				"					var result = $.parseJSON(data); \r\n" + 
				"					if (result.status == 200 || result.status == \"200\") {\r\n" + 
				"						/* alert(\"操作成功\"); */\r\n" + 
				"						$.messager.alert(\"操作提示\", \"操作成功！\", \"warning\", function () {\r\n" + 
				"							closeDialog();\r\n" + 
				"				        });\r\n" + 
				"					}else{\r\n" + 
				"						/* alert(\"操作失败\"); */\r\n" + 
				"						$.messager.alert('警告','操作失败!','error');\r\n" + 
				"					}\r\n" + 
				"			    }\r\n" + 
				"			}); \r\n" + 
				"		}\r\n" + 
				"		function clearForm(){\r\n" + 
				"			$('#mainForm').form('clear');\r\n" + 
				"		}\r\n" + 
				"	</script>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"  \r\n" + 
				"<div class=\"easyui-panel\"   style=\"width:400px\">\r\n" + 
				"		<div style=\"padding:10px 60px 20px 60px\">\r\n" + 
				"		<th:block th:if=\"${entity." + priColumnName + "} neq '' and ${entity." + priColumnName + "} != null\"  >\r\n" + 
				"			 <form id=\"mainForm\"   action=\"" + tableCamelName + "/update\"    method=\"post\" class=\"easyui-form\">  \r\n" + 
				"		</th:block>\r\n" + 
				"		<th:block th:if=\"${entity." + priColumnName + "} eq '' or ${entity." + priColumnName + "} == null\"  >\r\n" + 
				"			 <form id=\"mainForm\"   action=\"" + tableCamelName + "/create\"    method=\"post\" class=\"easyui-form\"> \r\n" + 
				"		</th:block>\r\n" + 
				"	     \r\n" + 
				priStr + 
				"	    	<table cellpadding=\"5\">\r\n" + 
				formStr +
				"	    	</table>\r\n" + 
				"	    </form>\r\n" + 
				"	    <div style=\"text-align:center;padding:5px\">\r\n" + 
				"	    	<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" onclick=\"submitForm()\">提交</a>\r\n" + 
				"	    	<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" onclick=\"clearForm()\">清空</a>\r\n" + 
				" 	    	<a href=\"javascript:void(0)\" id=\"colesssss\" class=\"easyui-linkbutton\" onclick=\"myCloseDialog()\">关闭</a>\r\n" + 
				"	    </div>\r\n" + 
				"	    </div>\r\n" + 
				"	</div>\r\n" + 
				"	<script>\r\n" + 
				"		function myCloseDialog() {\r\n" + 
				"			closeDialog();\r\n" + 
				"		}\r\n" + 
				"		function submitForm(){\r\n" + 
				"			  $('#mainForm').form('submit',{\r\n" + 
				"				onSubmit:function(){\r\n" + 
				"					return $(this).form('enableValidation').form('validate');\r\n" + 
				"				},\r\n" + 
				"				success:function(data){\r\n" + 
				"					var result = $.parseJSON(data); \r\n" + 
				"					if (result.status == 200 || result.status == \"200\") {\r\n" + 
				"						/* alert(\"操作成功\");\r\n" + 
				"						top.closeDialog(); */\r\n" + 
				"						$.messager.alert(\"操作提示\", \"操作成功！\", \"warning\", function () {\r\n" + 
				"							closeDialog();\r\n" + 
				"							 tabRefresh();\r\n" + 
				"				        });\r\n" + 
				"					}else{\r\n" + 
				"						/* alert(\"操作失败\"); */\r\n" + 
				"						$.messager.alert('警告','操作失败!','error');\r\n" + 
				"					}\r\n" + 
				"			    }\r\n" + 
				"			}); \r\n" + 
				"			 \r\n" + 
				"		}\r\n" + 
				"		function clearForm(){\r\n" + 
				"			$('#mainForm').form('clear');\r\n" + 
				"		}\r\n" + 
				"	</script>\r\n" + 
				"</body>\r\n" + 
				"</html>" ;
		return text;
	}
	
	

	
	/**
	 * 创建html文件
	 * @param packagePath
	 * @param tableName
	 * @param tableName 
	 */
	public void makeDetailJsp( String rootPath , String tableName, List<ColumnInfo> columnsList) {
		String tableCamelName = this.stringToCamel(tableName);
		String text = makeDetailJspStr(columnsList);
		String filePath = rootPath + ConstantUtils.HTML_PATH +tableCamelName.toLowerCase() + "/detail.html";
		Fileutils.write(filePath,text);
	}
	
	

	private String makeDetailJspStr(List<ColumnInfo> columnsList) {
		
		String trStr = "" ;
		for (ColumnInfo columnInfo : columnsList) {
 			String columnName = this.stringToCamel(columnInfo.getColumnName());
 			trStr += "			<tr>\r\n" + 
 					"				<td>" + columnInfo.getColumnComment() + "：</td>\r\n" + 
 					"				<td th:text=\"${entity." + columnName + "}\"></td>\r\n" + 
 					"			</tr>\r\n";
		}
		
		String text = "<!DOCTYPE html>\r\n" + 
				"<html lang=\"en\" xmlns:th=\"http://www.thymeleaf.org\">\r\n" + 
				"<head>\r\n" + 
				"	<meta charset=\"UTF-8\">\r\n" + 
				"	<title>详情</title>\r\n" + 
				"	<link th:href=\"@{../../jquery-easyui-1.6.6/themes/default/easyui.css}\" href=\"../../jquery-easyui-1.6.6/themes/default/easyui.css\" rel=\"stylesheet\" type=\"text/css\">\r\n" + 
				"    <link th:href=\"@{../../jquery-easyui-1.6.6/themes/icon.css}\" href=\"../../jquery-easyui-1.6.6/themes/icon.css\" rel=\"stylesheet\" type=\"text/css\">\r\n" + 
				"    <script th:src=\"@{../../jquery-easyui-1.6.6/jquery.min.js}\" src=\"../../jquery-easyui-1.6.6/jquery.min.js\" type=\"text/javascript\"></script>\r\n" + 
				"    <script th:src=\"@{../../jquery-easyui-1.6.6/jquery.easyui.min.js }\" src=\"../../jquery-easyui-1.6.6/jquery.easyui.min.js\" type=\"text/javascript\"></script>\r\n" + 
				"    <script th:src=\"@{../../jquery-easyui-1.6.6/jquery.cookie.js }\" src=\"../../jquery-easyui-1.6.6/jquery.cookie.js\" type=\"text/javascript\"></script>\r\n" + 
				"     \r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"	<div class=\"easyui-panel\"   style=\"width:400px\">\r\n" + 
				"		<table>\r\n" + 
				trStr +
				"		</table>\r\n" + 
				"		<div style=\"width:100%; text-align: center;\">\r\n" + 
				"			<a href=\"javascript:void(0)\"  class=\"easyui-linkbutton\" onclick=\"myCloseDialog();\">关闭</a>\r\n" + 
				"		</div>\r\n" + 
				"	</div>\r\n" + 
				"	<script type=\"text/javascript\">\r\n" + 
				"	function myCloseDialog() {\r\n" + 
				"		closeDialog();\r\n" + 
				"	}\r\n" + 
				"	</script>\r\n" + 
				"</body>\r\n" + 
				"</html>";
		return text;
	}
	

	/**
	 * 	字符串转驼峰式
	 * @param param
	 * @return
	 */
	public  String stringToCamel(String param) {
		if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if ( c == '_'){
               if (++i<len){
                   sb.append(Character.toUpperCase(param.charAt(i)));
               }
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
	}
	
	/**
	 * 	字符串首字母大写
	 * @param str
	 * @return
	 */
	public  String toUpperCaseFirstOne(String str) {
		return (new StringBuilder()).append(Character.toUpperCase(str.charAt(0))).append(str.substring(1)).toString();
	}
}
