import java.util.*;
public class Human extends Player{
	String[] baseAbilityName = {"技","体","心"};
	int[] baseAbility = {8,4,9};
	String[] abilitySuffix = {
		"A",
		"B",
		"C",
		"D",
		"E",
		"F",
	};
	int[] ability = {
		super.roll(2),
		super.roll(2),
		super.roll(2),
		super.roll(2),
		super.roll(2),
		super.roll(2)
	};
	int initExp = 0;

	public Human(){
		int num = 0;
		for(int ability:this.ability){
			System.out.printf("%s:%d ",abilitySuffix[num],ability);
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
			System.out.printf("%s:%d%n",this.statusName[j],this.status[j]);
		}
	}
}
