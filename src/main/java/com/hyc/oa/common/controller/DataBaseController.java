package com.hyc.oa.common.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hyc.oa.common.entity.ColumnInfo;
import com.hyc.oa.common.entity.TableInfo;
import com.hyc.oa.common.service.DataBaseService;
import com.hyc.oa.common.utils.ConstantUtils;
import com.hyc.oa.common.utils.ResultUtils;

@Controller
public class DataBaseController {
	
	private static final String COMMON = "coding/";
    private static final String LIST = COMMON+"list";
    private static final String FORM = COMMON+"form";
    private static final String MAKE_FILE = COMMON+"makeFile";
    private static final String CREATE = COMMON+"create";
    private static final String UPDATE = COMMON+"update";
    private static final String DELETE = COMMON+"delete";
    private static final String DETAIL = COMMON+"detail";
	

	@Autowired
	private DataBaseService dataBaseService;
	
	@RequestMapping(LIST)
	public void list (TableInfo tableInfo , HttpServletRequest request) {
		
		String pageNumber = request.getParameter("pageNumber");
    	String pageSize = request.getParameter("pageSize");
    	int pn = 1;
    	int ps = 10;
    	
    	if (StringUtils.isNotBlank(pageNumber)) {
    		pn = Integer.parseInt(pageNumber);
		}
    	if (StringUtils.isNotBlank(pageSize)) {
    		ps = Integer.parseInt(pageSize);
    	}
    	//分页并查询
        Page page = PageHelper.startPage( pn, ps );
        tableInfo.setTableSchema("oa");
        dataBaseService.tableList(tableInfo);
    	request.setAttribute("page", page);
	}
	
	@RequestMapping(MAKE_FILE)
	@ResponseBody
	public Map<String, Object> pub(HttpServletRequest request) throws Exception {
		String tableName = request.getParameter("tableName");
		List<ColumnInfo> columnsList = dataBaseService.columnList("oa",tableName);
		
		String packagePath = request.getParameter("packagePath");
		if (StringUtils.isBlank(packagePath)) {
			packagePath = ConstantUtils.JAVA_PACKAGE_PATH ;
		}
		String rootPath = "F:/hyc/code/";
		if (StringUtils.isBlank(rootPath)) {
			rootPath = ConstantUtils.SYSTEM_PATH ;
		}
		dataBaseService.makeController( rootPath , packagePath, tableName);
		dataBaseService.makeService( rootPath , packagePath, tableName);
		dataBaseService.makeDao( rootPath , packagePath, tableName);
		dataBaseService.makeEntity( rootPath , packagePath, tableName , columnsList);
		dataBaseService.makeMapper( rootPath,packagePath, tableName , columnsList);
		dataBaseService.makeListJsp( rootPath, tableName , columnsList);
		dataBaseService.makeFormJsp( rootPath , tableName , columnsList);
		dataBaseService.makeDetailJsp( rootPath , tableName , columnsList);
		return ResultUtils.success();
	}
}
