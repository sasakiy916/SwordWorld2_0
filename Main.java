public class Main{
	public static void main(String[] args){
		Character h = new Human();
		Character m = new Kobold();
		System.out.println();
		BattleManager bm = new BattleManager(h,m);//未完成
	}
}
