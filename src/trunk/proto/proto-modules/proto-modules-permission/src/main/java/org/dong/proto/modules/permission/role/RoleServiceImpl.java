package org.dong.proto.modules.permission.role;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.dong.proto.core.persistence.dao.DaoSupport;
import org.dong.proto.core.persistence.query.QueryCondition;
import org.dong.proto.modules.permission.constants.OSPConstants;
import org.dong.proto.modules.permission.user.User;
import org.dong.proto.modules.permission.user.UserService;
import org.dong.proto.util.easyui.ComboxInfo;
import org.dong.proto.util.easyui.EasyuiUtil;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl extends DaoSupport<Role> implements RoleService {

	@Resource
	private UserService userService;
	
	@Override
	public List<Role> queryById(String[] roleIDsStr) {
		Integer[] roleIds = new Integer[roleIDsStr.length]; 
		for (int i = 0; null != roleIDsStr&& i < roleIDsStr.length; i++) {
			roleIds[i] = Integer.parseInt(roleIDsStr[i].trim());
		}
		
		return queryAll(QueryCondition.init().addIn("id", roleIds));
	}

	@Override
	public String getComboxData() {
		
		//不显示超级管理员
		QueryCondition conditions = QueryCondition.init();
		conditions.addNot("name", OSPConstants.ROLE_SUPPER_ADMIN)
		.addOrder("id", QueryCondition.TYPE_ORDER_ASC);
		
		List<Role> objects = this.queryAll(conditions);
		List<ComboxInfo> items = new ArrayList<ComboxInfo>();
		for(Role u:objects){
			ComboxInfo c = new ComboxInfo(u.getId()+"",u.getName());
			items.add(c);
		}
        return EasyuiUtil.getCombox(items);
	}


	@Override
	public String getComboxData(int userId) {
		
		//不显示超级管理员
		QueryCondition conditions = QueryCondition.init();
		conditions.addNot("name", OSPConstants.ROLE_SUPPER_ADMIN)
		.addOrder("id", QueryCondition.TYPE_ORDER_ASC);
		
		User user = userService.get(userId);
		
		List<Role> objects = this.queryAll(conditions);
		List<ComboxInfo> items = new ArrayList<ComboxInfo>();
		for(Role u:objects){
			ComboxInfo c = new ComboxInfo(u.getId()+"",u.getName());
			if (hasRole(user,u)) {
				c.setSelected(true);
			}
			items.add(c);
		}
        return EasyuiUtil.getCombox(items);
	}
	/**
	 * 判断用户是否拥有该角色
	 * @param user
	 * @param r
	 * @return
	 */
	private boolean hasRole(User user, Role r) {
		for(Role ur : user.getRoles()){
			if (ur.getId() == r.getId()) {
				return true;
			}
		}
		return false;
	}

}
