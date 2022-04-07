import SwCharacter.*;
import java.awt.*;
import javax.swing.*;
public class Main{
	public static void main(String[] args){
		//ウィンドウ表示
		MainMenu menu = new MainMenu("ソードワールド再現");
		menu.setVisible(true);
		//キャラクター用意、作成
		Character h = new Human();
		Character m = new Kobold();
		System.out.println();
		//戦闘システム開始
		BattleManager bm = new BattleManager(h,m);//未完成
	}
}
