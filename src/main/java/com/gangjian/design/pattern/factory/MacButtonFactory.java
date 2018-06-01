package com.gangjian.design.pattern.factory;

public class MacButtonFactory implements ButtonFactory {
	@Override
	public Button createButton() {
		return new MacButton();
	}
}
