package state;

public class NewCharacterState extends MenuState{
	private NewCharacterState instance = new NewCharacterState();

	@Override
	public void execute() {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	private NewCharacterState() {
		super();
	}

	public NewCharacterState getInstance() {
		return instance;
	}
	@Override
	public void setState(Menu state) {
		// TODO 自動生成されたメソッド・スタブ
	}
}
