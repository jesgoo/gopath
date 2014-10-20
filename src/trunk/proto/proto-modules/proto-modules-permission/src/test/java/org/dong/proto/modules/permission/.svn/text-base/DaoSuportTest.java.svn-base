package org.dong.proto.modules.permission;


import java.util.List;

import org.dong.proto.core.factory.BeanCreateFactory;
import org.dong.proto.modules.permission.privilege.Privilege;
import org.dong.proto.modules.permission.privilege.PrivilegeService;
import org.dong.proto.util.log.LogUtil;
import org.junit.Test;

public class DaoSuportTest {

	@Test
	public void testCreateDB(){
		BeanCreateFactory.getBean("sessionFactory");
		LogUtil.info("--------乱码测试----");
	}
	
	@Test
	public void testAdd(){
		PrivilegeService privilegeService = (PrivilegeService) BeanCreateFactory.getBean("privilegeService");
		privilegeService.add(createPrivilege("添加"));
		privilegeService.add(createPrivilege("删除"));
		privilegeService.add(createPrivilege("修改"));
		privilegeService.add(createPrivilege("查询"));
		
	}

	private Privilege createPrivilege(String name) {
		Privilege Privilege = new Privilege();
		Privilege.setName(name);
		return Privilege;
	}
	
	@Test
	public void testQuery(){
		PrivilegeService privilegeService = (PrivilegeService) BeanCreateFactory.getBean("privilegeService");
		System.out.println("-------获取所有对象---------");
		List<Privilege> objects = privilegeService.queryAll();
		for(Privilege o : objects){
			System.out.println(o.getId()+"---"+o.getName());
		}
		
	}
	
}
