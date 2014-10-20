package org.dong.proto.webapp;


import java.util.ArrayList;
import java.util.List;

import org.dong.proto.core.factory.BeanCreateFactory;
import org.dong.proto.modules.permission.constants.OSPConstants;
import org.dong.proto.modules.permission.department.DepartmentService;
import org.dong.proto.modules.permission.department.DepartmentVO;
import org.dong.proto.modules.permission.permission.Permission;
import org.dong.proto.modules.permission.privilege.Privilege;
import org.dong.proto.modules.permission.privilege.PrivilegeService;
import org.dong.proto.modules.permission.resource.common.CommonResource;
import org.dong.proto.modules.permission.resource.common.CommonResourceService;
import org.dong.proto.modules.permission.resource.menu.Menu;
import org.dong.proto.modules.permission.resource.menu.MenuService;
import org.dong.proto.modules.permission.resource.menu.MenuVO;
import org.dong.proto.modules.permission.role.Role;
import org.dong.proto.modules.permission.role.RoleService;
import org.dong.proto.modules.permission.user.User;
import org.dong.proto.modules.permission.user.UserService;
import org.dong.proto.util.encrypt.MD5Encrypt;
import org.junit.Test;

public class DataInitTest {

	
	List<Role> roles = new ArrayList<Role>();
	List<User> users = new ArrayList<User>();
	
	@Test
	public void testCreateDB(){
		//菜单
		initMenu();
		//部门
		initDepartment();
		//普通资源
		initCommonResource();
		//操作
		initPrivilege();
		//角色
		initRole();
		//角色初始化权限
		initRolePermission();
		//用户
		initUser();
		//用户关联角色
		initUserRole();
		
	}
	
	private void initDepartment() {
		int i = 0;
		DepartmentService departmentService = (DepartmentService) BeanCreateFactory.getBean("departmentService");
		String departmentName = "中国航信";
		DepartmentVO[] nodes = new DepartmentVO[10];
		
		nodes[++i] = new DepartmentVO("重庆分公司","重庆研发中心",0);
		nodes[++i] = new DepartmentVO("北京分公司","北分",0);
		
		nodes[++i] = new DepartmentVO("航空业务部","电商",1);
		nodes[++i] = new DepartmentVO("java研发部","新一代",1);
		
		nodes[++i] = new DepartmentVO("海外组","海外B2B",3);
		nodes[++i] = new DepartmentVO("平台组","国内平台",3);
		nodes[++i] = new DepartmentVO("国内业务组","山航B2B、B2C",3);
		
		nodes[++i] = new DepartmentVO("新一代组","新一代",4);
		nodes[++i] = new DepartmentVO("离港组","离港",4);
		
		departmentService.createTree(departmentName,nodes);		
	}

	private void initUserRole() {
		addUserRole(OSPConstants.SUPPER_USER_NAME,OSPConstants.ROLE_SUPPER_ADMIN);
		addUserRole("lyfang@travelsky.com",OSPConstants.ROLE_ADMIN);
		addUserRole("dongd@travelsky.com",OSPConstants.ROLE_COMMON_USER);
	}

	private void initRolePermission() {
		
		addRoleMenu(OSPConstants.ROLE_SUPPER_ADMIN,OSPConstants.PRIVILEGE_ALL);
		addRoleResoure(OSPConstants.ROLE_SUPPER_ADMIN,"CommonResource",OSPConstants.PRIVILEGE_ALL);
		
		addRoleMenu(OSPConstants.ROLE_ADMIN,OSPConstants.PRIVILEGE_EDIT);
		addRoleResoure(OSPConstants.ROLE_ADMIN,"CommonResource",OSPConstants.PRIVILEGE_EDIT);
		
		addRoleMenu(OSPConstants.ROLE_COMMON_USER,OSPConstants.PRIVILEGE_QUERY);
		addRoleResoure(OSPConstants.ROLE_COMMON_USER,"CommonResource",OSPConstants.PRIVILEGE_QUERY);
		
	}

	private void initUser() {
		users.add(addUser(OSPConstants.SUPPER_USER_NAME,"董继波","中国航信重庆研发中心"));
		users.add(addUser("lyfang@travelsky.com","刘永芳","海外组"));
		users.add(addUser("dongd@travelsky.com","董东","新一代组"));
	}

