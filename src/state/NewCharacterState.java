package state;

import manager.CharacterCreater;
import option.Option;
import option.PlayerData;

public class NewCharacterState extends MenuState {
	
	protected NewCharacterState() {
		;
	}

	@Override
	public void execute() {
		System.out.println("キャラ作成\n");
		if (Option.loadFromCSV("player/player.json").size() < 5) {
			System.out.printf("新規キャラを作成します。%n%n");
			CharacterCreater cc = new CharacterCreater();
			try {
				PlayerData.save(cc.create());
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		} else {
			System.out.printf("ギルドに待機してる冒険者がいっぱいです。%n%n");
			Option.sleep(1000);
		}
		SelectWindow.getInstance().changeMenu();
	}

	@Override
	public void setState() {
		this.menuStates = new MenuState[] {
				this,
				MenuState.getInstance(MenuEnum.TITLE),
		};
	}
}
