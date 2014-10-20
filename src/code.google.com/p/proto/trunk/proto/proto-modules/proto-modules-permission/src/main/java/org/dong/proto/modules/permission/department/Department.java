package org.dong.proto.modules.permission.department;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;


@Entity(name="T_DEPARTMENT")
@TableGenerator(   
		name="T_DEPARTMENT_ID",				//生成器名称，也就是@GeneratedValue中的generator的名称
	    pkColumnValue="T_DEPARTMENT_ID",		//生成器表的主键列的记录名称
	    table="GENERATOR_TABLE",	//生成器表的表名
	    pkColumnName = "key_name",		//生成器表的主键列名
	    valueColumnName = "value",	//生成器表的下标列名
	    initialValue=1,				//初始值
	    allocationSize=1)			//下一个要使用的最大值
public class Department {
	
	//根节点
	@Transient//不是字段
	public final static int NODE_TYPE_ROOT = 1;
	//子节点
	@Transient//不是字段
	public final static int NODE_TYPE_CHILD = 2;
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="T_DEPARTMENT_ID")
	private int id;
	
    private String name;
    
    private String value;
    
    private String remark;
	
    private int pid;
	
    private int lft;
	
    private int rgt;
	
	private String rootName;
	
	private int nodeType = 0;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String key) {
		this.name = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getLft() {
		return lft;
	}

	public void setLft(int left) {
		this.lft = left;
	}

	public int getRgt() {
		return rgt;
	}

	public void setRgt(int right) {
		this.rgt = right;
	}

	public String getRootName() {
		return rootName;
	}

	public void setRootName(String rootName) {
		this.rootName = rootName;
	}

	public int getNodeType() {
		return nodeType;
	}

	public void setNodeType(int nodeType) {
		this.nodeType = nodeType;
	}
	
	
	
}
