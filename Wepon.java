public class Wepon{
	private String name;//武器名
	private int price;//価格
	private int needStr;//必要な筋力
	private int[] powers = new int[10];//威力表
	WeponList wepon;

	public Wepon(WeponList wepon) {
		this.name = wepon.getName();
		this.price = wepon.getPrice();
		this.needStr = wepon.getNeedStr();
		this.powers = wepon.getPowers();
	}
	
//	//武器選択
//	public int[] selectWepon(WeponList wepon) {
//		int[] powers = new int[10];
//		switch(wepon) {
//		case SHORTSWORD:
//			this.setName("ショートソード");
//			this.setPrice(80);
//			powers = new int[]{0,1,2,2,3,4,4,5,6,6};
//			break;
//		case KNIFE:
//			setName("ナイフ");
//			setPrice(30);
//			powers = new int[]{0,0,0,1,2,3,3,3,4,4};
//			break;
//		}
//		return powers;
//	}
	//武器名のアクセサ
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
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
