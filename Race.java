
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
	
	public enum HumanBirth{
		MAGITEC("魔動機師",8,4,9),
		MAGICIAN("魔法使い",7,4,9),;

		private final String birth;
		private final int tec;
		private final int body;
		private final int mind;
		HumanBirth(String birth, int tec, int body, int mind) {
			this.birth = birth;
			this.tec = tec;
			this.body = body;
			this.mind = mind;
		}
		public String getBirth() {
			return birth;
		}
		public int getTec() {
			return tec;
		}
		public int getBody() {
			return body;
		}
		public int getMind() {
			return mind;
		}
		
	}
	public enum ElfBirth{
		MAGITEC("剣士",12,5,9),
		MAGICIAN("射手",13,5,8),;

		private final String birth;
		private final int tec;
		private final int body;
		private final int mind;
		ElfBirth(String birth, int tec, int body, int mind) {
			this.birth = birth;
			this.tec = tec;
			this.body = body;
			this.mind = mind;
		}
		public String getBirth() {
			return birth;
		}
		public int getTec() {
			return tec;
		}
		public int getBody() {
			return body;
		}
		public int getMind() {
			return mind;
		}
		
	}
}
