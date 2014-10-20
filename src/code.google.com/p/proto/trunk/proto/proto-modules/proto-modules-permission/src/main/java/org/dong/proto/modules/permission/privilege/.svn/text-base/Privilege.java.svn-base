package org.dong.proto.modules.permission.privilege;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity(name="T_PRIVILEGE")
@TableGenerator(   
		name="T_PRIVILEGE_ID",				//生成器名称，也就是@GeneratedValue中的generator的名称
	    pkColumnValue="T_PRIVILEGE_ID",		//生成器表的主键列的记录名称
	    table="GENERATOR_TABLE",	//生成器表的表名
	    pkColumnName = "key_name",		//生成器表的主键列名
	    valueColumnName = "value",	//生成器表的下标列名
	    initialValue=1,				//初始值
	    allocationSize=1)			//下一个要使用的最大值
public class Privilege {


	private int id;
	
	private String name;

	public void setId(int id) {
		this.id = id;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="T_PRIVILEGE_ID")
	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
