package org.dong.proto.modules.permission.department;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/permission/department")
public class DepartmentController {

	@Resource
	private DepartmentService departmentService;
	
	@RequestMapping("/manage")
	public String manage(Model model) {
		return "permission/department/manage";
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public String query(Model model) {
		return departmentService.queryJsonData();
	}
	
	/***
	 * 添加新的树的根节点
	 */
	@RequestMapping("/addRootNode")
	@ResponseBody
	public String addRootNode(Department rootNode){
		departmentService.addRootNode(rootNode);
		return "操作成功";
	}
	
	/***
	 * 添加新的树的根节点
	 */
	@RequestMapping("/addChildNode")
	@ResponseBody
	public String addChildNode(int parentNodeId,Department childNode){
		Department parentNode = new Department();
		parentNode.setId(parentNodeId);
		departmentService.addChildNode(parentNode, childNode);
		return "操作成功";
	}
	
	/***
	 * 添加新的树的根节点
	 */
	@RequestMapping("/updateNode")
	@ResponseBody
	public String updateNode(Department parentNode){
		departmentService.updateNode(parentNode);
		return "操作成功";
	}
	
	/***
	 * 删除节点
	 */
	@RequestMapping("/deleteNode")
	@ResponseBody
	public String deleteNode(Department parentNode){
		departmentService.deleteNode(parentNode);
		return "操作成功";
	}
	/**
	 * 获取后台菜单树
	 */
	@RequestMapping("/getTreeDepartment")
	@ResponseBody
	public String getTreeDepartment(){
		return departmentService.queryJsonAllTreeDepartment();
	}
	
	
}
