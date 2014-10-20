package org.dong.proto.modules.permission.resource.menu;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.dong.proto.modules.permission.constants.OSPConstants;
import org.dong.proto.modules.permission.resource.Resource;

@Entity
@DiscriminatorValue(value=OSPConstants.RESOURCE_TYPE_MENU)
public class Menu extends Resource{
	
	//根节点
	@Transient//不是字段
	public final static int NODE_TYPE_ROOT = 1;
	//子节点
	@Transient//不是字段
	public final static int NODE_TYPE_CHILD = 2;
	
    private int pid;
	
    private int lft;
	
    private int rgt;
	
	private String rootName;
	
	private int nodeType = 0;
    
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getPid() {
		return pid;
	}
	/**
	 * @param nodeType the nodeType to set
	 */
	public void setNodeType(int nodeType) {
		this.nodeType = nodeType;
	}
	/**
	 * @return the nodeType
	 */
	public int getNodeType() {
		return nodeType;
	}
	/**
	 * @param left the left to set
	 */
	public void setLft(int left) {
		this.lft = left;
	}
	/**
	 * @return the left
	 */
	public int getLft() {
		return lft;
	}
	/**
	 * @param right the right to set
	 */
	public void setRgt(int right) {
		this.rgt = right;
	}
	/**
	 * @return the right
	 */
	public int getRgt() {
		return rgt;
	}
	/**
	 * @param rootName the rootName to set
	 */
	public void setRootName(String rootName) {
		this.rootName = rootName;
	}
	/**
	 * @return the rootName
	 */
	public String getRootName() {
		return rootName;
	}
	
}
