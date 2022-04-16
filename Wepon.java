public class Wepon extends Equipment{
	//武器種類の一覧列挙型
	public enum WeponList {
		SWORD,
		AXE,
	}
	private String name;//武器名
	private static String[] names = new String[] {
			"ソード",
			"アックス",
	};
	//武器一覧
	private int price;//価格
	private int needStr;//必要な筋力
	private int[] powers = new int[10];//威力表
	WeponList wepon;

	//武器名のアクセサ
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	//武器一覧のアクセサ
	public static String[] getNames() {
		return names;
	}
	public void setNames(String[] names) {
		this.names = names;
	}
	//価格のアクセサ
	public int getPrice(){
		return this.price;
	}
	public void setPrice(int price){
		this.price = price;
	}
	//必要な筋力のアクセサ
	public int getNeedStr(){
		return this.needStr;
	}
	public void setNeedStr(int needStr){
		this.needStr = needStr;
	}
	//威力表のアクセサ
	public int getPower(int dice){
		return this.powers[dice-1];
	}
	public void setPower(int[] power){
		for(int i=0;i<10;i++){
			this.powers[i] = power[i];
		}
	}
	public WeponList getWepon() {
		return wepon;
	}
	public void setWepon(WeponList wepon) {
		this.wepon = wepon;
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
