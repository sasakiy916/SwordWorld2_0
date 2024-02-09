package test;

import option.Option;
import state.SelectWindow;

public class MenuTest {

	public static void main(String[] args) {
		Option.init();
		SelectWindow menu = SelectWindow.getInstance();
		menu.changeMenu();
	}

}
