package org.dong.proto.util.excel;

import java.util.ArrayList;
import java.util.List;

/**
 *作者：dongjibo
 *创建时间：2012-5-29
 */
public class SheetInfo {
	
	//页名
	private String sheetName = "第一页";
	//标题栏
	private List<String> titles = new ArrayList<String>();
	//行数据
	private List<List<String>> rows = new ArrayList<List<String>>();
	
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public String getSheetName() {
		return sheetName;
	}
	/**
	 * 添加一行数据，可以用数组和list两种形式
	 * @param row 
	 */
	public void addRow(List<String> row) {
		getRows().add(row);
	}
	public void addRow(String[] row) {
		List<String> r = new ArrayList<String>();
		for (int i = 0; null != row && i < row.length; i++) {
			r.add(row[i]);
		}
		getRows().add(r);
	}
	/**
	 * 添加标题，可以用数组和list两种形式
	 * @param title
	 */
	public void setTitles(String[] title) {
		List<String> r = new ArrayList<String>();
		for (int i = 0; null != title && i < title.length; i++) {
			r.add(title[i]);
		}
		this.titles = r;
	}
	public void setTitles(List<String> titles) {
		this.titles = titles;
	}
	public List<String> getTitles() {
		return titles;
	}
	public void setRows(List<List<String>> rows) {
		this.rows = rows;
	}
	public List<List<String>> getRows() {
		return rows;
	}

}



