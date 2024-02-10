package state;

public class QuitState extends MenuState {
	private static final QuitState instance = new QuitState();

	private QuitState() {
		super(MenuEnum.QUIT);
	}

	public static QuitState getInstance() {
		return instance;
	}

	@Override
	public void setState() {
		this.menuStates = new MenuState[] {
				this,
		};
	}

	@Override
	public void execute() {
		System.out.printf("%n冒険お疲れさまでした<(_ _)>%n");
	}

}
