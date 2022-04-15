import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shop {
	private enum ShopMenu{
		EQUIPMENT,
		EQUIPTYPE,
		BUYEQUIP,
	}
	static Scanner scan = new Scanner(System.in);
	//購入
	public static void buy(Player player) {
		//所持金確認
		int money = player.getMoney();
		System.out.printf("所持金 %dG%n",money);
		//購入するかしないか
		System.out.print("装備品を購入しますか？(購入する:0,購入しない:1)>>");
		int buy = scan.nextInt();
		//装備の種類名取得
		String[] equips = new String[Equipment.getNames().length];
		for(int i=0;i<equips.length;i++) {
			equips[i] = Equipment.getNames()[i];
		}
		//武器の種類名を取得
		String[] wepons = new String[Wepon.getNames().length];
		for(int i=0;i<wepons.length;i++) {
			wepons[i] = Wepon.getNames()[i];
		}
		//防具の種類名取得
		String[] protectors = new String[Protector.getNames().length];
		for(int i=0;i<protectors.length;i++) {
			protectors[i] = Protector.getNames()[i];
		}
		//買い物開始
		while(buy != 1) {
			int select = 0;;//選択肢用の変数
			List<Equipment> shop = new ArrayList<>();//商品リスト

//			do {
//				ShopMenu menu = ShopMenu.values()[select];
//				switch(menu) {
//				
//				}
//			}while(select == 9);
			//武器,防具の選択
			select = selectWeponOrProtector(equips,select);
			//武器もしくは防具の種類を選ぶ
			prepareEquipment(shop,wepons,protectors,select);
			//装備を選んで購入
			do {
				select = 0;
				//武器一覧表示
				buy = selectAndBuyEquip(shop,select,buy,player);
			}while(buy == 1);

			//購入した装備を受け取る
			money = receiveEquip(shop,select,money,player);

			//買い物の継続確認
			System.out.printf("所持金 %dG%n",money);
			System.out.print("他の装備を購入する:0,しない:1>>");
			buy = scan.nextInt();
			System.out.println();
			player.setMoney(money);//残金を設定
		}
	}
	//武器か防具か選ぶ
	private static int selectWeponOrProtector(String[] equips,int select) {
		System.out.printf("%s 選択肢%n",format("装備の種類",8));
		for(String equipName:equips) {
			System.out.printf("%s  %d%n",format(equipName,8),select++);
		}
		System.out.print("武器と防具どちらを見ますか?>>");
		select = scan.nextInt();
		System.out.println();
		return select;
	}
	//装備の種類を選ぶ
	private static void prepareEquipment(List<Equipment> shop,String[] wepons,String[] protectors,int select) {
		if(select == 0) {
			select = 0;
			System.out.printf("%s 選択肢%n",format("武器名",8));
			for(String weponName:wepons) {
				System.out.printf("%s  %d%n",format(weponName,8),select++);
			}
			System.out.print("どの武器を見ますか?>>");
			select = scan.nextInt();
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
			//防具の種類
		}else {
			select = 0;
			System.out.printf("%s 選択肢%n",format("防具名",8));
			for(String protectorName:protectors) {
				System.out.printf("%s  %d%n",format(protectorName,8),select++);
			}
			System.out.print("どの防具を見ますか?>>");
			select = scan.nextInt();
			//防具の在庫用意
			Protector.ProrectorList protector = Protector.ProrectorList.values()[select];
			switch(protector) {
			case METALAROMR:
				for(MetalArmor.MetalArmorList metalArmor:MetalArmor.MetalArmorList.values()) {
					shop.add(new MetalArmor(metalArmor));
				}
				break;
			case NONMETALARMOR:
				for(NonMetalArmor.NonMetalArmorList nonMetalArmor:NonMetalArmor.NonMetalArmorList.values()) {
					shop.add(new NonMetalArmor(nonMetalArmor));
				}
				break;
			default:
				break;

			}
		}
		System.out.println();

	}
	//購入する装備を選ぶ
	private static int selectAndBuyEquip(List<Equipment> shop,int select,int buy, Player player) {
		System.out.printf("%s:%s 選択肢%n",format("武器名",18),format("価格",3));
		for(Equipment s:shop) {
			System.out.printf("%s:%s%d%n",format(s.getName(),18),format(""+s.getPrice(),8),select++);
		}
		//欲しい装備を選ぶ
		System.out.print("どれを購入しますか?>>");
		select = scan.nextInt();
		System.out.println();
		//装備の詳細表示
		System.out.println("キャラの筋力："+player.getStr());//デバッグ用
		System.out.println("装備詳細");
		if(shop.get(select) instanceof Wepon) {
			System.out.println((Wepon)shop.get(select));
		}
		if(shop.get(select) instanceof Protector) {
			System.out.println((Protector)shop.get(select));
		}
		System.out.print("購入する:0,しない:1>>");
		return scan.nextInt();

	}
	//購入した装備を受け取る
	private static int receiveEquip(List<Equipment> shop,int select,int money,Player player) {
		//筋力が足りるか
		if(shop.get(select).getNeedStr() <= player.getStr()) {
			//所持金減らす
			money = money - shop.get(select).getPrice();
			//購入後にプレイヤーに武器を渡す
			if(shop.get(select) instanceof Wepon) {
				player.w = (Wepon)shop.get(select);//武器をキャラに渡す
			}
			if(shop.get(select) instanceof Protector) {
				player.p = (Protector)shop.get(select);//防具をキャラに渡す
			}
		}else {
			System.out.printf("筋力が足りないため装備できません。%n他の装備の購入をおすすめします。%n");
		}
		System.out.println();
		return money;
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
