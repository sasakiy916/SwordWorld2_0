public abstract class Monster extends Character{
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
}
