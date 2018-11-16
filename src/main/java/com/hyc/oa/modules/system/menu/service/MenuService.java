package com.hyc.oa.modules.system.menu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyc.oa.common.utils.TreeNode;
import com.hyc.oa.modules.system.menu.dao.MenuDao;
import com.hyc.oa.modules.system.menu.entity.Menu;

/**  

* <p>Title: MenuService</p>  

* <p>Description: 菜单service</p>  

* @author hyc  

* @date 2018年10月26日  

*/
@Service
public class MenuService {
	
	@Autowired
	private MenuDao menuDao;

	/**
	 * 	保存
	 * @param entity
	 * @return
	 */
	@Transactional
	public String save(Menu entity) {
		String id = entity.getId();
		if (StringUtils.isBlank(id) || get(id) == null) {
			create( entity );
		}else {
			update( entity );
		}
		return entity.getId();
	}
	
	/**
	 * 	新增
	 * @param entity
	 * @return
	 */
	private String create(Menu entity) {
		entity.preInsert();
		menuDao.insert( entity );
		return entity.getId();
	}
	
	/**
	 * 	修改
	 * @param entity
	 * @return
	 */
	private String update(Menu entity) {
		entity.preUpdate();
		menuDao.update( entity );
		return entity.getId();
	}
	
	/**
	 * 	根据id获取菜单
	 * @param id
	 * @return
	 */
	public Menu get(String id) {
		return menuDao.getById(id);
	}
	
	public List<Menu> list(Menu entity) {
		return menuDao.list(entity);
	}
	
	/**
	 * 删除菜单
	 * @param id
	 * @return
	 */
	@Transactional
	public boolean delete(String id) {
		if (get(id) != null) {
			menuDao.delete(id);
			return true;
		}else {
			return false;
		}
	}
	
	public  List<TreeNode> makeTree( List<Menu> menuList , String openId) {
		List<TreeNode> resultList = new ArrayList<TreeNode>();
		Map<String, List<TreeNode>> map = new HashMap<String, List<TreeNode>>();
		for (Menu menu : menuList) {
			TreeNode node = menu.toTreeNode();
			if (menu.getId().equals(openId)) {
				node.setOpen(true);
			}
			if (StringUtils.isNotBlank(menu.getParentId()) && "0".equals(menu.getParentId())) {
				resultList.add(node);
			} else {
				List<TreeNode> subList = map.get(menu.getParentId());
				if (subList == null || subList.size() == 0) {
					subList = new ArrayList<TreeNode>();
				}
				subList.add(node);
				map.put(menu.getParentId(), subList);
			}
		}
 
		return makeMenuTree(resultList, map);

	}

	public  List<TreeNode> makeMenuTree(List<TreeNode> rootList, Map<String, List<TreeNode>> map) {
		for (TreeNode node : rootList) {
			List<TreeNode> subList = map.get(node.getId());
			if (subList != null && subList.size() > 0) {
				subList = makeMenuTree(subList, map);
			}
			node.setChildren(subList);
		}
		return rootList;
	}
	
	public void treeToString(List<Menu> menuTree) {
		StringBuilder result = null;
		result.append("[");
		for (Menu menu : menuTree) {
			String menuStr = "{";
			menuStr += "id:"+menu.getId();
			menuStr += ",name:"+menu.getName();
			menuStr += ", open:true, noR:true";
			menuStr += "}";
			result.append(menuStr);
		}
		result.append("]");
	}
	
}
