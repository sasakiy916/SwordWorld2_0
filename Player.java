import java.util.*;
public abstract class Player{
	private String name;//名前
	private int level;//レベル
	private int exp;//経験点
	private int age;//年齢
	private String birth;//生まれ
	//能力値
	private int hp;//HP
	private int mp;//MP
	private int resVit;//生命抵抗力
	private int resPow;//精神抵抗力
	private int dex;//器用度
	private int agi;//敏捷度
	private int str;//筋力
	private int vit;//生命力
	private int wis;//知力
	private int pow;//精神力
	public String[] statusName;//public → privateに修正予定 ゲッター作る 	
	public int[] status;//public → privateに修正予定 ゲッター作る
	//能力値ボーナス
	private int dexBonus;
	private int agiBonus;
	private int strBonus;
	private int vitBonus;
	private int wisBonus;
	private int powBonus;
	//基礎能力値
	private int tec;//技
	private int body;//体
	private int mind;//心
	private String[] baseAbilityName;
	private int[] baseAbility;
	//ダイスで決める能力値
	private int statusA;//A
	private int statusB;//B
	private int statusC;//C
	private int statusD;//D
	private int statusE;//E
	private int statusF;//F
	private String[] abilitySuffix;
	private int[] ability;

	public Player(){
		setLevel(1);
		this.statusName = new String[]{
			"HP",
			"MP",
			"生命抵抗力",
			"精神抵抗力",
			"器用度",
			"敏捷度",
			"筋力",
			"生命力",
			"知力",
			"精神力",
		};
		//setStatus(
		//		setHp(this.hp),
		//		setMp(this.mp),
		//		setResVit(this.resVit),
		//		setResPow(this.resPow),
		//		setDex(this.dex),
		//		setAgi(this.agi),
		//		setStr(this.str),
		//		setVit(this.vit),
		//		setWis(this.wis),
		//		setPow(this.pow),
		//);
		this.baseAbilityName = new String[]{"技","体","心"};
		this.baseAbility = new int[baseAbilityName.length];
		this.abilitySuffix = new String[]{
			"A",
			"B",
			"C",
			"D",
			"E",
			"F",
		};
		this.ability = new int[abilitySuffix.length];
	}

	//ダイスを振って出目の和を求めるメソッド
	//引数：振る個数
	protected int roll(int num){
		Dice d = new Dice();
		//ダイスの出目と振ったダイスの出目の和
		int dice =0;
		int diceSum = 0;
		
		System.out.println("ダイスの出目");
		//出目を決める
		for(int i=1;i<=num;i++){
			dice = d.dice[new Random().nextInt(6)];  
			System.out.printf("%d個目:%d%n",i,dice);
			//出目の和
			diceSum += dice;
		}
		System.out.println(diceSum);
		return diceSum;
	}

	//全能力値を決定
	public void decideAllStatus(){
		decideStatus();
		decideBonus();
		setHp(getLevel()*3 + getVit());
		setResVit(getLevel() + getVitBonus());
		setMp(getLevel()*3 + getPow());//修正予定、魔法使い系技能レベル未実装のため冒険者レベルで代用中
		setResPow(getLevel() + getPowBonus());
	}

	//HP,MP,抵抗力以外の能力値を決定
	public void decideStatus(){
		//器用度
		int dex = getTec() + getStatusA();
		setDex(dex);
		//敏捷度
		int agi = getTec() + getStatusB();
		setAgi(agi);
		//筋力
		int str = getBody() + getStatusC();
		setStr(str);
		//生命力
		int vit = getBody() + getStatusD();
		setVit(vit);
		//知力
		int wis = getMind() + getStatusE();
		setWis(wis);
		//精神力
		int pow = getMind() + getStatusF();
		setPow(pow);
	}

	//能力値ボーナスを決定
	public void decideBonus(){
		setDexBonus(getDex()/6);
		setAgiBonus(getAgi()/6);
		setStrBonus(getStr()/6);
		setVitBonus(getVit()/6);
		setWisBonus(getWis()/6);
		setPowBonus(getPow()/6);
	}

	//ステータスに各値を格納
	public void setStatus(){
		this.status = new int[]{
			getHp(),
			getMp(),
			getResVit(),
			getResPow(),
			getDex(),
			getAgi(),
			getStr(),
			getVit(),
			getWis(),
			getPow(),
		};
	}
	//名前のアクセサ
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	//経験点のアクセサ
	public int getExp(){
		return this.exp;
	}
	public void setExp(int exp){
		this.exp = exp;
	}

