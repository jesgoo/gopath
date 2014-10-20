package org.dong.proto.modules.permission.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/permission/user")
public class UserController {

	@Resource
	private UserService userService;
	
	@RequestMapping("/manage")
	public String manage(Model model) {
		return "permission/user/manage";
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public String query(Model model,int page,int rows) {
		return userService.queryPage4Datagrid(page, rows);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public String add(int departmentId,String rolesStr,User user) {
		return userService.add(user,departmentId,rolesStr);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(User user) {
		userService.delete(user);
		return "操作成功";
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String update(int departmentId,String rolesStr,User user) {
		userService.update(user,departmentId,rolesStr);
		return "操作成功";
	}
	
}
