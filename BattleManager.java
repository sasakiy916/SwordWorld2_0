import sw.character.*;
import java.util.*;
public class BattleManager{
	public BattleManager(Character p, Character m){
		//陣営の確認

		//魔物知識判定

		//先制判定

		//戦力の初期配置

		System.out.println("------------------");
		System.out.printf("%sのHP:%d%n%n",m.getName(),m.getHp());
		System.out.printf("%sのHP:%d%n",p.getName(),p.getHp());
		System.out.println("------------------");
		System.out.print("戦闘を開始します。(Enterで戦闘を進めていきます)");
		new Scanner(System.in).nextLine();
		//戦闘開始
		while(true){
			//先攻側の行動
			System.out.println("先攻側の行動");
			System.out.printf("%sのHP:%d%n",m.getName(),m.getHp());
			System.out.printf("%sの攻撃%n",p.getName());
			int damage = p.damageRoll();
			m.setHp(m.getHp() - damage);
			if(damage!=0){
				System.out.printf("%sに%dのダメージを与えた%n",m.getName(),damage);
			}
			//System.out.printf("%sのHP:%d%n%n",m.getName(),m.getHp());
			//生存判定
			isAliveCharacter(m);
			//現在の各状態
			System.out.println("------------------");
			System.out.printf("%sのHP:%d%n%n",m.getName(),m.getHp());
			System.out.printf("%sのHP:%d%n",p.getName(),p.getHp());
			System.out.println("------------------");
			if(!isAliveCharacter(m)){
				System.out.printf("%sを倒した%n",m.getName());
				break;
			}
			System.out.print("先攻側の行動終了,後攻側の行動に移ります。");
			new Scanner(System.in).nextLine();
			System.out.println("**********************************************");
			System.out.println("**********************************************");

			//後攻側の行動
			System.out.println("後攻側の攻撃");
			System.out.printf("%sのHP:%d%n",p.getName(),p.getHp());
			System.out.printf("%sの攻撃%n",m.getName());
			int secondDamage = m.damageRoll();
			p.setHp(p.getHp() - secondDamage);
			System.out.printf("%sに%dのダメージを与えた%n",p.getName(),secondDamage);
			//System.out.printf("%sのHP:%d%n%n",p.getName(),p.getHp());
			
			//生存判定
			isAliveCharacter(p);
			//現在の各状態
			System.out.println("------------------");
			System.out.printf("%sのHP:%d%n%n",m.getName(),m.getHp());
			System.out.printf("%sのHP:%d%n",p.getName(),p.getHp());
			System.out.println("------------------");
			if(!isAliveCharacter(p)){
				System.out.printf("%sはやられてしまった%n",p.getName());
				break;
			}
			System.out.print("後攻側の行動終了,");

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

	//生存判定
	private boolean isAliveCharacter(Character c){
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
}
