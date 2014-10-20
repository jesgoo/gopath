package org.dong.proto.modules.permission.department;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dong.proto.core.persistence.dao.DaoSupport;
import org.dong.proto.core.persistence.query.QueryCondition;
import org.dong.proto.modules.permission.constants.OSPConstants;
import org.dong.proto.modules.permission.permission.Permission;
import org.dong.proto.modules.permission.role.Role;
import org.dong.proto.modules.permission.role.RoleService;
import org.dong.proto.modules.permission.user.User;
import org.dong.proto.util.easyui.EasyuiUtil;
import org.dong.proto.util.easyui.TreeNodeInfo;
import org.springframework.stereotype.Service;

@Service("departmentService")
public class DepartmentServiceImpl extends DaoSupport<Department> implements DepartmentService {

	private static final String VALUE = "value";
	private static final String PID = "pid";
	private static final String ROOT_NAME = "rootName";
	private static final String LEFT = "lft";
	private static final String NODE_TYPE = "nodeType";
	private static final String ID = "id";
	private static final String TABLE_NAME="T_DEPARTMENT";
	
	public void addChildNode(Department parentNode, Department childNode) {
//		第一步：选取要被插入new child的外面这个圆的lft的值
//		第二步：原有的数据中所有的rgt如果>第一步中得到的lft的值，那么全部+2
//		第三步：原有的数据中所有的lft如果>第一步中得到的lft的值，那么全部+2
//		第四步：将插入的节点的lft与rgt的设计，新节点的lft =第一步中的lft+1，新节点的rgt=第一步中
		
//		--第一步
//		SELECT lft FROM t_resource where id='100';
//		--这一步我们得到的值为：1
//		--第二步：
//		UPDATE t_resource SET rgt = rgt +2 WHERE rgt >1;
//		--第三步：
//		UPDATE t_resource SET lft = lft +2 WHERE lft >1;
//		--第四步：
//		INSERT INTO t_resource (id, name, value, lft, rgt,pid) VALUES ('110','报表查询','报表查询的url', (1+1), (1 +2),'100');
		//1
		Department p = query(ID, parentNode.getId());
		int p_lft = p.getLft();
		//2
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE "+TABLE_NAME+" node SET rgt = rgt +2 WHERE rgt >").append(p_lft).append(" and node.rootName = '"+p.getRootName()+"' ");
		excuteSQL(sql.toString());
		//3
		sql = new StringBuffer();
		sql.append("UPDATE "+TABLE_NAME+" node SET lft = lft +2 WHERE lft >").append(p_lft).append(" and node.rootName = '"+p.getRootName()+"' ");
		excuteSQL(sql.toString());
		//4
		childNode.setPid(p.getId());
		childNode.setRootName(p.getRootName());
		childNode.setRemark(p.getRootName());
		childNode.setLft(p_lft+1);
		childNode.setRgt(p_lft+2);
		childNode.setNodeType(Department.NODE_TYPE_CHILD);
		add(childNode);
		
	}

	public void addRootNode(Department root) {
		//验证菜单是否已经存在了
		if (checkRootIsExisted(root)) {
//			throw new Exception(root.getKey()+"该菜单已存在了！");
			System.out.println(root.getName()+"该菜单已存在了！");
			return;
		}
		root.setRootName(root.getName());
		root.setRemark(root.getName());
		root.setLft(1);
		root.setRgt(2);
		root.setNodeType(Department.NODE_TYPE_ROOT);
		add(root);
	}

	/**
	 * 检测菜单的根节点是否已经存在
	 * @param root
	 * @return
	 */
	private boolean checkRootIsExisted(Department root) {
		return null != query("name", root.getName());
	}
	
