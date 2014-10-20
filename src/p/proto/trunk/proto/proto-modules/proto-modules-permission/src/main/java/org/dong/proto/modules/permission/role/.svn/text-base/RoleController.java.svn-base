package org.dong.proto.modules.permission.role;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/permission/role")
public class RoleController {

	@Resource
	private RoleService roleService;
	
	@RequestMapping("/manage")
	public String manage(Model model) {
		return "permission/role/role-manage";
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public String query(Model model,int page,int rows) {
		return roleService.queryPage4Datagrid(page, rows);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public String add(Role role) {
		if (null != roleService.query("name", role.getName())) {
			return "操作失败！<br>'"+role.getName()+"'记录已存在";
		}
		roleService.add(role);
		return "操作成功";
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(Role role) {
		roleService.delete(role);
		return "操作成功";
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String update(Role role) {
		roleService.update(role);
		return "操作成功";
	}
	
	/**
	 * 获取多选框内容
	 */
	@RequestMapping("/getComboxData")
	@ResponseBody
	public String getPrivilegeCombox() {
		return roleService.getComboxData();
	}
	
	/**
	 * 获取多选框内容
	 */
	@RequestMapping("/getComboxData4User")
	@ResponseBody
	public String getComboxData4User(int userId) {
		return roleService.getComboxData(userId);
	}
}
