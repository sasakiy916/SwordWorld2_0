package state;

public class NewCharacterState extends MenuState {
	private final static NewCharacterState instance = new NewCharacterState();

	private NewCharacterState() {
		super(TitleMenuEnum.NEWCHARACTER);
	}

	public static NewCharacterState getInstance() {
		return instance;
	}

	@Override
	public void execute() {
		System.out.println("キャラ作成\n");
		SelectWindow.getInstance().changeMenu();
	}

	@Override
	public void setState() {
		MenuState[] menuList = {
				this,
				TitleMenuState.getInstance(),
		};
		this.menuStates = menuList;
	}
}
