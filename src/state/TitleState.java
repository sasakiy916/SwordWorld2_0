package state;

public class TitleState extends MenuState {
	private final static TitleState instance = new TitleState();

	private TitleState() {
		super(MenuEnum.TITLE);
	}

	public static TitleState getInstance() {
		return instance;
	}

	@Override
	public void execute() {
		System.out.println("ソードワールド2.0 ～再現の章～");
		SelectWindow.getInstance().changeMenu();
	}

	@Override
	public void setState() {
		this.menuStates = new MenuState[] {
				NewCharacterState.getInstance(),
				GuildState.getInstance(),
				BattleState.getInstance(),
				DifficultyState.getInstance(),
				MenuState.getInstance(MenuEnum.BATTLE),
				QuitState.getInstance(),
				MenuState.getInstance(MenuEnum.NEWCHARACTER),
				MenuState.getInstance(MenuEnum.BATTLE),
				MenuState.getInstance(MenuEnum.BATTLE),
		};
	}
}
