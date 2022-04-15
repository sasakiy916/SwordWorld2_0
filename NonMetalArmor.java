
public class NonMetalArmor extends Protector {
	//非金属鎧一覧列挙
	public enum NonMetalArmorList{
		CLOTHARMOR,
		POINTGUARD,
		SOFTLEATHER,
		HARDLEATHER,
	}
	//コンストラクタ
	public NonMetalArmor(NonMetalArmorList nonMetalArmor) {
		setNames(new String[] {
				"クロースアーマー",
				"ポイントガード",
				"ソフトレザー",
				"ハードレザー",
		});
		switch(nonMetalArmor) {
		case CLOTHARMOR:
			setName(getNames()[nonMetalArmor.ordinal()]);
			setNeedStr(1);
			setAvoi(0);
			setDef(2);
			setPrice(15);
			break;
		case HARDLEATHER:
			setName(getNames()[nonMetalArmor.ordinal()]);
			setNeedStr(13);
			setAvoi(0);
			setDef(4);
			setPrice(340);
			break;
		case POINTGUARD:
			setName(getNames()[nonMetalArmor.ordinal()]);
			setNeedStr(1);
			setAvoi(1);
			setDef(0);
			setPrice(100);
			break;
		case SOFTLEATHER:
			setName(getNames()[nonMetalArmor.ordinal()]);
			setNeedStr(7);
			setAvoi(0);
			setDef(3);
			setPrice(150);
			break;
		default:
			break;
		
		}
	}
}
