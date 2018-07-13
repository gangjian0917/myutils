package com.gangjian.jvm;

public class StringInternTest {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = "HelloWorld";
		String s2 = new String("HelloWorld");
		String s3 = "Hello";
		String s4 = "World";
		String s5 = "Hello" + "World";
		String s6 = s3 + s4;
		String s7 = (s3 + s4).intern();

		System.out.println(s1 == s2);
		System.out.println(s1 == s5);
		System.out.println(s1 == s6);
		System.out.println(s1 == s6.intern());
		System.out.println(s2 == s2.intern());

		System.out.println(s1 == s7.intern());

	}
}
