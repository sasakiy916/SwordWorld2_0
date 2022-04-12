
public enum Race {
	HUMAN(new Human(),"人間"),
	ELF(null,"エルフ");
	
	private final Player player;
	private final String name;
	
	Race(Player player,String name){
		this.player = player;
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public Player getPlayer() {
		return player;
	}
}
