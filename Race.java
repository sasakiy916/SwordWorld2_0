
public enum Race {
	HUMAN("人間"),
	ELF("エルフ");
	
	private final String name;
	
	Race(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
