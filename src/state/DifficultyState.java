package state;

public class DifficultyState extends MenuState {

	protected DifficultyState() {
		;
	}

	@Override
	public void setState() {
		this.menuStates = new MenuState[] {
				MenuState.getInstance(MenuEnum.TITLE)
		};
	}

	@Override
	public void execute() {
		System.out.println("難易度選択");
		System.out.println("未実装");
		SelectWindow.getInstance().changeMenu();
	}

}
