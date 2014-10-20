package org.dong.proto.modules.permission.department;

import java.util.ArrayList;
import java.util.List;

public class DepartmentVO extends Department{
    
    private List<DepartmentVO> children = new ArrayList<DepartmentVO>();
    
    public DepartmentVO(String key,String value,int pid) {
    	this.setName(key);
    	this.setValue(value);
    	this.setNodeType(Department.NODE_TYPE_CHILD);
    	this.setPid(pid);
	}
    
    public DepartmentVO(Department u) {
    	this.setId(u.getId());
    	this.setName(u.getName());
    	this.setValue(u.getValue());
    	this.setLft(u.getLft());
    	this.setRgt(u.getRgt());
    	this.setPid(u.getPid());
    	this.setRemark(u.getRemark());
    	this.setRootName(u.getRootName());
    	this.setNodeType(u.getNodeType());
	}

	public void addChild(DepartmentVO child){
    	children.add(child);
    }
    
	public void setChildren(List<DepartmentVO> children) {
		this.children = children;
	}
	public List<DepartmentVO> getChildren() {
		return children;
	}
	
}
