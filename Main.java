import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main{
	enum TitleMenu{
		NEWCHARACTER,
		SELECTCHARACTER,
		BATTLE,
		DIFFICULTY,
		QUIT,
	}
	public static void main(String[] args) throws InterruptedException, IOException{
		//タイトル
		System.out.println("ソードワールド2.0 ～再現の章～");
		Player p = title();
		List<Character> playerParty = new ArrayList<>();
		List<Character> monsterParty = new ArrayList<>();

<<<<<<< HEAD
		//キャラ作成
		CharacterCreater cc = new CharacterCreater();
		Player p = cc.getPlayer();
		//作成したキャラをセーブ
=======
>>>>>>> work
		//パーティを組む
		//装備情報が保存されないのでロードしたら裸になってる
		playerParty.add(p);
		playerParty.add(PlayerData.load());
		playerParty.add(new TestPlayer("テスト"));
		playerParty.add(new TestPlayer("テスト2"));
		monsterParty.add(new Kobold());
		monsterParty.add(new Goblin());
		System.out.println();
		//戦闘
		BattleManager.battle(playerParty, monsterParty);
	}
	static Player title() throws IOException {
		Scanner scan = new Scanner(System.in);
		String[] titleMenu = new String[5];
		int wordWidth = 20;//文字幅
		titleMenu[0] = format("新規キャラ作成",wordWidth);
		titleMenu[1] = format("既存キャラ選択 未実装",wordWidth);
		titleMenu[2] = format("戦闘 未実装",wordWidth);
		titleMenu[3] = format("難易度選択 未実装",wordWidth);
		titleMenu[4] = format("キャラを一体ロードして戦闘開始",wordWidth);
		Player p = new TestPlayer("テストくん");
		//メニュー表示
		while(true) {
			System.out.println("---------------------------------");
			System.out.printf("%s:%s%n",format("メニュー",wordWidth),"選択肢");
			for(int i=0;i<titleMenu.length;i++) {
				System.out.printf("%s:%d%n",titleMenu[i],i);
			}
			System.out.println("---------------------------------");
			System.out.printf("※見た目だけの未完成タイトル%n");
			System.out.print("メニュー選択>>");
			int select = scan.nextInt();
			TitleMenu title = TitleMenu.values()[select];
			switch(title) {
			//新規キャラ作成
			case NEWCHARACTER:
				//未完成
				CharacterCreater cc = new CharacterCreater();
				p = cc.getPlayer();
				PlayerData.save(p);
				System.out.println("未完成");
				continue;
				//既存キャラ選択
			case SELECTCHARACTER:
				System.out.println("未実装");
				continue;
				//戦闘
			case BATTLE:
				System.out.println("未実装");
				continue;
				//難易度選択
			case DIFFICULTY:
				System.out.println("未実装");
				continue;
			default:
				return p;

			}
		}
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
