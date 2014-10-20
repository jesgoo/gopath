package org.dong.proto.modules.permission.privilege;



import org.dong.proto.core.persistence.dao.BaseDao;

public interface PrivilegeService extends BaseDao<Privilege>{

	/**
	 * 获取多选框数据
	 * @return
	 */
	String getComboxData();
	
}
