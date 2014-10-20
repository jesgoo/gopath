package org.dong.proto.modules.permission.privilege;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/permission/privilege")
public class PrivilegeController {

	@Resource
	private PrivilegeService privilegeService;
	
	@RequestMapping("/manage")
	public String manage(Model model) {
		return "permission/privilege/manage";
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public String query(Model model,int page,int rows) {
		return privilegeService.queryPage4Datagrid(page, rows);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public String add(Privilege privilege) {
		if (null != privilegeService.query("name", privilege.getName())) {
			return "操作失败！<br>'"+privilege.getName()+"'记录已存在";
		}
		privilegeService.add(privilege);
		return "操作成功";
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(Privilege privilege) {
		privilegeService.delete(privilege);
		return "操作成功";
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String update(Privilege privilege) {
		privilegeService.update(privilege);
		return "操作成功";
	}
	
	/**
	 * 获取多选框内容
	 */
	@RequestMapping("/getComboxData")
	@ResponseBody
	public String getPrivilegeCombox() {
		return privilegeService.getComboxData();
	}
}
