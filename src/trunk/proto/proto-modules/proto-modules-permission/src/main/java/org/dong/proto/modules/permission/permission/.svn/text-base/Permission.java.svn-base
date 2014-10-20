package org.dong.proto.modules.permission.permission;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

import org.dong.proto.modules.permission.privilege.Privilege;
import org.dong.proto.modules.permission.resource.Resource;
@Entity(name="T_PERMISSION")
@TableGenerator(   
		name="T_PERMISSION_ID",				//生成器名称，也就是@GeneratedValue中的generator的名称
	    pkColumnValue="T_PERMISSION_ID",		//生成器表的主键列的记录名称
	    table="GENERATOR_TABLE",	//生成器表的表名
	    pkColumnName = "key_name",		//生成器表的主键列名
	    valueColumnName = "value",	//生成器表的下标列名
	    initialValue=1,				//初始值
	    allocationSize=1)			//下一个要使用的最大值
public class Permission {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="T_PERMISSION_ID")
	private int id;
	
	@ManyToOne(targetEntity=Resource.class)
	private Resource resource;
	
	@ManyToOne(targetEntity=Privilege.class)
	private Privilege privilege;


	/**
	 * @param resource the resource to set
	 */
	public void setResource(Resource resource) {
		this.resource = resource;
	}

	/**
	 * @return the resource
	 */
	public Resource getResource() {
		return resource;
	}

	/**
	 * @param privilege the privilege to set
	 */
	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	/**
	 * @return the privilege
	 */
	public Privilege getPrivilege() {
		return privilege;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
}
