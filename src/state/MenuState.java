package state;

public abstract class MenuState {
	protected MenuState[] menuStates;
	protected TitleMenuEnum titleMenu;
	
	protected MenuState(TitleMenuEnum e) {
		this.titleMenu = e;
	}

	public abstract void setState();
	public abstract void execute();
	
	public MenuState[] getMenuStates() {
		if(this.menuStates == null) {
			setState();
		}
		return this.menuStates;
	}
	
	public String getName() {
		return this.titleMenu.getText();
	}
	
	public String[] getMenuNames() {
		MenuState[] menuStates = getMenuStates();
		String[] menuNames = new String[menuStates.length];
		int index = 0;
		for(MenuState menu:getMenuStates()) {
			menuNames[index++] = menu.getName();
		}
		return menuNames;
	}
}
