import java.util.List;

public class Monster extends Character{
	private int maxHp;
	private int maxMp;
	private int avoi;//回避力
	private int pre;//先制力
	private int hit;//命中力
	private int addDamage;//追加ダメージ
	private int fixedHit;//固定命中
	private int fixedAvoi;//固定回避
	private int money;//落とすお金
	private int exp;//落とす経験点
	
	//コンストラクタ
	public Monster() {
		
	}
	public Monster(String name,String suffix) {
		this(name);
		setName(getName() + suffix);
	}
	public Monster(String name) {
		setName(name);
		String path = "monster/モンスター一覧.csv";
		List<String[]> monsters = Option.load(path);
		for(int i=1;i<monsters.size();i++) {
			if(name.equals(monsters.get(i)[0])) {
				for(int j=0;j<monsters.get(i).length;j++) {
					if(monsters.get(0)[j].equals("命中力")) setHit(Integer.parseInt(monsters.get(i)[j]));
					if(monsters.get(0)[j].equals("固定命中力")) setFixedHit(Integer.parseInt(monsters.get(i)[j]));
					if(monsters.get(0)[j].equals("打撃点")) setAddDamage(Integer.parseInt(monsters.get(i)[j]));
					if(monsters.get(0)[j].equals("回避力")) setAvoi(Integer.parseInt(monsters.get(i)[j]));
					if(monsters.get(0)[j].equals("固定回避力")) setFixedAvoi(Integer.parseInt(monsters.get(i)[j]));
					if(monsters.get(0)[j].equals("防護点")) setDef(Integer.parseInt(monsters.get(i)[j]));
					if(monsters.get(0)[j].equals("HP")) setHp(Integer.parseInt(monsters.get(i)[j]));
					if(monsters.get(0)[j].equals("MP")) setMp(Integer.parseInt(monsters.get(i)[j]));
					if(monsters.get(0)[j].equals("先制値")) setPre(Integer.parseInt(monsters.get(i)[j]));
					if(monsters.get(0)[j].equals("ガメル")) setMoney(Integer.parseInt(monsters.get(i)[j]));
					if(monsters.get(0)[j].equals("経験点")) setExp(Integer.parseInt(monsters.get(i)[j]));
				}
				this.maxHp = getHp();
				this.maxMp = getMp();
				break;
			}
		}
	}
	//先制判定
	@Override
	public int judgePre() {
		return getPre();
	}
	//命中判定(固定値)
	@Override
	public int judgeHit() {
		return getFixedHit();
	}

	//回避判定(固定値)
	@Override
	public int judgeAvoi() {
		return getFixedAvoi();
	}

	public int getFixedHit() {
		return fixedHit;
	}
	public void setFixedHit(int fixedHit) {
		this.fixedHit = fixedHit;
	}
	public int getFixedAvoi() {
		return fixedAvoi;
	}
	public void setFixedAvoi(int fixedAvoi) {
		this.fixedAvoi = fixedAvoi;
	}
	public int getAvoi() {
		return avoi;
	}
	public void setAvoi(int avoi) {
		this.avoi = avoi;
	}
	public int getPre() {
		return pre;
	}
	public void setPre(int pre) {
		this.pre = pre;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getAddDamage() {
		return addDamage;
	}
	public void setAddDamage(int addDamage) {
		this.addDamage = addDamage;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	@Override
	public int damageRoll() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}
	@Override
	public int getMaxHp() {
		return this.maxHp;
	}
	@Override
	public int getMaxMp() {
		return this.maxMp;
	}
}
