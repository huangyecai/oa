package com.hyc.oa.modules.role.controller;

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
import com.hyc.oa.common.utils.ConstantUtils;
import com.hyc.oa.common.utils.ResultUtils;
import com.hyc.oa.modules.role.entity.Role;
import com.hyc.oa.modules.role.service.RoleService;

@Controller
public class RoleController {
	
	private static final String COMMON = "role/";
    private static final String LIST = COMMON+"list";
    private static final String FORM = COMMON+"form";
    private static final String CREATE = COMMON+"create";
    private static final String UPDATE = COMMON+"update";
    private static final String DELETE = COMMON+"delete";
    private static final String DETAIL = COMMON+"detail";

    @Autowired
    private RoleService roleService;
    
    /**
     * 列表查询
     * @param entity
     * @param request
     */
	@RequestMapping(LIST)
	public void list ( Role entity , HttpServletRequest request) {
		
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
    	if (entity.getStatus() == null) {
    		entity.setStatus(ConstantUtils.ALIVE);
		}
    	//分页并查询
        Page page = PageHelper.startPage( pn, ps );
    	List<Role> list = roleService.list(entity);
    	request.setAttribute("page", page);
	}
	
	/**
	 * 进入新建/编辑页面
	 * @param entity
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(FORM)
	public void form ( Role entity , HttpServletRequest request ) throws Exception {
		if (StringUtils.isNotBlank(entity.getId())) {
			entity = roleService.get(entity.getId());
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
	public Map<String, Object> create(Role entity, HttpServletRequest request) {
		String result = "" ;
		try {
			result = roleService.save(entity);
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
	public Map<String, Object> update(Role entity, HttpServletRequest request) {
		String result = "" ;
		try {
			result = roleService.save(entity);
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
	public Map<String, Object> delete(Role entity, HttpServletRequest request) {
		boolean result = false;
		try {
			result = roleService.delete(entity.getId());
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
	private void detail(Role entity, HttpServletRequest request) throws Exception {
		if (StringUtils.isNotBlank(entity.getId())) {
			entity = roleService.get(entity.getId());
			if (entity == null ) {
				throw new Exception("数据不存在");
			}
		}else {
			throw new Exception("请选择正确的数据");
		}
		 
		request.setAttribute("entity", entity);
	}
}
