package com.hyc.oa.modules.user.controller;

import com.hyc.oa.common.utils.TreeNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @RequestMapping("/")
    @ResponseBody
    public String test(HttpServletRequest request){
        return "OK";
    }
    @RequestMapping("index")
    public String index(HttpServletRequest request){
        TreeNode rootNode = new TreeNode();
        rootNode.setId(1);
        rootNode.setText("系统管理");
        rootNode.setUrl("");
        rootNode.setCode("XTGL");
        rootNode.setState("open");

        TreeNode node1 = new TreeNode();
        node1.setId(1);
        node1.setText("系统管理");
        node1.setUrl("");
        node1.setCode("XTGL");
        node1.setState("open");

        TreeNode node2 = new TreeNode();
        node2.setId(1);
        node2.setText("系统管理");
        node2.setUrl("");
        node2.setCode("XTGL");
        node2.setState("open");

        TreeNode node3 = new TreeNode();
        node3.setId(1);
        node3.setText("系统管理");
        node3.setUrl("");
        node3.setCode("XTGL");
        node3.setState("open");

        List<TreeNode> nodelist = new ArrayList<>();
        nodelist.add(node3);

        node2.setChildren(nodelist);

        nodelist = new ArrayList<>();
        nodelist.add(node2);
        nodelist.add(node1);

        rootNode.setChildren(nodelist);

        nodelist = new ArrayList<>();
        nodelist.add(rootNode);

        request.setAttribute("nemuList",nodelist);
        return "index";
    }
}
