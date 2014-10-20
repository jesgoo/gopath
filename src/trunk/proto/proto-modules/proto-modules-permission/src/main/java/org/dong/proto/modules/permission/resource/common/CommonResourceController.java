package org.dong.proto.modules.permission.resource.common;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/permission/resource/common")
public class CommonResourceController {

	@Resource
	private CommonResourceService commonResourceService;
	
	@RequestMapping("/manage")
	public String manage(Model model) {
		return "permission/resource/common/manage";
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public String query(Model model,int page,int rows) {
		return commonResourceService.queryPage4Datagrid(page, rows);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public String add(CommonResource commonResource) {
		if (null != commonResourceService.query("key", commonResource.getName())) {
			return "操作失败！<br>'"+commonResource.getName()+"'记录已存在";
		}
		commonResourceService.add(commonResource);
		return "操作成功";
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(CommonResource commonResource) {
		commonResourceService.delete(commonResource);
		return "操作成功";
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String update(CommonResource commonResource) {
		commonResourceService.update(commonResource);
		return "操作成功";
	}
	

	/**
	 * 获取多选框内容
	 */
	@RequestMapping("/getComboxData")
	@ResponseBody
	public String getPrivilegeCombox() {
		return commonResourceService.getComboxData();
	}
}
