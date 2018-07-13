package com.gangjian.jvm;

import java.util.ArrayList;
import java.util.List;

public class OutOfMemoryTest {
	
	static class OOMObject{
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<OOMObject> list = new ArrayList<>();
		while(true) {
			list.add(new OOMObject());
		}
	}

}
