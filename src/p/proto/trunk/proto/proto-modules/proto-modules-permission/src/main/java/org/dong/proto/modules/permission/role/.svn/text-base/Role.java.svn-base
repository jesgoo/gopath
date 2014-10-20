package org.dong.proto.modules.permission.role;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.TableGenerator;

import org.dong.proto.modules.permission.permission.Permission;

@Entity(name="T_ROLE")
@TableGenerator(   
		name="T_ROLE_ID",				//生成器名称，也就是@GeneratedValue中的generator的名称
	    pkColumnValue="T_ROLE_ID",		//生成器表的主键列的记录名称
	    table="GENERATOR_TABLE",	//生成器表的表名
	    pkColumnName = "key_name",		//生成器表的主键列名
	    valueColumnName = "value",	//生成器表的下标列名
	    initialValue=1,				//初始值
	    allocationSize=1)			//下一个要使用的最大值
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="T_ROLE_ID")
	private int id;
	
	private String name;
	
	@ManyToMany(targetEntity=Permission.class,cascade={CascadeType.ALL})
	@JoinTable(name="t_role_permission",joinColumns={@JoinColumn(name="rid")},
		inverseJoinColumns={@JoinColumn(name="pid")})
	private List<Permission> permissions = new ArrayList<Permission>();

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void addPermission(Permission p){
		permissions.add(p);
	}
	
	public void removePermission(Permission p){
		for(Permission r : permissions){
			if (r.getId() == p.getId()) {
				this.permissions.remove(r);
				break;//必须立即结束循环，不然正在遍历的list发生了变化，会报异常
			}
		}
		
	}
	
	public void updatePermission(Permission p){
		for(Permission r : permissions){
			if (r.getId() == p.getId()) {
				r.setPrivilege(p.getPrivilege());
				r.setResource(p.getResource());
				break;
			}
		}
	}
}
