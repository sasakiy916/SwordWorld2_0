package character.player;

import java.lang.reflect.InvocationTargetException;

public enum Race {
	HUMAN("人間",Human.class),
	ELF("エルフ",Elf.class),
	DWARF("ドワーフ",Dwarf.class),
	TABIT("タビット",Tabit.class),
	RUNEFALK("ルーンフォーク",RuneFalk.class),
	NIGHTMARE("ナイトメア",Nightmare.class);
	
	private final String name;
	private final Class<?> cls;
	
	Race(String name,Class<?> cls){
		this.name = name;
		this.cls = cls;
	}

	public String getName() {
		return name;
	}
	
	public Player getRaceInstace(){
		Player instance = null;
		try {
			instance = (Player) this.cls.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return instance;
	}
	
	public static String[] getNames() {
		Race[] race = Race.values();
		String[] names = new String[race.length];
		for(int i = 0;i<names.length;i++) {
			names[i] = race[i].getName();
		}
		return names;
	}
}
