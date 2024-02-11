package state;

public enum MenuEnum implements StateEnum{
	TITLE("タイトル",TitleState.class),
	NEWCHARACTER("新規キャラ作成",NewCharacterState.class), 
	SELECTCHARACTER("冒険者ギルド",GuildState.class),
	BATTLE("討伐クエスト",BattleState.class),
	DIFFICULTY("難易度選択",DifficultyState.class),
	QUIT("ゲーム終了",QuitState.class),
	;

	private final String text;
	private final Class<?> state;

	MenuEnum(final String name,Class<?> state) {
		this.text = name;
		this.state = state;
	}
	
	@Override
	public String getText() {
		return this.text;
	}
	
	Class<?> getState(){
		return this.state;
	}
}
