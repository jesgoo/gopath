package org.dong.proto.modules.permission.resource.common;


import java.util.ArrayList;
import java.util.List;

import org.dong.proto.core.persistence.dao.DaoSupport;
import org.dong.proto.core.persistence.query.QueryCondition;
import org.dong.proto.modules.permission.constants.OSPConstants;
import org.dong.proto.modules.permission.permission.PermissionVO;
import org.dong.proto.modules.permission.resource.Resource;
import org.dong.proto.modules.permission.resource.menu.Menu;
import org.dong.proto.modules.permission.resource.menu.MenuService;
import org.dong.proto.util.easyui.ComboxInfo;
import org.dong.proto.util.easyui.EasyuiUtil;
import org.springframework.stereotype.Service;

@Service("commonResourceService")
public class CommonResourceServiceImpl extends DaoSupport<CommonResource> implements CommonResourceService {

	@javax.annotation.Resource
	private MenuService menuService;
	
	@Override
	public void add(CommonResource t) {
		t.setResourceType(OSPConstants.RESOURCE_TYPE_COMMON);
		super.add(t);
	}
	
	
	@Override
	public String queryJsonData(QueryCondition conditions, int page, int rows) {
		return EasyuiUtil.getDataGrid(queryPage(conditions, page, rows), queryCount(conditions));
	}

	@Override
	public String getComboxData() {
		List<ComboxInfo> items = new ArrayList<ComboxInfo>();
		//查询普通资源
		List<CommonResource> objects = this.queryAll();
		for(Resource u:objects){
			ComboxInfo c = new ComboxInfo(u.getId()+"",u.getName());
			items.add(c);
		}
		//查询菜单树
//		List<List<Menu>> trees = menuService.queryAllMenuTree();
//		for(List<Menu> tree : trees){
//			for(Menu u:tree){
//				ComboxInfo c = new ComboxInfo(u.getId()+"",u.getRootName()+"-"+u.getKey());
//				items.add(c);
//			}
//		}
		
        return EasyuiUtil.getCombox(items);
	}

	@Override
	public String getComboxData(PermissionVO vo) {
		List<ComboxInfo> items = new ArrayList<ComboxInfo>();
		//查询普通资源
		List<CommonResource> objects = this.queryAll();
		for(Resource u:objects){
			ComboxInfo c = new ComboxInfo(u.getId()+"",u.getName());
			if (c.getId().equals(vo.getResourceId())) {
				c.setSelected(true);
			}
			items.add(c);
		}
		
		//查询菜单树
		List<List<Menu>> trees = menuService.queryAllMenuTree();
		for(List<Menu> tree : trees){
			for(Menu u:tree){
				ComboxInfo c = new ComboxInfo(u.getId()+"",u.getRootName()+"-"+u.getName());
				if (c.getId().equals(vo.getResourceId())) {
					c.setSelected(true);
				}
				items.add(c);
			}
		}
        return EasyuiUtil.getCombox(items);
	}

}
