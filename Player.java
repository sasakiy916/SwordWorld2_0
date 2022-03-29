import java.util.*;
public abstract class Player{
	int level = 1;
	int hp;
	int mp;
	String[] statusName = {
		"dex",
		"agi",
		"str",
		"vit",
		"wis",
		"pow",
	};
	int[] status = new int[statusName.length];
	int[] baseAbility = new int[3];
	int[] ability = new int[6];
	Dice d = new Dice();
	//public int attack(){
	//	System.out.printf("%sダメージを与えた",str);
	//	return this.str;
	//}
	//public Player(){
	//	setStatus();
	//}

	public int roll(int num){
		int dice =0;
		int diceSum = 0;
		
		System.out.println("ダイスの出目");
		for(int i=1;i<=num;i++){
			dice = this.d.dice[new Random().nextInt(6)];  
			System.out.printf("%d個目:%d%n",i,dice);
			diceSum += dice;
		}
		System.out.println(diceSum);
		return diceSum;
	}
}
