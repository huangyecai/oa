package com.hyc.oa.modules.system.menu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.NumberUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hyc.oa.modules.system.menu.entity.Menu;
import com.hyc.oa.modules.system.menu.service.MenuService;

@Controller
public class MenuController {
    private static final String COMMON = "system/menu/";
    private static final String LIST = COMMON+"list";
    private static final String JSON_LIST = COMMON+"jsonList";
    private static final String CREATE = COMMON+"create";
    private static final String UPDATE = COMMON+"update";
    private static final String FORM = COMMON+"form";
    
    @Autowired
    private MenuService menuService;
    
    @RequestMapping(FORM)
    public void form(Menu entity,HttpServletRequest request) throws Exception {
		if (StringUtils.isNotBlank(entity.getId())) {
			entity = menuService.get(entity.getId());
			if (entity == null ) {
				throw new Exception("数据不存在");
			}
		}
		request.setAttribute("entity", entity);
	}

    @RequestMapping(CREATE)
    @ResponseBody
    public Map<String, Object> create(Menu entity,HttpServletRequest request) {
    	Map<String, Object> map =new HashMap<>();
    	try {
    		menuService.save(entity);
		} catch (Exception e) {
			map.put("status", 500);
	    	map.put("message", "success");
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
    public Map<String, Object> update(Menu entity,HttpServletRequest request) {
    	Map<String, Object> map =new HashMap<>();
    	try {
    		menuService.save(entity);
		} catch (Exception e) {
			map.put("status", 500);
	    	map.put("message", "success");
	    	map.put("date", "");
	    	return map;
		}
    	map.put("status", 200);
    	map.put("message", "success");
    	map.put("date", "");
    	return map;
    }
    
    @RequestMapping(LIST)
    public void list(Menu entity,HttpServletRequest request){
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
    	List<Menu> list = menuService.list(entity);
//    	request.setAttribute("list", list);
    	request.setAttribute("page", page);
    }
    
    @RequestMapping(JSON_LIST)
    @ResponseBody
    public Map<String, Object> jsonList(Menu entity,HttpServletRequest request){
    	List<Menu> list = menuService.list(entity);
    	List<Map<String, Object>> list1 = new ArrayList<>();
    	for (Menu menu : list) {
    		Map<String, Object> map1 = new HashMap<String, Object>();
    		map1.put("id", menu.getId());
    		map1.put("name", menu.getName());
    		list1.add(map1);
		}
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("total", list.size());
    	map.put("rows", list1);
    	return map;
    }

    @RequestMapping("system/menu/menuTree")
    private void menuTree  (HttpServletRequest request){

    }
}
