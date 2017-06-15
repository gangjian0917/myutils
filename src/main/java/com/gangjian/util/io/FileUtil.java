package com.gangjian.util.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	/**
	 * 删除所有文件和子文件
	 *
	 * @param file
	 */
	public static void delete(File file) {
		if (file != null && file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					delete(files[i]);
				}
			}
			file.delete();
		}
	}

	/**
	 * 返回路径下所有文件和子文件
	 * 
	 * @param filePath
	 * @return
	 */
	public static List<File> readAllFile(String filePath) {
		File f = null;
		f = new File(filePath);
		List<File> resutList = new ArrayList<File>();
		File[] files = f.listFiles(); // 得到f文件夹下面的所有文件。
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					// 如何当前路径是文件夹，则循环读取这个文件夹下的所有文件
					resutList.addAll(readAllFile(file.getAbsolutePath()));
				} else {
					resutList.add(file);
				}
			}
		}
		return resutList;
	}

	/**
	 * 返回路径下所有文件夹和子文件夹
	 * 
	 * @param filePath
	 * @return
	 */
	public static List<File> readAllDirectory(String filePath) {
		List<File> list = new ArrayList<File>();
		File f = new File(filePath);
		File[] files = f.listFiles(); // 得到f文件夹下面的所有文件。
		if (files != null) {
			for (File dir : files) {
				if (dir.isDirectory()) {
					// 如何当前路径是文件夹，则循环读取这个文件夹下的所有文件
					list.add(dir);
					list.addAll(readAllDirectory(dir.getAbsolutePath()));
				}
			}
		}
		return list;
	}
}