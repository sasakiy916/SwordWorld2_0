import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
public class BattleManager{
	//戦闘システム
	public static void battle(List<Character> player,List<Character> enemy) throws InterruptedException {
		Random r = new Random();//ランダム準備
		Scanner scan = new Scanner(System.in);//スキャナー準備
		//プレイヤーとモンスターをMapに格納
		Map<List<Character>,Integer> parties = new LinkedHashMap<List<Character>,Integer>();
		parties.put(player, 0);
		parties.put(enemy, 0);
		System.out.println("戦闘開始！");
		//陣営の確認
		//魔物知識判定
		//先制判定
		System.out.println("先制判定を行います");
		//各パーティの一番高い先制値を確認
		//先制値の一番高いキャラの名前
		String playerPreName = "";
		String enemyPreName = "";
		for(List<Character> party:parties.keySet()) {
			int pre = parties.get(party);
			for(Character character:party) {
				if(pre < character.getPre()) {
					parties.put(party,character.getPre());
					if(character instanceof Player) {
						playerPreName = character.getName();
					}else {
						enemyPreName = character.getName();
					}
				}
			}
		}
		//先攻後攻を決める
		System.out.printf("");
		System.out.printf("モンスターの一番高い先制値>>%s:%d%n",enemyPreName,parties.get(enemy));
		//プレイヤーの先制値決定(モンスター側先制値以上なら先攻)
		int dice = Dice.roll(2);
		System.out.printf("プレイヤー側達成値>>%s:%d%n",playerPreName,parties.get(player)+dice);
		List<List<Character>> orderParty = new ArrayList<>();
		System.out.print("先攻:");
		if(parties.get(player) + dice >= parties.get(enemy)) {
			//プレイヤー先攻
			System.out.println("プレイヤー陣営");
			orderParty.add(player);
			orderParty.add(enemy);
		}else {
			//モンスター先攻
			System.out.println("モンスター陣営");
			orderParty.add(enemy);
			orderParty.add(player);
		}
		//戦力の初期配置
		System.out.println("味方陣営");
		displayStatus(player);
		System.out.println("敵陣営");
		displayStatus(enemy);
		//戦闘開始
		while(true) {
			for(List<Character> party:orderParty) {
				for(Character character:party) {
					int target;
					System.out.printf("%sの手番%n",character.getName());
					Thread.sleep(1000);
					if(character instanceof Player) {
						//プレイヤーのターン時
						target = r.nextInt(orderParty.size());
						roundAttack(character,enemy.get(target));
						//生存判定
						if(!isAlive(enemy.get(target))) {
							System.out.println(enemy.get(target).getHp());
							break;
						}
					}else {
						//モンスターのターン時
						target = r.nextInt(player.size());
						roundAttack(character,player.get(target));
						//生存判定
						if(!isAlive(player.get(target))) {
							System.out.println(player.get(target).getHp());
							break;
						}
					}
				}
			}
			//戦闘継続か終了かの確認
			//ステータス表示
			displayStatus(player);
			displayStatus(enemy);
			System.out.println("次のラウンド");
			//戦闘の継続、終了確認
			System.out.print("戦闘継続:Enter(終了:q もしくは quit)>>");
			String sc =scan.nextLine();
			System.out.println("**********************************************");
			if(sc.matches("q|quit")){
				System.out.println("戦闘終了");
				break;
			}
		}
		displayStatus(player);
		displayStatus(enemy);
		System.out.println("戦闘終了");
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
		Player player;
		Monster monster;
		//算出ダメージ
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
