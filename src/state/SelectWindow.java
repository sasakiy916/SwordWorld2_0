package state;

import java.util.Scanner;

import option.Option;

public class SelectWindow {
	private final static SelectWindow instance = new SelectWindow();
	private MenuState currentMenu = MenuState.getInstance(MenuEnum.TITLE);
	
	// コンストラクタ
	private SelectWindow() {
		super();
		currentMenu.setState();
	}
	
	// インスタンス取得
	public static SelectWindow getInstance() {
		return instance;
	}

	// 現在のメニュー設定
	private void setCurrentMenu(MenuState currentMenu) {
		this.currentMenu = currentMenu;
	}
	
	// ゲーム開始
	public void start() {
		this.currentMenu.execute();
	}

	// メニュー選択
	public void changeMenu() {
		Option.showSelectMenu(this.currentMenu.getMenuNames());
		Scanner scan = new Scanner(System.in);
		System.out.print("メニューを選んでください>>");
		int select = scan.nextInt() - 1;
		if(Option.isWithinRange(select, this.currentMenu.getMenuStates().length-1)) {
			setCurrentMenu(this.currentMenu.getMenuStates()[select]);
		}else if(select == -1){
			;
		}else {
			System.out.println("存在しないメニューです\n");
		}
		this.currentMenu.execute();
	}
	public void changeMenu(MenuEnum state) {
		setCurrentMenu(MenuState.getInstance(state));
		this.currentMenu.execute();
	}
}
