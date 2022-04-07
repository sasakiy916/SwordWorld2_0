public class Main{
	public static void main(String[] args){
		int a = 1;
		MainMenu menu = new MainMenu("ソードーワールド再現");
		Character h = new Human();
		Character m = new Kobold();
		System.out.println();
		BattleManager bm = new BattleManager(h,m);//未完成
	}
}
