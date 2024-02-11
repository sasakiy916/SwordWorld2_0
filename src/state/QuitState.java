package state;

public class QuitState extends MenuState {
	protected QuitState() {
		
	}

	@Override
	public void setState() {
		this.menuStates = new MenuState[] {
				this,
		};
	}

	@Override
	public void execute() {
		System.out.printf("%n冒険お疲れさまでした<(_ _)>%n");
	}

}
