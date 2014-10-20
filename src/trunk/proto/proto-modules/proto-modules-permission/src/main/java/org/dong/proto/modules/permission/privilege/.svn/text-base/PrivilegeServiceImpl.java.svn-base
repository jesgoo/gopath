package org.dong.proto.modules.permission.privilege;


import java.util.ArrayList;
import java.util.List;

import org.dong.proto.core.persistence.dao.DaoSupport;
import org.dong.proto.util.easyui.ComboxInfo;
import org.dong.proto.util.easyui.EasyuiUtil;
import org.springframework.stereotype.Service;

@Service("privilegeService")
public class PrivilegeServiceImpl extends DaoSupport<Privilege> implements PrivilegeService {

	@Override
	public String getComboxData() {
		List<Privilege> objects = this.queryAll();
		List<ComboxInfo> items = new ArrayList<ComboxInfo>();
		for(Privilege u:objects){
			ComboxInfo c = new ComboxInfo(u.getId()+"",u.getName());
			items.add(c);
		}
        return EasyuiUtil.getCombox(items);
	}



}
