package org.dong.proto.modules.permission.resource.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuVO extends Menu{
    
    private List<MenuVO> children = new ArrayList<MenuVO>();
    
    public MenuVO(String key,String value,int pid) {
    	this.setName(key);
    	this.setValue(value);
    	this.setNodeType(Menu.NODE_TYPE_CHILD);
    	this.setPid(pid);
	}
    
    public MenuVO(Menu u) {
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

	public void addChild(MenuVO child){
    	children.add(child);
    }
    
	public void setChildren(List<MenuVO> children) {
		this.children = children;
	}
	public List<MenuVO> getChildren() {
		return children;
	}
	
}
