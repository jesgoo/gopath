package org.dong.proto.util.easyui;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeNodeInfo {
	
	private String id ;;// node id, which is important to load remote data
	private String text;// node text to show
	private String state;// node state, 'open' or 'closed', default is 'open'. When set to 'closed', the node have children nodes and will load them from remote site
	private boolean checked;// Indicate whether the node is checked selected.
	private Map<String,String> attributes;// custom attributes can be added to a node
	private List<TreeNodeInfo> children;// an array nodes defines some children nodes
	
	public TreeNodeInfo(String id,String text) {
		attributes = new HashMap<String,String>();
		children = new ArrayList<TreeNodeInfo>();
		state = "open";//如果初始化为closed，说明有子节点，会去加载子节点
		checked = false;
		this.setId(id);
		this.setText(text);
	}
	/**
	 * 添加子节点
	 * @param child
	 */
	public void addChild(TreeNodeInfo child){
		children.add(child);
	}
	/**
	 * 添加属性
	 * @param key
	 * @param value
	 */
	public void addAttribute(String key,String value){
		attributes.put(key, value);
	}
	
	/**
	 * 获取属性
	 * @param key
	 * @return
	 */
	public String getAttribute(String key) {
		return attributes.get(key);
	}
	
	
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getState() {
		return state;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getText() {
		return text;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setAttributes(Map<String,String> attributes) {
		this.attributes = attributes;
	}
	public Map<String,String> getAttributes() {
		return attributes;
	}
	public void setChildren(List<TreeNodeInfo> children) {
		this.children = children;
	}
	public List<TreeNodeInfo> getChildren() {
		return children;
	}
	
	
	
}
