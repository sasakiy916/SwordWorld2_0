public abstract class Character{
	//ステータス
	private String name;//名前
	private int level;//レベル
	private int hp;//HP
	private int mp;//MP
	private int def;//防護点
	private int avo;//回避力
	Dice d;

	public Character(){
		d = new Dice();
	}
	//ダメージ算出
	public abstract int damageRoll();
	
	//名前のアクセサ
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	//レベルのアクセサ
	public int getLevel(){
		return this.level;
	}
	public void setLevel(int level){
		this.level = level;
	}

	//HPのアクセサ
	public int getHp(){
		return this.hp;
	}
	public void setHp(int hp){
		this.hp = hp;
	}

	//MPのアクセサ
	public int getMp(){
		return this.mp;
	}
	public void setMp(int mp){
		this.mp = mp;
	}

	//防護点のアクセサ
	public int getDef(){
		return this.def;
	}
	public void setDef(int def){
		this.def = def;
	}
}
