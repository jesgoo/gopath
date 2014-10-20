package org.dong.proto.modules.permission.permission;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/permission")
public class PermissionController {
	
	@Resource
	private PermissionService permissionService;
	
	@RequestMapping("/roleUpdateMenuPermission")
	@ResponseBody
	public String roleUpdateMenuPermission(PermissionVO vo) {
		permissionService.roleUpdateMenuPermission(vo);
		return "操作成功";
	}
	
	@RequestMapping("/queryRolePermissions")
	@ResponseBody
	public String queryRolePermissions(int roleId,String queryType) {
		return permissionService.queryRolePermissions(roleId,queryType);
	}
	
	@RequestMapping("/roleAddCommonPermission")
	@ResponseBody
	public String roleAddCommonPermission(PermissionVO vo) {
		return permissionService.roleAddCommonPermission(vo);
	}
	
	@RequestMapping("/roleDeletePermission")
	@ResponseBody
	public String roleDeletePermission(PermissionVO vo) {
		permissionService.roleDeletePermission(vo);
		return "操作成功";
	}
}
