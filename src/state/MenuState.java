package state;

public abstract class MenuState {
	protected MenuState[] menuList;
	protected String name;
	public abstract void setState(Menu state);
	public abstract void execute();
	
	public MenuState[] getMenuList() {
		return this.menuList;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String[] getMenuNames() {
		String[] menuNames = new String[menuList.length];
		int index = 0;
		for(MenuState menu:getMenuList()) {
			menuNames[index++] = menu.getName();
		}
		return menuNames;
	}
}
