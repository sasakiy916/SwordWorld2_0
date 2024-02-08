package option;

public class Guild {
	enum GuildMenu {
		BAR, 
		SHOP,
		BOARD,
		GROWUP,
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

	public void menu() {
		int widthLine = 25;
		while(true) {
			Option.printLine(widthLine);
			for(int i=0;i<guildMenus.length;i++) {
				System.out.printf("%s %s%n",guildMenus[i],i+1);
			}
			Option.printLine(widthLine);
		}
	}

	public static Guild getInstance() {
		return instance;
	}
}
