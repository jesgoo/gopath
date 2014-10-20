package org.dong.proto.modules.permission.user;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

import org.dong.proto.modules.permission.constants.OSPConstants;
import org.dong.proto.modules.permission.department.Department;
import org.dong.proto.modules.permission.permission.Permission;
import org.dong.proto.modules.permission.resource.Resource;
import org.dong.proto.modules.permission.role.Role;

@Entity(name="T_USER")
@TableGenerator(   
		name="T_USER_ID",				//生成器名称，也就是@GeneratedValue中的generator的名称
	    pkColumnValue="T_USER_ID",		//生成器表的主键列的记录名称
	    table="GENERATOR_TABLE",	//生成器表的表名
	    pkColumnName = "key_name",		//生成器表的主键列名
	    valueColumnName = "value",	//生成器表的下标列名
	    initialValue=1,				//初始值
	    allocationSize=1)			//下一个要使用的最大值
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="T_USER_ID")
	private int id;
	
	private String username;
	
	private String realName;
	
	private String password;
	
	@ManyToMany
	@JoinTable(name="t_user_role",joinColumns={@JoinColumn(name="uid")},
			inverseJoinColumns={@JoinColumn(name="rid")})
	private List<Role> roles = new ArrayList<Role>();
	
	@ManyToOne
	private Department department;
	
	@Override
	public String toString() {
		 return this.id+"_"+this.username+"_"+this.password;
	}
	
	public void addRole(Role role){
		this.roles.add(role);
	}
	
	public void removeRole(Role role){
		for(Role r : roles){
			if (r.getId() == role.getId()) {
				this.roles.remove(role);
			}
		}
	}
	
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * 验证用户是否为某角色
	 * @param roleName
	 * @return
	 */
	public boolean isRole(String roleName) {
		for(Role r : roles){
			if (r.getName().equals(roleName)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 验证指定资源对象的权限
	 * @param res
	 * @return
	 */
	public boolean hasPermission(Resource res) {
		for(Role r : roles){
			for(Permission p:r.getPermissions()){
//				if (p.getResource().getKey().equals(res.getKey())) {
//					return true;
//				}
				if (p.getResource().getId() == res.getId()) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * 验证资源和操作权限
	 * @param res	资源名称
	 * @param pri	操作名称
	 * @return
	 */
	public boolean hasPermission(String res,String pri) {
		for(Role r : roles){
			for(Permission p:r.getPermissions()){
				//判断资源
				if (p.getResource().getName().equals(res) 
						//判断操作
						&& (p.getPrivilege().getName().equals(pri) 
								//如果为操作为“所有”，则说明有操作权限
								|| p.getPrivilege().getName().equals(OSPConstants.PRIVILEGE_ALL))) {
					return true;
				}
			}
		}
		
		return false;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getRealName() {
		return realName;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Department getDepartment() {
		return department;
	}

	
}
