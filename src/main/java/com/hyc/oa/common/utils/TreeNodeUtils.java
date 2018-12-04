package com.hyc.oa.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class TreeNodeUtils {
	
	public static  List<TreeNode> makeTree(List<TreeNode> nodeList , String openId) {
		List<TreeNode> resultList = new ArrayList<TreeNode>();
		Map<String, List<TreeNode>> map = new HashMap<String, List<TreeNode>>();
		for (TreeNode node : nodeList) {
			if (node.getId().equals(openId)) {
				node.setOpen(true);
			}
			if (StringUtils.isNotBlank(node.getParentId()) && "0".equals(node.getParentId())) {
				resultList.add(node);
			} else {
				List<TreeNode> subList = map.get(node.getParentId());
				if (subList == null || subList.size() == 0) {
					subList = new ArrayList<TreeNode>();
				}
				subList.add(node);
				map.put(node.getParentId(), subList);
			}
		}
 
		return makeMenuTree(resultList, map);

	}

	public static  List<TreeNode> makeMenuTree(List<TreeNode> rootList, Map<String, List<TreeNode>> map) {
		for (TreeNode node : rootList) {
			List<TreeNode> subList = map.get(node.getId());
			if (subList != null && subList.size() > 0) {
				subList = makeMenuTree(subList, map);
			}
			node.setChildren(subList);
		}
		return rootList;
	}
}
