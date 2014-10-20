package org.dong.proto.modules.permission.permission;

public interface PermissionService {

	/**
	 * 角色修改菜单权限
	 * @param vo
	 */
	void roleUpdateMenuPermission(PermissionVO vo);

	/**
	 * 获取角色的权限信息，用于分页显示
	 * @param roleId
	 * @param queryType 
	 * @return
	 */
	String queryRolePermissions(int roleId, String queryType);

	/**
	 * 角色添加普通资源权限
	 * @param vo
	 * @return
	 */
	String roleAddCommonPermission(PermissionVO vo);

	/***
	 * 角色删除权限
	 * @param vo
	 */
	void roleDeletePermission(PermissionVO vo);

}
