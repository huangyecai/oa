package com.hyc.oa.modules.system.menu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hyc.oa.modules.system.menu.entity.Menu;
import com.hyc.oa.modules.system.menu.service.MenuService;

@Controller
public class MenuController {
    private static final String COMMON = "menu/";
    private static final String LITS = "menu/list";
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
    public String create(Menu entity,HttpServletRequest request) {
    	menuService.save(entity);
    	return LITS;
	}
    
    @RequestMapping(UPDATE)
    public String update(Menu entity,HttpServletRequest request) {
    	menuService.save(entity);
    	return LITS;
    }
    
    @RequestMapping(LITS)
    @ResponseBody
    public List<Menu> list(Menu entity,HttpServletRequest request){
    	return menuService.list(entity);
    }

    @RequestMapping("system/menu/menuTree")
    private void menuTree  (HttpServletRequest request){

    }
}
