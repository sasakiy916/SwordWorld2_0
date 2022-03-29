import java.util.*;
public class Human extends Player{
	int[] baseAbility = {8,4,9};//削除予定
	int[] ability = {//削除予定、数値を設定するメソッドをPlayerクラスに書く予定
		super.roll(2),
		super.roll(2),
		super.roll(2),
		super.roll(2),
		super.roll(2),
		super.roll(2)
	};
	int initExp = 0;

	public Human(){
		//status設定処理、Playerクラスに移動したい
		int num = 0;
		for(int ability:this.ability){
			System.out.printf("%s:%2d ",abilitySuffix[num],ability);
			num++;
		}
		System.out.printf("%n%n");
		num = 0;
		for(int baseAbility:this.baseAbility){
				this.status[num] = baseAbility + this.ability[num];
				num++;
				this.status[num] = baseAbility + this.ability[num];
				num++;
		}
		for(int j=0;j<statusName.length;j++){
			System.out.printf("%3s:%2d%n",this.statusName[j],this.status[j]);
		}
	}
}
