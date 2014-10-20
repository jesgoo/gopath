package org.dong.proto.modules.permission.resource.menu;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/permission/resource/menu")
public class MenuController {

	@Resource
	private MenuService menuService;
	
	@RequestMapping("/manage")
	public String manage(Model model) {
		return "permission/resource/menu/manage";
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public String query(Model model) {
		return menuService.queryJsonData();
	}
	
	/***
	 * 添加新的树的根节点
	 */
	@RequestMapping("/addRootNode")
	@ResponseBody
	public String addRootNode(Menu rootNode){
		menuService.addRootNode(rootNode);
		return "操作成功";
	}
	
	/***
	 * 添加新的树的根节点
	 */
	@RequestMapping("/addChildNode")
	@ResponseBody
	public String addChildNode(int parentNodeId,Menu childNode){
		Menu parentNode = new Menu();
		parentNode.setId(parentNodeId);
		menuService.addChildNode(parentNode, childNode);
		return "操作成功";
	}
	
	/***
	 * 添加新的树的根节点
	 */
	@RequestMapping("/updateNode")
	@ResponseBody
	public String updateNode(Menu parentNode){
		menuService.updateNode(parentNode);
		return "操作成功";
	}
	
	/***
	 * 删除节点
	 */
	@RequestMapping("/deleteNode")
	@ResponseBody
	public String deleteNode(Menu parentNode){
		menuService.deleteNode(parentNode);
		return "操作成功";
	}
	
	/**
	 * 获取后台菜单树
	 */
	@RequestMapping("/getTreeMenuOfBack")
	@ResponseBody
	public String getTreeMenuOfBack(){

		
		return menuService.queryJsonTreeMenu(null);
	}
	
	
	/**
	 * 根据角色和操作来获取菜单树
	 * 
	 */
	@RequestMapping("/getAllTreeMenu4Role")
	@ResponseBody
	public String getAllTreeMenu4Role(int roleId,int privilegeId){
		return menuService.queryJsonAllTreeMenu4Role(roleId,privilegeId);
	}
	
	
}
