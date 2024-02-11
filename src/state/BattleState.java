package state;

import java.util.List;

import character.monster.Goblin;
import character.monster.Kobold;
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
		monsterParty.add(new Kobold());
		monsterParty.add(new Goblin());
		System.out.println();
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
