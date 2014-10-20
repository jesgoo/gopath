package org.dong.proto.modules.permission.resource;


import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.TableGenerator;

import org.dong.proto.modules.permission.constants.OSPConstants;

@Entity(name="T_RESOURCE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) 
@DiscriminatorColumn(name = "R_TYPE", discriminatorType = DiscriminatorType.INTEGER, length = 1) 
@DiscriminatorValue(value=OSPConstants.RESOURCE_TYPE_BASE)//1、resource；2、Menu；3、CommonRescoure为了区分Menu;4、缓存
@TableGenerator(   
		name="T_RESOURCE_ID",				//生成器名称，也就是@GeneratedValue中的generator的名称
	    pkColumnValue="T_RESOURCE_ID",		//生成器表的主键列的记录名称
	    table="GENERATOR_TABLE",	//生成器表的表名
	    pkColumnName = "key_name",		//生成器表的主键列名
	    valueColumnName = "value",	//生成器表的下标列名
	    initialValue=1,				//初始值
	    allocationSize=1)			//下一个要使用的最大值
public class Resource {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="T_RESOURCE_ID")
	private int id;
	
	private String resourceType;
	
    private String name;
    
    private String value;
    
    private String remark;

	public void setName(String key) {
		this.name = key;
	}
	public String getName() {
		return name;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark() {
		return remark;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}


	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}


	public String getResourceType() {
		return resourceType;
	}
	
}
