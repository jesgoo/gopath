package org.dong.proto.modules.permission.user;


import java.util.List;

import org.dong.proto.modules.permission.role.Role;

public class UserVO extends User{
	
     private String roleNames;
     private String departmentName;
     private int departmentId;
     
     public UserVO(User user) {
    	 this.setId(user.getId());
    	 this.setUsername(user.getUsername());
    	 this.setPassword(user.getPassword());
    	 this.setRealName(user.getRealName());
    	 this.setRoleNames(this.getRoleNames(user.getRoles()));
    	 initDepartmentInfo(user);
    	
     }

     private void initDepartmentInfo(User user) {
    	 if (null != user.getDepartment()) {
 			 this.setDepartmentName(user.getDepartment().getName());
 			 this.setDepartmentId(user.getDepartment().getId());
 		}
	}


	/**
 	 * 获取用户的角色，并拼接成字符串
 	 * @param userRoles
 	 * @return
 	 */
 	private String getRoleNames(List<Role> roles) {
 		StringBuffer roleNames = new StringBuffer();
 		for(Role r : roles){
 			roleNames.append(r.getName());
 			roleNames.append(",");
 		}
 		if (null != roles && roles.size() > 0) {
 			roleNames.deleteCharAt(roleNames.lastIndexOf(","));
		}
 		
 		
 		return roleNames.toString();
 	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getDepartmentId() {
		return departmentId;
	}
     
	
	
}
