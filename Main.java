import java.util.ArrayList;
import java.util.List;

public class Main{
	public static void main(String[] args){
//		Shop.buy(new TestPlayer());//デバッグ用
//		Character h = new TestPlayer();//デバッグ
		List<Character> playerParty = new ArrayList<>();
		List<Character> monsterParty = new ArrayList<>();
//		playerParty.add(new TestPlayer("テスター君"));
//		playerParty.add(new TestPlayer("アルク"));
//		playerParty.add(new TestPlayer("ク"));
//		Character h = (Character)p;
		CharacterCreater cc = new CharacterCreater();
		Player p = cc.getPlayer();
		playerParty.add(p);
		Character m = new Kobold();
		monsterParty.add(m);
		System.out.println();
		BattleManager.battle(playerParty, monsterParty);
//		BattleManager bm = new BattleManager(playerParty,m);//未完成
	}
}
