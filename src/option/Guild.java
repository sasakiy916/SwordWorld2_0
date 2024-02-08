package option;

import java.util.Scanner;

public class Guild {
	enum GuildMenu {
		BAR, SHOP, BOARD, GROWUP,
	}

	//メニュー用意
	String[] guildMenus = {
			"酒場",
			"ショップ",
			"冒険者を追い出す",
			"キャラの成長",
	};
	private static Guild instance = new Guild();

	private Guild() {

	}

	public int menu() {
		int select = 0;
		Scanner scan = new Scanner(System.in);
		while (true) {
			Option.showSelectMenu(guildMenus);
			System.out.print("番号にてご用件を伺います(ギルドから出る:0)>>");
			select = scan.nextInt() - 1;
			if (select == -1) {
				System.out.println();
				break;
			}
			GuildMenu menu = null;
			try {
				menu = GuildMenu.values()[select];
			} catch (Exception e) {
				System.out.println("メニュー番号からお選びください。");
				System.out.println();
				continue;
			}
		}
		return select;
	}

	public static Guild getInstance() {
		return instance;
	}
}
