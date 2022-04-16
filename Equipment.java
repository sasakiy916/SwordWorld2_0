public abstract class Equipment{
	public enum EquipmentList {
		WEPON,
		PROTECTOR,
	}
	private String name;//名称
	private static String[] names = {
			"武器",
			"防具"
	};
	private int needStr;//必要筋力
	private int price;//価格
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNeedStr() {
		return needStr;
	}
	public void setNeedStr(int needStr) {
		this.needStr = needStr;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public static String[] getNames() {
		return names;
	}
	//武器情報の表示
	public String toString() {
		System.out.println("---------------------------------------");
		System.out.println(this instanceof Wepon?"・武器名:":"・防具名:"+getName());
		System.out.println("・価格:"+getPrice()+"G");
		System.out.println("・必要筋力:"+getNeedStr());
		return "";
	}
}