	public void deleteNode(Department node) {
//		第一步：选取要被删除的菜单的lft的值，rgt的值，以及宽度（width=rgt-lft+1）；
//		第二步：删除所有的位于第一步中得到的lft与rgt之间的节点；
//		第三步：将所有的右边界大于第一步中得到的rgt的所有节点的rgt的值减去第一步中得到的width
//		第四步：将所有的左边界大于第一步中得到的rgt的所有节点的lft的值减去第一步中得到的width
		
//		--第一步
//		--获取的 lft=2,rgt=7,width=6
//		SELECT lft, rgt, (rgt - lft +1) width FROM t_resource WHERE id ='120';
//		--第二步：
//		DELETE FROM t_resource WHERE lft BETWEEN 2 AND 7;
//		--第三步：
//		UPDATE t_resource SET rgt = rgt - 6 WHERE rgt > 7;
//		--第四部：
//		UPDATE t_resource SET lft = lft - 6 WHERE lft > 7;
		
		//1
		Department p = query(ID, node.getId());
		int lft = p.getLft();
		int rgt = p.getRgt();
		int width = (rgt-lft)+1;
		//2
		//删除菜单
		StringBuffer sql = new StringBuffer();
//		sql.append("DELETE FROM t_resource node WHERE  lft BETWEEN ").append(lft).append(" and ").append(rgt).append(" and node.remark = '"+node.getRootName()+"' ");
//		excuteSQL(sql.toString());
		//现在需要级联删除权限中的引用，所有不能直接用delete，要用hibernate的级联删除
		QueryCondition conditions = QueryCondition.init()
		.addBetween(LEFT, lft, rgt).
		addEqual(ROOT_NAME, p.getRootName());
		List<Department> resources = queryPage(conditions, 0, 0);
		for(Department r : resources){
			delete(r);
		}
		
		//3
		sql = new StringBuffer();
		sql.append("UPDATE "+TABLE_NAME+" node SET rgt = rgt - ").append(width).append(" WHERE rgt > ").append(rgt).append(" and node.rootName = '"+p.getRootName()+"' ");
		excuteSQL(sql.toString());
		//4
		sql = new StringBuffer();
		sql.append("UPDATE "+TABLE_NAME+" node SET lft = lft - ").append(width).append(" WHERE lft > ").append(rgt).append(" and node.rootName = '"+p.getRootName()+"' ");
		excuteSQL(sql.toString());
				
	}

	public List<List<Department>> queryAllDepartmentTree() {
		List<Department> roots = queryPage(QueryCondition.init().addEqual(NODE_TYPE, Department.NODE_TYPE_ROOT) , 0, 0);
		List<List<Department>> trees = new ArrayList<List<Department>>();
		for(Department r : roots){
			trees.add(this.queryDepartmentTree(r.getName()));
		}
		return trees;
	}

	public List<Department> queryDepartmentTree(String menuName) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ")
			.append("node.id,")
			.append("node.name ,")
			.append("node.lft,")
			.append("node.rgt,")
			.append("node.value ,")
//			.append("(COUNT(parent.id)-1) menuLevel,")
			.append("node.pid ,")
			.append("node.remark, ")
			.append("node.nodeType ,")
			.append("node.rootName ")
			.append("FROM "+TABLE_NAME+" node,"+TABLE_NAME+" parent ")
			.append("WHERE ")
			.append("node.lft BETWEEN parent.lft AND parent.rgt ")
			.append("and node.rootName = '"+menuName+"' ")
			.append("GROUP BY node.id,node.name,node.lft,node.rgt,node.value,node.pid,node.remark,node.nodeType,node.rootName ")
			.append("ORDER BY  node.lft");//一定要转换成number排序，不然只会比较第一个字符
		
