import java.util.List;
import java.util.Scanner;
public class CharacterCreater {
	private Player player;//プレイヤーのインスタンス
	Scanner scan = new Scanner(System.in);
//	Wepon wepon = new Wepon();
	//コンストラクタ
	public CharacterCreater() throws Exception {
		Scanner scan = new Scanner(System.in);
		decideRaceAndBirth();//種族と生まれの決定
		//名前入力
		String name;
		while(true) {
			System.out.print("冒険者の名前を決めて下さい>>");
			name = scan.next();
			System.out.printf("%sでよろしいですか?(はい:0,いいえ:1)>>",name);
			if(scan.nextInt() == 0) {
				break;
			}
		}
		this.player.setName(name);
		decideStatus();//能力値の決定
		decideJobLevel();//技能の習得
		decideLevel();//冒険者レベルの決定
		//decideHpAndMp();//HP,MPの決定
		this.player.setHp(this.player.getMaxHp());
		this.player.setMp(this.player.getMaxMp());
//		decideSkill();//戦闘特技の習得
		Shop.buy(this.player);
		completeCharacter();//キャラの完成
	}

	//種族と生まれの決定
	public void decideRaceAndBirth() throws Exception {
		//種族一覧表示
		System.out.println("種族一覧");
		for(Race race:Race.values()) {
			System.out.printf("%s:%d%n",race.getName(),race.ordinal());
		}
		//種族を選択
		String birthData = "birth/";
		System.out.print("種族を選んでください>>");
		int selectRace = scan.nextInt();
		System.out.println();
		//種族決定
		switch(Race.values()[selectRace]) {
		case HUMAN:
			setPlayer(new Human());
			birthData += "birth - 人間.csv";
			break;
		case ELF:
			setPlayer(new Elf());
			birthData += "birth - エルフ.csv";
			break;
		}
		//生まれの一覧表示
		//ファイル読み込み
		List<String[]> births = Option.load(birthData);
		//各項目の列番号取得
		int birth = 0;
		int tec = 0;
		int body = 0;
		int mind = 0;
		int initJob = 0;
		int initJobSelect = 0;
		int initExp = 0;
		for(int i=0;i<births.get(0).length;i++) {
			switch(births.get(0)[i]) {
			case "生まれ":
				birth = i;
				break;
			case "技":
				tec = i;
				break;
			case "体":
				body = i;
				break;
			case "心":
				mind = i;
				break;
			case "初期所有技能":
				initJob = i;
				break;
			case "複数ある場合":
				initJobSelect = i;
				break;
			case "初期経験点":
				initExp = i;
				break;
			}
		}

		//生まれ一覧表示
		System.out.println("生まれ一覧");
		int selectBirth = 0;//生まれ選択肢
		//種族ごとの生まれ一覧
		for(int i=1;i<births.size();i++) {
			System.out.printf("%s:%d%n",births.get(i)[birth],i);
		}
		//生まれを選択
		System.out.print("生まれを選んでください>>");
		selectBirth = scan.nextInt();
		//生まれと基礎能力値を設定
		this.player.setBirth(births.get(selectBirth)[birth]);
		if(births.get(selectBirth)[tec].matches("2d")) {
			System.out.printf("基礎能力値 技,体,心 を2d6で決めます%n");
			System.out.println("技");
			getPlayer().setTec(Dice.roll(2));
			System.out.println("体");
			getPlayer().setBody(Dice.roll(2));
			System.out.println("心");
			getPlayer().setMind(Dice.roll(2));
			System.out.println("最終的なステータス決定に使用されます");
		}else {
			getPlayer().setTec(Integer.parseInt(births.get(selectBirth)[tec]));
			getPlayer().setBody(Integer.parseInt(births.get(selectBirth)[body]));
			getPlayer().setMind(Integer.parseInt(births.get(selectBirth)[mind]));
		}
		//初期所有技能決定
		if(births.get(selectBirth)[initJobSelect].matches("and")) {
			getPlayer().jobLevelUp(births.get(selectBirth)[initJob]);//１つ目取得
			getPlayer().jobLevelUp(births.get(selectBirth)[initJob+1]);//2つ目取得
		}else if(births.get(selectBirth)[initJobSelect].matches("or")) {
				System.out.println("取得可能技能");
				System.out.println(births.get(selectBirth)[initJob]+":0");
				System.out.println(births.get(selectBirth)[initJob+1]+":1");
				System.out.print("取得する技能を選んで下さい>>");
				int select = scan.nextInt();
				switch(select) {
				case 0:
					getPlayer().jobLevelUp(births.get(selectBirth)[initJob]);//１つ目取得
					break;
				case 1:
					getPlayer().jobLevelUp(births.get(selectBirth)[initJob+1]);//2つ目取得
					break;
				}
		}else {
			if(!births.get(selectBirth)[initJob].matches("なし")) {
				getPlayer().jobLevelUp(births.get(selectBirth)[initJob]);//１つ目取得
			}
		}
		//初期経験点
		getPlayer().setExp(Integer.parseInt(births.get(selectBirth)[initExp]));
		System.out.println();
	}
	//能力値の決定
	public void decideStatus() throws InterruptedException {
		System.out.println(this.player);//デバッグ用
		//基礎能力値設定
		//A~F能力値ダイスロール
		this.player.setStatusA(Dice.roll(2));
		this.player.setStatusB(Dice.roll(2));
		this.player.setStatusC(Dice.roll(2));
		this.player.setStatusD(Dice.roll(2));
		this.player.setStatusE(Dice.roll(2));
		this.player.setStatusF(Dice.roll(2));
		//A~Fのダイスロール結果表示
		System.out.println("A~Fまでのダイス値");
		System.out.printf("%d,%d,%d,%d,%d,%d%n%n",this.player.getStatusA(),this.player.getStatusB(),this.player.getStatusC(),this.player.getStatusD(),this.player.getStatusE(),this.player.getStatusF());
	}
	//技能の習得
	public void decideJobLevel() {
		System.out.printf("経験点を消費して技能を取得できます%n%n");
		this.player.addJob();
	}
	//言語の習得
	
