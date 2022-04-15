import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class CharacterCreater {
	private Player player;//種族のインスタンス
	private String[] birth;//生まれ
	private int[][] birthBaseAbilities;
	Dice d = new Dice();//ダイス
	Scanner scan = new Scanner(System.in);
//	Wepon wepon = new Wepon();
	//コンストラクタ
	public CharacterCreater() {
		decideRaceAndBirth();//種族と生まれの決定
		decideStatus();//能力値の決定
		decideJobLevel();//技能の習得
		decideLevel();//冒険者レベルの決定
		decideHpAndMp();//HP,MPの決定
//		decideSkill();//戦闘特技の習得
		Shop.buy(this.player);
		//buyEquipment();//装備品の購入
		completeCharacter();//キャラの完成
//		this.player.w = new Sword(Sword.SwordList.values()[2]);
//		buyEquipment();
	}

	//種族と生まれの決定
	public void decideRaceAndBirth() {
		//種族一覧表示
		System.out.println("種族一覧");
		for(Race race:Race.values()) {
			System.out.printf("%s:%d%n",race.getName(),race.ordinal());
		}
		//種族を選択
		System.out.print("種族を選んでください>>");
		int selectRace = scan.nextInt();
		System.out.println();
		//種族決定
		switch(Race.values()[selectRace]) {
		case HUMAN:
			setPlayer(new Human());
			break;
		case ELF:
			setPlayer(new Elf());
			break;
		}
		//生まれの一覧表示
		System.out.println("生まれ一覧");
		this.birth = new String[this.player.getBirths().size()];//生まれの配列用意
		this.birthBaseAbilities = new int[this.player.getBirths().size()][3];//生まれごとの基礎能力値の配列用意
		int selectBirth = 0;//生まれ選択肢
		//種族ごとの生まれ一覧
		for(String key:getPlayer().getBirths().keySet()) {
			System.out.println(key + ":" + selectBirth);
			this.birth[selectBirth] = key;
			this.birthBaseAbilities[selectBirth] = getPlayer().getBirths().get(key);
			selectBirth++;
		}
		//生まれを選択
		System.out.print("生まれを選んでください>>");
		selectBirth = scan.nextInt();
		this.player.setBirth(this.birth[selectBirth]);
		//基礎能力値の決定
		this.player.setBaseAbilities(
				this.birthBaseAbilities[selectBirth][0],
				this.birthBaseAbilities[selectBirth][1],
				this.birthBaseAbilities[selectBirth][2]
				);
		System.out.println();
	}
	//能力値の決定
	public void decideStatus() {
		this.player.setName("アルクレイド");
		this.player.decideStatus();//デバッグ用
		//基礎能力値設定
//		this.player.setBaseAbilities(8, 4, 9);;
		//A~F能力値ダイスロール
		this.player.setStatusA(d.roll(2));
		this.player.setStatusB(d.roll(2));
		this.player.setStatusC(d.roll(2));
		this.player.setStatusD(d.roll(2));
		this.player.setStatusE(d.roll(2));
		this.player.setStatusF(d.roll(2));
		//全能力値決定
		this.player.decideAllStatus();
		//A~Fのダイスロール結果表示
		System.out.println("A~Fまでのダイス値");
		System.out.printf("%d,%d,%d,%d,%d,%d%n%n",this.player.getStatusA(),this.player.getStatusB(),this.player.getStatusC(),this.player.getStatusD(),this.player.getStatusE(),this.player.getStatusF());
//		this.player.setStatus();
//		//能力値表示
//		System.out.println("能力値が決定しました！");
//		System.out.println("------------------------");
//		for(int i=0;i<this.player.statusName.length;i++){
//			System.out.printf("%s:%d%n",this.player.statusName[i],this.player.status[i]);
//		}
//		System.out.printf("種族:%s 生まれ:%s%n",this.player.getRace(),this.player.getBirth()); 
//		System.out.println("------------------------");
	}
	//技能の習得
	public void decideJobLevel() {
		this.player.learnJob();
		System.out.printf("経験点を消費して技能を取得できます%n%n");
		this.player.addJob();
	}
	//言語の習得
	
	//冒険者レベルの決定
	public void decideLevel() {
		int maxJobLevel = this.player.getLevel();
		for(String key:this.player.getJobs().keySet()) {
			int jobLevel = this.player.getJobs().get(key);
			if(maxJobLevel < jobLevel) {
				this.player.setLevel(jobLevel);
			}
		}
	}
	//HPとMPの決定
	public void decideHpAndMp() {
		this.player.setHp(this.player.getLevel()*3 + this.player.getVit());
		this.player.setResVit(this.player.getLevel() + this.player.getVitBonus());
		this.player.setMp(this.player.getLevel()*3 + this.player.getPow());//修正予定、魔法使い系技能レベル未実装のため冒険者レベルで代用中
		this.player.setResPow(this.player.getLevel() + this.player.getPowBonus());
	}
	//戦闘特技の習得
	public void decideSkill() {
		System.out.println("aaaa");
		player.getSkills().add(new PassiveSkill());
		player.getSkills().add(new PassiveSkill());
		//戦闘特技一覧
		System.out.println("戦闘特技一覧");
		for(Skill a : player.getSkills()) {
			System.out.println(a.getName());
		}
//		System.out.println(new PassiveSkill().getName());
		
	}
	//装備品の購入　修正予定
	public void buyEquipment() {
		int money = this.player.getMoney();
		System.out.println();
		System.out.printf("所持金 %dG%n",money);
		//購入するかしないか
		System.out.print("装備品を購入しますか？(購入する:0,購入しない:1)>>");
		int buy = scan.nextInt();
		//武器の種類名を取得
		String[] wepons = new String[Wepon.getNames().length];
		for(int i=0;i<wepons.length;i++) {
			wepons[i] = Wepon.getNames()[i];
		}
		//購入処理開始
		while(buy != 1) {
			int select = 0;;//選択肢用の変数
			System.out.printf("%s 選択肢%n",format("武器名",8));
			for(String weponName:wepons) {
				System.out.printf("%s  %d%n",format(weponName,8),select++);
			}
			System.out.print("どの種類の武器を見ますか?>>");
			select = scan.nextInt();
			System.out.println();
			//お店の在庫武器用意
			List<Wepon> shop = new ArrayList<>();
			Wepon.WeponList wepon = Wepon.WeponList.values()[select];
			//選択肢によってお店の武器種類を変更
			switch(wepon) {
			case AXE:
				for(Axe.AxeList s:Axe.AxeList.values()) {
					shop.add(new Axe(s));
				}
				break;
			case SWORD:
				for(Sword.SwordList s:Sword.SwordList.values()) {
					shop.add(new Sword(s));
				}
				break;
			default:
				break;

			}
			do {
				select = 0;
				//武器一覧表示
				System.out.printf("%s:%s 選択肢%n",format("武器名",18),format("価格",3));
				for(Wepon s:shop) {
					System.out.printf("%s:%s%d%n",format(s.getName(),18),format(""+s.getPrice(),8),select++);
				}
				//欲しい武器を選ぶ
				System.out.print("どれを購入しますか?>>");
				select = scan.nextInt();
				System.out.println();
				//武器の詳細表示
				System.out.println(shop.get(select));
				System.out.print("購入する:0,しない:1>>");
				buy = scan.nextInt();
			}while(buy == 1);

			money = money - shop.get(select).getPrice();
			//購入後にプレイヤーに武器を渡す
			this.player.w = shop.get(select);//武器をキャラに渡す
			shop.set(select,new Sword(Sword.SwordList.values()[select]));//お店の同じ武器を新しいものに変える
			//購入の継続確認
			System.out.printf("所持金 %dG%n",money);
			System.out.print("他の装備も購入しますか？>>");
			buy = scan.nextInt();
			System.out.println();
			this.player.setMoney(money);//残金を設定
			//手持ちの武器と店の武器が別物かを確認
			//			System.out.printf("%s "+shop.get(select),shop.get(select).getName());
			//			System.out.println();
			//			System.out.printf("%s "+this.player.w,this.player.w.getName());
			//			System.out.println();
		}
	}

	//キャラ完成
	public void completeCharacter() {
		//ステータス一覧
		this.player.setStatus();
		System.out.println("キャラが完成しました！");
		System.out.println("------------------------");
		//キャラのステータス表示
		for(int i=0;i<this.player.statusName.length;i++){
			System.out.printf("%s",format(this.player.statusName[i] + ":" + this.player.status[i],15));
			if(i%2==0) {
				System.out.println();
			}
		}
		System.out.println();
		System.out.printf("種族:%s 生まれ:%s%n",this.player.getRace(),this.player.getBirth()); 
		System.out.printf("所持金 %dG%n", this.player.getMoney());
		System.out.println("------------------------");
		//技能一覧
		System.out.println("取得技能一覧");
		System.out.printf("%s|%s%n",format("技能",18),"レベル");
		for(String key:this.player.getJobs().keySet()) {
			int value = this.player.getJobs().get(key);
			if(value != 0) {
				System.out.printf("%s|%d%n",format(key,18),value);
			}
		}
		//装備品
		System.out.println("------------------------");
		System.out.println("現在の装備");
		System.out.println("武器:"+this.player.w.getName());
		System.out.println("防具:"+this.player.p.getName());
		System.out.println("------------------------");
	}
	//playerのアクセサ
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
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
