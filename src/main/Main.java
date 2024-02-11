package main;

import state.SelectWindow;

public class Main {

	public static void main(String[] args) throws Exception {
		// ゲームスタート
		SelectWindow game = SelectWindow.getInstance();
		game.start();
	}
}