	//冒険者レベルのアクセサ
	public int getLevel(){
		return this.level;
	}
	public void setLevel(int level){
		this.level = level;
	}

	//年齢のアクセサ
	public int getAge(){
		return this.age;
	}
	public void setAge(){
		this.age = age;
	}
	
	//生まれのアクセサ
	public String getBirth(){
		return this.birth;
	}
	public void setBirth(String birth){
		this.birth = birth;
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

	//生命抵抗力
	public int getResVit(){
		return this.resVit;
	}
	public void setResVit(int resVit){
		this.resVit = resVit;
	}

	//精神抵抗力
	public int getResPow(){
		return this.resPow;
	}
	public void setResPow(int resPow){
		this.resPow = resPow;
	}

	//器用度のアクセサ
	public int getDex(){
		return this.dex;
	}
	public void setDex(int dex){
		this.dex = dex;
	}
	
	//敏捷度のアクセサ
	public int getAgi(){
		return this.agi;
	}
	public void setAgi(int agi){
		this.agi = agi;
	}

	//筋力のアクセサ
	public int getStr(){
		return this.str;
	}
	public void setStr(int str){
		this.str = str;
	}

	//生命力のアクセサ
	public int getVit(){
		return this.vit;
	}
	public void setVit(int vit){
		this.vit = vit;
	}

	//知力のアクセサ
	public int getWis(){
		return this.wis;
	}
	public void setWis(int wis){
		this.wis = wis;
	}

	//精神力のアクセサ
	public int getPow(){
		return this.pow;
	}
	public void setPow(int pow){
		this.pow = pow;
	}

	//器用度ボーナスのアクセサ
	public int getDexBonus(){
		return this.dexBonus;
	}
	public void setDexBonus(int dexBonus){
		this.dexBonus = dexBonus; 
	}	
	//敏捷度ボーナスのアクセサ
	public int getAgiBonus(){
		return this.agiBonus;
	}
	public void setAgiBonus(int agiBonus){
		this.agiBonus = agiBonus;
	}

	//筋力ボーナスのアクセサ
	public int getStrBonus(){
		return this.strBonus;
	}
	public void setStrBonus(int strBonus){
		this.strBonus = strBonus;
	}

	//生命力ボーナスのアクセサ
	public int getVitBonus(){
		return this.vitBonus;
	}
	public void setVitBonus(int vitBonus){
		this.vitBonus = vitBonus;
	}
	
	//知力ボーナスのアクセサ
	public int getWisBonus(){
		return this.wisBonus;
	}
	public void setWisBonus(int dexBonus){
		this.wisBonus = wisBonus;
	}
	//精神力ボーナスのアクセサ
	public int getPowBonus(){
		return this.powBonus;
	}
	public void setPowBonus(int powBonus){
		this.powBonus = powBonus;
	}

	//技のアクセサ
	public int getTec(){
		return this.tec;
	}
	public void setTec(int tec){
		this.tec = tec;
	}

	//体のアクセサ
	public int getBody(){
		return this.body;
	}
	public void setBody(int body){
		this.body = body;
	}

	//心のアクセサ
	public int getMind(){
		return this.mind;
	}
	public void setMind(int mind){
		this.mind = mind;
	}

	//Aのアクセサ
	public int getStatusA(){
		return this.statusA;
	}
	public void setStatusA(int statusA){
		this.statusA = statusA;
	}

	//Bのアクセサ
	public int getStatusB(){
		return this.statusB;
	}
	public void setStatusB(int statusB){
		this.statusB = statusB;
	}

	//Cのアクセサ
	public int getStatusC(){
		return this.statusC;
	}
	public void setStatusC(int statusC){
		this.statusC = statusC;
	}

	//Dのアクセサ
	public int getStatusD(){
		return this.statusD;
	}
	public void setStatusD(int statusD){
		this.statusD = statusD;
	}

	//Eのアクセサ
	public int getStatusE(){
		return this.statusE;
	}
	public void setStatusE(int statusE){
		this.statusE = statusE;
	}

	//Fのアクセサ
	public int getStatusF(){
		return this.statusF;
	}
	public void setStatusF(int statusF){
		this.statusF = statusF;
	}
}