	private List<Role> initRole() {
		roles.add(addRole(OSPConstants.ROLE_SUPPER_ADMIN));
		roles.add(addRole(OSPConstants.ROLE_ADMIN));
		roles.add(addRole(OSPConstants.ROLE_COMMON_USER));
		return roles;
	}

	private void initCommonResource() {
		this.addCommonResource("CommonResource", "普通资源", "测试");
	}

	private MenuService initMenu() {
		int i = 0;
		MenuService menuService = (MenuService) BeanCreateFactory.getBean("menuService");
		String menuName = "合约书review书管理";
		MenuVO[] nodes = new MenuVO[4];
		i = 0;
		nodes[++i] = new MenuVO("合约书经典视图","/ams/page/dataManage.jsp.jsp",0);
		nodes[++i] = new MenuVO("工单信息导入","/ams/page/dataManage.jsp.jsp",0);
		nodes[++i] = new MenuVO("成本信息导入","/ams/page/dataManage.jsp.jsp",0);
		menuService.createTree(menuName,nodes);
		
		i = 0;
		menuName = "合约书任务管理";
		nodes = new MenuVO[3];
		nodes[++i] = new MenuVO("子任务业务管理","/ams/page/dataManage.jsp.jsp",0);
		nodes[++i] = new MenuVO("子任务研发管理","/ams/page/dataManage.jsp.jsp",0);
		menuService.createTree(menuName,nodes);
		
		i = 0;
		menuName = "合约书基础数据管理";
		nodes = new MenuVO[5];
		nodes[++i] = new MenuVO("产品线管理","/ams/page/dataManage.jsp.jsp",0);
		nodes[++i] = new MenuVO("产品管理","/ams/page/dataManage.jsp.jsp",0);
		nodes[++i] = new MenuVO("任务管理","/ams/page/dataManage.jsp.jsp",0);
		nodes[++i] = new MenuVO("其他数据管理","/ams/page/dataManage.jsp",0);
		menuService.createTree(menuName,nodes);
		
		i = 0;
		menuName = OSPConstants.BACK_MENU_NAME;
		nodes = new MenuVO[15];
		
		nodes[++i] = new MenuVO("权限管理","目录",0);
		nodes[++i] = new MenuVO("基础信息","目录",0);
		
		nodes[++i] = new MenuVO("用户","permission/user/manage",1);
		nodes[++i] = new MenuVO("角色","permission/role/manage",1);
		nodes[++i] = new MenuVO("操作","permission/privilege/manage",1);
		nodes[++i] = new MenuVO("部门","permission/department/manage",1);
		nodes[++i] = new MenuVO("资源","permission/resource/common/manage",1);
		nodes[++i] = new MenuVO("菜单","permission/resource/menu/manage",1);
		
		nodes[++i] = new MenuVO("合约书分类","/info/agreement_type/agreementType",2);
		nodes[++i] = new MenuVO("产品类别","/info/product_type/productType",2);
		nodes[++i] = new MenuVO("产品归属","/info/product_belong/productBelong",2);
		nodes[++i] = new MenuVO("任务来源","/info/task_origin/taskOrigin",2);
		nodes[++i] = new MenuVO("子任务状态","/info/subtask_status/subtaskStatus",2);
		nodes[++i] = new MenuVO("子任务进度","/info/subtask_progress/subtaskProgress",2);
		menuService.createTree(menuName,nodes);
		
		
		
		return menuService;
	}
	
	private void addCommonResource(String key,String value,String remark) {
		CommonResourceService resourceService = (CommonResourceService) BeanCreateFactory.getBean("commonResourceService");
		CommonResource resource = new CommonResource();
		resource.setName(key);
		resource.setValue(value);
		resource.setRemark(remark);
		resourceService.add(resource);
	}
	
