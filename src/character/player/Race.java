package character.player;


public enum Race {
	HUMAN("人間"),
	ELF("エルフ"),
	DWARF("ドワーフ"),
	TABIT("タビット"),
	RUNEFALK("ルーンフォーク"),
	NIGHTMARE("ナイトメア");
	
	private final String name;
	
	Race(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
