import java.util.Scanner;
public  class Player extends Character{
	private int exp;//経験点
	private int age;//年齢
	private String birth;//生まれ
	private int money = 1200;//所持金
	//能力値
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
	//武器
	Wepon w;

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
		//武器選択
		System.out.print("装備を選択してください。ショートソード:0,ナイフ:1>>");
		int weponSelect = new Scanner(System.in).nextInt();
		if(weponSelect == 0){
			w = new Wepon(WeponList.SHORTSWORD);
		}else{
			w = new Wepon(WeponList.KNIFE);
		}
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
	
	public int damageRoll(){
		int dice = this.d.roll(2)-2;
		if(dice > 0){
			System.out.printf("%sの威力は%d%n",w.getName(),w.getPower(dice));
			return w.getPower(dice)+this.strBonus;
		}else{
			System.out.println("自動失敗!ダメージを与えられなかった");
			return 0;
		}
	}
	//経験点のアクセサ
	public int getExp(){
		return this.exp;
	}
	public void setExp(int exp){
		this.exp = exp;
	}


	//年齢のアクセサ
	public int getAge(){
		return this.age;
	}
	public void setAge(int age){
		this.age = age;
	}
	
	//生まれのアクセサ
	public String getBirth(){
		return this.birth;
	}
	public void setBirth(String birth){
		this.birth = birth;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
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
	public void setWisBonus(int wisBonus){
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
