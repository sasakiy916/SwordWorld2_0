import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;
public class BattleManager{
	public static void battle(List<Character> player,List<Character> enemy) {
		Scanner scan = new Scanner(System.in);
		System.out.println("戦闘開始！");
		displayStatus(player);
		displayStatus(enemy);
		//陣営の確認
		//魔物知識判定
		//先制判定
		//戦力の初期配置
		//戦闘開始
		while(true) {
			roundAttack(player.get(0),enemy.get(0));
			if(!isAlive(enemy.get(0))) {
				System.out.println(enemy.get(0).getHp());
				break;
			}
			//戦闘継続か終了かの確認
			//ステータス表示
			displayStatus(player);
			displayStatus(enemy);
			System.out.println("次のラウンド");
			scan.nextLine();
		}
		displayStatus(player);
		displayStatus(enemy);
		System.out.println("戦闘終了");
	}
	public BattleManager(List<Character> p, Character m){
		//陣営の確認

		//魔物知識判定

		//先制判定

		//戦力の初期配置

		//		System.out.println("------------------");
		//		System.out.printf("%sのHP:%d%n%n",m.getName(),m.getHp());
		//		System.out.printf("%sのHP:%d%n",p.getName(),p.getHp());
		//		System.out.println("------------------");
		//		System.out.print("戦闘を開始します。(Enterで戦闘を進めていきます)");
		//		new Scanner(System.in).nextLine();
		//		//戦闘開始
		while(true){
			//			//先攻側の行動
			//			System.out.println("先攻側の行動");
			//			System.out.printf("%sのHP:%d%n",m.getName(),m.getHp());
			//			System.out.printf("%sの攻撃%n",p.getName());
			//			int damage = p.damageRoll();
			//			m.setHp(m.getHp() - damage);
			//			if(damage!=0){
			//				System.out.printf("%sに%dのダメージを与えた%n",m.getName(),damage);
			//			}
			//			//System.out.printf("%sのHP:%d%n%n",m.getName(),m.getHp());
			//			//生存判定
			//			isAliveCharacter(m);
			//			//現在の各状態
			//			System.out.println("------------------");
			//			System.out.printf("%sのHP:%d%n%n",m.getName(),m.getHp());
			//			System.out.printf("%sのHP:%d%n",p.getName(),p.getHp());
			//			System.out.println("------------------");
			//			if(!isAliveCharacter(m)){
			//				System.out.printf("%sを倒した%n",m.getName());
			//				break;
			//			}
			//			System.out.print("先攻側の行動終了,後攻側の行動に移ります。");
			//			new Scanner(System.in).nextLine();
			//			System.out.println("**********************************************");
			//			System.out.println("**********************************************");
			//
			//			//後攻側の行動
			//			System.out.println("後攻側の攻撃");
			//			System.out.printf("%sのHP:%d%n",p.getName(),p.getHp());
			//			System.out.printf("%sの攻撃%n",m.getName());
			//			int secondDamage = m.damageRoll();
			//			p.setHp(p.getHp() - secondDamage);
			//			System.out.printf("%sに%dのダメージを与えた%n",p.getName(),secondDamage);
			//			//System.out.printf("%sのHP:%d%n%n",p.getName(),p.getHp());
			//			
			//			//生存判定
			//			isAliveCharacter(p);
			//			//現在の各状態
			//			System.out.println("------------------");
			//			System.out.printf("%sのHP:%d%n%n",m.getName(),m.getHp());
			//			System.out.printf("%sのHP:%d%n",p.getName(),p.getHp());
			//			System.out.println("------------------");
			//			if(!isAliveCharacter(p)){
			//				System.out.printf("%sはやられてしまった%n",p.getName());
			//				break;
			//			}
			//			System.out.print("後攻側の行動終了,");

			//戦闘の継続、終了確認
			System.out.println("戦闘を継続しますか?(終了する場合は「q + Enter」)");
			String sc = new Scanner(System.in).nextLine();
			System.out.println("**********************************************");
			System.out.println("**********************************************");
			if(sc.equals("q")){
				System.out.println("戦闘終了");
				break;
			}
		}
	}

