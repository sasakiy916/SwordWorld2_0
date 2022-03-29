import java.util.*;
public abstract class Player{
	int level = 1;
	int hp;
	int mp;
	String[] statusName = {
		"器用度",
		"敏捷度",
		"筋力",
		"生命力",
		"知力",
		"精神力",
	};
	int[] status = new int[statusName.length];
	String[] baseAbilityName = {"技","体","心"};
	int[] baseAbility = new int[baseAbilityName.length];
	String[] abilitySuffix = {
		"A",
		"B",
		"C",
		"D",
		"E",
		"F",
	};
	int[] ability = new int[abilitySuffix.length];
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
	public void setStatus(){
	}
	public void setAbility(){
	}
	public void setBaseAbility(int tec,int body, int spi){
		int[] setBaseAbilities = {tec,body,spi};
		for(int i=0;i<setBaseAbilities.length;i++){
			this.baseAbility[i] = setBaseAbilities[i];
		}
	}
}
