package state;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

import character.player.Character;
import character.player.Player;
import manager.BattleManager;
import manager.PartyManager;
import option.Option;
import option.PlayerData;
import option.Shop;

public class GuildState extends MenuState {
	enum GuildMenu {
		BAR, SHOP, BOARD, GROWUP, HOTEL
	}

	protected GuildState() {
		;
	}

	@Override
	public void setState() {
		this.menuStates = new MenuState[] {
				MenuState.getInstance(MenuEnum.TITLE),
		};
	}

	@Override
	public void execute() {
		System.out.println("ギルド");
		Scanner scan = new Scanner(System.in);

		PartyManager manager = PartyManager.getInstance();

		List<Character> playerParty = manager.getPlayerParty();
		String path = "player/party.json";
		//メニュー用意
		String[] guildMenus = {
				"酒場",
				"ショップ",
				"冒険者を追い出す",
				"キャラの成長",
				"宿屋"
		};
		//メニュー表示
		while (true) {
			Option.showSelectMenu(guildMenus, 25);
			System.out.print("番号にてご用件を伺います(ギルドから出る:0)>>");
			int select = scan.nextInt() - 1;
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
					ObjectMapper mapper = new ObjectMapper();
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
				Option.sleep(1000);
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
				try {
					PlayerData.selectRemove(select);
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
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
			case HOTEL:
				System.out.println("出血大サービスだ！タダで泊めてやるよ！");
				for (Character c : playerParty) {
					if (c instanceof Player) {
						Player p = (Player) c;
						p.setHp(p.getMaxHp());
						System.out.printf("%sは眠った%n", p.getName());
					}
				}
				BattleManager.displayStatus(playerParty);
				break;
			default:
				break;
			}
		}
		Option.sleep(1000);
		SelectWindow.getInstance().changeMenu();
	}

}
