public class Sword extends Weapon {
	//武器一覧用の列挙型
	public enum SwordList {
		KNIFE,
		DAGGER,
		SHORTSWORD,
	}
	//コンストラクタ
	public Sword(SwordList sword){
		setNames(new String[] {
				"ナイフ",
				"ダガー",
				"ショートソード",
		});
		setWepon(WeponList.SWORD);
		switch(sword) {
		case KNIFE:
			setName(getNames()[sword.ordinal()]);
			setPrice(30);
			setNeedStr(1);
			setPower(new int[] {0,0,0,1,2,3,3,3,4,4});
			break;
		case DAGGER:
			setName(getNames()[sword.ordinal()]);
			setPrice(50);
			setNeedStr(3);
			setPower(new int[] {0,0,1,1,2,3,4,4,4,5});
			break;
		case SHORTSWORD:
			setName(getNames()[sword.ordinal()]);
			setPrice(80);
			setNeedStr(5);
			setPower(new int[]{0,1,2,2,3,4,4,5,6,6});
			break;
		default:
			System.out.println("未実装の武器です");
			break;
		}
	}
}
