package state;

public enum MenuEnum implements StateEnum{
	TITLE("タイトル"),
	NEWCHARACTER("新規キャラ作成"), 
	SELECTCHARACTER("冒険者ギルド"),
	BATTLE("討伐クエスト"),
	DIFFICULTY("難易度選択"),
	QUIT("ゲーム終了"),
	;

	private final String text;

	MenuEnum(final String name) {
		this.text = name;
	}
	
	public String getText() {
		return this.text;
	}
}
