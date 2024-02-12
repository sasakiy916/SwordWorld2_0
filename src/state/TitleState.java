package state;

public class TitleState extends MenuState {

	@Override
	public void execute() {
		System.out.println("ソードワールド2.0 ～再現の章～");
		SelectWindow.getInstance().changeMenu();
	}

	@Override
	public void setState() {
		this.menuStates = new MenuState[] {
				MenuState.getInstance(MenuEnum.NEWCHARACTER),
				MenuState.getInstance(MenuEnum.SELECTCHARACTER),
				MenuState.getInstance(MenuEnum.BATTLE),
				MenuState.getInstance(MenuEnum.DIFFICULTY),
				MenuState.getInstance(MenuEnum.QUIT),
		};
	}
}
