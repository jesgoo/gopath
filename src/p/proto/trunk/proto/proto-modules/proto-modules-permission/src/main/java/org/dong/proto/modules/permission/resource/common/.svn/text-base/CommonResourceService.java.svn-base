package org.dong.proto.modules.permission.resource.common;



import org.dong.proto.core.persistence.dao.BaseDao;
import org.dong.proto.core.persistence.query.QueryCondition;
import org.dong.proto.modules.permission.permission.PermissionVO;

public interface CommonResourceService extends BaseDao<CommonResource>{

	/**
	 * 获取资源的页面显示json数据
	 * @param conditions
	 * @param page
	 * @param rows
	 * @return
	 */
	public String queryJsonData(QueryCondition conditions, int page, int rows);

	/**
	 * 获取资源在权限中的下拉列表数据
	 * @return
	 */
	public String getComboxData();

	/**
	 * 获取指定权限的资源下拉列表
	 * @param vo
	 * @return
	 */
	public String getComboxData(PermissionVO vo);
	
}
