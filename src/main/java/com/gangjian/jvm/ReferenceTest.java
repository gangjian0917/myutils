package com.gangjian.jvm;

import java.lang.ref.SoftReference;

public class ReferenceTest {
	static class ReferenceObject {
		public long a = System.currentTimeMillis();
	}

	public static void main(String[] args) {
		ReferenceObject ro = new ReferenceObject();
		SoftReference<ReferenceObject> sr = new SoftReference(ro);
		System.gc();
		ReferenceObject roGeted = sr.get();
		System.out.println(roGeted.a);
	}
}