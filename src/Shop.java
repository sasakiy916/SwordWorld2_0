
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shop {
	enum EquipList{
		WEAPON,
		ARMOR,
		SHIELD,
	}
	enum ShopMenu{
		EQUIPMENT,
		EQUIPTYPE,
		BUYEQUIP,
	}
	static Scanner scan = new Scanner(System.in);
	//購入
	public static void buy(Player player) {
		//現在の装備
		Option.printLine(30);
		System.out.println("現在の装備");
		System.out.printf("武器:%s%n",player.getWeapon().getName());
		System.out.printf("鎧:%s%n",player.getArmor().getName());
		System.out.printf("盾:%s%n",player.getShield().getName());
		Option.printLine(30);
		//所持金確認
		int money = player.getMoney();
		System.out.printf("所持金 %dG%n",money);
		//購入するかしないか
		System.out.print("装備品を購入しますか？(購入する:0,購入しない:1)>>");
		int buy = scan.nextInt();
		System.out.println();
		//装備の種類名
		String[] equips = {
				"武器",
				"鎧",
				"盾",
		};
		//武器の種類名を取得
		List<String[]> equipList = Option.load("Equipment/各装備一覧.csv");
		String[] weapons = new String[equipList.size()-1];
		for(int i=0;i<equipList.size()-1;i++) {
			weapons[i] = equipList.get(i+1)[0];
		}
		//鎧の種類名取得
		String[] armor = new String[equipList.size()-1];
		for(int i=0;i<equipList.size()-1;i++) {
			armor[i] = equipList.get(i+1)[1];
		}
		//盾の種類名取得
		String[] shield = new String[equipList.size()-1];
		for(int i=0;i<equipList.size()-1;i++) {
			shield[i] = equipList.get(i+1)[2];
		}
		//買い物開始
		while(buy != 1) {
			int select = 0;//選択肢用の変数
			int selectEquipType = 0;//装備の種類番号
			List<Equipment> shop = new ArrayList<>();//商品リスト
			ShopMenu menu = ShopMenu.values()[0];//メニュー選択
			do {
				//ショップメニュー表示
				try {
					switch(menu) {
					//武器か防具か
					case EQUIPMENT:
						select = selectWeponOrProtector(equips,select);
						selectEquipType = select;
						System.out.println();
						String errorCheck = equips[select];//不正値チェック用
					//武器もしくは防具の種類
					case EQUIPTYPE:
						menu = ShopMenu.EQUIPTYPE;
						select = prepareEquipment(shop,weapons,armor,select);
						System.out.println();
						//不正値チェック(選択肢外かどうか)
						if(selectEquipType == 0) {
							errorCheck = weapons[select];
						}else if(selectEquipType == 1) {
							errorCheck = armor[select];
						}else {
							errorCheck = shield[select];
						}
						errorCheck = selectEquipType==0?weapons[select]:armor[select];//不正値チェック用/原因究明
						if(select == -1)break;//戻る
					//どれを買うか
					case BUYEQUIP:
						menu = ShopMenu.BUYEQUIP;
						do {
							//武器一覧表示
							select = selectAndBuyEquip(shop,select);
							if(select == -1)break;//戻る
							shop.get(select);//不正値チェック用
							//装備の詳細表示
							//デバッグ用
							System.out.println("------------------");
							System.out.println("名前:" + player.getName());
							System.out.println("現在の装備");
							System.out.println("・武器:" + player.getWeapon().getName());
							System.out.println("・鎧:" + player.getArmor().getName());
							System.out.println("・盾:" + player.getShield().getName());
							System.out.println("・キャラの筋力："+ player.getStr());//デバッグ用
							System.out.println("------------------");
							System.out.println();
							System.out.println("装備詳細");
							if(shop.get(select) instanceof Weapon) {
								//武器の場合
								System.out.println((Weapon)shop.get(select));
							}
							if(shop.get(select) instanceof Protector) {
								//防具の場合
								System.out.println((Protector)shop.get(select));
							}
							System.out.print("購入する:0,しない:1>>");
							buy = scan.nextInt();
						}while(buy == 1);
					}
				}catch(Exception e) {
					System.out.println("当店には取り扱いの無い商品です。");
					System.out.println();
					break;
				}
				//一つ前の選択肢に戻る
				if(select == -1) {
					shop.clear();
					switch(menu) {
					case EQUIPTYPE:
						menu = ShopMenu.EQUIPMENT;
						break;
					case BUYEQUIP:
						menu = ShopMenu.EQUIPTYPE;
						select = selectEquipType;
						break;
					case EQUIPMENT:
						break;
					}
					continue;
				}
				if(buy == 0)break;//購入するを選んだらbreak;
			}while(true);
			//購入した装備を受け取る
			try {
				money = receiveEquip(shop,select,money,player);
			}catch(Exception e) {
			}

			//買い物の継続確認
			System.out.println(player);
			System.out.println("------------------");
			System.out.printf("所持金 %dG%n",money);
			System.out.println("------------------");
			System.out.print("他の装備を購入する:0,しない:1>>");
			buy = scan.nextInt();
			System.out.println();
			player.setMoney(money);//残金を設定
		}							
	}
	//武器か防具か選ぶ
	private static int selectWeponOrProtector(String[] equips,int select) {
		select = 0;
		System.out.println("-----------------------------");
		System.out.printf("%s %s%n",Option.format("装備の種類",10),"選択肢");
		for(String equipName:equips) {
			System.out.printf("%s    %d%n",Option.format(equipName,10),++select);
		}
		System.out.println("-----------------------------");
		System.out.print("どの装備を見ますか?>>");
		select = scan.nextInt() - 1;
		return select;
	}
	//装備の種類を選ぶ
	private static int prepareEquipment(List<Equipment> shop,String[] wepons,String[] armors,int select) {
		try {
			EquipList equipList = EquipList.values()[select];
			ArrayList<String[]> equips = new ArrayList<String[]>();//装備の名前リスト
			String path = "";
			select = 0;
			switch(equipList) {
			//武器
			case WEAPON:
				System.out.println("-----------------------------");
				System.out.printf("%s 選択肢%n",Option.format("近接武器",8));
				for(String weponName:wepons) {
					System.out.printf("%s    %d%n",Option.format(weponName,8),++select);
				}
				System.out.println("-----------------------------");
				System.out.print("どの武器を見ますか?(一つ戻る:0)>>");
				select = scan.nextInt() - 1;//選択肢入力
				//選択肢によってお店の武器種類を変更
				Weapon.WeponList weapon = Weapon.WeponList.values()[select];
				path = "weapon/";//ファイルへのパス
				//種類ごとのパス設定
				switch(weapon) {
				case AXE:
					path += "アックスB.csv";
					break;
				case SWORD:
					path += "ソードB.csv";
					break;
				case FRAIL:
					path += "フレイルB.csv";
					break;
				case SPEAR:
					path += "スピアB.csv";
					break;
				case MACE:
					path += "メイスB.csv";
					break;
				case WARHAMMER:
					path += "ウォーハンマーB.csv";
					break;
				case STUFF:
					path += "スタッフB.csv";
					break;
				default:
					break;
				}
				break;
				//防具
			case ARMOR:
				System.out.println("-----------------------------");
				System.out.printf("%s 選択肢%n",Option.format("防具名",8));
				for(String armorName:armors) {
					if(!armorName.equals("")) {
						System.out.printf("%s  %d%n",Option.format(armorName,8),++select);
					}
				}
				System.out.println("-----------------------------");
				System.out.print("どの防具を見ますか?(一つ戻る:0)>>");
				select = scan.nextInt() - 1;
				//防具の在庫用意
				Armor.ArmorList protector = Armor.ArmorList.values()[select];
				path = "armor/";//ファイルのパス
				switch(protector) {
				case METALAROMR:
					path += "金属鎧B.csv";
					break;
				case NONMETALARMOR:
					path += "非金属鎧B.csv";
					break;
				default:
					break;
				}
				break;
			case SHIELD:
				path = "shield/盾B.csv";
				break;
			}
			//装備データの読み込み,Listに追加
			equips = Option.load(path);
			for(int i=1;i<equips.size();i++) {//一行目の見出しは無視
				if(equips.get(i)[0] != "") {//名称の無い行は無視(用法が二つある装備は片方だけ)
					if(path.matches("weapon/.*")) {
						shop.add(new Weapon(equips.get(i)[0],path));
					}else if(path.matches("armor/.*")){
						shop.add(new Armor(equips.get(i)[0],path));
					}else if(path.matches("shield/.*")){
						shop.add(new Shield(equips.get(i)[0],path));
					}
				}
			}
		}catch(Exception e) {
		}
		return select;
	}
	//購入する装備を選ぶ
	private static int selectAndBuyEquip(List<Equipment> shop,int select) {
		select = 0;
		System.out.println("-----------------------------");
		System.out.printf("%s:%s 選択肢%n",Option.format(shop.get(0) instanceof Weapon?"武器名":"防具名",18),Option.format("価格",3));
		for(Equipment s:shop) {
			System.out.printf("%s:%s%d%n",Option.format(s.getName(),18),Option.format(""+s.getPrice()+"G",8),++select);
		}
		System.out.println("-----------------------------");
		//欲しい装備を選ぶ
		System.out.print("どれを購入しますか?(一つ戻る:0)>>");
		select = scan.nextInt();
		System.out.println();
		return select-1;

	}
	//購入した装備を受け取る
	private static int receiveEquip(List<Equipment> shop,int select,int money,Player player) {
		//筋力が足りるか
		if(shop.get(select).getPrice() > player.getMoney()) {
			System.out.println("所持金が足りません");
			return money;
		}	
		if(shop.get(select).getNeedStr() <= player.getStr()) {
			//所持金減らす
			money = money - shop.get(select).getPrice();
			//購入後にプレイヤーに武器を渡す
			if(shop.get(select) instanceof Weapon) {
				player.setWeapon((Weapon)shop.get(select));//武器をキャラに渡す
			}
			if(shop.get(select) instanceof Armor) {
				player.setArmor((Armor)shop.get(select));//防具をキャラに渡す
			}
			if(shop.get(select) instanceof Shield) {
				player.setShield((Shield)shop.get(select));//盾をキャラに渡す
			}
			System.out.printf("%sを購入しました。%n",shop.get(select).getName());
		}else {
			System.out.printf("筋力が足りないため装備できません。%n他の装備の購入をおすすめします。%n");
		}
		System.out.println();
		return money;
	}
}