		return querySQLByEntity(sql.toString(),Department.class);
	}

	public void updateNode(Department node) {
		Department r = query(ID, node.getId());
		r.setName(node.getName());
		r.setValue(node.getValue());
		update(r);
	}

	public String queryJsonData() {
		List<List<Department>> trees = queryAllDepartmentTree();
		List<DepartmentVO> menuTrees = new ArrayList<DepartmentVO>();
		for(List<Department> tree : trees ){
			for(Department u:tree){
				DepartmentVO m = new DepartmentVO(u);
				DepartmentVO parent = getParentNode(menuTrees,m);
				//如果有父节点，则添加到父节点的子节点list中，否则直接添加到treeList中，作为一棵新的tree
				if (null != parent) {
					parent.addChild(m);
				}else{
					menuTrees.add(m);
				}
				
			}
		}
		
		return EasyuiUtil.getTreeGrid(menuTrees);
	}

	/**
	 * 获取父节点
	 * @param menuTree	父节点所在的tree
	 * @param m2		查找父节点的子节点
	 * @return
	 */
	private DepartmentVO getParentNode(List<DepartmentVO> menuTree, DepartmentVO m2) {
		DepartmentVO p = null;
		for(DepartmentVO m : menuTree){
			//是否为本层节点
			if (m.getId()==m2.getPid()) {
				p = m;
				break;
			}
			//如果不是本层节点，就检测是否为其子节点
			p = this.getParentNode(m.getChildren(), m2);
		}
		return p;
	}
	
	public String queryJsonTreeDepartment(User user) {
		List<TreeNodeInfo> menuTree = new ArrayList<TreeNodeInfo>();
		List<Department> items = this.queryDepartmentTree(OSPConstants.BACK_MENU_NAME);
		for (Iterator<Department> iterator = items.iterator(); iterator.hasNext();) {
			Department r = iterator.next();
			TreeNodeInfo node = new TreeNodeInfo(r.getId()+"", r.getName());
			node.addAttribute(PID, r.getPid()+"");
			node.addAttribute(VALUE, r.getValue());
			TreeNodeInfo parentNode = getParentNode(menuTree,node);
			if (null != parentNode) {
				parentNode.addChild(node);
			}else{
				menuTree.add(node);
			}
			
		}
		
		return EasyuiUtil.getTreeInfo(menuTree);
	}
	
	/**
	 * 获取tree插件的父节点，用于现在树形菜单
	 * @param menuTree
	 * @param node
	 * @return
	 */
	private TreeNodeInfo getParentNode(List<TreeNodeInfo> menuTree,
			TreeNodeInfo node) {
		TreeNodeInfo p = null;
		for(TreeNodeInfo m : menuTree){
			//是否为本层节点
			if (m.getId().equals(node.getAttribute(PID))) {
				p = m;
				break;
			}
			//如果不是本层节点，就检测是否为其子节点
			p = this.getParentNode(m.getChildren(), node);
		}
		return p;
	}
	
	public void createTree(String menuName,DepartmentVO[] nodes) {
		//添加root
		DepartmentVO root = new DepartmentVO(menuName,menuName,0);
		root.setNodeType(Department.NODE_TYPE_ROOT);
		root.setRootName(menuName);
		nodes[0] = root;
		//关联树形关系
		for (int i = 1; i < nodes.length; i++) {
			nodes[i].setRootName(menuName);
			nodes[nodes[i].getPid()].addChild(nodes[i]);
		}
		//保存tree
		addTree(root);
	}
	
	/**
	 * 保存菜单树
	 * @param r
	 */
	private void addTree(DepartmentVO r) {
		//转换成Department对象
		Department root = new Department();
		root.setName(r.getName());
		root.setValue(r.getValue());
		root.setRootName(r.getRootName());
		
		//保存Root节点
		if (r.getNodeType() == Department.NODE_TYPE_ROOT) {
			addRootNode(root);
		}
		
		//保存子节点
		for(DepartmentVO vo : r.getChildren()){
			addChildNode(root,vo.getName(),vo.getValue());
			//递归保存节点
			this.addTree(vo);
		}
	}
	
	private Department addChildNode(Department parent, String name, String value) {
		
		//获取父节点
		Department p = getNodeInAllDepartmentTree(parent.getName(),parent.getRootName());
		//初始化子节点
		Department child = new Department();
		child.setName(name);
		child.setValue(value);
		//如果父节点存在，则添加子节点
		if (null != p) {
			addChildNode(p,child);
		}
		return child;
	}

	/**
	 * 在所有菜单树中查找节点,name和rootName作为主键
	 * @param name
	 * @param rootName
	 * @return
	 */
	private Department getNodeInAllDepartmentTree( String name, String rootName) {
		
		
		Department node = null;
		//查询菜单
		List<List<Department>> trees = queryAllDepartmentTree();
		for(List<Department> tree : trees ){
//			System.out.println("--------"+tree.get(0).getKey()+"----------");
			for(Department u:tree){
//				System.out.println(u.getId()+"-"+u.getKey()+"-"+u.getValue());
				if (name.equals(u.getName())&& rootName.equals(u.getRootName())) {
					node = u;
					break;
				}
			}
		}
		return node;
	}


	@Override
	public String queryJsonAllTreeDepartment() {
		List<TreeNodeInfo> menuTree = new ArrayList<TreeNodeInfo>();
		
		List<List<Department>> trees = queryAllDepartmentTree();
		for(List<Department> tree : trees ){
			List<Department> items = tree;
			for (Iterator<Department> iterator = items.iterator(); iterator.hasNext();) {
				Department r = iterator.next();
				TreeNodeInfo node = new TreeNodeInfo(r.getId()+"", r.getName());
				node.addAttribute(PID, r.getPid()+"");
				node.addAttribute(VALUE, r.getValue());
				TreeNodeInfo parentNode = getParentNode(menuTree,node);
				if (null != parentNode) {
					parentNode.addChild(node);
				}else{
					menuTree.add(node);
				}
				
			}
		}
		
		
		
		return EasyuiUtil.getTreeInfo(menuTree);
	}

	@Override
	public String queryJsonAllTreeDepartment(int roleId,int privilegeId) {
		List<Permission> permissions = this.getPermissions(roleId, privilegeId);
		List<TreeNodeInfo> menuTree = new ArrayList<TreeNodeInfo>();
		List<List<Department>> trees = queryAllDepartmentTree();
		for(List<Department> tree : trees ){
			List<Department> items = tree;
			for (Iterator<Department> iterator = items.iterator(); iterator.hasNext();) {
				Department r = iterator.next();
				TreeNodeInfo node = new TreeNodeInfo(r.getId()+"", r.getName());
				node.addAttribute(PID, r.getPid()+"");
				node.addAttribute(VALUE, r.getValue());
				TreeNodeInfo parentNode = getParentNode(menuTree,node);
				if (null != parentNode) {
					parentNode.addChild(node);
				}else{
					menuTree.add(node);
				}
				
				//判断角色是否有该节点权限
				if (hasPermission(permissions,r.getId())) {
					node.setChecked(true);
				}
				
			}
		}
		
		return EasyuiUtil.getTreeInfo(menuTree);
	}

	private boolean hasPermission(List<Permission> permissions, int resourceId) {
		for(Permission p : permissions){
			if (OSPConstants.RESOURCE_TYPE_MENU.equals(p.getResource().getResourceType()) 
					&& p.getResource().getId() == resourceId) {
				return true;
			}
		}
		return false;
	}
	
	@javax.annotation.Resource
	private RoleService roleService;
	
	private List<Permission> getPermissions(int roleId,int privilegeId) {
		Role role = roleService.get(roleId);
		List<Permission> list = new ArrayList<Permission>();
		//如果privilegeId < 0表示初次获取，直接返回角色的权限就可以了
		if (privilegeId < 0) {
			list = role.getPermissions();
		}
		//获取指定操作的权限
		else {
			for(Permission p :role.getPermissions()){
				if (OSPConstants.RESOURCE_TYPE_MENU.equals(p.getResource().getResourceType()) 
						&& p.getPrivilege().getId() == privilegeId) {
					list.add(p);
				}
			}
		}
		
		
		return list;
	}
	
}
