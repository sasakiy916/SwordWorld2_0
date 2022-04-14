public class Wepon{
	private String name;//武器名
	private String[] names;//武器一覧
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
	public String[] getNames() {
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
}
