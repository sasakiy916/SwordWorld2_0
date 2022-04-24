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
	public static void main(String[] args) throws Exception{
		//タイトル
		List<Character> playerParty = new ArrayList<>();
		List<Character> monsterParty = new ArrayList<>();
//		title(playerParty,monsterParty);
		Scanner scan = new Scanner(System.in);
		int wordWidth = 20;//文字幅
		String[] titleMenu = {
				Option.format("新規キャラ作成",wordWidth),
				Option.format("冒険者ギルド",wordWidth),
				Option.format("討伐クエスト",wordWidth),
				Option.format("難易度選択 未実装",wordWidth),
				Option.format("ゲーム終了",wordWidth),
		};
		Player p = new TestPlayer("テストくん");//新キャラ用
		//メニュー表示
		while(true) {
			System.out.println("ソードワールド2.0 ～再現の章～");
			System.out.println("---------------------------------");
			System.out.printf("%s:%s%n",Option.format("メニュー",wordWidth),"選択肢");
			for(int i=0;i<titleMenu.length;i++) {
				System.out.printf("%s:%d%n",titleMenu[i],i);
			}
			System.out.println("---------------------------------");
			System.out.print("メニュー選択>>");
			int select = scan.nextInt();
			TitleMenu title = TitleMenu.values()[select];
			switch(title) {
			//新規キャラ作成
			case NEWCHARACTER:
				//未完成
				CharacterCreater cc = new CharacterCreater();
//				p = cc.getPlayer();
//				PlayerData.save(p);
				PlayerData.save(cc.create());
				System.out.println("未完成");
				continue;
				//既存キャラ選択
			case SELECTCHARACTER:
				System.out.println("未完成");
				Thread.sleep(1000);
				Shop.buy(PlayerData.load());
				break;
				//戦闘
			case BATTLE:
				System.out.println("未完成");
				Thread.sleep(1000);
				//パーティを組む
				playerParty.add(PlayerData.load());
				playerParty.get(0);
				playerParty.add(PlayerData.load());
				playerParty.add(PlayerData.load());
				monsterParty.add(new Kobold());
				monsterParty.add(new Goblin());
				System.out.println();
				//戦闘
				BattleManager.battle(playerParty, monsterParty);
				continue;
				//難易度選択
			case DIFFICULTY:
				System.out.println("未実装");
				Thread.sleep(1000);
				continue;
			default:
				System.out.printf("%n冒険お疲れさまでした<(_ _)>%n");
				return;

			}
		}
	}
	static void title(List<Character> playerParty,List<Character> monsterParty) throws Exception {
		Scanner scan = new Scanner(System.in);
		String[] titleMenu = new String[5];
		int wordWidth = 20;//文字幅
		titleMenu[0] = Option.format("新規キャラ作成",wordWidth);
		titleMenu[1] = Option.format("既存キャラ選択 未実装",wordWidth);
		titleMenu[2] = Option.format("討伐クエスト",wordWidth);
		titleMenu[3] = Option.format("難易度選択 未実装",wordWidth);
		titleMenu[4] = Option.format("ゲーム終了",wordWidth);
		Player p = new TestPlayer("テストくん");//新キャラ用
		//メニュー表示
		while(true) {
			System.out.println("---------------------------------");
			System.out.printf("%s:%s%n",Option.format("メニュー",wordWidth),"選択肢");
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
				Shop.buy(PlayerData.load());
				break;
				//戦闘
			case BATTLE:
				System.out.println("未実装");
				//パーティを組む
				//装備情報が保存されないのでロードしたら裸になってる
				playerParty.add(PlayerData.load());
				playerParty.add(PlayerData.load());
				playerParty.add(PlayerData.load());
				monsterParty.add(new Kobold());
				monsterParty.add(new Goblin());
				System.out.println();
				//戦闘
				BattleManager.battle(playerParty, monsterParty);
				continue;
				//難易度選択
			case DIFFICULTY:
				System.out.println("未実装");
				continue;
			default:
				return;

			}
		}
	}
}
