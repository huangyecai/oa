package com.hyc.oa.modules.user.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hyc.oa.common.controller.DateEditor;
import com.hyc.oa.common.utils.ConstantUtils;
import com.hyc.oa.common.utils.ResultUtils;
import com.hyc.oa.modules.department.entity.Department;
import com.hyc.oa.modules.department.service.DepartmentService;
import com.hyc.oa.modules.user.entity.User;
import com.hyc.oa.modules.user.service.UserService;

@Controller
public class UserController {
	
	private static  final String COMMON = "/user/";
    private  static final String LIST = COMMON + "list";
    private static final String FORM = COMMON+"form";
    private static final String CREATE = COMMON+"create";
    private static final String UPDATE = COMMON+"update";
    private static final String DELETE = COMMON+"delete";
    private static final String DETAIL = COMMON+"detail";
	private static final String FORBID = COMMON+"forbid";
	private static final String ENABLE = COMMON+"enable";
    
	@Autowired
    private UserService userService;
	
	@Autowired
	private DepartmentService departmentService;
	
	//只需要加上下面这段即可，注意不能忘记注解
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		//转换日期
		binder.registerCustomEditor(Date.class, new DateEditor());// DateEditor为自定义日期编辑器
	}

	@RequestMapping(LIST)
	public void list(User entity,HttpServletRequest request) {
		String pageNumber = request.getParameter("pageNumber");
    	String pageSize = request.getParameter("pageSize");
		int pn = 1;
    	int ps = 20;
    	if (StringUtils.isNotBlank(pageNumber)) {
    		pn = Integer.parseInt(pageNumber);
		}
    	if (StringUtils.isNotBlank(pageSize)) {
    		ps = Integer.parseInt(pageSize);
    	}
    	if ( request.getParameter("status") == null) {
			entity.setStatus(ConstantUtils.ALIVE);
		} 
    	request.setAttribute("status", entity.getStatus());
    	//分页并查询
        Page page = PageHelper.startPage( pn, ps );
        userService.list(entity);
        request.setAttribute("page", page);
	}
	
    /**
	 * 进入新建/编辑页面
	 * @param entity
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(FORM)
	public void form ( User entity , HttpServletRequest request ) throws Exception {
		if (StringUtils.isNotBlank(entity.getId())) {
			entity = userService.get(entity.getId());
			if (entity == null ) {
				throw new Exception("数据不存在");
			}
		}
		request.setAttribute("entity", entity);
	}

	/**
	 * 新建
	 */
	@RequestMapping(CREATE)
	@ResponseBody
	public Map<String, Object> create(User entity, HttpServletRequest request) {
		String result = "" ;
		try {
			result = userService.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.error();
		}
		if ( StringUtils.isNotBlank(result)) {
			return ResultUtils.success();
		}else {
			return ResultUtils.error();
		}
		
	}

	/**
	 * 更新
	 */
	@RequestMapping(UPDATE)
	@ResponseBody
	public Map<String, Object> update(User entity, HttpServletRequest request) {
		String result = "" ;
		try {
			result = userService.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.error();
		}
		if ( StringUtils.isNotBlank(result)) {
			return ResultUtils.success();
		}else {
			return ResultUtils.error();
		}
	}

	/**
	 * 删除
	 */
	@RequestMapping(DELETE)
	@ResponseBody
	public Map<String, Object> delete(User entity, HttpServletRequest request) {
		boolean result = false;
		try {
			result = userService.delete(entity.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.error();
		}
		
		if ( result ) {
			return ResultUtils.success();
		}else {
			return ResultUtils.error();
		}
	}
	
	/**
	 * 禁用
	 */
	@RequestMapping(FORBID)
	@ResponseBody
	public Map<String, Object> forbid(User entity, HttpServletRequest request) {
		boolean result = false;
		try {
			result = userService.forbid(entity.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.error();
		}
		
		if ( result ) {
			return ResultUtils.success();
		}else {
			return ResultUtils.error();
		}
	}
	
	/**
	 * 禁用
	 */
	@RequestMapping(ENABLE)
	@ResponseBody
	public Map<String, Object> enable(User entity, HttpServletRequest request) {
		boolean result = false;
		try {
			result = userService.enable(entity.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.error();
		}
		
		if ( result ) {
			return ResultUtils.success();
		}else {
			return ResultUtils.error();
		}
	}
	
	@RequestMapping(DETAIL)
	private void detail(User entity, HttpServletRequest request) throws Exception {
		if (StringUtils.isNotBlank(entity.getId())) {
			entity = userService.get(entity.getId());
			if (entity == null ) {
				throw new Exception("数据不存在");
			}
			Department dept = departmentService.get(entity.getOrganizationId());
			
			if (dept !=null && dept.getStatus() != ConstantUtils.DEAD) {
				entity.setOrganization(dept);
			}
		}else {
			throw new Exception("请选择正确的数据");
		}
		 
		request.setAttribute("entity", entity);
	}
   
}
