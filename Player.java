import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS)
@JsonIgnoreProperties(ignoreUnknown=true)
public  abstract class Player extends Character{
	private String gender;//性別
	private String race;//種族
	private String birth;//生まれ
	private int age;//年齢
	//基礎能力値
	private int tec;//技
	private int body;//体
	private int mind;//心
	//ダイスで決める能力値
	private int statusA;//A
	private int statusB;//B
	private int statusC;//C
	private int statusD;//D
	private int statusE;//E
	private int statusF;//F
	private int exp;//経験点
	//成長値
	private int growDex;//器用度
	private int growAgi;//敏捷度
	private int growStr;//筋力
	private int growVit;//生命力
	private int growWis;//知力
	private int growPow;//精神力
	private Map<String,Integer>jobs = new LinkedHashMap<String,Integer>();//技能と技能レベル
	//戦闘特技
	Set<Skill> skills = new LinkedHashSet<Skill>();
	//練技/呪歌(ルールブック２の内容のため実装予定無し)
	//言語(実装予定なし)
	//名誉系のフィールド(実装予定なし)
	//武器
	Weapon weapon = new Panch();
	//鎧
	Armor armor = new Nude(); 
	//盾
	Shield shield = new Nohand();
	//魔法発動体
	//アイテム
	private int money = 1200;//所持金
	private static int bank;//預金/借金
	//コンストラクタ
	public Player(){
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
	//自動失敗、自動成功判定
	public int autoJudge(int dice, int addValue) {

		switch(dice) {
		//1ゾロ 自動失敗
		case 2:
			System.out.println("自動失敗!");
			return 1;
		//6ゾロ 自動成功
		case 12:
			System.out.println("自動成功!");
			return 0;
		default:
			return dice + addValue;
		}
	}
	//先制判定
	@Override
	public int judgePre(){
		System.out.println("先制力判定");
		int dice = Dice.roll(2);
		return autoJudge(dice,getJobs().get("スカウト") + getAgiBonus());
	}
	//命中判定
	@Override
	public int judgeHit(){
		//ダイス値
		int dice = Dice.roll(2);
		//出目による判定
		return autoJudge(dice,getHit());
	}
	//回避判定:修正予定
	@Override
	public int judgeAvoi() {
		int dice = Dice.roll(2);
		return autoJudge(dice,getAvoi());
	}
	//ダメージロール
	public int damageRoll(){
		int dice = Dice.roll(2)-2;
		if(dice > 0){
			System.out.printf("%sの威力は%d%n",getWeapon().getName(),getWeapon().getPower(dice));
			return getWeapon().getPower(dice) + this.getStrBonus();
		}else{
			System.out.println("自動失敗!ダメージを与えられなかった");
			return 0;
		}
	}
	//技能の追加習得
	public void addJob() {
		Scanner scan = new Scanner(System.in);
		do {
			int select = 1;
			//所持技能表示
			System.out.println("取得技能一覧");
			System.out.printf("%s|%s%n",Option.format("技能",18),"レベル");
			for(String key:getJobs().keySet()) {
				int value = getJobs().get(key);
				if(value != 0) {
					System.out.printf("%s|%d%n",Option.format(key,18),value);
				}
			}
			System.out.println("------------------------");
			System.out.printf("所持経験点:%d%n",getExp());
			System.out.println("取得可能な技能");
			System.out.printf("%s|%s%n",Option.format("技能",18),"必要経験点");
			//取得可能な技能一覧表示
			for(String key:getJobs().keySet()) {
				int jobLevel = getJobs().get(key);
				System.out.printf("%s|%4d:%2d%n",Option.format(key,18),expTable(key)[jobLevel],select);
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
					}else {
						System.out.println("経験点が足りません");
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
					break;
				}
				selectJob++;
			}
			System.out.println();
			System.out.printf("残り経験点 %d%n",getExp());
//			System.out.print("更に技能を取得しますか？(する:0,しない:1)>>");
//			select = scan.nextInt();
//			System.out.println();
//			if(select == 1)break;
		}while(true);
	}
	//経験点テーブル(削除予定)
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
	//所持金のアクセサ
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	//生命抵抗力
	public int getResVit(){
		return getLevel() + getVitBonus();
	}
	//精神抵抗力
	public int getResPow(){
		return getLevel() + getPowBonus();
	}
	//器用度の取得
	public int getDex(){
		return this.tec + this.statusA + this.growDex;
	}
	//敏捷度の取得
	public int getAgi(){
		return this.tec + this.statusB + this.growAgi;
	}
	//筋力の取得
	public int getStr(){
		return this.body + this.statusC + this.growStr;
	}
	//生命力の取得
	public int getVit(){
		return this.body + this.statusD + this.growVit;
	}
	//知力の取得
	public int getWis(){
		return this.mind + this.statusE + this.growWis;
	}
	//精神力の取得
	public int getPow(){
		return this.mind + this.statusF + this.growPow;
	}
	//器用度ボーナスのアクセサ
	public int getDexBonus(){
		return getDex() / 6;
	}
	//敏捷度ボーナスのアクセサ
	public int getAgiBonus(){
		return getAgi() / 6;
	}
	//筋力ボーナスのアクセサ
	public int getStrBonus(){
		return getStr() / 6;
	}
	//生命力ボーナスのアクセサ
	public int getVitBonus(){
		return getVit() / 6;
	}
	//知力ボーナスのアクセサ
	public int getWisBonus(){
		return getWis() / 6;
	}
	//精神力ボーナスのアクセサ
	public int getPowBonus(){
		return getPow() / 6;
	}
	//最大HPの取得
	public int getMaxHp() {
		return getLevel() * 3 + getVit();
	}
	//最大MPの取得(修正予定)
	public int getMaxMp() {
		return getLevel() * 3 + getPow();
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
	//器用度成長値
	public int getGrowDex() {
		return growDex;
	}
	public void setGrowDex(int growDex) {
		this.growDex = growDex;
	}
	public int getGrowAgi() {
		return growAgi;
	}
	public void setGrowAgi(int growAgi) {
		this.growAgi = growAgi;
	}
	//筋力成長値
	public int getGrowStr() {
		return growStr;
	}
	public void setGrowStr(int growStr) {
		this.growStr = growStr;
	}
	//生命力成長値
	public int getGrowVit() {
		return growVit;
	}
	public void setGrowVit(int growVit) {
		this.growVit = growVit;
	}
	//知力成長値
	public int getGrowWis() {
		return growWis;
	}
	public void setGrowWis(int growWis) {
		this.growWis = growWis;
	}
	//精神力成長値
	public int getGrowPow() {
		return growPow;
	}
	public void setGrowPow(int growPow) {
		this.growPow = growPow;
	}
	//防護点:修正予定
	@Override
	public int getDef() {
		return getArmor().getDef() + getShield().getDef();
	}
	//回避:修正予定 シューター以外の戦士系技能レベルによって変わる
	@Override
	public int getAvoi() {
		int maxJobLevel = 0;
		for(String job:getJobs().keySet()) {
			if(job.matches("ファイター|グラップラー|フェンサー") && maxJobLevel < getJobs().get(job)) {
				maxJobLevel = getJobs().get(job);
			}
		}
		return maxJobLevel + getAgiBonus();
	}
	//先制力
	@Override
	public int getPre() {
		return getJobs().get("スカウト") + getAgiBonus();
	}
	//命中:修正予定 戦士系技能レベルによって変わる
	@Override
	public int getHit() {
		return getDexBonus();
	}
	//追加ダメージ:修正予定 戦士系技能レベルによって変わる
	@Override
	public int getAddDamage() {
		return getStrBonus();
	}
	//種族アクセサ
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	//技能アクセサ
	public Map<String,Integer> getJobs() {
		return jobs;
	}
	public void setJobs(Map<String,Integer> jobs) {
		this.jobs = jobs;
	}
	//戦闘特技のアクセサ
	public Set<Skill> getSkills() {
		return skills;
	}
	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}
	//性別のアクセサ
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Weapon getWeapon() {
		return weapon;
	}
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	public Armor getArmor() {
		return armor;
	}
	public void setArmor(Armor armor) {
		this.armor = armor;
	}
	public Shield getShield() {
		return shield;
	}
	public void setShield(Shield shield) {
		this.shield = shield;
	}
	public static int getBank() {
		return bank;
	}
	public static void setBank(int bank) {
		Player.bank = bank;
	}
	
