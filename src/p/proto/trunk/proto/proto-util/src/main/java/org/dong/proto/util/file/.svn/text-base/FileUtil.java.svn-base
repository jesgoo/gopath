package org.dong.proto.util.file;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 *作者：dongjibo
 *创建时间：2012-7-17
 */
public class FileUtil {

	/**
	 * 删除文件夹
	 * @param filePath
	 */
	public static void deleteFileDir(String filePath) {
		File files = new File(filePath);
		String[] names = files.list();
		for (int i = 0; i < names.length; i++) {
			//删除文件
			File f = new File(filePath +"\\"+names[i]);
			if (f.isDirectory()) {
				deleteFileDir(f.getAbsolutePath());
			} else {
				f.delete();
			}
			
		}
		//删除目录
		files.delete();
	}
	
	/**
	 * 获取文件夹下的文件名称
	 * @param filePath	文件路径
	 * @param fileType	文件类型
	 * @return
	 */
	public static String[] getFileNames(String filePath, String fileType) {
		File fileDir = new File(filePath);
        return fileDir.list(new DirFilter("."+fileType));
        
	}

	/**
	 * 读取行数据
	 * @param fileName
	 * @param listener
	 */
	public static void readLine(String fileName,FileReadListener listener) {
		 File file = new File(fileName);
	        BufferedReader reader = null;
	        try {
	            reader = new BufferedReader(new FileReader(file));
	            String tempString = null;
	            int line = 1;
	            // 一次读入一行，直到读入null为文件结束
	            while ((tempString = reader.readLine()) != null) {
	                // 显示行号
//	                System.out.println("line " + line + ": " + tempString);
	                listener.excute(line,tempString);
	                line++;
	            }
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e1) {
	                }
	            }
	        }
	}
	
	
	
}

/**
 * 文件过滤器
 * @author dongjibo
 *
 */
 class DirFilter implements FilenameFilter{
	private String type;
	public DirFilter(String tp){
	this.type=tp;
	}
	@Override
	public boolean accept(File dir, String name) {
		return name.indexOf(type)!=-1;
	}
	
}
/**
 * 文件读取处理
 * @author dongjibo
 *
 */
 interface FileReadListener{
	 /**
	  * 文件读取处理函数
	  * @param index	行号
	  * @param s		行的内容
	  */
	 public void excute(int index,String s);
 }
 
