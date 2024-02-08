package state;

import option.Option;

public class Menu {
	private static Menu instance = new Menu();
	private MenuState menu = TitleMenuState.getInstance();
	
	private Menu() {
		super();
	}
	
	public static Menu getInstance() {
		return instance;
	}
	
	public void changeMenu() {
		Option.showSelectMenu(this.menu.getMenuNames());
		this.menu.execute();
	}
}
