import java.util.*;
public class Monster implements Character{
	private String name;//名前
	private int hp;//HP
	private int mp;//MP
	private int def;//防護点
	private int hit;//命中力
	private int atk;//打撃力
	public Dice d;//ダイス（未カプセル化）
	
	public Monster(){
		d = new Dice();
	}

	//名前のアクセサ
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	//HPのアクセサ
	public int getHp(){
		return this.hp;
	}
	public void setHp(int hp){
		this.hp = hp;
	}

	//MPのアクセサ
	public int getMp(){
		return this.mp;
	}
	public void setMp(int mp){
		this.mp = mp;
	}

	//打撃力のアクセサ
	public int getAtk(){
		return this.atk;
	}
	public void setAtk(int atk){
		this.atk = atk;
	}
}
