package com.gangjian.jvm;

import java.util.ArrayList;
import java.util.List;
/**
 * -XX:PermSize=10M -XX:MaxPermSize=10M
 * jdk1.6
 * @author jamesding
 *
 */
public class RuntimeConstantPoolOOM {

	public static void main(String[] args) {
		try {
			List<String> list = new ArrayList<>();
			int i = 0;
			while (true) {
				list.add(String.valueOf(i++).intern());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
