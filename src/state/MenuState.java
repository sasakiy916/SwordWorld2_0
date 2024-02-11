package state;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public abstract class MenuState {
	private static final HashMap<MenuEnum,MenuState> instanceMap = new HashMap<>();
	protected MenuState[] menuStates;
	protected StateEnum titleMenu;

	protected MenuState() {
		
	}

	// インスタンス取得
	protected static MenuState getInstance(MenuEnum e) {
		if(!instanceMap.containsKey(e)) {
			MenuState state = null;
			try {
				state = (MenuState)e.getState().getDeclaredConstructor().newInstance();
				instanceMap.put(e, state);
			} catch (InstantiationException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			} catch (NoSuchMethodException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			} catch (SecurityException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
			state.titleMenu = e;
		}
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

	// メニュー名取得
	public String getName() {
		return this.titleMenu.getText();
	}

	// メニュー名一覧取得
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
