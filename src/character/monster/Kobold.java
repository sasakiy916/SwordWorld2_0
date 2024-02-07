package main;

public class Kobold extends Monster{
	public Kobold(){
		setName("コボルド");
		setHp(13);
		setMp(10);
		setAddDamage(1);
		setDef(1);
		setPre(10);
		setHit(2);
		setFixedHit(9);
		setAvoi(1);
		setFixedAvoi(8);
		setMoney(30);
		setExp(100);
	}

	@Override
	public int damageRoll() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}
}
