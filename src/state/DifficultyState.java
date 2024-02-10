package state;

public class DifficultyState extends MenuState {
	private static final DifficultyState instance = new DifficultyState();

	private DifficultyState() {
		super(MenuEnum.DIFFICULTY);
	}

	public static DifficultyState getInstance() {
		return instance;
	}

	@Override
	public void setState() {
		this.menuStates = new MenuState[] {
				this,
				TitleState.getInstance(),
				NewCharacterState.getInstance(),
		};
	}

	@Override
	public void execute() {
		System.out.println("難易度選択");
		System.out.println("未実装");
		SelectWindow.getInstance().changeMenu();
	}

}
