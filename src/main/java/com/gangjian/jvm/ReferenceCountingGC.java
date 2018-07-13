package com.gangjian.jvm;

public class ReferenceCountingGC {
	public Object instance = null;
	private static int _100m = 1 * 1024 * 1024;
	private byte[] bigSize = new byte[2 * _100m];

	public static void testGc() {
		ReferenceCountingGC objA = new ReferenceCountingGC();
		ReferenceCountingGC objB = new ReferenceCountingGC();
		objA.instance = objB;
		objB.instance = objA;

		objA = null;
		objB = null;
		System.gc();
	}

	public static void main(String[] args) {
		testGc();
	}
}