package state;

public class TitleMenuState extends MenuState{
	private final static TitleMenuState instance = new TitleMenuState();

	private TitleMenuState() {
		super(TitleMenuEnum.TITLE);
	}

	public static TitleMenuState getInstance() {
		System.out.println("GET:"+instance);
		return instance;
	}

	@Override
	public void execute() {
		System.out.println("タイトル\n");
		SelectWindow.getInstance().changeMenu();
	}
	
	@Override
	public void setState() {
		MenuState[] menuList = {
				this,
				NewCharacterState.getInstance(),
		};
		this.menuStates = menuList;
	}
}
