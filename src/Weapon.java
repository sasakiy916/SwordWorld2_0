
import java.util.List;

public class Weapon extends Equipment{
	//武器種類の一覧列挙型
	public enum WeponList {
		SWORD,
		AXE,
		SPEAR,
		STUFF,
		FRAIL,
		MACE,
		WARHAMMER
		
	}
	//private String name;//名称
	private String usage;//用法
	private String kinds;//武器の種類
	//private int needStr;//必筋
	private int hit;//命中
	private int power;//威力
	private int[] powers = new int[10];//威力表
	private int critical;//クリティカル値
	//private int price;//価格
	private static String[] names = new String[] {
			"ソード",
			"アックス",
			"スピア",
			"スタッフ",
			"フレイル",
			"メイス",
			"ウォーハンマー",
	};
	//コンストラクタ
	public Weapon() {
		
	}
	public Weapon(String weapon,String path) {
		List<String[]> weapons = Option.load(path);
		int name = 0;
		int usage = 0;
		int kinds = 0;
		int needStr = 0;
		int hit = 0;
		int critical = 0;
		int power = 0;
		int powers = 0;
		int price = 0;
		for(int i=0;i<weapons.get(0).length;i++){
			switch(weapons.get(0)[i]) {
			case "名称":	
				name = i;
				break;
			case "用法":	
				usage = i;
				break;
			case "必筋":	
				needStr = i;
				break;
			case "命中":	
				hit = i;
				break;
			case "威力":	
				power = i;
				break;
			case "C値":	
				critical = i;
				break;
			case "価格":	
				price = i;
				break;
			case "3":
				powers = i;
				break;
			}
		}
		//装備の種類名がある行
		int weaponKind = 0;
		for(int i=1;i<weapons.size();i++) {
			if(weapons.get(i)[name].matches(weapon)) {
				weaponKind = i;
			}
		}
		setName(weapons.get(weaponKind)[name]);//名称
		setUsage(weapons.get(weaponKind)[usage]);//用法
		setKinds(weapons.get(weaponKind)[kinds]);//種類
		setNeedStr(Integer.parseInt(weapons.get(weaponKind)[needStr]));//必筋
		setHit(Integer.parseInt(weapons.get(weaponKind)[hit]));//命中
		setCritical(Integer.parseInt(weapons.get(weaponKind)[critical]));//クリティカル
		setPower(Integer.parseInt(weapons.get(weaponKind)[power]));//威力
		//威力表の数値取得
		int[] powerTable = new int[getPowers().length];
		for(int i=0;i<getPowers().length;i++) {
			powerTable[i] = Integer.parseInt(weapons.get(weaponKind)[powers + i]);
		}
		setPowers(powerTable);//威力表
		setPrice(Integer.parseInt(weapons.get(weaponKind)[price]));
	}
	//武器名のアクセサ
	//武器一覧のアクセサ
	public static String[] getNames() {
		return names;
	}
	public void setNames(String[] names) {
		this.names = names;
	}
	//価格のアクセサ
	//必要な筋力のアクセサ
	//威力表のアクセサ
	public int getPower(int dice){
		return this.powers[dice-1];
	}
	public void setPower(int[] power){
		for(int i=0;i<10;i++){
			this.powers[i] = power[i];
		}
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	public String getKinds() {
		return kinds;
	}
	public void setKinds(String kinds) {
		this.kinds = kinds;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public int[] getPowers() {
		return powers;
	}
	public void setPowers(int[] powers) {
		this.powers = powers;
	}
	public int getCritical() {
		return critical;
	}
	public void setCritical(int critical) {
		this.critical = critical;
	}
	//武器情報の表示
	public String toString() {
		int[] diceNums = {3,4,5,6,7,8,9,10,11,12};
		super.toString();
		System.out.println("・威力表");
		System.out.print("　　出目:");
		for(int i=0;i<diceNums.length;i++) {
			System.out.printf("%2d",diceNums[i]);
			System.out.print(i!=powers.length-1?"|":"");
		}
		System.out.println();
		System.out.println("　　　　 -----------------------------");
		System.out.print("　　威力:");
		for(int i=0;i<powers.length;i++) {
			System.out.printf("%2d",this.powers[i]);
			System.out.print(i!=powers.length-1?"|":"");
		}
		System.out.println();
		System.out.println("---------------------------------------");
		return "";
	}
}
