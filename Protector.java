public abstract class Protector extends Equipment{
	//防具一覧列挙
	public enum ProrectorList{
		NONMETALARMOR,
		METALAROMR,
	}
	private String name;//名称
	private int def;//防護点
	private int needStr;//必要筋力
	private int price;//価格
	private int avoi;//回避
	private String kinds;//種類
	private String rank;//ランク
	private static String[] names = {
			"非金属鎧",
			"金属鎧",
	};//防護名称一覧
	//名前のアクセサ
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//防具名称一覧のアクセサ
	public static String[] getNames() {
		return names;
	}
	public void setNames(String[] names) {
		this.names = names;
	}
	//防護点のアクセサ
	public int getDef() {
		return def;
	}
	public void setDef(int def) {
		this.def = def;
	}
	//必要筋力のアクセサ
	public int getNeedStr() {
		return needStr;
	}
	public void setNeedStr(int needStr) {
		this.needStr = needStr;
	}
	//価格のアクセサ
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	//回避のアクセサ
	public int getAvoi() {
		return avoi;
	}
	public void setAvoi(int avoi) {
		this.avoi = avoi;
	}
	public String getKinds() {
		return kinds;
	}
	public void setKinds(String kinds) {
		this.kinds = kinds;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	//防具の情報表示
	public String toString() {
		super.toString();
		System.out.printf("・防護点:%d%n",getDef());
		if(getAvoi() != 0) {
			System.out.printf("・回避:%s%d%n",getAvoi()>0?"+":"", getAvoi());
		}
		System.out.println("---------------------------------------");
		return "";
	}
}
