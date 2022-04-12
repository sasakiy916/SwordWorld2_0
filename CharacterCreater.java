import java.nio.charset.Charset;
import java.util.Scanner;
public class CharacterCreater {
	private Player player;
	Dice d = new Dice();
	Scanner scan = new Scanner(System.in);
//	Wepon wepon = new Wepon();
	//コンストラクタ
	public CharacterCreater() {
		setPlayer(decideRaceAndBirth());
		decideStatus();
//		buyEquipment();
	}

	//種族と生まれの決定
	public Player decideRaceAndBirth() {
		//種族一覧表示
		for(Race race:Race.values()) {
			System.out.printf("%s:%d%n",race.getName(),race.ordinal());
		}
		//種族を選択
		System.out.print("種族を選んでください>>");
		int selectRace = scan.nextInt();
		Player p = Race.values()[selectRace].getPlayer();
		return p;
	}
	//能力値の決定
	public void decideStatus() {
		this.player.setName("アルクレイド");
		//基礎能力値設定
		this.player.setBaseAbilities(8, 4, 9);;
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
		this.player.setStatus();
		//能力値表示
		System.out.println("能力値が決定しました！");
		System.out.println("------------------------");
		for(int i=0;i<this.player.statusName.length;i++){
			System.out.printf("%s:%d%n",this.player.statusName[i],this.player.status[i]);
		}
		System.out.println("------------------------");
	}
	//技能の習得
	
	//言語の習得
	
	//冒険者レベルの決定
	
	//HPとMPの決定
	
	//戦闘特技の習得
	
	//装備品の購入
	public void buyEquipment() {
		int money = this.player.getMoney();
		//購入するかしないか
		System.out.print("装備品を購入しますか？(購入する:0,購入しない:1)>>");
		int buy = scan.nextInt();
		while(buy == 0) {
			//武器一覧表示
			int select = 0;
			for(WeponList wepon:WeponList.values()) {
				System.out.printf("%s %3dG :%d%n",format(wepon.getName(),15),wepon.getPrice(),select);
				select++;
			}
			//購入する武器を選ぶ
			System.out.print("どれを購入しますか?>>");
			select = scan.nextInt();
			//購入後にプレイヤーに武器を渡す

			//購入の継続確認
			System.out.print("他の装備も購入しますか？>>");
			buy = scan.nextInt();
		}
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
