package com.gangjian.design.pattern.factory;

public class WinButtonFactory implements ButtonFactory {
	@Override
	public Button createButton() {
		return new WinButton();
	}
}
