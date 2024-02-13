package main;

import java.util.Random;

import option.Option;

public class Dice {
	private static int[] dices = { 1, 2, 3, 4, 5, 6 };
	private static Random rand = new Random();

	//ダイスを振って出目の和を求めるメソッド
	//引数：振る個数
	public static int roll(int num, boolean isSkip) {
		//ダイスの出目と振ったダイスの出目の和
		int dice = 0;
		int diceSum = 0;
		int ms = isSkip ? 500 : 0;

		System.out.printf("%dd6の出目 ", num);
		//出目を決める
		for (int i = 1; i <= num; i++) {
			if (i != 1) {
				Option.sleep(ms);
				System.out.print("+");
			}
			dice = Dice.dices[Dice.rand.nextInt(Dice.dices.length)];
			System.out.printf("%d", dice);
			//出目の和
			diceSum += dice;
		}
		Option.sleep(ms);
		System.out.printf(" → %d%n", diceSum);
		Option.sleep(ms * 2);
		return diceSum;
	}

	public static int roll(int num) {
		return roll(num,true);
	}
}
