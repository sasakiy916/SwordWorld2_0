import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public  abstract class Player extends Character{
	private int exp;//経験点
	private int age;//年齢
	private String race;//種族
	private String birth;//生まれ
	private Map<String,Integer>jobs = new LinkedHashMap<String,Integer>();//技能と技能レベル
	private Map<String,int[]>births = new LinkedHashMap<String,int[]>();//生まれ表
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

	//コンストラクタ
	public Player(){
		setLevel(1);
		this.statusName = new String[]{
				"冒険者レベル",
				"HP",
				"生命抵抗力",
				"MP",
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
		//技能の準備
		//戦士系
		getJobs().put("ファイター",0);
		getJobs().put("グラップラー",0);
		getJobs().put("フェンサー",0);
		getJobs().put("シューター",0);
		//魔法使い系
		getJobs().put("ソーサラー",0);
		getJobs().put("コンジャラー",0);
		getJobs().put("プリースト",0);
		getJobs().put("フェアリーテイマー",0);
		getJobs().put("マギテック",0);
		//その他技能
		getJobs().put("スカウト",0);
		getJobs().put("レンジャー",0);
		getJobs().put("セージ",0);
	}
	//基礎能力値設定
	public void setBaseAbilities(int tec,int body,int mind) {
		setTec(tec);
		setBody(body);
		setMind(mind);
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
				getLevel(),
				getHp(),
				getResVit(),
				getMp(),
				getResPow(),
				getDex(),
				getAgi(),
				getStr(),
				getVit(),
				getWis(),
				getPow(),
		};
	}

	//ダメージロール
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

	//生まれによる技能習得
	public abstract void learnJob();
	//技能の追加習得
	public void addJob() {
		Scanner scan = new Scanner(System.in);
		do {
			int select = 1;
			//所持技能表示
			System.out.println("取得技能一覧");
			System.out.printf("%s|%s%n",format("技能",18),"レベル");
			for(String key:getJobs().keySet()) {
				int value = getJobs().get(key);
				if(value != 0) {
					System.out.printf("%s|%d%n",format(key,18),value);
				}
			}
			System.out.println("------------------------");
			System.out.printf("所持経験点:%d%n",getExp());
			System.out.println("取得可能な技能");
			System.out.printf("%s|%s%n",format("技能",18),"必要経験点");
			//取得可能な技能一覧表示
			for(String key:getJobs().keySet()) {
				int jobLevel = getJobs().get(key);
				System.out.printf("%s|%4d:%2d%n",format(key,18),expTable(key)[jobLevel],select);
				select++;
			}
			//取得技能の選択
			System.out.print("取得したい技能を選択(取得終了:0)>>");
			select = scan.nextInt();
			if(select == 0)break;
			int selectJob = 1;
			for(String key:getJobs().keySet()) {
				int jobLevel = getJobs().get(key);
				if(selectJob == select) {
					//経験点が足りるか
					if(getExp() >= expTable(key)[jobLevel]) {
						jobLevelUp(key);
						System.out.printf("%sレベル%d 取得%n",key,getJobs().get(key));
						setExp(getExp() - expTable(key)[jobLevel]);
						break;
					}else {
						System.out.println("経験点が足りません");
						break;
					}
				}
				selectJob++;
			}
			System.out.println();
			System.out.printf("残り経験点 %d%n",getExp());
			System.out.print("更に技能を取得しますか？(する:0,しない:1)>>");
			select = scan.nextInt();
			System.out.println();
			if(select == 1)break;
		}while(true);
	}
	//経験点テーブル
	public int[] expTable(String job) {
		int[] expTable = null;
		//経験テーブルA
		int[] expTableA = {
				1000,
				1000,
				1500,
				1500,
				2000,
				2500,
		};
		//経験テーブルB
		int[] expTableB = {
				500,
				1000,
				1000,
				1500,
				1500,
				2000,
		};
		if( job == "ファイター" 
				||job == "グラップラー"
				||job == "ソーサラー"
				||job == "コンジャラー"
				||job == "プリースト"
				||job == "フェアリーテイマー"
				||job == "マギテック") {
			expTable = expTableA;
		}else {
			expTable = expTableB;
		}
		return expTable;
	}
	//技能レベルアップ
	public void jobLevelUp(String job) {
		this.getJobs().put(job, this.getJobs().get(job)+1);
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

	//生まれ表のアクセサ
	public Map<String,int[]> getBirths() {
		return births;
	}
	public void setBirths(Map<String,int[]> births) {
		this.births = births;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public Map<String,Integer> getJobs() {
		return jobs;
	}
	public void setJobs(Map<String,Integer> jobs) {
		this.jobs = jobs;
	}
	//全角半角の文字位置合わせ
	private static String format(String target, int length){
		int byteDiff = (getByteLength(target, Charset.forName("UTF-8"))-target.length())/2;
		return String.format("%-"+(length-byteDiff)+"s", target);
	}

	//文字のバイト数取得
	private static int getByteLength(String string, Charset charset) {
		return string.getBytes(charset).length;
	}
}
