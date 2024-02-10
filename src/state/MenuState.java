package state;

import java.util.HashMap;

public abstract class MenuState {
	private static MenuState instance;
	private static final HashMap<MenuEnum,MenuState> instanceMap = new HashMap<>();
	protected MenuState[] menuStates;
	protected StateEnum titleMenu;

	protected MenuState(MenuEnum e) {
		this.titleMenu = e;
		MenuState.instance = this;
		instanceMap.put(e, this);
	}
	
	// デバッグ用
	public static HashMap<MenuEnum,MenuState> getMap(){
		return instanceMap;
	}

	public static MenuState getInstance(MenuEnum e) {
		return instanceMap.get(e);
	}

	public abstract void setState();

	public abstract void execute();

	public MenuState[] getMenuStates() {
		if (this.menuStates == null) {
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
		for (MenuState menu : getMenuStates()) {
			menuNames[index++] = menu.getName();
		}
		return menuNames;
	}
}
