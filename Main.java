import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.com.fasterxml.jackson.databind.ObjectMapper;

public class Main{
	public static void main(String[] args) throws InterruptedException, IOException{
		//Jsonのテスト
//		Player h = new TestPlayer("テスト5君");
//		h.setMoney(5000);
//		h.jobLevelUp("ファイター");
//		h.jobLevelUp("フェンサー");
//		//キャラのセーブ、ロード
//		String json = PlayerData.save(h);
//		if(PlayerData.load() instanceof TestPlayer) {
//			System.out.println("テスト君");
//		}
//		PlayerData.selectRemove(2);

        //タイトル
		System.out.println("ソードワールド2.0 ～再現の章～");
		title();
//		Shop.buy(new TestPlayer());//デバッグ用
//		Character h = new TestPlayer();//デバッグ
		List<Character> playerParty = new ArrayList<>();
		List<Character> monsterParty = new ArrayList<>();
//		playerParty.add(new TestPlayer("テスター君"));
//		playerParty.add(new TestPlayer("アルク"));
//		playerParty.add(new TestPlayer("ク"));
//		Character h = (Character)p;

		//キャラ作成
		CharacterCreater cc = new CharacterCreater();
		Player p = cc.getPlayer();
		//作成したキャラをセーブ
		PlaterData.save(p);
		System.out.println(PlayerData.load());
		//パーティを組む
		playerParty.add(p);
		playerParty.add(new TestPlayer("テスト"));
		playerParty.add(new TestPlayer("テスト2"));
		monsterParty.add(new Kobold());
		monsterParty.add(new Goblin());
		System.out.println();
		BattleManager.battle(playerParty, monsterParty);
//		BattleManager bm = new BattleManager(playerParty,m);//未完成
//		//作成したプレイヤーを保存（予定）
//		Player aruk = new TestPlayer("tesut");
//		PlayerData.save();
//		PlayerData.load(aruk);
//		System.out.println(aruk.getName());
//		System.out.println(aruk.getHp());
	}
	static void title() {
		String[] titleMenu = new String[4];
		int wordWidth = 20;
		titleMenu[0] = format("新規キャラ作成",wordWidth);
		titleMenu[1] = format("既存キャラ選択",wordWidth);
		titleMenu[2] = format("戦闘",wordWidth);
		titleMenu[3] = format("難易度選択",wordWidth);
		//メニュー表示
		while(true) {
			System.out.println("---------------------------------");
			System.out.printf("%s:%s%n",format("メニュー",wordWidth),"選択肢");
			for(int i=0;i<titleMenu.length;i++) {
				System.out.printf("%s:%d%n",titleMenu[i],i);
			}
			System.out.println("---------------------------------");
			break;
		}
		System.out.printf("※見た目だけの未完成タイトル%n%n");
		//新規キャラ作成
		//既存キャラ選択
		//戦闘
		//難易度選択
	}
	//全角半角の文字位置合わせ
	private static String format(String target, int length){
		int byteDiff = (getByteLength(target, Charset.forName("UTF-8"))-target.length())/2;
		return String.format("%-"+(length-byteDiff)+"s", target);
	}

	//文字のバイト数取得
	private static int getByteLength(String string, Charset charset) {
		return string.getBytes(charset).length;
	}
}
