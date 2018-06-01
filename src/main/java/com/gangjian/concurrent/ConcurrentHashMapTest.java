package com.gangjian.concurrent;

import java.util.Hashtable;

/**
 * <Java并发编程的艺术> chapter 6
 * 
 * @date 2018年1月30日
 * @time 上午10:20:30
 * @author gangjian
 * @version 1.0.0-RC01
 */
public class ConcurrentHashMapTest {

	// hashMap并发测试
	public static void concurrentHashMapTest() {
		// ConcurrentHashMap map = new ConcurrentHashMap();
	}

	public static void main(String[] args) {
		concurrentHashMapTest();
		System.out.println(Integer.MAX_VALUE);
		new Hashtable<>();
		System.out.println(Integer.parseInt("0001111", 2) & 15);
		System.out.println(Integer.parseInt("0011111", 2) & 15);
		System.out.println(Integer.parseInt("0111111", 2) & 15);
		System.out.println(Integer.parseInt("1111111", 2) & 15);
	}
}
