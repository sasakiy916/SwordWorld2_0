import java.util.*;
public class BattleManager{
	public BattleManager(Player p, Monster m){
		//陣営の確認

		//魔物知識判定

		//先制判定

		//戦力の初期配置

		System.out.println("------------------");
		System.out.printf("%sのHP:%d%n%n",m.getName(),m.getHp());
		System.out.printf("%sのHP:%d%n",p.getName(),p.getHp());
		System.out.println("------------------");
		System.out.print("戦闘を開始します。Enterで進みます。");
		new Scanner(System.in).nextLine();
		//戦闘開始
		while(true){
			//先攻側の行動
			System.out.println("先攻側の行動");
			System.out.printf("%sのHP:%d%n",m.getName(),m.getHp());
			System.out.printf("%sの攻撃%n",p.getName());
			m.setHp(m.getHp() - p.getStrBonus());
			System.out.printf("%sに%dのダメージを与えた%n",m.getName(),p.getStrBonus());
			//System.out.printf("%sのHP:%d%n%n",m.getName(),m.getHp());
			if(!isAliveMonster(m))break;

			//後攻側の行動
			System.out.println("後攻側の攻撃");
			System.out.printf("%sのHP:%d%n",p.getName(),p.getHp());
			System.out.printf("%sの攻撃%n",m.getName());
			int mAtk =  m.getAtk() + m.d.roll(2);
			p.setHp(p.getHp() - mAtk);
			System.out.printf("%sに%dのダメージを与えた%n",p.getName(),mAtk);
			//System.out.printf("%sのHP:%d%n%n",p.getName(),p.getHp());
			if(!isAlivePlayer(p))break;
			
			//現在の各状態
			System.out.println("------------------");
			System.out.printf("%sのHP:%d%n%n",m.getName(),m.getHp());
			System.out.printf("%sのHP:%d%n",p.getName(),p.getHp());
			System.out.println("------------------");

			//戦闘の継続、終了確認
			System.out.println("戦闘を継続しますか?(終了する場合は「q + Enter」)");
			String sc = new Scanner(System.in).nextLine();
			if(sc.equals("q")){
				System.out.println("戦闘終了");
				break;
			}
		}
	}

	private boolean isAliveMonster(Monster m){
		if(m.getHp() < 0){
			m.setHp(0);
			System.out.printf("%sのHP:%d%n",m.getName(),m.getHp());
			System.out.printf("%sを倒した",m.getName());
			return false;
		}
		System.out.printf("%sのHP:%d%n%n",m.getName(),m.getHp());
		return true;
	}
	private boolean isAlivePlayer(Player p){
		if(p.getHp() < 0){
			p.setHp(0);
			System.out.printf("%sのHP:%d%n",p.getName(),p.getHp());
			System.out.printf("%sはやられてしまった",p.getName());
			return false;
		}
		System.out.printf("%sのHP:%d%n%n",p.getName(),p.getHp());
		return true;
	}
}
