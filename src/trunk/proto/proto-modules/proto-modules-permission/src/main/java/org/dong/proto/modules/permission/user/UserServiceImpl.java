package org.dong.proto.modules.permission.user;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.dong.proto.core.exception.OSExceptionFactory;
import org.dong.proto.core.persistence.dao.DaoSupport;
import org.dong.proto.core.persistence.query.QueryCondition;
import org.dong.proto.modules.permission.constants.OSPConstants;
import org.dong.proto.modules.permission.department.DepartmentService;
import org.dong.proto.modules.permission.role.Role;
import org.dong.proto.modules.permission.role.RoleService;
import org.dong.proto.util.easyui.EasyuiUtil;
import org.dong.proto.util.encrypt.MD5Encrypt;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends DaoSupport<User> implements UserService {

	@Resource
	private RoleService roleService;
	
	@Resource
	private DepartmentService departmentService;
	
	@Override
	public String queryPage4Datagrid(int page, int rows) {
		
		//只显示角色不是超级管理员或者角色为null的用户
		QueryCondition conditions = QueryCondition.init();
		conditions.addOr(conditions.createNot("roles.name", OSPConstants.ROLE_SUPPER_ADMIN),
				conditions.createIsNull("roles.name"))
		.addOrder("id", QueryCondition.TYPE_ORDER_ASC);
		
		List<User> users = this.queryPage(conditions,page, rows);
		List<UserVO> userVOs = new ArrayList<UserVO>();
		for(User u : users){
			 if (!u.isRole(OSPConstants.ROLE_SUPPER_ADMIN)) {
				 userVOs.add(new UserVO(u));
			 }
		}
		return EasyuiUtil.getDataGrid(userVOs, queryCount(conditions));
	}

	@Override
	public String add(User user, int departmentId, String rolesStr) {
		
		if (null != query("username", user.getUsername())) {
			return "操作失败！<br>'"+user.getUsername()+"'记录已存在";
		}
		
		// 添加用户,管理权限时，都默认初始密码
		user.setPassword(MD5Encrypt
				.encryptPassword(OSPConstants.PASSWORD_DEFOULT));
		// 添加角色
		List<Role> roles = roleService.queryById(rolesStr.split(","));
		user.setRoles(roles);
		
		//添加部门
		user.setDepartment(departmentService.get(departmentId));
		
		add(user);
		return "操作成功";
	}

	@Override
	public void update(User userVO,int departmentId, String rolesStr) {
		
		User user = query("id", userVO.getId());
		
		// 角色
		List<Role> roles = roleService.queryById(rolesStr.split(","));
		user.setRoles(roles);
		
		//部门
		user.setDepartment(departmentService.get(departmentId));
		
		update(user);
	}

	@Override
	public User backLogin(String randCode, String username, String password,
			String valdateCode) throws Exception {
		User user = this.query("username", username);
		if (!randCode.equalsIgnoreCase(valdateCode)) {
			OSExceptionFactory.throwException("登陆失败，验证码错误");
		}else if (null == user) {
			OSExceptionFactory.throwException("登陆失败，用户名不存在");
		}else if (!MD5Encrypt.checkEncryptPassword(password, user.getPassword())) {
			OSExceptionFactory.throwException("登陆失败，密码错误");
		}
		
		return user;
	}
	
}