	//ステータス表示
	private static void displayStatus(List<Character> party) {
			for(int i=0;i<party.size();i++) {
				System.out.print("-----------");
			}
			System.out.print("-");
			System.out.println();
			System.out.print("|");
			//キャラの名前
			for(int i=0;i<party.size();i++) {
				System.out.printf("%s",format(party.get(i).getName(),10));
				System.out.print("|");
			}
			System.out.println();
			System.out.print("|");
			//HP
			for(int i=0;i<party.size();i++) {
				System.out.printf("%s:%s",format("ＨＰ",3),format(""+party.get(i).getHp(),5));
				System.out.print("|");
			}
			System.out.println();
			System.out.print("|");
			//MP
			for(int i=0;i<party.size();i++) {
				System.out.printf("%s:%s",format("ＭＰ",3),format(""+party.get(i).getMp(),5));
				System.out.print("|");
			}
			System.out.println();
			for(int i=0;i<party.size();i++) {
				System.out.print("-----------");
			}
			System.out.print("-");
			System.out.println();
	}
	
	//ラウンド毎の攻撃
	private static void roundAttack(Character first,Character second) {
		//命中判定
		int hit = first.judgeHit();
		switch(hit) {
		//自動失敗
		case 1:
			System.out.println("命中判定に自動失敗しました。");
			return;
		//自動成功
		case 0:
			System.out.println("命中判定に自動成功");
		//出目3～11
		default:
			System.out.printf("達成値:%d%n",hit);
			//回避判定
			System.out.println("回避判定");
			int avoi = second.judgeAvoi();
			System.out.printf("達成値:%d%n",avoi);
			System.out.println(avoi);
			switch(avoi) {
			//自動成功
			case 0:
				System.out.println("回避に自動成功");
				return;
			//自動失敗
			case 1:
				System.out.println("回避に自動失敗");
				break;
			//出目3～11
			default:
				if(hit <= avoi) {
					System.out.println("回避に成功");
					return;
				}else {
					System.out.println("回避に失敗");
					break;
				}
			}
		}
		//ダメージ決定
		//算出ダメージ
		Player player;
		Monster monster;
		int calcDamage;
		if(first instanceof Player) {
			player = (Player)first;
			monster = (Monster)second;
			//武器の威力表から結果を出す
			int power = Dice.roll(2)-2;
			if(power != 0) {
				calcDamage = player.w.getPower(power);
			}else {
				System.out.println("攻撃、自動失敗");
				return;
			}
		}else {
			monster = (Monster)first;
			player = (Player)second;
			calcDamage = Dice.roll(2);
		}
		//合算ダメージ
		int totalDamage = calcDamage + first.getAddDamage();
		//適用ダメージ
		int appliedDamage = totalDamage - second.getDef(); 
		if(appliedDamage <= 0) {
			System.out.printf("%sにダメージを与えられなかった%n",second.getName());
			return;
		}
		//HPを減少させる
		second.setHp(second.getHp() - appliedDamage);
		System.out.printf("%sが%sに%dダメージを与えた%n",first.getName(),second.getName(),appliedDamage);
	}

	//生存判定
	private static boolean isAlive(Character c){
		if(c.getHp() <=0){
			c.setHp(0);
			return false;
		}
		return true;
	}
	private boolean isAliveMonster(Character m){
		if(m.getHp() <= 0){
			m.setHp(0);
			System.out.printf("%sのHP:%d%n",m.getName(),m.getHp());
			return false;
		}
		System.out.printf("%sのHP:%d%n%n",m.getName(),m.getHp());
		return true;
	}
	private boolean isAlivePlayer(Character p){
		if(p.getHp() <= 0){
			p.setHp(0);
			System.out.printf("%sのHP:%d%n",p.getName(),p.getHp());
			System.out.printf("%sはやられてしまった",p.getName());
			return false;
		}
		System.out.printf("%sのHP:%d%n%n",p.getName(),p.getHp());
		return true;
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
