package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

import character.monster.Goblin;
import character.monster.Kobold;
import character.player.Character;
import character.player.Player;
import option.BattleManager;
import option.CharacterCreater;
import option.Option;
import option.PlayerData;
import option.Shop;

public class Main {
	enum TitleMenu {
		NEWCHARACTER, SELECTCHARACTER, BATTLE, DIFFICULTY, QUIT,
	}

	enum GuildMenu {
		BAR, SHOP, BOARD, GROWUP,
	}

	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		//		PlayerData.load();
		//タイトル
		List<Character> playerParty = new ArrayList<>();
		List<Character> monsterParty = new ArrayList<>();
		String path = "player/party.json";
		//パーティ情報の読み込み
		ObjectMapper mapper = new ObjectMapper();
		List<String> loadParty = Option.loadString(path);
		for (String loadMember : loadParty) {
			playerParty.add(mapper.readValue(loadMember, Player.class));
		}
		//タイトルメニュー用意
		int wordWidth = 20;//文字幅
		String[] titleMenu = {
				Option.format("新規キャラ作成", wordWidth),
				Option.format("冒険者ギルド", wordWidth),
				Option.format("討伐クエスト", wordWidth),
				Option.format("難易度選択 未実装", wordWidth),
				Option.format("ゲーム終了", wordWidth),
		};
		//メニュー表示
		while (true) {
			System.out.println("ソードワールド2.0 ～再現の章～");
			Option.printLine(33);
			System.out.printf("%s:%s%n", Option.format("メニュー", wordWidth), "選択肢");
			for (int i = 0; i < titleMenu.length; i++) {
				System.out.printf("%s:%d%n", titleMenu[i], i);
			}
			Option.printLine(33);
			System.out.print("メニュー選択>>");
			int select = scan.nextInt();
			TitleMenu title = TitleMenu.values()[select];
			switch (title) {
			//新規キャラ作成
			case NEWCHARACTER:
				//未完成
				//保存キャラが一定数になると作成出来ない
				if (Option.loadFromCSV("player/player.json").size() < 5) {
					System.out.printf("新規キャラを作成します。%n%n");
					CharacterCreater cc = new CharacterCreater();
					PlayerData.save(cc.create());
				} else {
					System.out.printf("ギルドに待機してる冒険者がいっぱいです。%n%n");
					Thread.sleep(1000);
				}
				continue;
			//冒険者ギルド
			case SELECTCHARACTER:
				//メニュー用意
				String[] guildMenus = {
						"酒場",
						"ショップ",
						"冒険者を追い出す",
						"キャラの成長",
				};
				//メニュー表示
				while (true) {
					Option.showSelectMenu(guildMenus, 25);
					System.out.print("番号にてご用件を伺います(ギルドから出る:0)>>");
					select = scan.nextInt() - 1;
					if (select == -1) {
						System.out.println();
						break;
					}

					GuildMenu menu = null;
					if (Option.isWithinRange(select, guildMenus.length)) {
						menu = GuildMenu.values()[select];
					} else {
						System.out.println("メニュー番号からお選びください。\n");
						continue;
					}

					switch (menu) {
					//未完成
					//酒場
					case BAR:
						System.out.println("未完成");
						System.out.println("パーティに誘う冒険者を探します");
						System.out.println("現在のパーティ");
						BattleManager.displayStatus(playerParty);
						//デバッグ中
						try {
							FileInputStream fis = new FileInputStream("player/player.json");
							InputStreamReader isr = new InputStreamReader(fis, "utf-8");
							BufferedReader br = new BufferedReader(isr);
							List<Player> stayMember = new ArrayList<>();
							String line;
							while ((line = br.readLine()) != null) {
								stayMember.add(mapper.readValue(line, Player.class));
							}
							br.close();//ファイル閉じる
							//酒場に居る冒険者一覧
							System.out.println("酒場に居る冒険者");
							for (int i = 0; i < stayMember.size(); i++) {
								System.out.printf("%s %s%n", stayMember.get(i).getName(), i + 1);
							}
							System.out.print("誰かパーティに誘いますか？(上記番号から選ぶor戻る:0)>>");
							int selectStay = scan.nextInt() - 1;
							if (selectStay == -1)
								break;
							Player newMenber = stayMember.get(selectStay);
							System.out.println(newMenber);
							System.out.printf("%sをパーティに誘いますか？(誘う:0,やめる:1)>>", newMenber.getName());
							select = scan.nextInt();
							if (select == 1) {
								continue;
							}
							//パーティが四人居たら
							if (playerParty.size() < 4) {
								playerParty.add(newMenber);
								stayMember.remove(selectStay);
							} else {
								System.out.print("パーティがいっぱいです。誰かと交代しますか(はい:0,いいえ:1)>>");
								select = scan.nextInt();
								if (select == 0) {
									for (int i = 0; i < playerParty.size(); i++) {
										System.out.printf("%s %d%n", playerParty.get(i).getName(), i);
									}
									System.out.print("誰と交代しますか?>>");
									select = scan.nextInt();
									Character change = playerParty.get(select);
									playerParty.remove(select);
									playerParty.add(newMenber);
									stayMember.remove(selectStay);
									stayMember.add((Player) change);
								} else if (select == 1) {
									break;
								}
							}
							//パーティ情報の保存
							File partyPath = new File(path);
							partyPath.delete();
							for (Character player : playerParty) {
								if (player instanceof Player) {
									PlayerData.save((Player) player, path, true);
								}
							}
							//待機冒険者の保存
							path = "player.json";
							File stayPath = new File("player/" + path);
							stayPath.delete();
							for (Player stayPlayer : stayMember) {
								PlayerData.save(stayPlayer);
							}
						} catch (Exception e) {
							System.out.println("酒場に待機してる冒険者はいません。");
						}
						Thread.sleep(1000);
						System.out.println();
						break;
					//ショップ
					case SHOP:
						for (int i = 0; i < playerParty.size(); i++) {
							System.out.printf("%s %d%n", playerParty.get(i).getName(), i + 1);
						}
						System.out.print("誰が買い物しますか?>>");
						select = scan.nextInt() - 1;
						if (select == -1)
							break;
						Shop.buy((Player) playerParty.get(select));
						break;
					//掲示板
					//デバッグ中
					case BOARD:
						System.out.println("未完成");
						if (!PlayerData.showStayPlayer()) {
							System.out.println("酒場には誰も居ません");
							break;
						}
						System.out.print("誰を追い出しますか?(戻る:0)>>");
						select = scan.nextInt() - 1;
						if (select == -1)
							break;
						PlayerData.selectRemove(select);
						break;
					//キャラの成長
					case GROWUP:
						System.out.println("未完成");
						System.out.println("経験点を消費して技能レベルを上げられます");
						for (int i = 0; i < playerParty.size(); i++) {
							System.out.printf("%s %d%n", playerParty.get(i).getName(), i + 1);
						}
						System.out.print("誰の技能レベルを上げますか?(戻る:0)>>");
						select = scan.nextInt() - 1;
						System.out.println();
						if (select == -1)
							break;
						((Player) playerParty.get(select)).addJob();
						break;
					}
				}
				Thread.sleep(1000);
				break;
			//戦闘
			case BATTLE:
				System.out.println("未完成");
				Thread.sleep(1000);
				//モンスター用意
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
}
