import java.util.ArrayList;
import java.util.List;

public class Main{
	public static void main(String[] args) throws InterruptedException{
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
		playerParty.add(new TestPlayer("テスト"));
		playerParty.add(new TestPlayer("テスト2"));
		monsterParty.add(new Kobold());
		monsterParty.add(new Goblin());
		System.out.println();
		BattleManager.battle(playerParty, monsterParty);
//		BattleManager bm = new BattleManager(playerParty,m);//未完成
	}
}
