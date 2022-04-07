package character;
import java.util.*;
public class Human extends Player{
	//String[] birthName = {
	//	"魔動技師",
	//	"魔法使い",
	//};
	//int[][] baseAbilites = {
	//	{8,4,9},
	//	{7,4,10},
	//};

	public Human(){
		Dice d = new Dice();
		setName("アルクレイド");
		//基礎能力値設定
		setTec(8);
		setBody(4);
		setMind(9);
		//A~F能力値ダイスロール
		setStatusA(d.roll(2));
		setStatusB(d.roll(2));
		setStatusC(d.roll(2));
		setStatusD(d.roll(2));
		setStatusE(d.roll(2));
		setStatusF(d.roll(2));
		//全能力値決定
		decideAllStatus();
		//A~Fのダイスロール結果表示
		System.out.println("A~Fまでのダイス値");
		System.out.printf("%d,%d,%d,%d,%d,%d%n%n",getStatusA(),getStatusB(),getStatusC(),getStatusD(),getStatusE(),getStatusF());
		setStatus();
		//能力値表示
		System.out.println("能力値が決定しました！");
		System.out.println("------------------------");
		for(int i=0;i<statusName.length;i++){
			System.out.printf("%s:%d%n",statusName[i],status[i]);
		}
		System.out.println("------------------------");
	}
}
