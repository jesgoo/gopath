package org.dong.proto.modules.permission.permission;

import java.util.ArrayList;
import java.util.List;

import org.dong.proto.modules.permission.constants.OSPConstants;
import org.dong.proto.modules.permission.privilege.Privilege;
import org.dong.proto.modules.permission.resource.Resource;
import org.dong.proto.modules.permission.role.Role;
import org.dong.proto.modules.permission.role.RoleService;
import org.dong.proto.util.easyui.EasyuiUtil;
import org.dong.proto.util.string.StringUtil;
import org.springframework.stereotype.Service;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

	@javax.annotation.Resource
	private RoleService roleService;
	
	@Override
	public void roleUpdateMenuPermission(PermissionVO vo) {
		Role role = roleService.get(Integer.parseInt(vo.getRoleId()));
		//删除原有的权限
		removePermission(vo, role);
		//添加新的权限
		String[] ids = getResourceIdsUpdate(vo);
		if(!StringUtil.isEmpty(ids)){
			for (String sid : ids){
				vo.setResourceId(sid.trim());
				role.addPermission(this.getPermission(vo));
			}
		}
		
		roleService.update(role);
	}


	private void removePermission(PermissionVO vo, Role role) {
		int privilegeId = Integer.parseInt(vo.getPrivilegeId());
		
		//获取数据库中该角色和该操作对应的“菜单”权限集合
		List<Permission> list = new ArrayList<Permission>();
		for(Permission p :role.getPermissions()){
			if(null != p.getPrivilege() ){
				if (OSPConstants.RESOURCE_TYPE_MENU.equals(p.getResource().getResourceType()) 
						&& p.getPrivilege().getId() == privilegeId) {
					list.add(p);
				}
			}
		}
		
		for(Permission p : list){
			role.removePermission(p);
		}
	}


	private String[] getResourceIdsUpdate(PermissionVO vo) {
		return vo.getMenuIds().split(",");
	}
	
	private Permission getPermission(PermissionVO vo) {
		Permission pm = new Permission();
		if (!StringUtil.isEmpty(vo.getId())) {
			pm.setId(Integer.parseInt(vo.getId()));
		}
		if (!StringUtil.isEmpty(vo.getResourceId())) {
			Resource rs = new Resource();
			rs.setId(Integer.parseInt(vo.getResourceId()));
			pm.setResource(rs);
		}
		if (!StringUtil.isEmpty(vo.getPrivilegeId())) {
			Privilege p = new Privilege();
			p.setId(Integer.parseInt(vo.getPrivilegeId()));
			pm.setPrivilege(p);
		}

		return pm;
	}


	@Override
	public String queryRolePermissions(int roleId,String queryType) {
		Role role = roleService.query("id", roleId);
		List<PermissionVO> voItems = new ArrayList<PermissionVO>();
		
		for(Permission m : role.getPermissions()){
			PermissionVO p = new PermissionVO();
			p.setId(m.getId()+"");
			p.setPrivilegeId(m.getPrivilege().getId()+"");
			p.setPrivilegeName(m.getPrivilege().getName());
			p.setResourceId(m.getResource().getId()+"");
			p.setResourceKey(m.getResource().getName());
			p.setResourceValue(m.getResource().getValue());
			p.setRoleId(role.getId()+"");
			p.setRoleName(role.getName());
			
			if (checkQueryType(m,queryType)) {
				voItems.add(p);
			}
			
		}
		
		return EasyuiUtil.getDataGrid(voItems, 0);
	}


	/**
	 * 根据查询类型来显示权限
	 * @param m
	 * @param queryType 1、resource；2、Menu；3、CommonRescoure为了区分Menu;4、缓存
	 * @return
	 */
	private boolean checkQueryType(Permission m, String queryType) {
		//默认查询的所有
		if (OSPConstants.RESOURCE_TYPE_BASE.equals(queryType)) {
			return true;
		}
		//查询确定类型
		else if (queryType.equals(m.getResource().getResourceType())) {
			return true;
		}
		else{
			return false;
		}
		
	}


	@Override
	public String roleAddCommonPermission(PermissionVO vo) {
		
		Role role = roleService.get(Integer.parseInt(vo.getRoleId()));
		
		if (hasCommonPermission(role,vo)) {
			return "操作失败！<br>记录已存在";
		}
		
		//添加新的权限
		role.addPermission(this.getPermission(vo));		
		roleService.update(role);
		
		return "操作成功";
		
	}


	private boolean hasCommonPermission(Role role, PermissionVO vo) {
		int rid = Integer.parseInt(vo.getResourceId());
		int pid = Integer.parseInt(vo.getPrivilegeId());
		for(Permission p : role.getPermissions()){
			if (p.getResource().getId() ==rid && p.getPrivilege().getId() == pid ) {
				return true;
			}
		}
		return false;
	}


	@Override
	public void roleDeletePermission(PermissionVO vo) {
		Role role = roleService.get(Integer.parseInt(vo.getRoleId()));
		//删除的权限
		role.removePermission(this.getPermission(vo));		
		roleService.update(role);
	}
	
}
