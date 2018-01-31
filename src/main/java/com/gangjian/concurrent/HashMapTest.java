package com.gangjian.concurrent;

import java.util.HashMap;
import java.util.UUID;

/**
 * <Java并发编程的艺术> chapter 6
 * 
 * @date 2018年1月30日
 * @time 上午10:20:30
 * @author gangjian
 * @version 1.0.0-RC01
 */
public class HashMapTest {

	// hashMap并发测试
	public static void hashMapTest() {
		final HashMap<String, String> map = new HashMap<String, String>(2);
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100000000; i++) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							map.put(UUID.randomUUID().toString(), "");
						}
					}).start();
				}
			}
		});

		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println(Runtime.getRuntime().availableProcessors());
		hashMapTest();
	}
}
