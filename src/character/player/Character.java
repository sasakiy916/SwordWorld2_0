package character.player;


 public abstract class Character{
	//ステータス
	private String name = "名無し";//名前
	private int level = 1;//レベル
	private int hp;//HP
	private int mp;//MP
	private int def;//防護点

	//コンストラクタ
	public Character() {
		
	}
	//先制判定
	public abstract int judgePre();
	//命中判定
	public abstract int judgeHit();
	//回避判定
	public abstract int judgeAvoi();
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
	//現在HPのアクセサ
	public int getHp(){
		return this.hp;
	}
	public void setHp(int hp){
		this.hp = hp;
	}
	//現在MPのアクセサ
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
	//回避のアクセサ
	public abstract int getAvoi();
	//先制力のアクセサ
	public abstract int getPre();
	//命中のアクセサ
	public abstract int getHit();
	//追加ダメージ
	public abstract int getAddDamage();
}
