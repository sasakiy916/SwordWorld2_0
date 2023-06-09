package main;

public class Shield extends Protector {
	//盾リスト
	enum List{
		SHIELD,
	}
	public Shield() {
		
	}
	public Shield(String name,String path) {
		super(name, path);
	}
}