	//Player情報の表示
	public String toString() {
		System.out.println("------------------------");
		//キャラのステータス表示
		int width = 15;
		System.out.printf("名前:%s%n",getName());
		System.out.printf("%s",Option.format("HP:" + getHp() + "/" + getMaxHp(),width));
		System.out.printf("%s%n",Option.format("MP:" + getMp() + "/" + getMaxMp(),width));
		System.out.printf("%s",Option.format("生命抵抗力:" + getResVit(),width));
		System.out.printf("%s%n",Option.format("精神抵抗力:" + getResPow(),width));
		System.out.printf("%s",Option.format("器用度:" + getDex(),width));
		System.out.printf("%s%n",Option.format("敏捷度:" + getAgi(),width));
		System.out.printf("%s",Option.format("筋力:" + getStr(),width));
		System.out.printf("%s%n",Option.format("生命力:" + getVit(),width));
		System.out.printf("%s",Option.format("知力:" + getWis(),width));
		System.out.printf("%s%n",Option.format("精神力:" + getPow(),width));
		System.out.printf("%s%n",Option.format("防護点:" + getDef(),width));
		System.out.println();
		System.out.printf("種族:%s 生まれ:%s%n",getRace(),getBirth()); 
		System.out.printf("経験点:%d%n",getExp());
		System.out.printf("所持金 %dG%n", getMoney());
		System.out.println("------------------------");
		//技能一覧
		System.out.println("取得技能一覧");
		System.out.printf("%s|%s%n",Option.format("技能",18),"レベル");
		for(String key:getJobs().keySet()) {
			int value = getJobs().get(key);
			if(value != 0) {
				System.out.printf("%s|%d%n",Option.format(key,18),value);
			}
		}
		//装備品
		System.out.println("------------------------");
		System.out.println("現在の装備");
		System.out.println("武器:" + getWeapon().getName());
		System.out.println("鎧:" + getArmor().getName());
		System.out.println("盾:"+getShield().getName());
		System.out.println("------------------------");
		return "";
	}
}
