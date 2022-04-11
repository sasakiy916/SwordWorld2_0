
public enum WeponList {
	KNIFE("ナイフ",30,1,new int[]{0,0,0,1,2,3,3,3,4,4}),
	SHORTSWORD("ショートソード",80,5,new int[]{0,1,2,2,3,4,4,5,6,6}),;

	private final String name;
	private final int price;
	private final int needStr;
	private final int[] powers;
	WeponList(String name, int price, int needStr, int[] powers) {
		this.name = name;
		this.price = price;
		this.needStr = needStr;
		this.powers = powers;
	}
	public String getName() {
		return name;
	}
	public int getPrice() {
		return price;
	}
	public int[] getPowers() {
		return powers;
	}
	public int getNeedStr() {
		return needStr;
	}

}
