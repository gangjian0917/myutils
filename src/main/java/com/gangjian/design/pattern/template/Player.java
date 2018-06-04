package com.gangjian.design.pattern.template;

public class Player {
	public static void main(String[] args) {
		Game chessGame = new Chess();
		chessGame.initializeGame();
		chessGame.playOneGame(4); // call template method
	}
}
