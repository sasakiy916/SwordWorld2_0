package main;

import state.SelectWindow;

public class Main {

	public static void main(String[] args) throws Exception {
		// ゲームスタート
		SelectWindow game = SelectWindow.getInstance();
//		BattleState bs = BattleState.getAAA();
//		System.out.println(bs);
//		System.out.println(BattleState.getInstance());
		game.start();
	}
}
