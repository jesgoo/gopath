package org.dong.proto.modules.permission.department;

import java.util.List;

import org.dong.proto.core.persistence.dao.BaseDao;



public interface DepartmentService extends BaseDao<Department>{
	/**
	 * 创建菜单树
	 * @param menuName	菜单名称
	 * @param nodes		菜单的节点
	 */
	public void createTree(String menuName,DepartmentVO[] nodes);
	/**
	 * 添加菜单节点
	 * @param parentNode
	 * @param childNode 
	 */
	public void addChildNode(Department parentNode, Department childNode);

	/**
	 * 删除菜单节点
	 * @param node
	 */
	public void deleteNode(Department node);

	/**
	 * 根据菜单根名称，查询菜单
	 * @param menuName 
	 */
	public List<Department> queryDepartmentTree(String menuName);

	/**
	 * 添加菜单的root节点
	 * @param root
	 */
	public void addRootNode(Department root);
	
	/**
	 * 查询页面显示的json数据
	 * @return
	 */
	public String queryJsonData();

	/**
	 * 查询所有的menutree
	 * @return
	 */
	public List<List<Department>> queryAllDepartmentTree();

	/**
	 * 更新node
	 * @param node
	 */
	public void updateNode(Department node);

	
	/**
	 * 查询添加权限时要用的菜单树
	 * 需要查询所有的
	 * @return
	 */
	public String queryJsonAllTreeDepartment();
	/**
	 * 查询角色的菜单
	 * @param roleId
	 * @param privilegeId 
	 * @return
	 */
	public String queryJsonAllTreeDepartment(int roleId, int privilegeId);

}
