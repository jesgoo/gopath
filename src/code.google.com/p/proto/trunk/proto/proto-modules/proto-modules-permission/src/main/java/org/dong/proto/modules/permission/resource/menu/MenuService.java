package org.dong.proto.modules.permission.resource.menu;

import java.util.List;

import org.dong.proto.core.persistence.dao.BaseDao;
import org.dong.proto.modules.permission.user.User;



public interface MenuService extends BaseDao<Menu>{
	/**
	 * 创建菜单树
	 * @param menuName	菜单名称
	 * @param nodes		菜单的节点
	 */
	public void createTree(String menuName,MenuVO[] nodes);
	/**
	 * 添加菜单节点
	 * @param parentNode
	 * @param childNode 
	 */
	public void addChildNode(Menu parentNode, Menu childNode);

	/**
	 * 删除菜单节点
	 * @param node
	 */
	public void deleteNode(Menu node);

	/**
	 * 根据菜单根名称，查询菜单
	 * @param menuName 
	 */
	public List<Menu> queryMenuTree(String menuName);

	/**
	 * 添加菜单的root节点
	 * @param root
	 */
	public void addRootNode(Menu root);
	
	/**
	 * 查询页面显示的json数据
	 * @return
	 */
	public String queryJsonData();

	/**
	 * 查询所有的menutree
	 * @return
	 */
	public List<List<Menu>> queryAllMenuTree();

	/**
	 * 更新node
	 * @param node
	 */
	public void updateNode(Menu node);

	/**
	 * ajax查询菜单树
	 * @param user 
	 * @return
	 */
	public String queryJsonTreeMenu(User user);
	
	/**
	 * 获取查单
	 * 放入session中
	 * @param dbUser
	 * @return
	 */
	public String getMenu(User dbUser);
	
	/**
	 * 查询添加权限时要用的菜单树
	 * 需要查询所有的
	 * @return
	 */
	public String queryJsonAllTreeMenu();
	/**
	 * 查询角色的菜单
	 * @param roleId
	 * @param privilegeId 
	 * @return
	 */
	public String queryJsonAllTreeMenu4Role(int roleId, int privilegeId);

}
