package manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import character.player.Player;
import character.player.Race;
import main.Dice;
import option.Option;
import option.Shop;
import skill.PassiveSkill;
import skill.Skill;

public class CharacterCreater {
	private Player player;//プレイヤーのインスタンス
	Scanner scan = new Scanner(System.in);

	//キャラ作成
	public Player create(){
		decideRaceAndBirth();//種族と生まれの決定
		//名前入力
		String name;
		while (true) {
			System.out.print("冒険者の名前を決めて下さい>>");
			name = scan.next();
			System.out.printf("%sでよろしいですか?(はい:0,いいえ:1)>>", name);
			if (scan.nextInt() == 0) {
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
		return this.player;
	}

	//種族と生まれの決定
	public void decideRaceAndBirth(){
		//種族一覧表示
		System.out.println("種族一覧");
		Option.showSelectMenu(Race.getNames());

		//種族を選択
		String birthData = "birth/";
		System.out.print("種族を選んでください>>");
		int selectRace = scan.nextInt() - 1;
		System.out.println();

		//種族決定
		Race race = Race.values()[selectRace];
		this.player = race.getRaceInstace();
		birthData += String.format("birth - %s.csv", race.getName());

		//ファイル読み込み
		Map<String, String[]> birthsMap = Option.loadFromCSVWithTitleCol(birthData);

		//生まれ一覧表示
		System.out.println("生まれ一覧");
		Option.showSelectMenu(birthsMap.get("生まれ"));

		//生まれを選択
		System.out.print("生まれを選んでください>>");
		int selectBirth = scan.nextInt() - 1;

		//生まれと基礎能力値を設定
		this.player.setBirth(birthsMap.get("生まれ")[selectBirth]);
		char[] baseStatuses = { '技', '体', '心' };
		for (char baseStatus : baseStatuses) {
			System.out.println(baseStatus);
			String data = birthsMap.get(String.valueOf(baseStatus))[selectBirth];
			int status = data.equals("2d") ? Dice.roll(2) : Integer.parseInt(data);
			switch (baseStatus) {
			case '技':
				this.player.setTec(status);
				break;
			case '体':
				this.player.setBody(status);
				break;
			case '心':
				this.player.setMind(status);
				break;
			}
		}
		System.out.println("最終的なステータス決定に使用されます");

		//初期所有技能決定
		String initJob = "初期所有技能";
		List<String> initJobList = new ArrayList<>();
		String[] initJobs = {
				birthsMap.get(initJob)[selectBirth],
				birthsMap.get(initJob + "2")[selectBirth],
		};
		for(String job:initJobs) {
			if (!job.matches("(なし)*")) {
				initJobList.add(job);
			}
		}

		if (initJobList.size() > 0) {
			String multiCase = birthsMap.get("複数ある場合")[selectBirth];
			if (multiCase.matches("or")) {
				System.out.println("取得可能技能");
				Option.showSelectMenu(initJobList);
				System.out.print("取得する技能を選んで下さい>>");
				int select = scan.nextInt() - 1;
				this.player.jobLevelUp(initJobList.get(select));
			} else if (multiCase.matches("and")) {
				for (String job : initJobList) {
					this.player.jobLevelUp(job);
				}
			} else {
				this.player.jobLevelUp(initJobs[0]);
			}
		}
		//初期経験点
		String exp = birthsMap.get("初期経験点")[selectBirth];
		this.player.setExp(Integer.parseInt(exp));
		System.out.println();
	}

	//能力値の決定
	public void decideStatus(){
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
		System.out.printf("%d,%d,%d,%d,%d,%d%n%n", this.player.getStatusA(), this.player.getStatusB(),
				this.player.getStatusC(), this.player.getStatusD(), this.player.getStatusE(), this.player.getStatusF());
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
		for (String key : this.player.getJobs().keySet()) {
			int jobLevel = this.player.getJobs().get(key);
			if (maxJobLevel < jobLevel) {
				this.player.setLevel(jobLevel);
			}
		}
	}

	//戦闘特技の習得(作成中)
	public void decideSkill() {
		System.out.println("aaaa");
		player.getSkills().add(new PassiveSkill());
		player.getSkills().add(new PassiveSkill());
		//戦闘特技一覧
		System.out.println("戦闘特技一覧");
		for (Skill a : player.getSkills()) {
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
		System.out.printf("種族:%s 生まれ:%s%n", this.player.getRace(), this.player.getBirth());
		System.out.printf("所持金 %dG%n", this.player.getMoney());
		System.out.println("------------------------");
		//技能一覧
		System.out.println("取得技能一覧");
		System.out.printf("%s|%s%n", Option.format("技能", 18), "レベル");
		for (String key : this.player.getJobs().keySet()) {
			int value = this.player.getJobs().get(key);
			if (value != 0) {
				System.out.printf("%s|%d%n", Option.format(key, 18), value);
			}
		}
		//装備品
		System.out.println("------------------------");
		System.out.println("現在の装備");
		System.out.println("武器:" + this.player.getWeapon().getName());
		System.out.println("鎧" + this.player.getArmor().getName());
		System.out.println("盾:" + this.player.getShield().getName());
		System.out.println("------------------------");
	}
}
