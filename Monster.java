public abstract class Monster extends Character{
	private int avoi;//回避力
	private int pre;//先制力
	private int hit;//命中力
	private int addDamage;//追加ダメージ
	private int fixedHit;//固定命中
	private int fixedAvoi;//固定回避
	
	//先制判定
	@Override
	public int judgePre() {
		return getPre();
	}
	//命中判定(固定値)
	@Override
	public int judgeHit() {
		return getFixedHit();
	}

	//回避判定(固定値)
	@Override
	public int judgeAvoi() {
		return getFixedAvoi();
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
	public int getAvoi() {
		return avoi;
	}
	public void setAvoi(int avoi) {
		this.avoi = avoi;
	}
	public int getPre() {
		return pre;
	}
	public void setPre(int pre) {
		this.pre = pre;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getAddDamage() {
		return addDamage;
	}
	public void setAddDamage(int addDamage) {
		this.addDamage = addDamage;
	}
}
