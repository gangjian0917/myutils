package com.gangjian.jvm;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class DirectMemoryOOM {

	public static int _1M = 1024 * 1024;

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = Unsafe.class.getDeclaredFields();
		for (Field f : fields) {
			System.out.println(f);
		}

		Field unsafeField = fields[0];
		// System.out.println(unsafeField);
		unsafeField.setAccessible(true);
		Unsafe unsafe = (Unsafe) unsafeField.get(null);

		while (true) {
			unsafe.allocateMemory(_1M);
		}
	}

}