	public void initPrivilege(){
		//创建操作
		addPrivilege(OSPConstants.PRIVILEGE_ALL);
		addPrivilege(OSPConstants.PRIVILEGE_ADD);
		addPrivilege(OSPConstants.PRIVILEGE_QUERY);
		addPrivilege(OSPConstants.PRIVILEGE_EDIT);
		addPrivilege(OSPConstants.PRIVILEGE_DELETE);
		addPrivilege(OSPConstants.PRIVILEGE_SUBTASK_SAVE);
		addPrivilege(OSPConstants.PRIVILEGE_SUBTASK_CANCEL);
		addPrivilege(OSPConstants.PRIVILEGE_SUBTASK_CONFIRM);
		addPrivilege(OSPConstants.PRIVILEGE_SUBTASK_REFUSE);
		addPrivilege(OSPConstants.PRIVILEGE_SUBTASK_START);
		addPrivilege(OSPConstants.PRIVILEGE_SUBTASK_SUBMIT);
		
	}

	private void addPrivilege(String name) {
		PrivilegeService privilegeService = (PrivilegeService) BeanCreateFactory.getBean("privilegeService");
		Privilege privilege = new Privilege();
		privilege.setName(name);
		privilegeService.add(privilege);
	}
	

	private void addRoleMenu(String roleName, String privilegeName) {
		//角色、菜单、权限关系
		MenuService menuService = (MenuService) BeanCreateFactory.getBean("menuService");
		PrivilegeService privilegeService = (PrivilegeService) BeanCreateFactory.getBean("privilegeService");
		RoleService roleService = (RoleService) BeanCreateFactory.getBean("roleService");
		//由于单元测试无法打开OpenSessionInViewInterceptor
		//所以直接从数据库查的对象，无法懒加载
		//但是不知道为什么添加后的对象可以，所以这里从list中去获取
		Role role = getRole(roleName);
		
		List<Menu> menus = menuService.queryAll();
		Privilege privilege = privilegeService.query("name", privilegeName);//操作
		for(Menu m : menus){
			Permission p = new Permission();
			p.setPrivilege(privilege);
			p.setResource(m);
			System.out.println(m.getId()+"-"+m.getName());
			role.addPermission(p);
			
		}
		roleService.update(role);
	}
	
	private Role getRole(String roleName) {
		for(Role role:roles){
			if (roleName.equals(role.getName())) {
				return role;
			}
		}
		return null;
	}

	private void addRoleResoure(String roleName, String resourceName,
			String privilegeName) {
		//角色、菜单、权限关系
		PrivilegeService privilegeService = (PrivilegeService) BeanCreateFactory.getBean("privilegeService");
		RoleService roleService = (RoleService) BeanCreateFactory.getBean("roleService");
		CommonResourceService resourceService = (CommonResourceService) BeanCreateFactory.getBean("commonResourceService");
		
		Role role = getRole(roleName);
		Privilege privilege = privilegeService.query("name", privilegeName);//操作
		CommonResource resource = resourceService.query("name", resourceName);
		Permission p = new Permission();
		p.setPrivilege(privilege);
		p.setResource(resource);
		role.addPermission(p);
			
		roleService.update(role);
	}
	
	private User addUser(String username, String realName,String departmentName) {
		//创建用户
		UserService userService = (UserService) BeanCreateFactory.getBean("userService");
		DepartmentService departmentService = (DepartmentService) BeanCreateFactory.getBean("departmentService");
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(MD5Encrypt.encryptPassword(OSPConstants.PASSWORD_DEFOULT));
		user.setRealName(realName);
		user.setDepartment(departmentService.query("name", departmentName));
		
		userService.add(user);
		return user;
	}
	
	private Role addRole(String roleName) {
		//创建角色 
		RoleService roleService = (RoleService) BeanCreateFactory.getBean("roleService");
		Role role = new Role();
		role.setName(roleName);
		roleService.add(role);
		return role;
	}
	
	private void addUserRole(String userName, String roleName) {
		//用户和角色关联
		UserService userService = (UserService) BeanCreateFactory.getBean("userService");
		
		User user = getUser(userName);
		Role role = getRole(roleName);
		user.addRole(role);
		userService.update(user);
	}

	private User getUser(String userName) {
		for(User user:users){
			if (userName.equals(user.getUsername())) {
				return user;
			}
		}
		return null;
	}
	
}
