package org.dong.proto.modules.permission.user;


import org.dong.proto.core.persistence.dao.BaseDao;

public interface UserService extends BaseDao<User>{

	/**
	 * 添加用户
	 * @param user
	 * @param departmentId
	 * @param rolesStr 
	 * @return
	 */
	String add(User user, int departmentId, String rolesStr);

	/**
	 * 修改用户
	 * @param deparmentId 
	 */
	void update(User user, int departmentId, String rolesStr);

	/**
	 * 后台登陆
	 * @param randCode
	 * @param username
	 * @param password
	 * @param valdateCode
	 */
	User backLogin(String randCode, String username, String password,
			String valdateCode) throws Exception;
}

