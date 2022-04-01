import java.util.*;
public class Dice{
	int[] dice = {1,2,3,4,5,6};

	//ダイスを振って出目の和を求めるメソッド
	//引数：振る個数
	protected int roll(int num){
		Dice d = new Dice();
		//ダイスの出目と振ったダイスの出目の和
		int dice =0;
		int diceSum = 0;
		
		System.out.printf("%nダイスの出目%n");
		//出目を決める
		for(int i=1;i<=num;i++){
			dice = d.dice[new Random().nextInt(6)];  
			System.out.printf("%d個目:%d%n",i,dice);
			//出目の和
			diceSum += dice;
		}
		System.out.printf("出目合計:%d%n%n",diceSum);
		return diceSum;
	}
}
