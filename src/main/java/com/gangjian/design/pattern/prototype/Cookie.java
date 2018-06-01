package com.gangjian.design.pattern.prototype;

/** Prototype Class **/
public class Cookie implements Cloneable {

	@Override
	protected Object clone() throws CloneNotSupportedException {
		//In an actual implementation of this pattern you would now attach references to
        //the expensive to produce parts from the copies that are held inside the prototype.
		return super.clone();
	}

}
