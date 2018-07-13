package com.gangjian.jvm;

public class JvmStackSOF {

	private int stackLength = 1;

	public void stackLeak() {
		System.out.println("stackLength=" + stackLength);
		stackLength++;
		stackLeak();

	}

	public static void main(String[] args) {
		JvmStackSOF js = new JvmStackSOF();
		try {
			js.stackLeak();

		} catch (Exception e) {
			System.out.println("stackLength=" + js.stackLength);
			e.printStackTrace();
		}
	}

}
