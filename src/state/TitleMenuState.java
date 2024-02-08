package state;

public class TitleMenuState extends MenuState{
	private static TitleMenuState instance = new TitleMenuState();

	private TitleMenuState() {
		super();
		MenuState[] menuList = {
				this,
		};
		this.name = "タイトル";
		this.menuList = menuList;
	}

	@Override
	public void setState(Menu state) {
//		state.changeMenu(this);
	}
	
	public static TitleMenuState getInstance() {
		return instance;
	}

	@Override
	public void execute() {
		System.out.println("タイトル");
	}
	
}
