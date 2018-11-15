package com.hyc.oa.modules.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hyc.oa.common.utils.TreeNode;
import com.hyc.oa.modules.user.entity.User;
import com.hyc.oa.modules.user.service.UserService;

@Controller
public class UserController {
	
	private static  final String COMMAND = "/user/";
    private  static final String TEST = COMMAND + "test";
    private  static final String LIST = COMMAND + "list";
    private  static final String LIST1 = COMMAND + "list1";
    
	@Autowired
    private UserService userService;

    @RequestMapping(TEST)
    @ResponseBody
    public Map<String,Object> test(HttpServletRequest request){
       User user = userService.getById("1");

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("result",user);
        return  map;
    }
//    @RequestMapping("/")
//    @ResponseBody
//    public String test(HttpServletRequest request){
//        return "OK";
//    }
    @RequestMapping("index")
    public String index(HttpServletRequest request){
         
        return "index";
    }
}
