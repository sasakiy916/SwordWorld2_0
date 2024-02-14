
public class Goblin extends Monster {
	public Goblin(){
		setName("ゴブリン");
		setHp(16);
		setMp(12);
		setAddDamage(2);
		setDef(2);
		setPre(11);
		setHit(3);
		setFixedHit(10);
		setAvoi(3);
		setFixedAvoi(10);
		setMoney(100);
		setExp(150);
	}

	@Override
	public int damageRoll() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}
}