	//冒険者レベルの決定
	public void decideLevel() {
		int maxJobLevel = this.player.getLevel();
		for(String key:this.player.getJobs().keySet()) {
			int jobLevel = this.player.getJobs().get(key);
			if(maxJobLevel < jobLevel) {
				this.player.setLevel(jobLevel);
			}
		}
	}
	//戦闘特技の習得
	public void decideSkill() {
		System.out.println("aaaa");
		player.getSkills().add(new PassiveSkill());
		player.getSkills().add(new PassiveSkill());
		//戦闘特技一覧
		System.out.println("戦闘特技一覧");
		for(Skill a : player.getSkills()) {
			System.out.println(a.getName());
		}
	}

	//キャラ完成
	public void completeCharacter() {
		//ステータス一覧
		System.out.println("キャラが完成しました！");
		System.out.println("------------------------");
		//キャラのステータス表示
		System.out.println(this.player);//デバッグ用
		System.out.println();
		System.out.printf("種族:%s 生まれ:%s%n",this.player.getRace(),this.player.getBirth()); 
		System.out.printf("所持金 %dG%n", this.player.getMoney());
		System.out.println("------------------------");
		//技能一覧
		System.out.println("取得技能一覧");
		System.out.printf("%s|%s%n",Option.format("技能",18),"レベル");
		for(String key:this.player.getJobs().keySet()) {
			int value = this.player.getJobs().get(key);
			if(value != 0) {
				System.out.printf("%s|%d%n",Option.format(key,18),value);
			}
		}
		//装備品
		System.out.println("------------------------");
		System.out.println("現在の装備");
		System.out.println("武器:"+this.player.getWeapon().getName());
		System.out.println("鎧"+this.player.getArmor().getName());
		System.out.println("盾:"+this.player.getShield().getName());
		System.out.println("------------------------");
	}
	//playerのアクセサ
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
