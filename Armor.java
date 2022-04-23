
public class Armor extends Protector {
	//防具一覧列挙
	public enum ArmorList{
		NONMETALARMOR,
		METALAROMR,
	}
	public Armor() {
		
	}
	public Armor(String name,String path) {
		super(name,path);
	}
}
