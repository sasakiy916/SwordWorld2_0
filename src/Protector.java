
import java.util.List;

public abstract class Protector extends Equipment{
	private String name;//名称
	private int def;//防護点
	private int needStr;//必要筋力
	private int price;//価格
	private int avoi;//回避
	private String kinds;//種類
	private String rank;//ランク
	//コンストラクタ
	public Protector() {
		//処理なし
	}
	public Protector(String name,String path) {
		List<String[]> protectors = Option.load(path);
		int def = 0;//防護点
		int needStr = 0;//必要筋力
		int price = 0;//価格
		int avoi = 0;//回避
		for(int i=0;i<protectors.get(0).length;i++){
			switch(protectors.get(0)[i]) {
			case "防護点":
				def = i;
				break;
			case "必筋":	
				needStr = i;
				break;
			case "価格":	
				price = i;
				break;
			case "回避":	
				avoi = i;
				break;
			}
		}
		//装備の種類名がある行
		int weaponKind = 0;
		for(int i=1;i<protectors.size();i++) {
			if(protectors.get(i)[0].matches(name)) {
				weaponKind = i;
			}
		}
		setName(name);
		setDef(Integer.parseInt(protectors.get(weaponKind)[def]));
		setNeedStr(Integer.parseInt(protectors.get(weaponKind)[needStr]));
		setPrice(Integer.parseInt(protectors.get(weaponKind)[price]));
		setAvoi(Integer.parseInt(protectors.get(weaponKind)[avoi]));
	}
	//名前のアクセサ
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
