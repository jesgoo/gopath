package org.dong.proto.modules.permission.role;

import java.util.List;

import org.dong.proto.core.persistence.dao.BaseDao;

public interface RoleService extends BaseDao<Role>{


	/**
	 * 用于用户选择角色使用的json数据
	 * @return
	 */
	public String getComboxData();

	/**
	 * 根据id获取角色
	 * @param roleIDs id数组
	 */
	public List<Role> queryById(String[] roleIDs);

	/**
	 * 用户修改时，获取用户的角色
	 * @param userId
	 * @return
	 */
	String getComboxData(int userId);
	
}
