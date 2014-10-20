package org.dong.proto.util.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 *作者：dongjibo
 *创建时间：2012-5-29
 */
public class ExcelFactory {

	private static ExcelFactory excelFactory;
	
	public final static int CHAR_WIDTH = 256;//每个字符的宽度
	
	private HSSFWorkbook book;
	
	private int columWidth;//列宽
	
	private boolean hasBorder;//是否画边框
	
	private ExcelFactory() {
		columWidth = 10;
		hasBorder(false);
	}
	
	public static ExcelFactory getInstance() {
		if (null == excelFactory) {
			return excelFactory = new ExcelFactory();
		} else {
			return excelFactory;
		}
		
	}

	/**
	 * 创建excel文件
	 * @param sheets excel中的每页内容
	 */
	public void createExcel( List<SheetInfo> sheets) {
		book = new HSSFWorkbook();
		HSSFSheet sheet =null;
		HSSFRow row = null;
		HSSFCell cell = null;
		
		//设置边框
		HSSFFont font = book.createFont();
		HSSFCellStyle cellStyle = book.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
		
		//解析页
		for(SheetInfo s : sheets){
			sheet = book.createSheet(s.getSheetName());
			//解析标题title
			row = sheet.createRow(0);
			for (int i = 0; null != s.getTitles() && i < s.getTitles().size(); i++) {
				cell = row.createCell(i);
				cell.setCellValue(s.getTitles().get(i));
				//设置列宽度
				sheet.setColumnWidth(i,columWidth);
				//设置边框
				if (isHasBorder()) {
					cell.setCellStyle(cellStyle);
				}
				
			}
			//解析行数据
			for (int i = 0; null !=  s.getRows() && i < s.getRows().size(); i++) {
				row = sheet.createRow(i+1);
				//解析列
				for (int j = 0; j < s.getRows().get(i).size(); j++) {
					cell = row.createCell(j);
					cell.setCellValue(s.getRows().get(i).get(j));
					//设置边框
					if (isHasBorder()) {
						cell.setCellStyle(cellStyle);
					}
				}
			}
		}
		
		
	}
	
	/**
	 *  生产文件
	 * @param fileName 文件名称，可以包含路径
	 */
	public void saveToFile(String fileName) {
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			book.write(fileOut);
			fileOut.close();
			book = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 读取文件缓存
	 * @param out 流
	 */
	public void saveToFile(OutputStream out) {
		try {
			book.write(out);
			book = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 设置列宽，最大宽度为255*255,即最大255个字符宽
	 * @param columWidth
	 */
	public void setColumWidth(int columWidth) {
		if (columWidth > CHAR_WIDTH*255) {
			columWidth = CHAR_WIDTH*255;
		}
		this.columWidth = columWidth;
	}

	public int getColumWidth() {
		return columWidth;
	}

	public void hasBorder(boolean hasBorder) {
		this.hasBorder = hasBorder;
	}

	public boolean isHasBorder() {
		return hasBorder;
	}

}



