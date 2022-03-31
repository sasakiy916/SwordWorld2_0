public class Main{
	public static void main(String[] args){
		Player h = new Human();
		Monster m = new Kobold();
		System.out.println();
		BattleManager bm = new BattleManager(h,m);//未完成
	}
}
