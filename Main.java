public class Main{
	public static void main(String[] args){
//		Player p = new Human();
		CharacterCreater cc = new CharacterCreater();
		Player p = cc.getPlayer();
		Character h = (Character)p;
		Character m = new Kobold();
		System.out.println();
		BattleManager bm = new BattleManager(h,m);//未完成
	}
}
