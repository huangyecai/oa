package com.hyc.oa.modules.department.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hyc.oa.common.utils.TreeNode;
import com.hyc.oa.common.utils.TreeNodeUtils;
import com.hyc.oa.modules.department.entity.Department;
import com.hyc.oa.modules.department.service.DepartmentService;
import com.hyc.oa.modules.system.menu.entity.Menu;

/**
 * 

* <p>Title: DepartmentController</p>  

* <p>Description: 部门控制器</p>  

* @author hyc  

* @date 2018年11月20日
 */
@Controller
public class DepartmentController {
	
	private static final String COMMON = "department/";
    private static final String LIST = COMMON+"list";
    private static final String JSON_LIST = COMMON+"jsonList";
    private static final String CREATE = COMMON+"create";
    private static final String UPDATE = COMMON+"update";
    private static final String FORM = COMMON+"form";
    private static final String DELETE = COMMON+"delete";
    private static final String DETAIL = COMMON+"detail";
	
	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping("department/tree")
	public void tree() {
		
	}
	
	@RequestMapping("department/departmentTree")
	public void departmentTree(Department entity, HttpServletRequest request ) {
		Department dept = new Department();
		dept.setStatus(1);
		List<Department> list = departmentService.list(dept);
		
		List<TreeNode> deptTree = new ArrayList<TreeNode>();
		for (Department department : list) {
			TreeNode node = new TreeNode();
			node.setId(department.getId());
			node.setName(department.getName());
			node.setParentId(department.getParentId());
			deptTree.add(node);
		}
		deptTree = TreeNodeUtils.makeTree(deptTree,"");
		request.setAttribute("list", deptTree);
	}
	
	@RequestMapping("department/list")
	public String list(Department entity, HttpServletRequest request) {
		
		List<Department> list = departmentService.list(entity);
		
		request.setAttribute("list", list);
		return "department/departmentTree";
	}

	@RequestMapping(FORM)
	public void name(Department entity,HttpServletRequest request) throws Exception {
		if (StringUtils.isNotBlank(entity.getId())) {
			entity = departmentService.get(entity.getId());
			if (entity == null ) {
				throw new Exception("数据不存在");
			}
		}else {
			if (StringUtils.isBlank(entity.getParentId()) || "0".equals(entity.getParentId())) {
				entity.setParentId("0");
				entity.setParentIds("0");
			}else {
				entity.setParentIds(departmentService.get(entity.getParentId()).getParentIds()+","+entity.getParentId());
			}
		}
		 
		request.setAttribute("entity", entity);
	}
	@RequestMapping(CREATE)
    @ResponseBody
	private Map<String, Object> create(Department entity, HttpServletRequest request) {
		Map<String, Object> map =new HashMap<>();
    	try {
    		Department parent ;
    		if (StringUtils.isNotBlank(entity.getParentId()) && !"0".equals(entity.getParentId())) {
    			parent =  departmentService.get(entity.getParentId());
			}
    		entity.setStatus(1);
    		departmentService.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", 500);
	    	map.put("message", e);
	    	map.put("date", "");
	    	return map;
		}
    	map.put("status", 200);
    	map.put("message", "success");
    	map.put("date", "");
    	return map;
	}
	
	@RequestMapping(UPDATE)
	@ResponseBody
	private Map<String, Object> update(Department entity, HttpServletRequest request) {
		Map<String, Object> map =new HashMap<>();
		try {
			departmentService.save(entity);
		} catch (Exception e) {
			map.put("status", 500);
			map.put("message", e);
			map.put("date", "");
			return map;
		}
		map.put("status", 200);
		map.put("message", "success");
		map.put("date", "");
		return map;
	}
	
	@RequestMapping(DETAIL)
	private void detail(Department entity, HttpServletRequest request) throws Exception {
		if (StringUtils.isNotBlank(entity.getId())) {
			entity = departmentService.get(entity.getId());
			if (entity == null ) {
				throw new Exception("数据不存在");
			}
		}else {
			throw new Exception("请选择正确的数据");
		}
		 
		request.setAttribute("entity", entity);
	}
	
	@RequestMapping(DELETE)
    @ResponseBody
    public Map<String, Object> delete(Menu entity,HttpServletRequest request) {
    	Map<String, Object> map =new HashMap<>();
    	boolean result = false;
    	try {
    		if (StringUtils.isBlank(entity.getId())) {
    			throw new Exception("请选择正确的数据");
			}
    		result = departmentService.delete(entity.getId());
    	} catch (Exception e) {
    		map.put("status", 500);
    		map.put("message", e);
    		map.put("date", "");
    		return map;
    	}
    	if (result) {
    		map.put("status", 200);
        	map.put("message", "success");
        	map.put("date", "");
		}else {
			map.put("status", 500);
	    	map.put("message", "error");
	    	map.put("date", "");
		}
    	
    	return map;
    }
}
