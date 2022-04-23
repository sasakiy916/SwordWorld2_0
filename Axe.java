public class Axe extends Weapon {
	//斧一覧列挙
	public enum AxeList{
		HANDAXE,
		BATTLEAXE,
	}//コンストラクタ
	public Axe(AxeList axe){
		setNames(new String[] {
				"ハンドアックス",
				"バトルアックス",
		});
//		setWepon(WeponList.AXE);
		switch(axe) {
		case BATTLEAXE:
			setName(getNames()[axe.ordinal()]);
			setPrice(360);
			setNeedStr(16);
			setPower(new int[] {1,2,3,4,6,6,7,8,9,10});
			break;
		case HANDAXE:
			setName(getNames()[axe.ordinal()]);
			setPrice(90);
			setNeedStr(7);
			setPower(new int[] {1,2,2,3,4,4,5,6,6,7});
			break;
		default:
			break;
		}
	}
}