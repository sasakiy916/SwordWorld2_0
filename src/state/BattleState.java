package state;

import java.util.List;

import character.monster.Monster;
import character.player.Character;
import manager.BattleManager;
import manager.PartyManager;
import option.Option;

public class BattleState extends MenuState {
	protected BattleState() {
		
	}

	@Override
	public void setState() {
		this.menuStates = new MenuState[] {
				this,
				MenuState.getInstance(MenuEnum.TITLE)
		};
	}

	@Override
	public void execute() {
		System.out.println("討伐クエスト");
		System.out.println("未完成");
		PartyManager manager = PartyManager.getInstance();
		List<Character> playerParty = manager.getPlayerParty();
		List<Character> monsterParty = manager.getMonsterParty();
		Option.sleep(1000);
		//モンスター用意
		String[] difficulty = { "駆け出し", "ベテラン", "伝説" };
		while (true) {
			int selectDifficulty = Option.selectMenu(difficulty, "難易度");
			switch (selectDifficulty) {
			case 0:
				monsterParty.add(new Monster("コボルド", "A"));
				monsterParty.add(new Monster("ゴブリン"));
				monsterParty.add(new Monster("コボルド", "B"));
				break;
			case 1:
				monsterParty.add(new Monster("ゴブリン", "A"));
				monsterParty.add(new Monster("ゴブリン", "B"));
				monsterParty.add(new Monster("レッドキャップ"));
				break;
			case 2:
				monsterParty.add(new Monster("ドラゴン", "A"));
				monsterParty.add(new Monster("ドラゴン", "B"));
				monsterParty.add(new Monster("レッサーオーガ"));
				break;
			default:
				System.out.println("選択肢から選択してください");
				continue;
			}
			break;
		}
		//戦闘
		try {
			BattleManager.battle(playerParty, monsterParty);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		SelectWindow.getInstance().changeMenu();
	}

}
