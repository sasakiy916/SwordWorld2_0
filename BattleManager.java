import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
public class BattleManager{
	//戦闘システム
	public static void battle(List<Character> player,List<Character> enemy) throws Exception {
		Random r = new Random();//ランダム準備
		Scanner scan = new Scanner(System.in);//スキャナー準備
		int sumMoney = 0;//戦闘後に貰えるお金
		int sumExp = 0;//戦闘後に貰える経験点
		//プレイヤーとモンスターをMapに格納
		Map<List<Character>,Integer> parties = new LinkedHashMap<List<Character>,Integer>();
		parties.put(player, 0);
		parties.put(enemy, 0);
		System.out.println("戦闘開始！");
		//陣営の確認
		System.out.println("味方陣営");
		displayStatus(player);
		System.out.println("敵陣営");
		displayStatus(enemy);
		//魔物知識判定
		//先制判定
		int preLine = 30;
		Option.printLine(preLine);
		System.out.println("<<先制判定>>");
		Option.printLine(preLine,"+");
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
		//先攻→後攻の順でパーティ追加
		List<List<Character>> orderParty = new ArrayList<>();
		Option.printLine(preLine,"+");
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
		Option.printLine(preLine);
		//戦力の初期配置
		//戦闘開始
		while(true) {
			for(List<Character> party:orderParty) {
				//どっちかのパーティが全滅してるか
				if(orderParty.get(0).isEmpty() || orderParty.get(1).isEmpty()) {
					break;
				}
				//攻撃開始
				for(Character character:party) {
					int target;//攻撃対象
					Thread.sleep(1000);
					List<Character> targetParty = new ArrayList<>();//攻撃される側
					if(character instanceof Player) {
						//プレイヤーのターン時
						targetParty = enemy;
					}else {
						//モンスターのターン時
						targetParty = player;
					}
					target = r.nextInt(targetParty.size());
					Option.printLine(25);
					System.out.printf("攻撃宣言:%s → %s%n",character.getName(),targetParty.get(target).getName());
					Option.printLine(25,"+");
					roundAttack(character,targetParty.get(target));
					//生存判定
					if(!isAlive(targetParty.get(target))) {
						if(targetParty.get(target) instanceof Player) {
							//プレイヤーの場合
							System.out.printf("%sのHP:%d%n",targetParty.get(target).getName(),targetParty.get(target).getHp());
							System.out.printf("%sがやられてしまった！%n",targetParty.get(target).getName());
						}else {
							//モンスターの場合
							System.out.printf("%sのHP:%d%n",enemy.get(target).getName(),enemy.get(target).getHp());
							System.out.printf("%sを倒した！%n",enemy.get(target).getName());
							sumMoney += ((Monster)enemy.get(target)).getMoney();
							sumExp += ((Monster)enemy.get(target)).getExp();
						}
						//パーティから削除
						targetParty.remove(target);
					}
				}
			}
			//全滅処理
			if(player.isEmpty()) {
				System.out.println("プレイヤーの全滅");
				displayStatus(player);
				displayStatus(enemy);
				break;
			}else if(enemy.isEmpty()) {
				System.out.println("モンスターの全滅");
				displayStatus(player);
				displayStatus(enemy);
				System.out.printf("%dG取得した%n",sumMoney);
				System.out.printf("%d経験点取得した%n",sumExp);
				for(Character pl:player) {
					Player ppl = (Player)pl;
					ppl.setMoney(ppl.getMoney()+sumMoney);
					ppl.setExp(ppl.getExp()+sumExp);
					ppl.setHp(ppl.getMaxHp());
				}
				break;
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
				System.out.printf("%d経験点取得した%n",sumExp);
				System.out.println("途中で逃げたためお金は拾えなかった。");
				for(Character pl:player) {
					Player ppl = (Player)pl;
					ppl.setExp(ppl.getExp()+sumExp);
					ppl.setHp(ppl.getMaxHp());
				}
				break;
			}
		}
		//パーティ情報保存
		String path = "party.json";
		File partyPath = new File("player/"+path);
		partyPath.delete();//既存ファイル削除
		for(Character savePlayer:player) {
			if(savePlayer instanceof Player) {
				PlayerData.save((Player)savePlayer,path,true);
			}
		}
		System.out.println("戦闘終了");
	}
	//パーティの簡易ステータス表示

	//ステータス表示
	public  static void displayStatus(List<Character> party) {
		for(int i=0;i<party.size();i++) {
			System.out.print("-----------");
		}
		System.out.print("-");
		System.out.println();
		System.out.print("|");
		//キャラの名前
		for(int i=0;i<party.size();i++) {
			System.out.printf("%s",Option.format(party.get(i).getName(),10));
			System.out.print("|");
		}
		System.out.println();
		System.out.print("|");
		//HP
		for(int i=0;i<party.size();i++) {
			System.out.printf("%s:%s",Option.format("ＨＰ",3),Option.format(""+party.get(i).getHp(),5));
			System.out.print("|");
		}
		System.out.println();
		System.out.print("|");
		//MP
		for(int i=0;i<party.size();i++) {
			System.out.printf("%s:%s",Option.format("ＭＰ",3),Option.format(""+party.get(i).getMp(),5));
			System.out.print("|");
		}
		System.out.println();
		for(int i=0;i<party.size();i++) {
			System.out.print("-----------");
		}
		System.out.print("-");
		System.out.println();
	}

	//ラウンド毎の攻撃(武器攻撃)
	private static void roundAttack(Character first,Character second) throws Exception {
		//命中判定
		Option.printLine(25);
		System.out.printf("<<%sの命中判定>>%n",first.getName());
		int hit = first.judgeHit();
		switch(hit) {
		//自動失敗
		case 1:
			System.out.println("攻撃を外してしまった");
			return;
			//自動成功
		case 0:
			System.out.println("命中判定に自動成功");
			break;
			//出目3～11
		default:
			System.out.printf("命中値:%d%n",hit);
			break;
		}
		//回避判定
		Option.printLine(25);
		System.out.printf("<<%sの回避判定>>%n",second.getName());
		int avoi = second.judgeAvoi();
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
			System.out.printf("回避値:%d%n",avoi);
			System.out.print("命中");
			if(hit <= avoi) {
				System.out.println("<=回避 → 成功");
				System.out.printf("%sは%sの攻撃を避けた!%n",second.getName(),first.getName());
				return;
			}else {
				System.out.println(">回避 → 失敗");
				break;
			}
		}
		Option.printLine(25);
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
				calcDamage = player.getWeapon().getPower(power);
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
		Option.printLine(25);
	}

	//生存判定
	private static boolean isAlive(Character c){
		if(c.getHp() <=0){
			c.setHp(0);
			return false;
		}
		return true;
	}
}
