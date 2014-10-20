package org.dong.proto.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

import org.dong.proto.util.log.LogUtil;


/**
 *作者：dongjibo
 *创建时间：2012-5-30
 */
public class ZipToFile {
	/**
     * 缓存区大小默认20480
     */
    private final static int FILE_BUFFER_SIZE = 20480;
     
    /**
     * 将指定目录的ZIP压缩文件解压到指定的目录
     * @param zipFilePath       ZIP压缩文件的路径
     * @param zipFileName       ZIP压缩文件名字
     * @param targetFileDir     ZIP压缩文件要解压到的目录
     * @return flag             布尔返回值
     */
    public static boolean unzip(String zipFilePath, String zipFileName, String targetFileDir){
    	boolean flag = false;
        File file = new File(zipFilePath + "/" + zipFileName);
        if(false == file.exists()) {
            LogUtil.info("压缩文件【" + zipFilePath + "/" + zipFileName + "】不存在");
            return false;
  	    }
        
        LogUtil.info("解压文件【"+zipFileName+"】开始");
        LogUtil.info("解压文件【" + zipFilePath + "/" + zipFileName + "】到【" + targetFileDir + "】目录下");
        //开始解压ZIP压缩文件的处理
        byte[] buf = new byte[FILE_BUFFER_SIZE];
        int readSize = -1;
        ZipInputStream zis = null;
        FileOutputStream fos = null;
        try {
            // 判断目标目录是否存在，不存在则创建
            File newdir = new File(targetFileDir);
            if (false == newdir.exists()) {
                newdir.mkdirs();
                newdir = null;
            }
            zis = new ZipInputStream(new FileInputStream(file));
            ZipEntry zipEntry = zis.getNextEntry();
            // 开始对压缩包内文件进行处理
            while (null != zipEntry) {
                String zipEntryName = zipEntry.getName().replace('\\', '/');
                //判断zipEntry是否为目录，如果是，则创建
                if(zipEntry.isDirectory()) {
                    int indexNumber = zipEntryName.lastIndexOf('/');
                    File entryDirs = new File(targetFileDir + zipEntryName.substring(0, indexNumber));
                    entryDirs.mkdirs();
                    entryDirs = null;
                    LogUtil.info("创建文件夹："+targetFileDir + zipEntryName.substring(0, indexNumber));
                } else {
                    try {
                        fos = new FileOutputStream(targetFileDir + zipEntryName);
                        while ((readSize = zis.read(buf, 0, FILE_BUFFER_SIZE)) != -1) {
                            fos.write(buf, 0, readSize);
                        }
                        LogUtil.info("解压文件："+targetFileDir + zipEntryName);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException(e.getCause()); 
                    } finally {
                        try {
                            if (null != fos) {
                                fos.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            throw new RuntimeException(e.getCause()); 
                        }
                    }
                }
                zipEntry = zis.getNextEntry();
            }
            flag = true;
            LogUtil.info("--解压成功--");
        } catch (ZipException e) {
            LogUtil.error("--解压失败--", e);
            throw new RuntimeException(e.getCause()); 
        } catch (IOException e) {
            LogUtil.error("--解压失败--", e);
            throw new RuntimeException(e.getCause()); 
        } finally {
            try {
                if (null != zis) {
                    zis.close();
                }
                if (null != fos) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getCause()); 
            }
        }
    	
        LogUtil.info("解压文件【"+zipFileName+"】结束");
    	
    	return flag;
    }
    
    /**
     * 接完目录下所有的zip文件
     * @param zipFilePath
     * @param targetFileDir
     */
	public static void unzip(String zipFilePath, String targetFileDir) {
        String[] fileNames = FileUtil.getFileNames(zipFilePath, "zip");
        for (int i = 0; null != fileNames && i < fileNames.length; i++) {
        	ZipToFile.unzip(zipFilePath, fileNames[i], targetFileDir);
		}
	}
	
}




