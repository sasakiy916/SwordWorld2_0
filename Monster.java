public abstract class Monster extends Character{
	private int atk;//打撃力
	private int fixedHit;//固定命中
	private int fixedAvoi;//固定回避
	
	public int damageRoll(){
		return Dice.roll(2)+this.atk;
	}@Override
	public int judgeHit() {
		return getFixedHit();
	}

	@Override
	public int judgeAvoi() {
		return getFixedAvoi();
	}
	//打撃力のアクセサ
	public int getAtk(){
		return this.atk;
	}
	public void setAtk(int atk){
		this.atk = atk;
	}
	public int getFixedHit() {
		return fixedHit;
	}
	public void setFixedHit(int fixedHit) {
		this.fixedHit = fixedHit;
	}
	public int getFixedAvoi() {
		return fixedAvoi;
	}
	public void setFixedAvoi(int fixedAvoi) {
		this.fixedAvoi = fixedAvoi;
	}
}
