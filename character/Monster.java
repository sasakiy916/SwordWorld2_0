package character;
import java.util.*;
public abstract class Monster extends Character{
	private int hit;//命中力
	private int atk;//打撃力
	
	public int damageRoll(){
		return this.d.roll(2)+this.atk;
	}
	//打撃力のアクセサ
	public int getAtk(){
		return this.atk;
	}
	public void setAtk(int atk){
		this.atk = atk;
	}
}
